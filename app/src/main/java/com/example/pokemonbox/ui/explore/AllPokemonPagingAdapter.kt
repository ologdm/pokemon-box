package com.example.pokemonbox.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.pokemonbox.databinding.VhPokemonBinding
import com.example.pokemonbox.domain.Pokemon

class AllPokemonPagingAdapter(
    private val onClick: (String) -> Unit
) : PagingDataAdapter<Pokemon, PokemonVH>(AllPokemonPagingAdapter) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val bindingVh = VhPokemonBinding.inflate(layoutInflater, parent, false)
        return PokemonVH(bindingVh)
    }


    override fun onBindViewHolder(holder: PokemonVH, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)

        holder.itemView.setOnClickListener {
            onClick(item.name)
        }


    }


    companion object : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }

    }


}