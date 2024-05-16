package com.example.rickandmorty.network

import com.example.rickandmorty.api.CharacterApiService
import com.example.rickandmorty.api.EpisodeAPIService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    val api: CharacterApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharacterApiService::class.java)
    }

    val episodeApi: EpisodeAPIService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EpisodeAPIService::class.java)
    }
}