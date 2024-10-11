package com.example.pokemonbox.domain


data class Pokemon(
    val id: Int, // base
    val name: String, // base
    val types: List<String>, // from detail list stringhe
    val description: String  // from species
) {

    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"

}
