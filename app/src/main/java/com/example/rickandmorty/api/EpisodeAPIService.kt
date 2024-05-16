package com.example.rickandmorty.api

import com.example.rickandmorty.model.Episode
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeAPIService {
    @GET("episode/{id}")
    suspend fun getEpisode(@Path("id") episodeId: Int): Episode
}