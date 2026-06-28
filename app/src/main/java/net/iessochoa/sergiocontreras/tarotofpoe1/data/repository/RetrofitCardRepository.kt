package net.iessochoa.sergiocontreras.tarotofpoe1.data.repository

import android.content.Context
import net.iessochoa.sergiocontreras.tarotofpoe1.data.network.RetrofitClient
import net.iessochoa.sergiocontreras.tarotofpoe1.data.network.TarotOfPoeApiService
import net.iessochoa.sergiocontreras.tarotofpoe1.data.network.toDomain
import net.iessochoa.sergiocontreras.tarotofpoe1.domain.model.DivinationCard
import net.iessochoa.sergiocontreras.tarotofpoe1.domain.repository.CardRepository

/**
 * [CardRepository] real que consume el endpoint `GET /cards` del Worker de
 * Cloudflare vía Retrofit y mapea los [net.iessochoa.sergiocontreras.tarotofpoe1.data.network.CardDto]
 * al modelo de dominio.
 *
 * Si la llamada de red falla (sin conexión, Worker caído...), cae al
 * [fallback] con los datos de ejemplo para que la UI no se quede vacía.
 */
class RetrofitCardRepository(
    private val api: TarotOfPoeApiService,
    private val fallback: CardRepository = DummyCardRepository(),
) : CardRepository {

    override suspend fun getCards(): List<DivinationCard> = try {
        api.getCards().map { it.toDomain() }
    } catch (e: Exception) {
        fallback.getCards()
    }

    // El endpoint solo expone /cards, así que resolvemos la carta filtrando la lista.
    override suspend fun getCard(id: String): DivinationCard? =
        getCards().firstOrNull { it.id == id }

    companion object {
        /** Crea el repositorio con el [TarotOfPoeApiService] singleton de [RetrofitClient]. */
        fun create(context: Context): RetrofitCardRepository =
            RetrofitCardRepository(RetrofitClient.getApiService(context))
    }
}