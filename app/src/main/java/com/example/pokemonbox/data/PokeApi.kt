package com.example.pokemonbox.data

import com.example.pokemonbox.data.dto.PokemonBaseDto
import com.example.pokemonbox.data.dto.PokemonDetailDto
import com.example.pokemonbox.data.dto.PokemonSpeciesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    suspend fun getAllPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): List<PokemonBaseDto>


    @GET("pokemon/{name}")
    //https://pokeapi.co/api/v2/pokemon/bulbasaur/
    suspend fun getPokemonDetail(
        @Path("name") name: String
    ): PokemonDetailDto


    // pokemon description
    //https://pokeapi.co/api/v2/pokemon-species/bulbasaur/
    @GET("pokemon-species/{name}")
    fun getPokemonSpecies(
        @Path("name") name: String
    ): PokemonSpeciesDto

}


// per ottenere i dati completi,
// 1. chiamata lista paging
// 2. detail per ogni elemento lista (list<Type>)
// 3. species per ogni elemento lista ()


// PAGING - all pokemon
// https://pokeapi.co/api/v2/pokemon // -> start

//https://pokeapi.co/api/v2/pokemon?offset=20&limit=20  // ->  pagina2
//https://pokeapi.co/api/v2/pokemon?offset=40&limit=20 ->next
//https://pokeapi.co/api/v2/pokemon?offset=0&limit=20 ->previous

// trakt ->  ...?page={1}&limit={10}


// esempi ricerca