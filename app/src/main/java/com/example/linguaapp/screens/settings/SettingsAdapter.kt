package com.example.linguaapp.screens.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.linguaapp.databinding.SettingsListItemBinding
import com.example.linguaapp.model.SWord

class SettingsAdapter(private val listSettings: MutableList<SWord>): RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>() {

    class SettingsViewHolder(val binding: SettingsListItemBinding) : RecyclerView.ViewHolder(binding.root)

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
        numberPicker.minValue = 1
        numberPicker.maxValue = 10
        numberPicker.wrapSelectorWheel = false

        // Handle NumberPicker value changes
        numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            // Update the quantity in the SWord object when a new value is selected
            word.quantity = newVal
        }
    }
}