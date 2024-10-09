package com.example.pokemonbox.data.dto

data class PokemonListResponseDto(
    val count: Int,
    val next : String?,
    val previous :String?,
    val results : List<PokemonBaseDto>
    )
