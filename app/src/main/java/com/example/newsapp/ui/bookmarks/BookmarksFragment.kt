package com.example.newsapp.ui.bookmarks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.data.models.Article
import com.example.newsapp.databinding.FragmentBookmarksBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarksFragment : Fragment(R.layout.fragment_bookmarks) {

    private val viewModel: BookmarksViewModel by viewModel()

    private var binding: FragmentBookmarksBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBookmarksBinding.bind(view)

        val onArticleClicked = { article: Article ->
            findNavController().navigate(
                BookmarksFragmentDirections.toArticleDetailsFragment(article.url)
            )
        }

        val adapter = BookmarksAdapter(onArticleClicked)


        binding?.apply {
            rv.adapter = adapter
        }

        viewModel.tasks.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}