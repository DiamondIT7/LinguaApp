package com.example.linguaapp.screens.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.linguaapp.databinding.ActivitySettingsBinding
import com.example.linguaapp.model.SWord

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

        binding.btBack.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
    }
}