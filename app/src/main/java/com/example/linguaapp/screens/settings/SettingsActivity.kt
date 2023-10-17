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
            val selectedWordsString = Gson().toJson(settingsAdapter.getSelectedWordsWithQuantities())
            val displayIntent = Intent(this, DisplayActivity::class.java)
            displayIntent.putExtra("selectedWords", selectedWordsString)
            startActivity(displayIntent)
        }


        binding.btBack.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
    }
}