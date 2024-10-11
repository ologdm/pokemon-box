package com.example.pokemonbox.ui.explore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.pokemonbox.data.PokeApi
import com.example.pokemonbox.data.AllPokemonPagingSource
import com.example.pokemonbox.data.ExploreRepository
import com.example.pokemonbox.domain.Pokemon
import com.example.pokemonbox.utils.IoResponse
import com.example.pokemonbox.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewmodel @Inject constructor(
    private val pokeApi: PokeApi,
    private val repository: ExploreRepository
) : ViewModel() {

    companion object {
        private val DEBOUNCE_DELAY: Long = 300L
    }

    // paging all pokemons
    val statePaging = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = { AllPokemonPagingSource(pokeApi) }
    ).flow
        .cachedIn(viewModelScope)


    // search pokemon by name
    val searchResultStatus = MutableStateFlow<UiState<Pokemon>>(UiState())

    private val _searchQueryFlow = MutableStateFlow("")
    val isSearchingFlow: Flow<Boolean> = _searchQueryFlow
        .map { it.isNotBlank() } // blank - tutta la stringa senza caratteri



    init {
        viewModelScope.launch {
            _searchQueryFlow
                .debounce(DEBOUNCE_DELAY)
                .filter { it.isNotBlank() }
                .collectLatest {
                    loadSearchResult(it) // OK
                }
        }
    }

    // usare su fragment - x.doAfterTextChanged()
    fun updateQuery(query: String) {
        _searchQueryFlow.value = query
    }

    // usata internamente
    private suspend fun loadSearchResult(name: String) {
        searchResultStatus.value = UiState(isLoading = true) // resetto ui prima di fare la chiamata
        when (val response = repository.getPokemonSearchResult(name)) {
            is IoResponse.Success -> {
                searchResultStatus.value = UiState(data = response.dataValue)
                // loading farse, error false di default
            }

            is IoResponse.Error -> {
                searchResultStatus.value = UiState(isError = true)
                response.t.printStackTrace()
            }

        }
    }


}