package com.example.pokemonbox.domain

import com.example.pokemonbox.data.dto.TypeSlotDto

class Pokemon(
    val id: Int,
    val name: String,
    private val url: String,
    val types: List<TypeSlotDto>, // from detail list stirnghe
    val description: String  // from species
) {

    // es json_url - https://pokeapi.co/api/v2/pokemon/1/"
    private val index: Int = url
        .split("/") // divide string in ["https:", "", "pokeapi.co", "api", "v2", "pokemon", "1", ""]
        .dropLast(1)        // Removes the last segment (assuming it's not needed)
        .last()                // get the last element (safely), or null if it doesn't exist
        .toInt()               // Convert to Int, returns null if conversion is not possible


    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"

}
