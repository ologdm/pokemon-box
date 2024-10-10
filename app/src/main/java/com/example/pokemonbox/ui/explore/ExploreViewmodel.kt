package com.example.pokemonbox.ui.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.pokemonbox.data.PokeApi
import com.example.pokemonbox.data.repository.AllPokemonPagingSource
import com.example.pokemonbox.data.repository.ExploreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ExploreViewmodel @Inject constructor(
    private val pokeApi: PokeApi,
    private val repository: ExploreRepository
) : ViewModel() {


    // paging all pokemons
    val statePaging = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = { AllPokemonPagingSource(pokeApi) }
    ).flow
        .cachedIn(viewModelScope)


    // search pokemon by name



}