package com.example.pokemonbox.utils

data class UiUtils<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val isError: Boolean = false,
)
