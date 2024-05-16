package com.example.rickandmorty.repository

import com.example.rickandmorty.model.Episode
import com.example.rickandmorty.network.RetrofitInstance

class CharacterRepository {
    private val api = RetrofitInstance.api
    private val episodeApi = RetrofitInstance.episodeApi

    suspend fun getCharacters() = api.getCharacters()
    suspend fun getCharacterEpisodes(episodeIds: List<String>): List<Episode> {
        val episodes = mutableListOf<Episode>()
        for (episodeId in episodeIds) {
            val episode = episodeApi.getEpisode(episodeId.toInt())
            episodes.add(episode)
        }
        return episodes
    }
}