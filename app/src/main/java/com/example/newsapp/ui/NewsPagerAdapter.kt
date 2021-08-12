package com.example.newsapp.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.newsapp.ui.everything.EverythingFragment
import com.example.newsapp.ui.top_headlines.TopHeadlinesFragment

class NewsPagerAdapter(
    val fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                TopHeadlinesFragment()
            }
            1 -> {
                EverythingFragment()
            }
            else -> {
                throw UnsupportedOperationException()
            }
        }
    }
}