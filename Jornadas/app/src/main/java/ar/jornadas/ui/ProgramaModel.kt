package ar.jornadas.ui

import android.app.Application
import androidx.lifecycle.*
import ar.jornadas.Servicios
import ar.jornadas.data.Charla
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProgramaModel(app: Application, private val guardado: SavedStateHandle) : AndroidViewModel(app) {
    private val servicios = Servicios.de(app)
    val mesa = servicios.mesa
    private val repo = servicios.agenda

    /** null = todavía se está leyendo la base local. */
    val charlas: StateFlow<List<Charla>?> = repo.programa
        .map<List<Charla>, List<Charla>?> { it }
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)
    val hayEdicion: StateFlow<Boolean?> = repo.hayEdicion
        .map<Boolean, Boolean?> { it }
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    // Filtros en SavedStateHandle: sobreviven a rotación y a muerte del proceso,
    // pero una tarea nueva arranca sin filtros.
    val dia: StateFlow<Int> = guardado.getStateFlow(CLAVE_DIA, 0)
    val tema: StateFlow<String> = guardado.getStateFlow(CLAVE_TEMA, "Todos")
    fun elegirDia(valor: Int) { guardado[CLAVE_DIA] = valor }
    fun elegirTema(valor: String) { guardado[CLAVE_TEMA] = valor }

    private val _cargando = MutableStateFlow(false)
    val cargando: StateFlow<Boolean> = _cargando.asStateFlow()
    private val _fallo = MutableStateFlow(false)
    val fallo: StateFlow<Boolean> = _fallo.asStateFlow()

    private var descarga: Job? = null

    /** Una sola carga automática por proceso; rotar o volver del fondo no descarga de nuevo. */
    fun cargaAutomatica() {
        if (servicios.cargaAutomaticaHecha) return
        servicios.cargaAutomaticaHecha = true
        recargar()
    }

    /** Pedido explícito. Si ya hay una petición en curso, los toques extra se ignoran. */
    fun recargar() {
        if (descarga?.isActive == true) return
        descarga = viewModelScope.launch {
            _cargando.value = true
            _fallo.value = false
            try {
                repo.sincronizar()
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                // Red, HTTP o respuesta inválida: la base no se tocó, se siguen mostrando los datos guardados.
                _fallo.value = true
            } finally {
                _cargando.value = false
            }
        }
    }

    fun marcar(id: String) { viewModelScope.launch { repo.cambiarFavorita(id) } }

    private companion object {
        const val CLAVE_DIA = "filtro_dia"
        const val CLAVE_TEMA = "filtro_tema"
    }
}
