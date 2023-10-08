package com.example.linguaapp.screens.search

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.linguaapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var phoneticsAdapter: PhoneticsAdapter
    private lateinit var meaningsAdapter: MeaningsAdapter
    private lateinit var viewModel: SearchViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        phoneticsAdapter = PhoneticsAdapter() // Initialize phoneticsAdapter
        meaningsAdapter = MeaningsAdapter() // Initialize meaningsAdapter

        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        binding.rvPhonetics.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = phoneticsAdapter
            isNestedScrollingEnabled = false
        }

        binding.rvMeanings.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = meaningsAdapter
            isNestedScrollingEnabled = false
        }

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query.isNotBlank()) {
                    // Call the ViewModel to search for the word
                    viewModel.searchWord(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Handle query text change if needed
                return false
            }
        })

        // Observe changes in word details and update the meaningsAdapter
        viewModel.wordDetails.observe(viewLifecycleOwner) { wordDetails ->
            // Check if wordDetails is not null and not empty
            wordDetails?.takeIf { it.isNotEmpty() }?.let { apiResponseList ->
                val meanings = apiResponseList.flatMap { apiResponseItem ->
                    apiResponseItem.meanings
                }

                val phonetics = apiResponseList.flatMap { apiResponseItem ->
                    apiResponseItem.phonetics
                }

                meaningsAdapter.listMeanings = meanings // Update the list in your adapter
                meaningsAdapter.notifyDataSetChanged() // Notify the adapter that the data has changed

                // Update the phonetics adapter with the new data
                phoneticsAdapter.listPhonetics = phonetics // Update the list in your phoneticsAdapter
                phoneticsAdapter.notifyDataSetChanged() // Notify the phoneticsAdapter that the data has changed

                // Update the word text view
                binding.tvWordMain.text = binding.searchBar.query // Set the user's search query as the word
            }
        }


        return binding.root
    }
}