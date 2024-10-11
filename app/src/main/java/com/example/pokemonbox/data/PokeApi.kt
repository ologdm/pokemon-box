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
//        @Query("limit") limit: Int
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


// per ottenere i dati completi:
// 1. chiamata lista paging
// 2. detail per ogni elemento lista (list<Type>)
// 3. species per ogni elemento lista ()


// PAGING - all pokemon
// https://pokeapi.co/api/v2/pokemon  (?offset=0&limit=20) default start

//https://pokeapi.co/api/v2/pokemon?offset=20&limit=20  // ->  pagina2
//https://pokeapi.co/api/v2/pokemon?offset=40&limit=20 ->next
//https://pokeapi.co/api/v2/pokemon?offset=0&limit=20 ->previous