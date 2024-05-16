package com.example.rickandmorty.api

import com.example.rickandmorty.model.CharacterResponse
import retrofit2.http.GET

interface CharacterApiService {
    @GET("character")
    suspend fun getCharacters(): CharacterResponse
}