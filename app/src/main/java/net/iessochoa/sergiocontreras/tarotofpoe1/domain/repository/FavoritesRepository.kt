package net.iessochoa.sergiocontreras.tarotofpoe1.domain.repository

import kotlinx.coroutines.flow.Flow

/**
 * Favoritos locales del usuario. Independiente de [CardRepository] (origen de las
 * cartas): aquí solo se gestiona qué cartas están marcadas, persistido en Room.
 */
interface FavoritesRepository {

    /** Ids de las cartas favoritas, en tiempo real. */
    fun observeFavoriteIds(): Flow<List<String>>

    /** `true` mientras [cardId] esté en favoritos. */
    fun observeIsFavorite(cardId: String): Flow<Boolean>

    /** Marca o desmarca [cardId] como favorito según [favorite]. */
    suspend fun setFavorite(cardId: String, favorite: Boolean)
}