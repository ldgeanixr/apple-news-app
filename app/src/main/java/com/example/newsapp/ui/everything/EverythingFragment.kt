package com.example.newsapp.ui.everything

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.data.models.Article
import com.example.newsapp.databinding.FragmentEverythingBinding
import com.example.newsapp.ui.ArticlesAdapter
import com.example.newsapp.ui.NewsFragmentDirections
import kotlinx.android.synthetic.main.fragment_everything.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EverythingFragment : Fragment(R.layout.fragment_everything) {
    private val viewModel: EverythingViewModel by viewModel()

    private var binding: FragmentEverythingBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEverythingBinding.bind(view)

        val onBookmarkClicked = { article: Article ->
            viewModel.onBookmarkClicked(article)
        }

        val onArticleClicked = { article: Article ->
            findNavController().navigate(
                NewsFragmentDirections
                    .toArticleDetailsFragment(article.url)
            )
        }

        val adapter = ArticlesAdapter(onArticleClicked, onBookmarkClicked)


        binding?.apply {
            rv.adapter = adapter
            swipeRefresh.setOnRefreshListener {
                adapter.refresh()
            }
        }


        viewModel.articles.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
            swipeRefresh.isRefreshing = false
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}