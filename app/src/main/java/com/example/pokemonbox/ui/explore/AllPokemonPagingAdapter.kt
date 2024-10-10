package com.example.pokemonbox.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.pokemonbox.data.dto.PokemonBaseDto
import com.example.pokemonbox.databinding.VhPokemonBinding

class AllPokemonPagingAdapter
    : PagingDataAdapter<PokemonBaseDto, PokemonVH>(AllPokemonPagingAdapter) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val bindingVh = VhPokemonBinding.inflate(layoutInflater, parent, false)
        return PokemonVH(bindingVh)
    }

    override fun onBindViewHolder(holder: PokemonVH, position: Int) {
        val item = getItem(position)


        holder.bind(item!!)


    }


    companion object : DiffUtil.ItemCallback<PokemonBaseDto>() {
        override fun areItemsTheSame(oldItem: PokemonBaseDto, newItem: PokemonBaseDto): Boolean {
            return oldItem.url == newItem.url // url contiene id univoco
        }

        override fun areContentsTheSame(oldItem: PokemonBaseDto, newItem: PokemonBaseDto): Boolean {
            return oldItem == newItem
        }

    }


}