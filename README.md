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

## Estado

En desarrollo activo. Funcionalidades y UI sujetas a cambios.

**Hecho:**

- **Retrofit** → consume el endpoint `/cards` del Worker de Cloudflare, con fallback a `DummyCardRepository`.

**Importado pero aún sin integrar (TODO):**

- **Room** → persistir cartas favoritas en local (entidad + DAO + métodos `getFavorites()`, `addFavorite()`, etc.). El botón de favorito (corazón) ya existe en el detalle, pero de momento con estado en memoria.