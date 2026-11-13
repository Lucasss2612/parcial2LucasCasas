package ar.jornadas.data

class AgendaRepository(private val api: ProgramaApi, private val dao: CarteleraDao) {
    val programa = dao.observar()
    val hayEdicion = dao.hayEdicion()

    /** Descarga la instantánea completa y la aplica de forma atómica. Si falla, no toca la base. */
    suspend fun sincronizar() {
        val fichas = api.consultar().map {
            ContenidoCharla(it.id, it.title, it.track, it.day, it.time, it.room, it.speaker, it.summary)
        }
        dao.aplicarInstantanea(fichas, System.currentTimeMillis())
    }

    suspend fun cambiarFavorita(id: String) = dao.alternar(id)
}
