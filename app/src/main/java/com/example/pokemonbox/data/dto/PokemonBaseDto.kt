package com.example.pokemonbox.data.dto

data class PokemonBaseDto(
    val name: String,
    val url: String
) {

    val id: Int
        get() = url
            .split("/") // divide string in ["https:", "", "pokeapi.co", "api", "v2", "pokemon", "1", ""]
            .dropLast(1)        // Removes the last segment (assuming it's not needed)
            .last()                // get the last element (safely), or null if it doesn't exist
            .toInt()               // Convert to Int, returns null if conversion is not possible


}

