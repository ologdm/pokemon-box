package com.example.pokemonbox.ui.explore

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.pokemonbox.data.dto.PokemonBaseDto
import com.example.pokemonbox.databinding.VhPokemonBinding

class PokemonVH(
    private val binding: VhPokemonBinding
) : ViewHolder(binding.root) {

    fun bind (item :PokemonBaseDto){
        binding.pokemonName.text = item.name
        println("XXX ${item.imageUrl}")
        println("XXX ${item.url}")
        Glide.with(binding.root.context)
            .load(item.imageUrl)
            // placeholder - pallina pikemon
            .into(binding.pokemonImage)


    }


}