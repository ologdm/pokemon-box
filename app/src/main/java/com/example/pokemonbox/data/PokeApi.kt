package com.example.pokemonbox.data

import com.example.pokemonbox.data.dto.PokemonDetailDto
import com.example.pokemonbox.data.dto.PokemonListResponseDto
import com.example.pokemonbox.data.dto.PokemonSpeciesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {


    @GET("pokemon")
    suspend fun getAllPokemonList(
        @Query("offset") offset: Int
    ): PokemonListResponseDto


    // DETAIL
    // by pokemon_id (use for pokemon list)
    @GET("pokemon/{pokemon_id}")
    suspend fun getPokemonDetail(
        @Path("pokemon_id") pokemonId: Int
    ): PokemonDetailDto

    // by name (use for search)
    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(
        @Path("name") name: String
    ): PokemonDetailDto


    // SPECIES
    // by pokemon_id (use for pokemon list)
    @GET("pokemon-species/{pokemon_id}")
    suspend fun getPokemonSpecies(
        @Path("pokemon_id") pokemonId: Int
    ): PokemonSpeciesDto

    // by pokemon name (use for search)
    @GET("pokemon-species/{name}")
    suspend fun getPokemonSpecies(
        @Path("name") name: String
    ): PokemonSpeciesDto

}