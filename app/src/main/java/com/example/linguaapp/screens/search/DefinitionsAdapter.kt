package com.example.linguaapp.screens.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.linguaapp.R
import com.example.linguaapp.databinding.DefinitionsListItemBinding
import com.example.linguaapp.model.Definition

class DefinitionsAdapter(private val definitions: List<Definition>): RecyclerView.Adapter<DefinitionsAdapter.DefinitionsViewHolder>() {

    class DefinitionsViewHolder(val binding: DefinitionsListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionsViewHolder {
        val binding = DefinitionsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DefinitionsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return definitions.size
    }

    override fun onBindViewHolder(holder: DefinitionsViewHolder, position: Int) {
        val definition = definitions[position]

        holder.binding.tvDefinitionItem.text = "Definition: ${definition.definition}"
        if (definition.example == null) {
            holder.binding.tvExampleDefinition.text = "No examples found"
        } else {
            holder.binding.tvExampleDefinition.text = "Example: ${definition.example}"
        }

        if (definition.synonyms.isEmpty()){
            holder.binding.tvExampleSynonyms.visibility = View.GONE
            holder.binding.tvSynonymsItem.visibility = View.GONE
        } else {
            holder.binding.tvExampleSynonyms.text = "${definition.synonyms}"
        }

        if (definition.antonyms.isEmpty()){
            holder.binding.tvExampleAntonyms.visibility = View.GONE
            holder.binding.tvAntonymsItem.visibility = View.GONE
        } else {
            holder.binding.tvExampleAntonyms.text = "${definition.antonyms}"
        }

        holder.binding.tvExampleSynonyms.isSelected = true
        holder.binding.tvExampleAntonyms.isSelected = true
    }
}