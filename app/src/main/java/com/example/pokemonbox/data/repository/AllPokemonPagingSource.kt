package com.example.pokemonbox.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokemonbox.data.PokeApi
import com.example.pokemonbox.data.dto.PokemonBaseDto
import com.example.pokemonbox.data.dto.PokemonListResponseDto
import javax.inject.Singleton
import kotlin.coroutines.cancellation.CancellationException

@Singleton
class AllPokemonPagingSource(
    private val pokeApi: PokeApi
) : PagingSource<Int, PokemonBaseDto>() {

    // loadSize = sceglie l'adapter, start x3
    // pageSize - definisco io, rimane quello

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, PokemonBaseDto> {
        val offset = params.key ?: 0 // start offset

        return try {
            val resultPokemons  = pokeApi.getAllPokemonList(offset).results


            LoadResult.Page(
                data = resultPokemons,
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
        state: PagingState<Int, PokemonBaseDto>
    ): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(state.config.pageSize) // +20
                ?: anchorPage?.nextKey?.minus(state.config.pageSize) // -20
        }
    }

}