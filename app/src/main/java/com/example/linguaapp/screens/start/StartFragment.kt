package com.example.linguaapp.screens.start

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.linguaapp.databinding.FragmentStartBinding
import com.example.linguaapp.model.Card
import com.example.linguaapp.model.SWord
import com.example.linguaapp.screens.CardsAdapter
import com.example.linguaapp.screens.settings.SettingsActivity

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private lateinit var cardsAdapter: CardsAdapter
    private val cardList = listOf(
        Card("Fruits", 7),
        Card("Study", 5)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)

        // Initialize cardsAdapter with item click listener
        cardsAdapter = CardsAdapter(object : CardsAdapter.OnItemClickListener {
            override fun onItemClick(card: Card) {
                val wordList = when (card.name) {
                    "Fruits" -> {
                        mutableListOf(
                            SWord("Apple", 1),
                            SWord("Banana", 2),
                            SWord("Watermelon", 5),
                            SWord("Pear", 3),
                            SWord("Grape", 2),
                            SWord("Pamella", 4),
                            SWord("Fruit", 1)
                        )
                    }
                    "Study" -> {
                        mutableListOf(
                            SWord("Math", 3),
                            SWord("Pen", 7),
                            SWord("Literature", 5),
                            SWord("Physics", 2),
                            SWord("Classmate", 2)
                        )
                    }
                    else -> emptyList()
                }

                val intent = Intent(requireContext(), SettingsActivity::class.java)
                intent.putParcelableArrayListExtra("wordList", ArrayList(wordList))
                startActivity(intent)
            }
        })

        cardsAdapter.setCardList(cardList)

        binding.rvStartCards.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cardsAdapter
        }

        return binding.root
    }
}