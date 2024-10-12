package com.example.pokemonbox.fakes

import com.example.pokemonbox.data.PokeApi
import com.example.pokemonbox.data.dto.PokemonDetailDto
import com.example.pokemonbox.data.dto.PokemonListResponseDto
import com.example.pokemonbox.data.dto.PokemonSpeciesDto


class FakePokeApi(
    private val getDetail: (String) -> PokemonDetailDto,
    private val getSpecies: (String) -> PokemonSpeciesDto
) : PokeApi {

    override suspend fun getPokemonDetail(name: String): PokemonDetailDto {
        return getDetail(name)
    }

    override suspend fun getPokemonSpecies(name: String): PokemonSpeciesDto {
        return getSpecies(name)
    }


    override suspend fun getAllPokemonList(offset: Int): PokemonListResponseDto {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonDetail(pokemonId: Int): PokemonDetailDto {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonSpecies(pokemonId: Int): PokemonSpeciesDto {
        TODO("Not yet implemented")
    }
}

