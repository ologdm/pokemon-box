package com.example.pokemonbox.utils

import com.example.pokemonbox.data.PokeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

// TODO: repository modules


@Module
@InstallIn(SingletonComponent::class)
class DaggerModules {

    // RETROFIT
    @Provides
    @Singleton
    fun getPokeApi(): PokeApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create()) // TODO: modificare con serializable
            // no header
            .build()

        return retrofit.create(PokeApi::class.java)
    }




}

