package com.example.pokemonbox.ui.explore

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.pokemonbox.R
import com.example.pokemonbox.databinding.FragmentPokemonBinding
import com.example.pokemonbox.domain.Pokemon
import com.example.pokemonbox.utils.fragmentViewLifecycleScope
import com.example.pokemonbox.utils.viewBinding
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExploreFragment : Fragment(R.layout.fragment_pokemon) {

    private val binding by viewBinding { FragmentPokemonBinding.bind(it) }
    private val viewmodel by viewModels<ExploreViewmodel>()
    private val adapter = AllPokemonPagingAdapter()


    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // PAGING LIST
        fragmentViewLifecycleScope.launch {
            viewmodel.statePaging.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }

        fragmentViewLifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                val isSearchMode = viewmodel.isSearchingFlow.first() // true if notBlank

                binding.loadingBar.isVisible =
                    ((loadStates.refresh == LoadState.Loading) && (!isSearchMode))

                if (loadStates.hasError && !isSearchMode){
                    binding.errorText.isVisible = true
                    binding.errorText.text = getString(R.string.something_went_wrong)
                }
            }
        }


        // SEARCH
        // search input
        binding.searchInputEditText.doAfterTextChanged { s ->
            viewmodel.updateQuery(s.toString())
        }

        // search text state
        fragmentViewLifecycleScope.launch {
            viewmodel.isSearchingFlow
                .collectLatest { isText ->
                    // alternates between RecyclerView and SearchResultLayout
                    binding.recyclerView.isVisible = !isText
                    binding.searchResultLayout.root.isVisible = isText
                    // reset state views
                    binding.errorText.isVisible = false
                    binding.loadingBar.isVisible = false
                }
        }

        // search result
        fragmentViewLifecycleScope.launch {
            viewmodel.searchResultStatus.collectLatest { uiState ->
                binding.searchResultLayout.divider.isVisible = false

                val isSearchMode = viewmodel.isSearchingFlow.first()
                // search loading
                binding.loadingBar.isVisible = (uiState.isLoading && isSearchMode)
                // search error
                if (uiState.isError && isSearchMode){
                    binding.errorText.isVisible = true
                    binding.errorText.text = getString(R.string.no_pokemon_found)
                }
                binding.searchResultLayout.root.isVisible =
                    (uiState.data != null)

                updateSearchResultLayout(uiState.data)
            }
        }
    }



    private fun updateSearchResultLayout (pokemon: Pokemon?){
        // text
        binding.searchResultLayout.pokemonName.text = pokemon?.name.orEmpty()
        binding.searchResultLayout.pokemonDescription.text =
            pokemon?.description.orEmpty()
        // chips
        binding.searchResultLayout.typesChipGroup.removeAllViews()
        pokemon?.types?.forEach { type ->
            val chip = getPokemonTypeChip(requireContext(), type)
            binding.searchResultLayout.typesChipGroup.addView(chip)
        }
        // image
        Glide.with(requireContext())
            .load(pokemon?.imageUrl)
            .into(binding.searchResultLayout.pokemonImage)
    }


    companion object{
        fun getPokemonTypeChip(context: Context, type: String) : Chip {
            return Chip(context).apply {
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
                setPadding(2, 2, 2, 2)
            }

        }
    }

}
