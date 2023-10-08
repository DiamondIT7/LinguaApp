package com.example.linguaapp.screens.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.linguaapp.databinding.DefinitionsListItemBinding
import com.example.linguaapp.model.Definition

class DefinitionsAdapter: RecyclerView.Adapter<DefinitionsAdapter.DefinitionsViewHolder>() {

    private var listDefinitions = emptyList<Definition>()

    class DefinitionsViewHolder(val binding: DefinitionsListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionsViewHolder {
        val binding = DefinitionsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DefinitionsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listDefinitions.size
    }

    override fun onBindViewHolder(holder: DefinitionsViewHolder, position: Int) {
        holder.binding.tvDefinitionItem.text = "Definition: ${listDefinitions[position].definition}"
        holder.binding.tvExampleDefinition.text = "Example: ${listDefinitions[position].example}"

        val synonyms = StringBuilder()
        val antonyms = StringBuilder()

        synonyms.append(listDefinitions[position].synonyms)
        antonyms.append(listDefinitions[position].antonyms)

        holder.binding.tvExampleSynonyms.text = synonyms
        holder.binding.tvExampleAntonyms.text = antonyms

        holder.binding.tvExampleSynonyms.isSelected = true
        holder.binding.tvExampleAntonyms.isSelected = true
    }
}