package net.iessochoa.sergiocontreras.tarotofpoe1.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Una carta marcada como favorita. Solo guardamos el id de la carta
 * (la `DivinationCard` completa vive en el Worker / Retrofit); aquí únicamente
 * persistimos qué cartas ha marcado el usuario.
 */
@Entity(tableName = "favorite_cards")
data class FavoriteCardEntity(
    @PrimaryKey val cardId: String,
)