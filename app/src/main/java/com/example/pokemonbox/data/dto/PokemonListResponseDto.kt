package com.example.pokemonbox.data.dto

data class PokemonListResponseDto(
    val count: Int, // total pokemon number
    val next : String?,
    val previous :String?,
    val results : List<PokemonBaseDto>
    )
