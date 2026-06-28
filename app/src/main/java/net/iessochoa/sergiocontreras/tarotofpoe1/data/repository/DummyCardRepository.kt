package net.iessochoa.sergiocontreras.tarotofpoe1.data.repository

import net.iessochoa.sergiocontreras.tarotofpoe1.domain.model.DivinationCard
import net.iessochoa.sergiocontreras.tarotofpoe1.domain.model.DivinationCardSamples
import net.iessochoa.sergiocontreras.tarotofpoe1.domain.repository.CardRepository

// API Retrofit conectada:
//  ✓ Worker en Cloudflare que expone GET /cards (≥20 cartas con imagen, nombre y descripción).
//  ✓ BASE_URL en RetrofitClient (https://tarotofpoe1.contry-1990.workers.dev/).
//  ✓ Interfaz Retrofit (TarotOfPoeApiService) + DTOs (CardDto/AcquisitionDto) con mappers a dominio.
//  ✓ RetrofitCardRepository : CardRepository consume la API real (enganchado en NavigationRoot).
//  ✓ Este DummyCardRepository queda como fallback offline / preview.

/**
 * In-memory fallback backed by [DivinationCardSamples]. Used by
 * [net.iessochoa.sergiocontreras.tarotofpoe1.data.repository.RetrofitCardRepository]
 * when the network call fails, and by @Preview composables.
 */
class DummyCardRepository : CardRepository {
    override suspend fun getCards(): List<DivinationCard> = DivinationCardSamples.all

    override suspend fun getCard(id: String): DivinationCard? =
        DivinationCardSamples.all.firstOrNull { it.id == id }
}