package com.example.linguaapp.screens.search

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.linguaapp.databinding.PhoneticListItemBinding
import com.example.linguaapp.model.Phonetic

class PhoneticsAdapter : RecyclerView.Adapter<PhoneticsAdapter.PhoneticsViewHolder>() {

    var listPhonetics = emptyList<Phonetic>()

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
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()

                player.setAudioAttributes(audioAttributes)
                player.setDataSource("https:${listPhonetics[position].audio}")
                player.prepareAsync()
                player.setOnPreparedListener {
                    // Start playing when prepared
                    player.start()
                }
                player.setOnCompletionListener {
                    // Release the MediaPlayer when playback is completed
                    player.release()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("MediaPlayer", "Error playing audio: ${e.message}")
                Toast.makeText(holder.itemView.context, "Couldn't play audio!", Toast.LENGTH_SHORT).show()
                player.release() // Release the MediaPlayer in case of an error
            }
        }
    }
}