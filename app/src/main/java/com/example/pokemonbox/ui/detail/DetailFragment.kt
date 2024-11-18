package com.example.pokemonbox.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.pokemonbox.R
import com.example.pokemonbox.databinding.FragmentDetailBinding
import com.example.pokemonbox.utils.viewBinding


class DetailFragment : Fragment(R.layout.fragment_pokemon) {

    private val binding by viewBinding { FragmentDetailBinding.bind(it) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments

        bundle?.let {
            binding.name.text = bundle.getString("name_key")
        }

        binding.back.setOnClickListener {
//            getA
        }

    }


    companion object {
        fun create(name: String): DetailFragment {
            val fragment = DetailFragment()
            val bundle = Bundle()
            bundle.putString("name_key", name)
//            bundle.putParcelable("pokemon_key", pokemon)
            fragment.arguments = bundle
            return fragment
        }
    }


}