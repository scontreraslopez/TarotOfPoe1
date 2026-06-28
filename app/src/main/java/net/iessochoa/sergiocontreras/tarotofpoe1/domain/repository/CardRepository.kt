package net.iessochoa.sergiocontreras.tarotofpoe1.domain.repository

import net.iessochoa.sergiocontreras.tarotofpoe1.domain.model.DivinationCard

interface CardRepository {
    suspend fun getCards(): List<DivinationCard>
    suspend fun getCard(id: String): DivinationCard?

    // TODO ROOM: persistir cartas favoritas localmente.
    //  - Añadir entidad FavoriteCardEntity + FavoriteCardDao en Room.
    //  - Exponer aquí: getFavorites(), addFavorite(id), removeFavorite(id), isFavorite(id).
    //  - Implementar en un RoomFavoritesRepository y conectarlo en CardDetailScreen (botón ★).
}