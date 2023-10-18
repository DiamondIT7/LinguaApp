package com.example.linguaapp.screens.display

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.linguaapp.databinding.ActivityDisplayBinding
import com.example.linguaapp.model.SWord
import com.example.linguaapp.screens.search.MeaningsAdapter
import com.example.linguaapp.screens.search.PhoneticsAdapter
import com.example.linguaapp.screens.search.SearchViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DisplayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDisplayBinding
    private lateinit var phoneticsAdapter: PhoneticsAdapter
    private lateinit var meaningsAdapter: MeaningsAdapter
    private var wordsWithQuantities: MutableList<SWord> = mutableListOf()
    private var currentWordIndex: Int = 0
    private var wordsToDisplay: MutableList<SWord> = mutableListOf()
    private lateinit var wordsToDisplayCopy: MutableList<SWord>
    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        phoneticsAdapter = PhoneticsAdapter()
        meaningsAdapter = MeaningsAdapter()

        // Get the list of words with quantities
        val wordsWithQuantitiesString = intent.getStringExtra("wordsWithQuantities")
        wordsWithQuantities = Gson().fromJson(wordsWithQuantitiesString, object : TypeToken<List<SWord>>() {}.type)

        // Create a copy of the list for display
        wordsToDisplay.addAll(wordsWithQuantities)
        wordsToDisplay.shuffle()
        wordsToDisplayCopy = mutableListOf()

        // Display the first word
        displayNextWord()

        binding.btNext.setOnClickListener {
            // Display the next word
            displayNextWord()
        }

        binding.btPrevious.setOnClickListener {
            if (currentWordIndex > 0) {
                // Go back to the previous word
                currentWordIndex--
                displayWordInfo(wordsWithQuantities[currentWordIndex])
            }
        }
    }

    // Function to display the next word
    private fun displayNextWord() {
        if (wordsToDisplay.isNotEmpty()) {
            val word = wordsToDisplay[0]
            displayWordInfo(word)
            if (word.quantity == 1) {
                wordsToDisplay.removeAt(0)
            } else {
                word.quantity--
            }
        } else {
            if (wordsToDisplayCopy.isNotEmpty()) {
                wordsToDisplay.addAll(wordsToDisplayCopy)
                wordsToDisplayCopy.clear()
                displayNextWord()
            } else {
                // No more words to display
                finish()
            }
        }
    }

    // Function to display word and its information
    private fun displayWordInfo(word: SWord) {
        binding.tvWordMain.text = word.name

        // Retrieve word information using the SearchViewModel
        viewModel.searchWord(word.name)

        // Set up RecyclerViews for phonetics and meanings (similar to your SearchFragment logic)
        binding.rvPhonetics.apply {
            layoutManager = LinearLayoutManager(this@DisplayActivity)
            adapter = phoneticsAdapter
            isNestedScrollingEnabled = false
        }

        binding.rvMeanings.apply {
            layoutManager = LinearLayoutManager(this@DisplayActivity)
            adapter = meaningsAdapter
            isNestedScrollingEnabled = false
        }

        // Observe changes in word details and update the meaningsAdapter
        viewModel.wordDetails.observe(this) { wordDetails ->
            wordDetails?.let { apiResponseList ->
                val meanings = apiResponseList.flatMap { apiResponseItem ->
                    apiResponseItem.meanings
                }

                val phonetics = apiResponseList.flatMap { apiResponseItem ->
                    apiResponseItem.phonetics
                }

                meaningsAdapter.listMeanings = meanings // Update the list in your adapter
                meaningsAdapter.notifyDataSetChanged()

                // Update the phonetics adapter with the new data
                phoneticsAdapter.listPhonetics = phonetics
                phoneticsAdapter.notifyDataSetChanged() // Notify the phoneticsAdapter that the data has changed()
            }
        }
    }
}