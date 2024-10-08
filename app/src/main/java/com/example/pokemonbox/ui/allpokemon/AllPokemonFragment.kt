package com.example.pokemonbox.ui.allpokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pokemonbox.databinding.FragmentAllpokemonBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllPokemonFragment : Fragment() {

    private var _binding: FragmentAllpokemonBinding? = null
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllpokemonBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }








}