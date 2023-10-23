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
import com.example.linguaapp.screens.settings.SettingsActivity

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private lateinit var cardsAdapter: CardsAdapter
    private val cardList = listOf(
        Card("Fruits and berries", 17),
        Card("School", 20),
        Card("Programming", 21),
        Card("Tourism", 20)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)

        cardsAdapter = CardsAdapter(object : CardsAdapter.OnItemClickListener {
            override fun onItemClick(card: Card) {
                val wordList = when (card.name) {
                    "Fruits and berries" -> {
                        mutableListOf(
                            SWord("Apple"),
                            SWord("Banana"),
                            SWord("Watermelon"),
                            SWord("Pear"),
                            SWord("Grape"),
                            SWord("Strawberry"),
                            SWord("Fruit"),
                            SWord("Melon"),
                            SWord("Orange"),
                            SWord("Kiwi"),
                            SWord("Cherry"),
                            SWord("Currant"),
                            SWord("Raspberry"),
                            SWord("Blackberry"),
                            SWord("Berry"),
                            SWord("Gooseberry"),
                            SWord("Lemon"),
                        )
                    }
                    "School" -> {
                        mutableListOf(
                            SWord("Math"),
                            SWord("Algebra"),
                            SWord("Geometry"),
                            SWord("Pen"),
                            SWord("Literature"),
                            SWord("Physics"),
                            SWord("Classmate"),
                            SWord("Chemistry"),
                            SWord("Grade"),
                            SWord("Homework"),
                            SWord("Biology"),
                            SWord("Teacher"),
                            SWord("Professor"),
                            SWord("Geography"),
                            SWord("Informatics"),
                            SWord("Pencil"),
                            SWord("Art"),
                            SWord("Eraser"),
                            SWord("Classroom"),
                            SWord("Exam")
                        )
                    }
                    "Programming" -> {
                        mutableListOf(
                            SWord("Programming"),
                            SWord("Code"),
                            SWord("Array"),
                            SWord("Variable"),
                            SWord("Processor"),
                            SWord("Integer"),
                            SWord("App"),
                            SWord("Developer"),
                            SWord("User"),
                            SWord("Server"),
                            SWord("Crash"),
                            SWord("Technology"),
                            SWord("Interface"),
                            SWord("Algorithm"),
                            SWord("Error"),
                            SWord("Designer"),
                            SWord("Feature"),
                            SWord("Bug"),
                            SWord("Development"),
                            SWord("Deadline"),
                            SWord("Thread")
                        )
                    }
                    "Tourism" -> {
                        mutableListOf(
                            SWord("Tourism"),
                            SWord("Tourist"),
                            SWord("Bag"),
                            SWord("Luggage"),
                            SWord("Passport"),
                            SWord("Visa"),
                            SWord("Airport"),
                            SWord("Adventure"),
                            SWord("Destination"),
                            SWord("Memorial"),
                            SWord("Excursion"),
                            SWord("Restaurant"),
                            SWord("Hotel"),
                            SWord("Taxi"),
                            SWord("Map"),
                            SWord("Guide"),
                            SWord("Hitchhike"),
                            SWord("Culture"),
                            SWord("Locals"),
                            SWord("World")
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