package com.example.pokemonbox.ui

import androidx.fragment.app.FragmentActivity
import com.example.pokemonbox.R
import com.example.pokemonbox.ui.allpokemon.AllPokemonFragment
import javax.inject.Inject

class Navigator @Inject constructor(
    private val fragmentActivity: FragmentActivity
) {

    fun startAllPokemonFragment() {
        fragmentActivity.supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, AllPokemonFragment())
            .commit()
    }


}