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
- **Retrofit** + kotlinx.serialization _(importado, integración TODO)_
- **Coil 3** para carga de imágenes
- **Room** (KSP) para persistencia local _(importado, integración TODO)_

## Backend / API

Las cartas se sirven desde un **Worker de Cloudflare** propio, que expone un endpoint `GET /cards` (≥20 cartas con imagen, nombre y descripción):

```
https://tarotofpoe1.contry-1990.workers.dev/
```

La `BASE_URL` ya está configurada en `RetrofitClient` y el scaffold de Retrofit (`TarotOfPoeApiService`, DTOs) está creado. De momento la app sigue tirando de `DummyCardRepository` con datos de ejemplo: queda **pendiente (TODO)** sustituirlo por un `RetrofitCardRepository` que consuma el Worker real.

## Estado

En desarrollo activo. Funcionalidades y UI sujetas a cambios.

**Importado pero aún sin integrar (TODO):**

- **Retrofit** → consumir el endpoint `/cards` del Worker de Cloudflare (reemplazar `DummyCardRepository`).
- **Room** → persistir cartas favoritas en local (entidad + DAO + métodos `getFavorites()`, `addFavorite()`, etc.).