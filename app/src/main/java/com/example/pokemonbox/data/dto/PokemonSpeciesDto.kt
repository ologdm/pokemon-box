package com.example.pokemonbox.data.dto

import com.google.gson.annotations.SerializedName

// speciesDto - con tutte le categorie presenti,
// uso solo quelle necessarie


data class PokemonSpeciesDto(
    val id: Int,
    val name: String,
    @SerializedName("flavor_text_entries") val flavorTextEntries: List<FlavorTextEntry>,
    /* don't use
//val base_happiness: Int,
//val capture_rate: Int,
//val color: NamedAPIResource,
//val egg_groups: List<NamedAPIResource>,
//val evolution_chain: EvolutionChainLink?,
//val evolves_from_species: NamedAPIResource?,
//val form_descriptions: List<Any>,
//val forms_switchable: Boolean,
//val gender_rate: Int,
//val genera: List<Genus>,
//val generation: NamedAPIResource,
//val growth_rate: NamedAPIResource,
//val habitat: NamedAPIResource?,
//val has_gender_differences: Boolean,
//val hatch_counter: Int,
//val is_baby: Boolean,
//val is_legendary: Boolean,
//val is_mythical: Boolean,
//val names: List<Name>,
//val order: Int,
//val pal_park_encounters: List<PalParkEncounter>,
//val pokedex_numbers: List<PokedexNumber>,
//val shape: NamedAPIResource?,
//val varieties: List<Variety>
     */
)


data class FlavorTextEntry(
    @SerializedName("flavor_text") val flavorText: String,
    val language: NamedApiResource
)


// Dto == PokemonBaseDto
data class NamedApiResource(
    val name: String,
    val url: String
)
