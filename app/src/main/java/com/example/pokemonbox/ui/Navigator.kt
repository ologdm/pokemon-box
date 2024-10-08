package com.example.pokemonbox.ui

import androidx.fragment.app.FragmentActivity
import javax.inject.Inject

class Navigator @Inject constructor(
    private val fragmentActivity: FragmentActivity
) {

    fun startAllPokemonsFragment (){
        val fragmentManager = fragmentActivity.supportFragmentManager
//        fragmentManager.beginTransaction().replace().commit()
    }


    fun startDetailFragment(){
        val fragmentManager = fragmentActivity.supportFragmentManager
    }


}