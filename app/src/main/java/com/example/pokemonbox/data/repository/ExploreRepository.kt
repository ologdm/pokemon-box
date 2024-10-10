package com.example.pokemonbox.data.repository

import com.example.pokemonbox.data.PokeApi
import com.example.pokemonbox.data.dto.PokemonDetailDto
import com.example.pokemonbox.data.dto.PokemonSpeciesDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExploreRepository @Inject constructor(
    private val api: PokeApi
) {

    // TODO
    suspend fun getDetailData(name: String): PokemonDetailDto {
        return try {
            api.getPokemonDetail(name)
        } catch (ex: Throwable) {
            throw ex
        }
    }

    // TODO
    suspend fun getPokemonSpeciesData(name: String): PokemonSpeciesDto {
        return try {
            api.getPokemonSpecies(name)
        } catch (ex: Throwable) {
            throw ex
        }
    }

}