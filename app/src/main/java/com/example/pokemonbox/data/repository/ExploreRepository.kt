package com.example.pokemonbox.data.repository

import com.example.pokemonbox.data.PokeApi
import com.example.pokemonbox.data.dto.PokemonDetailDto
import com.example.pokemonbox.data.dto.PokemonSpeciesDto
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExploreRepository @Inject constructor(
    private val pokeApi: PokeApi
) {

    // TODO
    suspend fun getDetailData(name: String): PokemonDetailDto {
        return try {
            pokeApi.getPokemonDetail(name)
        } catch (ex: Throwable) {
            throw ex
        }
    }

    // TODO
    suspend fun getPokemonSpeciesData(name: String): PokemonSpeciesDto {
        return try {
            pokeApi.getPokemonSpecies(name)
        } catch (ex: Throwable) {
            throw ex
        }
    }




}

//    // TEST COROUTINES
//    private val resultPokemons = listOf(1, 2, 3, 4, 5, 5, 6, 8, 9, 10)
//
/*
//    // test parallelo superato
//    suspend fun testParallelo() {
//        // faccio solo partire le chiamate in parallelo
//        // (1)
//        val pokemonDetailDeferredList = coroutineScope {
//            resultPokemons.map {
//                async {
//                    pokeApi.getPokemonDetail(it)
//                    delay(5000)
//                }
//            }
//        }
//        // (2)
//        val pokemonSpeciesDeferredList = coroutineScope {
//            resultPokemons.map {
//                async {
//                    pokeApi.getPokemonSpecies(it)
//                    delay(2000)
//                }
//            }
//        }
//
//        // faccio partire prima le chiamate, poi dopo scrivo qua (come callback)
//        val pokemonDetailList = pokemonDetailDeferredList.awaitAll().forEach {
//            println("TTT DETA $it")
//        }
//        val pokemonSpeciesList = pokemonSpeciesDeferredList.awaitAll().forEach {
//            println("TTT SPEC $it")
//        }
//
//    }
//
//
//    // test parallelo superato
//    suspend fun testParallelo2() {
//        // faccio solo partire le chiamate in parallelo
//        // (1)
//
//        coroutineScope {
//            val pokemonDetailDeferredList = resultPokemons.map {
//                async {
//                    pokeApi.getPokemonDetail(it)
//                    delay(5000)
//                }
//            }
//            // (2)
//
//            val pokemonSpeciesDeferredList =
//                resultPokemons.map {
//                    async {
//                        // esegue la funzione sotto su thread parallelo
//                        pokeApi.getPokemonSpecies(it)
//                        delay(2000)
//                    }
//                }
//
//            // faccio partire prima le chiamate, poi dopo scrivo qua (come callback)
//            val pokemonDetailList = pokemonDetailDeferredList.awaitAll().forEach {
//                println("TTT DETA $it")
//            }
//
//            val pokemonSpeciesList = pokemonSpeciesDeferredList.awaitAll().forEach {
//                println("TTT SPEC $it")
//            }
//        }
//
//
//    }

 */


