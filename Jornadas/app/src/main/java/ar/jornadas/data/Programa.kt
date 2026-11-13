package ar.jornadas.data

import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import retrofit2.http.GET
import kotlinx.coroutines.flow.Flow

data class FichaRemota(val id: String, val title: String, val track: String, val day: Int,
    val time: String, val room: String, val speaker: String, val summary: String)

interface ProgramaApi {
    @GET("programa") suspend fun consultar(): List<FichaRemota>
}

@Entity(tableName = "charlas")
data class Charla(@PrimaryKey val id: String, val titulo: String, val tema: String, val dia: Int,
    val hora: String, val sala: String, val ponente: String, val resumen: String,
    val favorita: Boolean = false)

/** Datos que publica la organización. No incluye la marca del usuario (favorita). */
data class ContenidoCharla(val id: String, val titulo: String, val tema: String, val dia: Int,
    val hora: String, val sala: String, val ponente: String, val resumen: String) {
    fun comoCharlaNueva() = Charla(id, titulo, tema, dia, hora, sala, ponente, resumen, favorita = false)
}

/**
 * Registro de que existe al menos una edición descargada con éxito.
 * Permite distinguir "nunca se descargó" de "se descargó un programa vacío" (v4).
 */
@Entity(tableName = "edicion")
data class Edicion(@PrimaryKey val id: Int = 0, val momento: Long)

@Dao
abstract class CarteleraDao {
    @Query("SELECT * FROM charlas ORDER BY dia, hora, sala")
    abstract fun observar(): Flow<List<Charla>>

    @Query("SELECT EXISTS(SELECT 1 FROM edicion)")
    abstract fun hayEdicion(): Flow<Boolean>

    @Query("UPDATE charlas SET favorita = NOT favorita WHERE id = :id")
    abstract suspend fun alternar(id: String)

    @Query("DELETE FROM charlas WHERE id NOT IN (:vigentes)")
    abstract suspend fun retirarAusentes(vigentes: List<String>)

    @Query("DELETE FROM charlas")
    abstract suspend fun retirarTodas()

    /** Sólo inserta charlas nuevas; las existentes se ignoran para no tocar su marca. */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertarNuevas(fichas: List<Charla>)

    /** Actualización parcial: cambia el contenido publicado y deja intacta la columna favorita. */
    @Update(entity = Charla::class)
    abstract suspend fun actualizarContenido(fichas: List<ContenidoCharla>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun registrarEdicion(edicion: Edicion)

    /**
     * Aplica una instantánea completa del programa en una sola transacción:
     * retira lo que ya no está (con su marca), agrega lo nuevo sin marcar y actualiza
     * el contenido de lo vigente conservando la marca que tenga EN ESTE MOMENTO
     * (incluida una marca cambiada mientras la petición estaba en curso).
     */
    @Transaction
    open suspend fun aplicarInstantanea(fichas: List<ContenidoCharla>, momento: Long) {
        if (fichas.isEmpty()) retirarTodas() else retirarAusentes(fichas.map { it.id })
        insertarNuevas(fichas.map { it.comoCharlaNueva() })
        actualizarContenido(fichas)
        registrarEdicion(Edicion(momento = momento))
    }
}

@Database(entities = [Charla::class, Edicion::class], version = 2, exportSchema = false)
abstract class Archivo : RoomDatabase() {
    abstract fun cartelera(): CarteleraDao
}

/**
 * v1 -> v2: agrega la tabla edicion sin borrar las charlas ni las marcas de los usuarios.
 * Si la instalación anterior ya tenía charlas, eso cuenta como una edición descargada.
 */
val MIGRACION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS edicion (id INTEGER NOT NULL, momento INTEGER NOT NULL, PRIMARY KEY(id))")
        db.execSQL("INSERT OR IGNORE INTO edicion (id, momento) SELECT 0, 0 WHERE EXISTS (SELECT 1 FROM charlas)")
    }
}
