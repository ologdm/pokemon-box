package com.example.pokemonbox.data.dto

data class PokemonListResponseDto(
    val next : String?,
    val results : List<PokemonBaseDto>
)
