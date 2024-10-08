package com.example.pokemonbox.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemonbox.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigator.startAllPokemonFragment()
    }
}