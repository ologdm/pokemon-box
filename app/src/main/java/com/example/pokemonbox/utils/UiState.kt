package com.example.pokemonbox.utils

data class UiState<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val isError: Boolean = false,
)