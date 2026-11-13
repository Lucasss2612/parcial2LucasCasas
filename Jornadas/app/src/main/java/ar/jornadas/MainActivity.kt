package ar.jornadas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import ar.jornadas.ui.*

class MainActivity : ComponentActivity() {
    private val model: ProgramaModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null && intent.hasExtra("servicio_disponible")) {
            model.mesa.disponible = intent.getBooleanExtra("servicio_disponible", true)
            intent.removeExtra("servicio_disponible")
        }
        // Después de aplicar la configuración de la mesa. Se ignora si ya hubo una carga
        // automática en este proceso (rotar o volver del fondo no descarga de nuevo).
        model.cargaAutomatica()
        setContent { Portada(model) }
    }
    // Se quitó la recarga en onStart: disparaba una descarga en cada rotación o regreso del fondo.
}
