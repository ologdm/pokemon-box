package com.example.pokemonbox.ui.explore

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.pokemonbox.data.dto.PokemonBaseDto
import com.example.pokemonbox.databinding.VhPokemonBinding
import com.example.pokemonbox.domain.Pokemon

class PokemonVH(
    private val binding: VhPokemonBinding
) : ViewHolder(binding.root) {

    fun bind (item :Pokemon){
        binding.pokemonName.text = item.name
        println("XXX ${item.imageUrl}")
        println("XXX ${item.url}")

        binding.pokemonDescription.text = item.description


        Glide.with(binding.root.context)
            .load(item.imageUrl)
            // placeholder - pallina pikemon
            .into(binding.pokemonImage)


    }


}