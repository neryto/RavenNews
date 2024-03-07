package com.raven.home.presentation.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap.WRAP
import com.google.android.flexbox.FlexboxLayoutManager
import com.raven.core.CommunicationViewModel
import com.raven.home.R
import com.raven.home.databinding.NewsFragmentBinding
import com.raven.home.presentation.NewsData
import com.raven.home.presentation.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private val newsViewModel: NewsViewModel by viewModels()
    private val communicationViewModel : CommunicationViewModel by activityViewModels()
    private lateinit var binding: NewsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NewsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCollectors()
        newsViewModel.getNews()
    }

    private fun setupCollectors() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    newsViewModel.error.collect { error ->
                        error?.let { notNullError ->
                            showError(notNullError)
                        }
                    }
                }
                launch {
                    newsViewModel.articles.collect { articles ->
                        articles?.let { notNullArticles ->
                            binding.recyclerViewNews.apply {
                                adapter = NewsAdapter(notNullArticles) { item ->
                                    showDetail(item)
                                }
                                layoutManager = FlexboxLayoutManager(
                                    requireContext(),
                                    FlexDirection.COLUMN, WRAP
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun showDetail(item: NewsData.Article) {
        communicationViewModel.navigateToDetail("5")
    }


    private fun showError(error: String) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(getString(R.string.error_title))
        alertDialogBuilder.setMessage(error)
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}
