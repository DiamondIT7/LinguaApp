package com.example.linguaapp.screens.settings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.linguaapp.databinding.ActivitySettingsBinding
import com.example.linguaapp.model.SWord
import com.example.linguaapp.screens.display.DisplayActivity
import com.google.gson.Gson

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var settingsAdapter: SettingsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val wordList = intent.getParcelableArrayListExtra<SWord>("wordList")

        settingsAdapter = SettingsAdapter(wordList ?: mutableListOf())
        binding.rvSettingsList.apply {
            layoutManager = LinearLayoutManager(this@SettingsActivity)
            adapter = settingsAdapter
        }

        binding.btStart.setOnClickListener {
            binding.btStart.setOnClickListener {
                val selectedWordsWithQuantities = settingsAdapter.getSelectedWordsWithQuantities()
                val wordsWithQuantities = mutableListOf<SWord>()

                // Create a list with words repeated according to their quantities
                for ((word, quantity) in selectedWordsWithQuantities) {
                    repeat(quantity) {
                        wordsWithQuantities.add(SWord(word, 1))
                    }
                }

                // Shuffle the list
                wordsWithQuantities.shuffle()

                // Serialize the list and pass it as a string extra
                val wordsWithQuantitiesString = Gson().toJson(wordsWithQuantities)
                val intent = Intent(this, DisplayActivity::class.java)
                intent.putExtra("wordsWithQuantities", wordsWithQuantitiesString)
                startActivity(intent)
            }
        }


        binding.btBack.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
    }
}