package com.example.pokemonbox.ui.explore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.pokemonbox.data.PokeApi
import com.example.pokemonbox.data.dto.PokemonDetailDto
import com.example.pokemonbox.data.repository.AllPokemonPagingSource
import com.example.pokemonbox.data.repository.ExploreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ExploreViewmodel @Inject constructor(
    private val pokeApi: PokeApi,
    private val repository: ExploreRepository
) : ViewModel() {

    // tre chiamate differenti:
    // 1. paging
    val statePaging = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = { AllPokemonPagingSource(pokeApi) }
    ).flow
        .cachedIn(viewModelScope)



}