package net.iessochoa.sergiocontreras.tarotofpoe1.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCardDao {

    /** Ids de todas las cartas favoritas, en tiempo real (se reemite al cambiar). */
    @Query("SELECT cardId FROM favorite_cards")
    fun observeFavoriteIds(): Flow<List<String>>

    /** `true` mientras la carta [cardId] esté en favoritos. */
    @Query("SELECT EXISTS(SELECT 1 FROM favorite_cards WHERE cardId = :cardId)")
    fun observeIsFavorite(cardId: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(favorite: FavoriteCardEntity)

    @Query("DELETE FROM favorite_cards WHERE cardId = :cardId")
    suspend fun remove(cardId: String)
}