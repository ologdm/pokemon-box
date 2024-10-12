package com.example.pokemonbox.data.dto

import com.example.pokemonbox.domain.Pokemon
import com.example.pokemonbox.utils.removeLineBreaks


data class PokemonDetailDto(
    val id: Int,
    val name: String,
    val types: List<TypeSlotDto>,
)


// total 12 types
data class TypeSlotDto(
    val slot: Int,
    val type: TypeDto
)

data class TypeDto(
    val name: String,
    val url: String
)


fun PokemonDetailDto.toDomain(
    pokemonSpeciesDto: PokemonSpeciesDto?,
): Pokemon {
    return Pokemon(
        id = this.id,
        name = this.name.replaceFirstChar { it.uppercaseChar() },
        types = this.types.map { it.type.name },
        description = pokemonSpeciesDto?.flavorTextEntries
            ?.sortedBy { it.flavorText.length }
            ?.firstOrNull { it.language.name == "en" }?.flavorText?.removeLineBreaks()
            ?: "no description"
    )
}


