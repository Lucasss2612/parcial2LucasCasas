package ar.jornadas.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ$\u0010\r\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0097@\u00a2\u0006\u0002\u0010\u0010J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\'J\u001c\u0010\u0014\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00150\u0006H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00060\u0012H\'J\u0016\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0019H\u00a7@\u00a2\u0006\u0002\u0010\u001aJ\u001c\u0010\u001b\u001a\u00020\u00042\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\u001d\u001a\u00020\u0004H\u00a7@\u00a2\u0006\u0002\u0010\u001e\u00a8\u0006\u001f"}, d2 = {"Lar/jornadas/data/CarteleraDao;", "", "()V", "actualizarContenido", "", "fichas", "", "Lar/jornadas/data/ContenidoCharla;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "alternar", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "aplicarInstantanea", "momento", "", "(Ljava/util/List;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hayEdicion", "Lkotlinx/coroutines/flow/Flow;", "", "insertarNuevas", "Lar/jornadas/data/Charla;", "observar", "registrarEdicion", "edicion", "Lar/jornadas/data/Edicion;", "(Lar/jornadas/data/Edicion;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "retirarAusentes", "vigentes", "retirarTodas", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract class CarteleraDao {
    
    public CarteleraDao() {
        super();
    }
    
    @androidx.room.Query(value = "SELECT * FROM charlas ORDER BY dia, hora, sala")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<ar.jornadas.data.Charla>> observar();
    
    @androidx.room.Query(value = "SELECT EXISTS(SELECT 1 FROM edicion)")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Boolean> hayEdicion();
    
    @androidx.room.Query(value = "UPDATE charlas SET favorita = NOT favorita WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object alternar(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM charlas WHERE id NOT IN (:vigentes)")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object retirarAusentes(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> vigentes, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM charlas")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object retirarTodas(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Sólo inserta charlas nuevas; las existentes se ignoran para no tocar su marca.
     */
    @androidx.room.Insert(onConflict = 5)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertarNuevas(@org.jetbrains.annotations.NotNull()
    java.util.List<ar.jornadas.data.Charla> fichas, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Actualización parcial: cambia el contenido publicado y deja intacta la columna favorita.
     */
    @androidx.room.Update(entity = ar.jornadas.data.Charla.class)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object actualizarContenido(@org.jetbrains.annotations.NotNull()
    java.util.List<ar.jornadas.data.ContenidoCharla> fichas, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object registrarEdicion(@org.jetbrains.annotations.NotNull()
    ar.jornadas.data.Edicion edicion, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Aplica una instantánea completa del programa en una sola transacción:
     * retira lo que ya no está (con su marca), agrega lo nuevo sin marcar y actualiza
     * el contenido de lo vigente conservando la marca que tenga EN ESTE MOMENTO
     * (incluida una marca cambiada mientras la petición estaba en curso).
     */
    @androidx.room.Transaction()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object aplicarInstantanea(@org.jetbrains.annotations.NotNull()
    java.util.List<ar.jornadas.data.ContenidoCharla> fichas, long momento, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}