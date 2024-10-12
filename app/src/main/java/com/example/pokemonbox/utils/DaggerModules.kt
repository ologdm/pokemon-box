package com.example.pokemonbox.utils

import com.example.pokemonbox.data.PokeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DaggerModules {

    // RETROFIT
    @Provides
    @Singleton
    fun getPokeApi(): PokeApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(PokeApi::class.java)
    }




}

