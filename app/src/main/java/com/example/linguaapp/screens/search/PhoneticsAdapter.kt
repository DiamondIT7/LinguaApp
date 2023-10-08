package com.example.linguaapp.screens.search

import android.media.AudioManager
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.linguaapp.databinding.PhoneticListItemBinding
import com.example.linguaapp.model.Phonetic

class PhoneticsAdapter : RecyclerView.Adapter<PhoneticsAdapter.PhoneticsViewHolder>() {

    private var listPhonetics = emptyList<Phonetic>()

    class PhoneticsViewHolder(val binding: PhoneticListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneticsViewHolder {
        val binding = PhoneticListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhoneticsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listPhonetics.size
    }

    override fun onBindViewHolder(holder: PhoneticsViewHolder, position: Int) {
        holder.binding.tvPhoneticsItem.text = listPhonetics[position].text
        holder.binding.ibPhoneticsItem.setOnClickListener {
            val player = MediaPlayer()
            try {
                player.setAudioStreamType(AudioManager.STREAM_MUSIC)
                player.setDataSource("https:${listPhonetics[position].audio}")
                player.prepare()
                player.start()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(holder.itemView.context, "Couldn't play audio!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}