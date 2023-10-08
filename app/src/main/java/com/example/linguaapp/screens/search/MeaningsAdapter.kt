package com.example.linguaapp.screens.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.linguaapp.databinding.MeaningsListItemBinding
import com.example.linguaapp.model.Meaning

class MeaningsAdapter: RecyclerView.Adapter<MeaningsAdapter.MeaningsViewHolder>() {

    private var listMeanings = emptyList<Meaning>()

    class MeaningsViewHolder(val binding: MeaningsListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningsViewHolder {
        val binding = MeaningsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MeaningsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listMeanings.size
    }

    override fun onBindViewHolder(holder: MeaningsViewHolder, position: Int) {
        holder.binding.tvMeaningsItem.text = listMeanings[position].partOfSpeech
        holder.binding.rvMeaningsItem.setHasFixedSize(true)
        holder.binding.rvMeaningsItem.layoutManager = GridLayoutManager(holder.itemView.context, 1)
    }
}