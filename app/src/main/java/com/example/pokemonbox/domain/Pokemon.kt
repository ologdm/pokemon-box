package com.example.pokemonbox.domain

import com.example.pokemonbox.data.dto.TypeSlotDto

data class Pokemon(
    val id: Int, // = base
    val name: String, // =base
    val url: String, // =base
    val types: List<String>, // from detail list stringhe - TODO converter
    val description: String  // from species  - TODO converter
) {

    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"

}
