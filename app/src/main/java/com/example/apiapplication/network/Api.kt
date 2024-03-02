package com.example.apiapplication.network

import com.example.apiapplication.model.network.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("/character")
    suspend fun getCharacter(): Response<CharacterResponse>
}