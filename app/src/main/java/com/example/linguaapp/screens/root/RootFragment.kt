package com.example.linguaapp.screens.root

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.linguaapp.R
import com.example.linguaapp.databinding.FragmentRootBinding
import com.google.android.material.tabs.TabLayoutMediator

class RootFragment : Fragment() {

    private lateinit var binding: FragmentRootBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRootBinding.inflate(inflater, container, false)

        binding.tlRoot.tabIconTint = null
        binding.vpRoot.adapter = ViewPagerAdapter(requireActivity())

        TabLayoutMediator(binding.tlRoot, binding.vpRoot) { tab, pos ->
            when (pos) {
                0 -> tab.setIcon(R.drawable.start_icon)
                1 -> tab.setIcon(R.drawable.search_icon)
                2 -> tab.setIcon(R.drawable.personal_icon)
            }
        }.attach()

        return binding.root
    }
}