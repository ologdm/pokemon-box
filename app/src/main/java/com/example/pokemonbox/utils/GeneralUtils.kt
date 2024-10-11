package com.example.pokemonbox.utils


fun String.removeLineBreaks(): String {
    return this.replace("\n", " ")
}

