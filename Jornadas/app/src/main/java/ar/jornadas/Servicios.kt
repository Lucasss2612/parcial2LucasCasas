package ar.jornadas

import android.content.Context
import androidx.room.Room
import ar.jornadas.data.*
import ar.jornadas.lab.ServicioLocal
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Dependencias compartidas por todo el proceso: una sola base, un solo cliente HTTP
 * y un solo servicio de prueba (su contador vuelve a cero sólo al morir el proceso).
 */
class Servicios private constructor(context: Context) {
    val mesa = ServicioLocal(context)
    private val archivo = Room.databaseBuilder(context, Archivo::class.java, "cartelera.db")
        .addMigrations(MIGRACION_1_2)
        .build()
    private val api = Retrofit.Builder().baseUrl("https://programa.invalid/")
        .client(OkHttpClient.Builder().addInterceptor(mesa).build())
        .addConverterFactory(GsonConverterFactory.create()).build().create(ProgramaApi::class.java)
    val agenda = AgendaRepository(api, archivo.cartelera())

    /** Vive lo mismo que el proceso: permite una sola carga automática por sesión. */
    @Volatile var cargaAutomaticaHecha = false

    companion object {
        @Volatile private var instancia: Servicios? = null

        fun de(context: Context): Servicios =
            instancia ?: synchronized(this) {
                instancia ?: Servicios(context.applicationContext).also { instancia = it }
            }
    }
}
