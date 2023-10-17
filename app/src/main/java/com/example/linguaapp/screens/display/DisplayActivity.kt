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
    private var shuffledWordList: MutableList<SWord> = mutableListOf()
    private var currentWordIndex: Int = 0
    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        phoneticsAdapter = PhoneticsAdapter()
        meaningsAdapter = MeaningsAdapter()

        // Get the shuffled list of words and their quantities from the intent
        val selectedWordsString = intent.getStringExtra("selectedWords")
        val selectedWords: Map<String, Int> = Gson().fromJson(selectedWordsString, object : TypeToken<Map<String, Int>>() {}.type)

        // Extract the words from the map and create a list
        shuffledWordList = selectedWords.map { SWord(it.key, it.value) }.toMutableList()

        if (shuffledWordList.isNotEmpty()) {
            // Shuffle the list of words for randomization
            shuffledWordList.shuffle()

            // Display the first word and its information
            displayWordInfo(shuffledWordList[currentWordIndex])

            // Handle "Next" button click
            binding.btNext.setOnClickListener {
                currentWordIndex++
                if (currentWordIndex < shuffledWordList.size) {
                    // Display the next word
                    displayWordInfo(shuffledWordList[currentWordIndex])
                } else {
                    // No more words to display, return to the previous activity (StartFragment)
                    finish()
                }
            }

            // Handle "Previous" button click
            binding.btPrevious.setOnClickListener {
                if (currentWordIndex > 0) {
                    // Go back to the previous word
                    currentWordIndex--
                    displayWordInfo(shuffledWordList[currentWordIndex])
                }
            }
        } else {
            // Handle the case where no words are available
            finish()
        }
    }

    // Function to display word and its information
    private fun displayWordInfo(word: SWord) {
        binding.tvWordMain.text = word.name

        // Retrieve word information using the SearchViewModel
        viewModel.searchWord(word.name)

        // Observe changes in word details and update the meaningsAdapter
        viewModel.wordDetails.observe(this) { wordDetails ->
            wordDetails?.let { apiResponseList ->
                val meanings = apiResponseList.flatMap { apiResponseItem ->
                    apiResponseItem.meanings
                }

                val phonetics = apiResponseList.flatMap { apiResponseItem ->
                    apiResponseItem.phonetics
                }

                meaningsAdapter.listMeanings = meanings
                meaningsAdapter.notifyDataSetChanged()

                phoneticsAdapter.listPhonetics = phonetics
                phoneticsAdapter.notifyDataSetChanged()

                // Handle quantity for the word
                if (word.quantity > 0) {
                    word.quantity--
                } else {
                    // Remove the word from the shuffled list to prevent further display
                    shuffledWordList.removeAt(currentWordIndex)
                }
            }
        }

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
    }
}