package net.iessochoa.sergiocontreras.tarotofpoe1.data.repository

import net.iessochoa.sergiocontreras.tarotofpoe1.domain.model.DivinationCard

/**
 * Source of divination cards. Suspend functions so the same interface fits a
 * future Retrofit/Firestore implementation without touching the ViewModels.
 */
interface CardRepository {
    suspend fun getCards(): List<DivinationCard>
    suspend fun getCard(id: String): DivinationCard?
}