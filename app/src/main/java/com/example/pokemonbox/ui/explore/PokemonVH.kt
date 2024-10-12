package com.example.pokemonbox.ui.explore

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.pokemonbox.R
import com.example.pokemonbox.databinding.VhPokemonBinding
import com.example.pokemonbox.domain.Pokemon

class PokemonVH(
    private val binding: VhPokemonBinding
) : ViewHolder(binding.root) {

    private val context = binding.root.context

    fun bind(item: Pokemon) {
        binding.pokemonName.text = item.name.replaceFirstChar { it.uppercaseChar() }
        binding.pokemonDescription.text = item.description

        Glide.with(binding.root.context)
            .load(item.imageUrl)
            .placeholder(R.drawable.pokemon_placeholder)
            .into(binding.pokemonImage)


        binding.typesChipGroup.removeAllViews()
        item.types.forEach { type ->
            val chip = ExploreFragment.getPokemonTypeChip(context, type)
            binding.typesChipGroup.addView(chip)
        }
    }


}