package com.example.pokemonbox.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonbox.R
import com.example.pokemonbox.databinding.FragmentPokemonBinding
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

        binding?.recyclerView?.adapter = adapter
        binding?.recyclerView?.layoutManager = LinearLayoutManager(requireContext())

        // PAGING LIST
        viewLifecycleOwner.lifecycleScope.launch {
            viewmodel.statePaging.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest {
                val isSearchMode = viewmodel.isSearchingFlow.first()
                binding?.loadingBar?.isVisible = it.refresh == LoadState.Loading && !isSearchMode
                binding?.errorText?.isVisible = it.hasError && !isSearchMode
            }
        }

        // STATO STRING RICERCA
        viewLifecycleOwner.lifecycleScope.launch {
            viewmodel.isSearchingFlow
                .collectLatest { isText->
                    binding?.recyclerView?.isVisible = !isText
                    binding?.searchResultLayout?.root?.isVisible = isText
                    binding?.errorText?.isVisible = false
                    binding?.loadingBar?.isVisible = false
                }
        }

        // SEARCH RESULT
        viewLifecycleOwner.lifecycleScope.launch {
            viewmodel.searchResultStatus.collectLatest {
                binding?.searchResultLayout?.divider?.isVisible = false
                val isSearchMode = viewmodel.isSearchingFlow.first()
                binding?.loadingBar?.isVisible = it.isLoading && isSearchMode
                binding?.errorText?.isVisible = it.isError && isSearchMode

                binding?.searchResultLayout?.root?.isVisible = it.data != null
                binding?.searchResultLayout?.pokemonName?.text = it.data?.name.orEmpty()
                binding?.searchResultLayout?.pokemonDescription?.text = it.data?.description.orEmpty()
            }
        }



//        viewmodel.loadSearchResult(query) -su viewmodel
        binding?.searchInputEditText?.doAfterTextChanged { s ->
            viewmodel.updateQuery(s.toString())
        }

    }

}
