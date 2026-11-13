package ar.jornadas.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ar.jornadas.data.Charla

private val TEMAS = listOf("Todos", "Android", "Datos", "Calidad")

@Composable
fun Catalogo(model: ProgramaModel, abrir: (String) -> Unit) {
    // Sin LaunchedEffect de recarga: entrar al catálogo o volver de una ficha no descarga.
    val dia by model.dia.collectAsStateWithLifecycle()
    val tema by model.tema.collectAsStateWithLifecycle()
    val fichas by model.charlas.collectAsStateWithLifecycle()
    val hayEdicion by model.hayEdicion.collectAsStateWithLifecycle()
    val cargando by model.cargando.collectAsStateWithLifecycle()
    val fallo by model.fallo.collectAsStateWithLifecycle()

    // Los filtros siempre quedan editables, también durante la espera.
    Selector(listOf("Ambos días", "Día 1", "Día 2"), dia, model::elegirDia)
    Selector(TEMAS, TEMAS.indexOf(tema).coerceAtLeast(0)) { model.elegirTema(TEMAS[it]) }
    TextButton(onClick = model::recargar, enabled = !cargando) { Text("Actualizar") }
    if (cargando) LinearProgressIndicator(Modifier.fillMaxWidth())

    val leyendoBase = hayEdicion == null || fichas == null
    val lista: List<Charla> = fichas ?: emptyList()
    when {
        leyendoBase -> Unit // leyendo la base local
        hayEdicion == false -> {
            // Nunca hubo una descarga exitosa: no hay nada guardado que mostrar.
            if (fallo) {
                Text("No se pudo descargar el programa y todavía no hay una copia guardada.")
                OutlinedButton(onClick = model::recargar, enabled = !cargando) { Text("Reintentar") }
            } else if (!cargando) {
                Text("Todavía no se descargó el programa.")
                OutlinedButton(onClick = model::recargar) { Text("Descargar") }
            }
        }
        else -> {
            if (fallo) OutlinedButton(onClick = model::recargar, enabled = !cargando) { Text("Reintentar") }
            val visibles = lista.filter { (dia == 0 || it.dia == dia) && (tema == "Todos" || it.tema == tema) }
            when {
                lista.isEmpty() -> Text("Sin charlas programadas")
                visibles.isEmpty() -> Text("No hay charlas para este filtro")
                else -> Grilla(visibles, abrir)
            }
        }
    }
}

@Composable
private fun Selector(opciones: List<String>, indice: Int, elegir: (Int) -> Unit) {
    Row(Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        opciones.forEachIndexed { i, texto ->
            FilterChip(selected = i == indice, onClick = { elegir(i) }, label = { Text(texto) })
        }
    }
}

@Composable
fun Grilla(fichas: List<Charla>, abrir: (String) -> Unit) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(bottom = 12.dp)) {
        items(fichas, key = { it.id }) { ficha ->
            ElevatedCard(onClick = { abrir(ficha.id) }, modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("Día ${ficha.dia} · ${ficha.hora} · ${ficha.sala}", style = MaterialTheme.typography.labelMedium)
                    Text(ficha.titulo, style = MaterialTheme.typography.titleLarge)
                    Text(if (ficha.favorita) "${ficha.tema} · En mi agenda" else ficha.tema)
                }
            }
        }
    }
}
