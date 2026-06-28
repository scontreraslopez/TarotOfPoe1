package net.iessochoa.sergiocontreras.tarotofpoe1.data.network

import retrofit2.http.GET

interface TarotOfPoeApiService {

    // /cards endpoint -> devuelve un array JSON de cartas directamente
    @GET("cards")
    suspend fun getCards(): List<CardDto>
}