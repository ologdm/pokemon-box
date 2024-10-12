package com.example.pokemonbox.utils

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.example.pokemonbox.R
import com.google.android.material.chip.Chip


// fragment extended property for scope
val Fragment.fragmentViewLifecycleScope: LifecycleCoroutineScope
    get() = this.viewLifecycleOwner.lifecycleScope


fun String.removeLineBreaks(): String {
    return this.replace("\n", " ")
}



