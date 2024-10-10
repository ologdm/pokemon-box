package com.example.pokemonbox.data.dto


// detailDto - con tutte le categorie presenti,
// uso solo quelle necessarie

data class PokemonDetailDto(
    val id: Int,
    val name: String,
    val types: List<TypeSlotDto>, // funzione conversione su joinData()
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

