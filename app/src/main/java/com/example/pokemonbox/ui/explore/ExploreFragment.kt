package com.example.pokemonbox.ui.explore

import android.graphics.drawable.GradientDrawable.Orientation
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonbox.databinding.FragmentPokemonBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExploreFragment : Fragment() {

    private var _binding: FragmentPokemonBinding? = null
    private val binding get() = _binding

    private val viewmodel by viewModels <ExploreViewmodel>()

    private val adapter = AllPokemonPagingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        binding?.recyclerView?.adapter = adapter
        binding?.recyclerView?.layoutManager = LinearLayoutManager(requireContext())

        // observer - unico, multiplo?
        viewLifecycleOwner.lifecycleScope.launch {
            viewmodel.statePaging.collectLatest {pagingData->
                adapter.submitData(pagingData)
                val x = pagingData.map {
                   it
                }


                }
            }
        }

    }
