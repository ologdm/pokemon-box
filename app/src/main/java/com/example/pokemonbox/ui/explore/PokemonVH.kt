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
            // placeholder - pallina pikemon
            .into(binding.pokemonImage)

        binding.typesChipGroup.removeAllViews()
        item.types.forEach { type ->
            val chip = Chip(context).apply {
                text = type
                textSize = 16f
                isClickable = false
                isCheckable = false

                // Design personalizzato
                setChipBackgroundColorResource(com.google.android.material.R.color.material_grey_300)  // Imposta il colore di sfondo
                setTextColor(context.getColor(com.google.android.material.R.color.material_grey_800))  // Imposta il colore del testo
                chipCornerRadius = 24f  // Raggio degli angoli per un design più morbido
                chipStrokeWidth = 1f  // Larghezza del bordo
                chipStrokeColor = context.getColorStateList(com.google.android.material.R.color.material_grey_300)  // Colore del bordo
//                elevation = 4f  // Ombra per dare un effetto di profondità

            }
            binding.typesChipGroup.addView(chip)
        }

    }


}