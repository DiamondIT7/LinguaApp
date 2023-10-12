package com.example.linguaapp.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.linguaapp.databinding.CardListItemBinding
import com.example.linguaapp.model.Card

class CardsAdapter(private val onItemClickListener: OnItemClickListener): RecyclerView.Adapter<CardsAdapter.CardsViewHolder>() {

    var listCards = emptyList<Card>()

    interface OnItemClickListener {
        fun onItemClick(card: Card)
    }

    class CardsViewHolder(val binding: CardListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        val binding = CardListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listCards.size
    }

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        holder.binding.tvCollectionName.text = listCards[position].name
        holder.binding.tvCollectionSize.text = "${listCards[position].size} words"

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(listCards[position])
        }
    }

    fun setCardList(cards: List<Card>) {
        listCards = cards
        notifyDataSetChanged() // Notify the adapter that the data has changed
    }
}