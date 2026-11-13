package ar.jornadas.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u0000 /2\u00020\u0001:\u0001/B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\'\u001a\u00020(J\u000e\u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020\u0016J\u000e\u0010+\u001a\u00020(2\u0006\u0010*\u001a\u00020%J\u000e\u0010,\u001a\u00020(2\u0006\u0010-\u001a\u00020%J\u0006\u0010.\u001a\u00020(R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001f\u0010\u000f\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00100\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000eR\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\t0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u000eR\u0011\u0010\u001c\u001a\u00020\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020!X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u000e\u00a8\u00060"}, d2 = {"Lar/jornadas/ui/ProgramaModel;", "Landroidx/lifecycle/AndroidViewModel;", "app", "Landroid/app/Application;", "guardado", "Landroidx/lifecycle/SavedStateHandle;", "(Landroid/app/Application;Landroidx/lifecycle/SavedStateHandle;)V", "_cargando", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_fallo", "cargando", "Lkotlinx/coroutines/flow/StateFlow;", "getCargando", "()Lkotlinx/coroutines/flow/StateFlow;", "charlas", "", "Lar/jornadas/data/Charla;", "getCharlas", "descarga", "Lkotlinx/coroutines/Job;", "dia", "", "getDia", "fallo", "getFallo", "hayEdicion", "getHayEdicion", "mesa", "Lar/jornadas/lab/ServicioLocal;", "getMesa", "()Lar/jornadas/lab/ServicioLocal;", "repo", "Lar/jornadas/data/AgendaRepository;", "servicios", "Lar/jornadas/Servicios;", "tema", "", "getTema", "cargaAutomatica", "", "elegirDia", "valor", "elegirTema", "marcar", "id", "recargar", "Companion", "app_debug"})
public final class ProgramaModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.SavedStateHandle guardado = null;
    @org.jetbrains.annotations.NotNull()
    private final ar.jornadas.Servicios servicios = null;
    @org.jetbrains.annotations.NotNull()
    private final ar.jornadas.lab.ServicioLocal mesa = null;
    @org.jetbrains.annotations.NotNull()
    private final ar.jornadas.data.AgendaRepository repo = null;
    
    /**
     * null = todavía se está leyendo la base local.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<ar.jornadas.data.Charla>> charlas = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> hayEdicion = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> dia = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> tema = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _cargando = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> cargando = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _fallo = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> fallo = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job descarga;
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    public static final java.lang.String CLAVE_DIA = "filtro_dia";
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    public static final java.lang.String CLAVE_TEMA = "filtro_tema";
    @org.jetbrains.annotations.NotNull()
    private static final ar.jornadas.ui.ProgramaModel.Companion Companion = null;
    
    public ProgramaModel(@org.jetbrains.annotations.NotNull()
    android.app.Application app, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle guardado) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final ar.jornadas.lab.ServicioLocal getMesa() {
        return null;
    }
    
    /**
     * null = todavía se está leyendo la base local.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<ar.jornadas.data.Charla>> getCharlas() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getHayEdicion() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getDia() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getTema() {
        return null;
    }
    
    public final void elegirDia(int valor) {
    }
    
    public final void elegirTema(@org.jetbrains.annotations.NotNull()
    java.lang.String valor) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getCargando() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getFallo() {
        return null;
    }
    
    /**
     * Una sola carga automática por proceso; rotar o volver del fondo no descarga de nuevo.
     */
    public final void cargaAutomatica() {
    }
    
    /**
     * Pedido explícito. Si ya hay una petición en curso, los toques extra se ignoran.
     */
    public final void recargar() {
    }
    
    public final void marcar(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lar/jornadas/ui/ProgramaModel$Companion;", "", "()V", "CLAVE_DIA", "", "CLAVE_TEMA", "app_debug"})
    static final class Companion {
        
        private Companion() {
            super();
        }
    }
}