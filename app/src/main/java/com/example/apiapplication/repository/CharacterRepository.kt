package com.example.apiapplication.repository

import com.example.apiapplication.model.Character
import com.example.apiapplication.network.Api
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepository @Inject constructor(
    private val api: Api
) {

    private var list = emptyList<Character>()


    suspend fun getCharacter(id: String): Character? {
        return list.find {
            id == it.id
        }
    }

    suspend fun getCharacters(): List<Character> {
        list = api.getCharacter().body()?.data?.map {
            Character(
                it._id.toString(),
                it.imageUrl,
                it.name,
                it.films,
                it.shortFilms,
                it.tvShows,
                it.videoGames,
                it.parkAttractions
            )
        } ?: emptyList()
        return list
    }
}