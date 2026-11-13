# Cambios — Jornadas del Sur (Android)

## 41 · Programa sin señal
- **Problema:** ante una falla, `aviso` reemplazaba la grilla (se ocultaba la lista). No había forma de distinguir "nunca descargué" de "descargué un programa vacío" (v4), porque en ambos casos la tabla queda vacía. Sólo se atrapaba `IOException`.
- **Cambio:** nueva tabla `edicion` (Room v2) que registra que hubo al menos una descarga exitosa. La UI decide con `hayEdicion` + `fallo`:
  - falla con edición previa → lista, ficha y Mi agenda siguen visibles + "Sin conexión: se están usando los datos guardados" + Reintentar;
  - falla sin edición → mensaje + Reintentar;
  - edición vacía (v4) → "Sin charlas programadas", también al reabrir sin señal.
- Se atrapa cualquier excepción (red, HTTP, parseo) salvo `CancellationException`. Si la descarga falla, la base no se toca.
- **Migración 1→2** (`MIGRACION_1_2`): crea `edicion` sin borrar charlas ni marcas; si ya había charlas, cuenta como edición descargada. Se conservan los datos de quien actualiza.

## 42 · Agenda personal
- **Problema:** `@Insert(REPLACE)` con `favorita = false` borraba todas las marcas en cada sincronización y nunca eliminaba charlas retiradas (la 11 seguía en v3).
- **Cambio:** `CarteleraDao.aplicarInstantanea` en una `@Transaction`:
  1. borra las charlas que no vienen en la instantánea (y con ellas su marca → si regresan, regresan sin marcar);
  2. inserta las nuevas sin marcar (`IGNORE`);
  3. actualiza sólo el contenido publicado de las vigentes con un `@Update` parcial (`ContenidoCharla`, sin la columna `favorita`), así la 11 conserva su marca en v2 con el título y la sala nuevos;
  4. registra la edición.
- Como la marca no se copia desde la respuesta sino que queda la que está en la base al momento de escribir, una marca agregada o quitada durante la petición se respeta.

## 43 · Volver al punto de lectura
- **Problema:** el tema estaba en un `remember` del Catálogo: se perdía al abrir una ficha (el composable sale de la composición) y al rotar. El día vivía en el ViewModel y no sobrevivía a la muerte del proceso.
- **Cambio:** día y tema en `SavedStateHandle` (`getStateFlow`). Sobreviven a rotación, a entrar y salir de la ficha y a la muerte del proceso (la ficha abierta ya estaba en `rememberSaveable`). Una tarea nueva no restaura estado guardado, así que arranca sin filtros.

## 44 · Control de descargas
- **Problema:** se recargaba en `onStart` (cada rotación o regreso del fondo) y en un `LaunchedEffect` del Catálogo (cada vez que se volvía de una ficha o de otra pestaña). Dos toques lanzaban dos peticiones. `Servicios` se creaba por ViewModel (varias instancias de Room y del contador).
- **Cambio:**
  - `Servicios` es una única instancia por proceso con una bandera `cargaAutomaticaHecha`: una carga automática por sesión.
  - `MainActivity` llama a `cargaAutomatica()` en `onCreate`, después de aplicar `servicio_disponible`. Se quitaron la recarga de `onStart` y el `LaunchedEffect`.
  - `recargar()` ignora pedidos si ya hay un `Job` activo (dos toques = un intento). Al terminar, con éxito o falla, se puede volver a pedir.
  - Los filtros nunca se deshabilitan durante la espera.

## Verificación sugerida (manual)
1. v1, marcar la 11 → v2 + Actualizar: la 11 sigue marcada con «Compose en producción avanzada» / «Sala Puerto».
2. Marcar otra charla con la demora larga activa y la petición en curso → al llegar la respuesta la marca se mantiene.
3. v3 + Actualizar: desaparece la 11 (catálogo y Mi agenda), aparece la 90, el resto conserva marcas. Volver a v1: la 11 vuelve sin marcar.
4. v4 + Actualizar: «Sin charlas programadas». Apagar el servicio, `adb shell am kill ar.jornadas` y reabrir: sigue «Sin charlas programadas» con aviso de datos guardados.
5. `pm clear` + arranque con `servicio_disponible false`: mensaje + Reintentar.
6. Día 2 + Datos → abrir «Cachés para viajeros» → Volver → rotar: 2 resultados. Repetir con la ficha abierta + Inicio + `am kill` + Recientes: vuelve la ficha y, al volver, ambos filtros.
7. Contador de la Mesa: rotar, abrir fichas y cambiar de pestaña no suma; dos toques rápidos en Actualizar suman 1.
