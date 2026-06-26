package net.iessochoa.sergiocontreras.tarotofpoe1.data.network

import retrofit2.http.GET

interface TarotOfPoeApiService {

    // /cards endpoint
    @GET("cards")
    suspend fun getCards(): ResponseDto
}