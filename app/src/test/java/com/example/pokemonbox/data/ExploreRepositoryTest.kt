package com.example.pokemonbox.data

import com.example.pokemonbox.data.dto.FlavorTextEntryDto
import com.example.pokemonbox.data.dto.NamedApiResourceDto
import com.example.pokemonbox.data.dto.PokemonDetailDto
import com.example.pokemonbox.data.dto.PokemonSpeciesDto
import com.example.pokemonbox.data.dto.TypeDto
import com.example.pokemonbox.data.dto.TypeSlotDto
import com.example.pokemonbox.domain.Pokemon
import com.example.pokemonbox.fakes.FakePokeApi
import com.example.pokemonbox.utils.IoResponse
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class ExploreRepositoryTest {

    @Test
    fun `test getPokemonSearchResult success`() = runTest {
        val api = FakePokeApi(
            getDetail = {
                PokemonDetailDto(1, "bulbasaur", listOf(TypeSlotDto(0, TypeDto("grass", ""))))
            },
            getSpecies = {
                PokemonSpeciesDto(
                    id = 1,
                    name = "bulbasaur",
                    flavorTextEntries = listOf(
                        FlavorTextEntryDto("description", NamedApiResourceDto("en", ""))
                    )
                )
            }
        )
        val repository = ExploreRepository(api)
        val result = repository.getPokemonSearchResult("bulbasaur")
        Assert.assertEquals(
            IoResponse.Success(
                Pokemon(
                    id = 1,
                    name = "Bulbasaur",
                    types = listOf("grass"),
                    description = "description"
                )
            ),
            result
        )
    }


    @Test
    fun `test getPokemonSearchResult failure`() = runTest {
        val api = FakePokeApi(
            getDetail = { error("") },
            getSpecies = { error("") }
        )
        val repository = ExploreRepository(api)
        val result = repository.getPokemonSearchResult("bulbasaur")
        Assert.assertTrue(result is IoResponse.Error)

    }


    @Test
    fun `test getPokemonSearchResult failure detail`() = runTest {
        val api = FakePokeApi(
            getDetail = {
                PokemonDetailDto(0, "", emptyList())
            },
            getSpecies = { error("") }
        )
        val repository = ExploreRepository(api)
        val result = repository.getPokemonSearchResult("bulbasaur")
        Assert.assertTrue(result is IoResponse.Error)
    }


    @Test
    fun `test getPokemonSearchResult failure species`() = runTest {
        val api = FakePokeApi(
            getDetail = { error("") },
            getSpecies = {
                PokemonSpeciesDto(0, "", emptyList())
            }
        )
        val repository = ExploreRepository(api)
        val result = repository.getPokemonSearchResult("bulbasaur")
        Assert.assertTrue(result is IoResponse.Error)
    }

}
