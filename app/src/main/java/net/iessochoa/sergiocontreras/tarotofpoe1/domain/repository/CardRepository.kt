package net.iessochoa.sergiocontreras.tarotofpoe1.domain.repository

import net.iessochoa.sergiocontreras.tarotofpoe1.domain.model.DivinationCard

interface CardRepository {
    suspend fun getCards(): List<DivinationCard>
    suspend fun getCard(id: String): DivinationCard?
}