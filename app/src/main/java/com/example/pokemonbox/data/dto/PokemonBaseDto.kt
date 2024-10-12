package com.example.pokemonbox.data.dto

data class PokemonBaseDto(
    val name: String,
    val url: String
) {

    val id: Int
        get() = url
            .split("/")
            .dropLast(1)
            .last()
            .toInt()


}

