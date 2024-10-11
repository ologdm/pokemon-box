package com.example.pokemonbox.utils


fun String.removeLineBreaks(): String {
    return this.replace("\n", " ")
}


data class UiState<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val isError: Boolean = false,
)
