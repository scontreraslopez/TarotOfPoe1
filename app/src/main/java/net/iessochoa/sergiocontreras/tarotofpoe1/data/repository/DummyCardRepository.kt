package net.iessochoa.sergiocontreras.tarotofpoe1.data.repository

import net.iessochoa.sergiocontreras.tarotofpoe1.domain.model.DivinationCard
import net.iessochoa.sergiocontreras.tarotofpoe1.domain.model.DivinationCardSamples
import net.iessochoa.sergiocontreras.tarotofpoe1.domain.repository.CardRepository

/**
 * In-memory fallback backed by [DivinationCardSamples]. Swap for the real
 * Retrofit/Firestore repository later — the [CardRepository] contract stays the same.
 */
class DummyCardRepository : CardRepository {
    override suspend fun getCards(): List<DivinationCard> = DivinationCardSamples.all

    override suspend fun getCard(id: String): DivinationCard? =
        DivinationCardSamples.all.firstOrNull { it.id == id }
}