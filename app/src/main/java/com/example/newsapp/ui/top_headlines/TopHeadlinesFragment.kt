package com.example.newsapp.ui.top_headlines

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.data.models.Article
import com.example.newsapp.databinding.FragmentTopHeadlinesBinding
import com.example.newsapp.ui.ArticlesAdapter
import com.example.newsapp.ui.NewsFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopHeadlinesFragment : Fragment(R.layout.fragment_top_headlines) {
    private val viewModel: TopHeadlinesViewModel by viewModel()

    private var binding: FragmentTopHeadlinesBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTopHeadlinesBinding.bind(view)

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
        }


        viewModel.articles.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }


        viewModel.refresh.observe(viewLifecycleOwner) {
            adapter.refresh()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}