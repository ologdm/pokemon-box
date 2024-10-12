package com.example.pokemonbox.utils

import android.content.Context
import com.example.pokemonbox.R
import com.google.android.material.chip.Chip

data class UiUtils<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val isError: Boolean = false,
)
