package com.example.pokemonbox.ui.allpokemon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonbox.data.PokeApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AllPokemonViewmodel @Inject constructor(
    private val pokeApi: PokeApi
) : ViewModel() {

    // tre chiamate differenti:
    // 1. paging
    // 2. detail + caching
    // 3. species + caching



}