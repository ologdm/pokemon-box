package com.example.pokemonbox.data.repository

import com.example.pokemonbox.data.PokeApi
import com.example.pokemonbox.data.dto.PokemonDetailDto
import com.example.pokemonbox.data.dto.PokemonSpeciesDto
import com.example.pokemonbox.data.dto.toDomain
import com.example.pokemonbox.domain.Pokemon
import com.example.pokemonbox.utils.IoResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.cancellation.CancellationException

@Singleton
class ExploreRepository @Inject constructor(
    private val pokeApi: PokeApi
) {


    suspend fun getPokemonSearchResult(name: String): IoResponse<Pokemon> {
        return coroutineScope {
            try {
                val detailDeferred = async { pokeApi.getPokemonDetail(name) }
                val speciesDeferred = async { pokeApi.getPokemonSpecies(name) }

                val detailDto = detailDeferred.await()
                val speciesDto = speciesDeferred.await()

                IoResponse.Success(detailDto.toDomain(speciesDto))

            } catch (ex: CancellationException) {
                throw ex
            } catch (ex: Throwable) {
                IoResponse.Error(ex)
            }
        }
    }


}


