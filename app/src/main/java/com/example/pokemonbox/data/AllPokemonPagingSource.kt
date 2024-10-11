package com.example.pokemonbox.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokemonbox.data.PokeApi
import com.example.pokemonbox.data.dto.PokemonBaseDto
import com.example.pokemonbox.data.dto.PokemonDetailDto
import com.example.pokemonbox.data.dto.PokemonSpeciesDto
import com.example.pokemonbox.domain.Pokemon
import com.example.pokemonbox.utils.removeLineBreaks
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Singleton
import kotlin.coroutines.cancellation.CancellationException

@Singleton
class AllPokemonPagingSource(
    private val pokeApi: PokeApi
) : PagingSource<Int, Pokemon>() {

    // loadSize = sceglie l'adapter, start x3
    // pageSize - definisco io, rimane quello

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Pokemon> {
        val offset = params.key ?: 0 // start offset

        return try {
            // 1.
            val resultPokemons = pokeApi.getAllPokemonList(offset).results

            // 2.1
            val pokemonDetailDeferredList = coroutineScope {
                resultPokemons.map {
                    async {
                        pokeApi.getPokemonDetail(it.id)
                    }
                }
            }

            //2.2
            val pokemonSpeciesDeferredList = coroutineScope {
                resultPokemons.map {
                    async {
                        pokeApi.getPokemonSpecies(it.id)
                    }
                }
            }

            //3 ritorno risultato da thread paralleli
            val pokemonDetailList = pokemonDetailDeferredList.awaitAll()
            val pokemonSpeciesList = pokemonSpeciesDeferredList.awaitAll()

            val pokemons =
                joinData(resultPokemons, pokemonDetailList, pokemonSpeciesList)


            LoadResult.Page(
                data = pokemons,
                prevKey = null, // doesn't need because I always start loading data from beginning
                nextKey = if (resultPokemons.isEmpty()) null else (offset + 20) // ok
            )
        } catch (ex: CancellationException) {
            throw ex
        } catch (ex: Throwable) {
            LoadResult.Error(ex)
        }
    }


    // +20, -20
    override fun getRefreshKey(
        state: PagingState<Int, Pokemon>
    ): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(state.config.pageSize) // +20
                ?: anchorPage?.nextKey?.minus(state.config.pageSize) // -20
        }
    }


    // #########################################################
    private fun joinData(
        pokemonBaseDtos: List<PokemonBaseDto>,
        pokemonDetailsDtos: List<PokemonDetailDto>,
        pokemonSpeciesDtos: List<PokemonSpeciesDto>,
    ): List<Pokemon> {
        val result = mutableListOf<Pokemon>()
        for (pokemonBaseDto in pokemonBaseDtos) {
            val detail = pokemonDetailsDtos.find { it.id == pokemonBaseDto.id }
            val species = pokemonSpeciesDtos.find { it.id == pokemonBaseDto.id }
            val pokemon = Pokemon(
                id = pokemonBaseDto.id,
                name = pokemonBaseDto.name,
                types = detail?.types?.map { it.type.name }
                    ?: emptyList(), // se primo slot vuoto, anche gli altri saran vuoti
                description = species?.flavorTextEntries
                    ?.sortedBy { it.flavorText.length }
                    ?.firstOrNull { it.language.name == "en" }?.flavorText?.removeLineBreaks()
                    ?: "no description"
            )
            result.add(pokemon)
        }
        return result
    }


}