package com.example.newsapp.ui.article_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentArticleDetailsBinding

class ArticleDetailsFragment : Fragment(R.layout.fragment_article_details) {

    val args: ArticleDetailsFragmentArgs by navArgs()
    private var binding: FragmentArticleDetailsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentArticleDetailsBinding.bind(view)

        val url = args.url

        binding?.apply {
            webview.loadUrl(url)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}