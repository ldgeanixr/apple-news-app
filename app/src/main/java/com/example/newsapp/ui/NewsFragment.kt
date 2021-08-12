package com.example.newsapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsBinding
import com.google.android.material.tabs.TabLayoutMediator

class NewsFragment : Fragment(R.layout.fragment_news) {

    private var binding: FragmentNewsBinding? = null
    private lateinit var pagerAdapter: NewsPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)

        pagerAdapter = NewsPagerAdapter(this)

        setupPager()
    }

    private fun setupPager() {
        binding?.apply {
            viewPager.adapter = pagerAdapter
            viewPager.isUserInputEnabled = false
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when (position) {
                    0 -> {
                        tab.setText(R.string.top_headlines)
                        tab.setIcon(R.drawable.ic_flame)
                    }
                    1 -> {
                        tab.setText(R.string.everything)
                    }
                }
            }.attach()
            viewPager.setCurrentItem(0, false)
        }

    }
}