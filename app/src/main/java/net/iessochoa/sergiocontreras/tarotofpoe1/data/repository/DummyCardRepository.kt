package net.iessochoa.sergiocontreras.tarotofpoe1.data.repository

import net.iessochoa.sergiocontreras.tarotofpoe1.domain.model.DivinationCard
import net.iessochoa.sergiocontreras.tarotofpoe1.domain.model.DivinationCardSamples
import net.iessochoa.sergiocontreras.tarotofpoe1.domain.repository.CardRepository

// TODO API Retrofit: reemplazar por un RetrofitCardRepository que consuma el Cloudflare Worker.
//  ✓ Worker en Cloudflare que expone GET /cards (≥20 cartas con imagen, nombre y descripción).
//    BASE_URL ya apunta a él en RetrofitClient (https://tarotofpoe1.contry-1990.workers.dev/).
//  - Definir la interfaz Retrofit (CardApiService) + modelos de respuesta (CardDto).
//  - Implementar RetrofitCardRepository : CardRepository llamando a la API real.
//  - Mantener DummyCardRepository como fallback offline / preview.

/**
 * In-memory fallback backed by [DivinationCardSamples]. Swap for the real
 * Retrofit/Firestore repository later — the [CardRepository] contract stays the same.
 */
class DummyCardRepository : CardRepository {
    override suspend fun getCards(): List<DivinationCard> = DivinationCardSamples.all

    override suspend fun getCard(id: String): DivinationCard? =
        DivinationCardSamples.all.firstOrNull { it.id == id }
}