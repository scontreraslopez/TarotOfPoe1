package net.iessochoa.sergiocontreras.tarotofpoe1.data.repository

import android.content.Context
import net.iessochoa.sergiocontreras.tarotofpoe1.data.local.AppDatabase
import net.iessochoa.sergiocontreras.tarotofpoe1.data.local.FavoriteCardDao
import net.iessochoa.sergiocontreras.tarotofpoe1.data.local.FavoriteCardEntity
import net.iessochoa.sergiocontreras.tarotofpoe1.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow

/**
 * Implementación de [FavoritesRepository] respaldada por Room ([FavoriteCardDao]).
 */
class RoomFavoritesRepository(
    private val dao: FavoriteCardDao,
) : FavoritesRepository {

    override fun observeFavoriteIds(): Flow<List<String>> = dao.observeFavoriteIds()

    override fun observeIsFavorite(cardId: String): Flow<Boolean> =
        dao.observeIsFavorite(cardId)

    override suspend fun setFavorite(cardId: String, favorite: Boolean) {
        if (favorite) {
            dao.add(FavoriteCardEntity(cardId))
        } else {
            dao.remove(cardId)
        }
    }

    companion object {
        /** Crea el repositorio con el DAO del [AppDatabase] singleton. */
        fun create(context: Context): RoomFavoritesRepository =
            RoomFavoritesRepository(AppDatabase.getInstance(context).favoriteCardDao())
    }
}