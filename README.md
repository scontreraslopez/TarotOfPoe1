# TarotOfPoe1

> **Status:** Work In Progress (WIP)

Aplicación Android que actúa como **catálogo / browser de las Divination Cards de Path of Exile 1**. Permite explorar las cartas, consultar sus recompensas y detalles asociados.

Las Divination Cards tienen un arte espectacular, son muchas y es importante saber como conseguirlas.

![Apocalypse divination card](apocalypse_card.png)

## Contexto

Proyecto desarrollado en el marco del curso **"Programación Avanzada con Jetpack Compose y Kotlin"**.

## Stack

- Kotlin
- Jetpack Compose
- Material 3
- Firebase Auth + Firestore
- **Retrofit** + kotlinx.serialization _(consumiendo el Worker de Cloudflare)_
- **Coil 3** para carga de imágenes
- **Room** (KSP) para persistencia local _(importado, integración TODO)_

## Acceso / Login

El login usa **Firebase Auth** (email + contraseña). Puedes **registrar el email que quieras**: ahora mismo **no se envía correo de validación**, así que la cuenta queda activa al instante.

Para pruebas rápidas hay un usuario ya creado:

```
email:    test@test.com
password: 123456
```

## Backend / API

Las cartas se sirven desde un **Worker de Cloudflare** propio, que expone un endpoint `GET /cards` (≥20 cartas con imagen, nombre y descripción):

```
https://tarotofpoe1.contry-1990.workers.dev/
```

La app consume este endpoint vía `RetrofitClient` (`BASE_URL` + converter de kotlinx.serialization) y `TarotOfPoeApiService`, mapeando los DTOs al modelo de dominio. El `RetrofitCardRepository` es el repositorio activo; si la red falla cae automáticamente a `DummyCardRepository` (datos de ejemplo) para que la UI no se quede vacía.

## Arquitectura: `domain.repository` vs `data.repository`

El proyecto separa **el contrato** de **la implementación**, siguiendo la idea de Clean Architecture:

- **`domain.repository`** → **interfaces (el "qué")**. Definen el contrato que necesita la app sin saber de dónde salen los datos. No dependen de Retrofit, Room ni Android. Ej.: `CardRepository` (`getCards()`, `getCard(id)`) y `FavoritesRepository` (`observeIsFavorite(id)`, `setFavorite(id, fav)`). Los ViewModels dependen **solo** de estas interfaces.

- **`data.repository`** → **implementaciones (el "cómo")**. Cada clase resuelve el contrato contra una fuente concreta:
  - `RetrofitCardRepository : CardRepository` → llama al Worker de Cloudflare (con fallback a `DummyCardRepository`).
  - `DummyCardRepository : CardRepository` → datos de ejemplo en memoria (fallback / `@Preview`).
  - `RoomFavoritesRepository : FavoritesRepository` → persiste en Room vía el DAO.

**¿Por qué?** El ViewModel pide un `CardRepository` y le da igual si detrás hay Retrofit, Room o datos falsos. Así se puede **cambiar la fuente sin tocar la UI**, **testear** con dobles y **mockear** en previews. La regla de dependencia apunta siempre hacia dentro: `data` conoce `domain`, nunca al revés.

## Estado

En desarrollo activo. Funcionalidades y UI sujetas a cambios.

**Hecho:**

- **Retrofit** → consume el endpoint `/cards` del Worker de Cloudflare, con fallback a `DummyCardRepository`.
- **Room** → persiste las cartas favoritas en local (`FavoriteCardEntity` + `FavoriteCardDao` + `AppDatabase`). El botón de favorito (corazón) en el detalle marca/desmarca y se mantiene entre reinicios.