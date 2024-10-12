package com.example.pokemonbox.ui.explore

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.pokemonbox.R
import com.example.pokemonbox.databinding.VhPokemonBinding
import com.example.pokemonbox.domain.Pokemon
import com.google.android.material.chip.Chip

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
            val chip = Chip(context).apply {
                text = type.replaceFirstChar { it.uppercaseChar() }
                textSize = 18f
                isClickable = false
                isCheckable = false

                setChipBackgroundColorResource(R.color.my_background_color)
                setTextColor(context.getColor(R.color.chip_text_color))
                chipCornerRadius = 24f
                chipStrokeWidth = 1f
                chipStrokeColor = context.getColorStateList(R.color.my_background_color)
                setEnsureMinTouchTargetSize(false)

                chipStartPadding = 4f
                chipEndPadding = 4f
                setPadding(2,2,2,2)

            }
            binding.typesChipGroup.addView(chip)
        }

    }


}