package com.example.pokemonbox.ui.explore

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonbox.R
import com.example.pokemonbox.databinding.FragmentPokemonBinding
import com.example.pokemonbox.utils.fragmentViewLifecycleScope
import com.example.pokemonbox.utils.viewBinding
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
            adapter.loadStateFlow.collectLatest {loadStates->
                val isSearchMode = viewmodel.isSearchingFlow.first() // true if notBlank

                binding.loadingBar.isVisible =
                    ((loadStates.refresh == LoadState.Loading) && (!isSearchMode))
                binding.errorText.isVisible =
                    (loadStates.hasError && !isSearchMode)


            }
        }


        // SEARCH
        binding.searchInputEditText.doAfterTextChanged { s ->
            viewmodel.updateQuery(s.toString())
        }

        fragmentViewLifecycleScope.launch {
            viewmodel.isSearchingFlow
                .collectLatest { isText ->
                    binding.recyclerView.isVisible = !isText
                    binding.searchResultLayout.root.isVisible = isText
                    binding.errorText.isVisible = false
                    binding.loadingBar.isVisible = false
                }
        }

        // search result
        fragmentViewLifecycleScope.launch {
            viewmodel.searchResultStatus.collectLatest { uiState ->
                binding.searchResultLayout.divider.isVisible = false
                val isSearchMode = viewmodel.isSearchingFlow.first()
                binding.loadingBar.isVisible = (uiState.isLoading && isSearchMode)
                binding.errorText.isVisible = (uiState.isError && isSearchMode)

                binding.searchResultLayout.root.isVisible = (uiState.data != null)
                binding.searchResultLayout.pokemonName.text = uiState.data?.name.orEmpty()
                binding.searchResultLayout.pokemonDescription.text =
                    uiState.data?.description.orEmpty()
            }
        }


    }

}
