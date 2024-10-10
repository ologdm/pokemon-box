package com.example.pokemonbox.data.dto

import com.example.pokemonbox.domain.Pokemon

data class PokemonBaseDto(
    val name: String, // "bulbasaur"
    val url: String // "https://pokeapi.co/api/v2/pokemon/{id}/""
) {

    // TODO - test, eliminare
    // es json_url - https://pokeapi.co/api/v2/pokemon/1/"
    val index: Int
        get() = url
            .split("/") // divide string in ["https:", "", "pokeapi.co", "api", "v2", "pokemon", "1", ""]
            .dropLast(1)        // Removes the last segment (assuming it's not needed)
            .last()                // get the last element (safely), or null if it doesn't exist
            .toInt()               // Convert to Int, returns null if conversion is not possible


    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"

}


fun PokemonBaseDto.toDomain(): Pokemon {
    return Pokemon(
        id = index,
        name = name,
        url = url,
        emptyList(),
        ""
    )
}
