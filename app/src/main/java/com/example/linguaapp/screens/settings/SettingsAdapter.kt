package com.example.linguaapp.screens.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.linguaapp.databinding.SettingsListItemBinding
import com.example.linguaapp.model.SWord

class SettingsAdapter(private val listSettings: MutableList<SWord>): RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>() {

    class SettingsViewHolder(val binding: SettingsListItemBinding) : RecyclerView.ViewHolder(binding.root)

    // Create a mutable map to store selected words with their quantities
    private val selectedWordsWithQuantities: MutableMap<String, Int> = mutableMapOf()

    fun getSelectedWordsWithQuantities(): Map<String, Int> {
        return selectedWordsWithQuantities
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val binding = SettingsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SettingsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listSettings.size
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        val word = listSettings[position]
        holder.binding.tvWordName.text = word.name

        val numberPicker = holder.binding.npWordQuantity
        numberPicker.minValue = 0
        numberPicker.maxValue = 5
        numberPicker.wrapSelectorWheel = true

        // Initially set the value to the word's quantity
        numberPicker.value = selectedWordsWithQuantities[word.name] ?: 0 // Use the stored value if available

        // Handle NumberPicker value changes
        numberPicker.setOnValueChangedListener { _, _, newVal ->
            // Update the selectedWordsWithQuantities map
            selectedWordsWithQuantities[word.name] = newVal
        }
    }
}