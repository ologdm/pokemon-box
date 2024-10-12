package com.example.pokemonbox.data.dto

import com.google.gson.annotations.SerializedName



data class PokemonSpeciesDto(
    val id: Int,
    val name: String,
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntryDto>,
)


data class FlavorTextEntryDto(
    @SerializedName("flavor_text") val flavorText: String,
    val language: NamedApiResourceDto
)


data class NamedApiResourceDto(
    val name: String,
    val url: String
)
