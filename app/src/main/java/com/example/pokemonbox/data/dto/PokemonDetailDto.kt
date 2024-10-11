package com.example.pokemonbox.data.dto

import com.example.pokemonbox.domain.Pokemon
import com.example.pokemonbox.utils.removeLineBreaks


data class PokemonDetailDto(
    val id: Int,
    val name: String,
    val types: List<TypeSlotDto>, // conversion function on joinData()
    /* don't use
//    @SerializedName("base_experience") val baseExperience: Int,
//    val height: Int,
//    val weight: Int,
//    val order: Int,
//    @SerializedName("is_default") val isDefault: Boolean,
//    val abilities: List<AbilitySlot>,
//    val stats: List<Stat>,
//    val sprites: Sprites,
//    val moves: List<MoveSlot>,
//    val species: Species,
//    val forms: List<FormDto>,
//    @SerializedName("past_types") val pastTypes: List<TypeSlotDto>
//    @SerializedName("game_indices") val gameIndices: List<GameIndexDto>
//    @SerializedName("held_items") val heldItems: List<HeldItemDto>
//    @SerializedName("location_area_encounters") val locationAreaEncounters: String
     */
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
//        val detail = pokemonDetailsDtos.find { it.id == pokemonBaseDto.id }
//        val species = pokemonSpeciesDtos.find { it.id == pokemonBaseDto.id }
    return Pokemon(
        id = this.id,
        name = this.name,
        types = this.types.map { it.type.name }, // se primo slot vuoto, anche gli altri saran vuoti
        description = pokemonSpeciesDto?.flavorTextEntries
            ?.sortedBy { it.flavorText.length }
            ?.firstOrNull { it.language.name == "en" }?.flavorText?.removeLineBreaks()
            ?: "no description"
    )
}


