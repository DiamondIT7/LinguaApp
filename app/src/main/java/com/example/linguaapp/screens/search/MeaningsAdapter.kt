package com.example.linguaapp.screens.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.linguaapp.databinding.MeaningsListItemBinding
import com.example.linguaapp.model.Meaning

class MeaningsAdapter: RecyclerView.Adapter<MeaningsAdapter.MeaningsViewHolder>() {

    var listMeanings = emptyList<Meaning>()

    class MeaningsViewHolder(val binding: MeaningsListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningsViewHolder {
        val binding = MeaningsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MeaningsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listMeanings.size
    }

    override fun onBindViewHolder(holder: MeaningsViewHolder, position: Int) {
        val meaning = listMeanings[position]

        holder.binding.tvMeaningsItem.text = meaning.partOfSpeech

        // Pass the list of definitions to the DefinitionsAdapter
        val definitionsAdapter = DefinitionsAdapter(meaning.definitions)

        holder.binding.rvMeaningsItem.setHasFixedSize(true)
        holder.binding.rvMeaningsItem.layoutManager = GridLayoutManager(holder.itemView.context, 1)
        holder.binding.rvMeaningsItem.adapter = definitionsAdapter
    }
}