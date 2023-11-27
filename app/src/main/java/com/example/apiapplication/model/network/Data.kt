package com.example.apiapplication.model.network

data class Data(
    val __v: Int,
    val _id: Int,
    val allies: List<Any>,
    val createdAt: String,
    val enemies: List<Any>,
    val films: List<String>,
    val imageUrl: String,
    val name: String,
    val parkAttractions: List<String>,
    val shortFilms: List<String>,
    val sourceUrl: String,
    val tvShows: List<String>,
    val updatedAt: String,
    val url: String,
    val videoGames: List<String>
)