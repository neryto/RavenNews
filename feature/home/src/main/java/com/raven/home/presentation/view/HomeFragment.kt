package com.raven.home.presentation.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap.WRAP
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.raven.home.R
import com.raven.home.databinding.DetailItemBinding
import com.raven.home.databinding.HomeFragmentBinding
import com.raven.home.presentation.NewsData
import com.raven.home.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel : HomeViewModel by viewModels()
    private lateinit var binding : HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCollectors()
        homeViewModel.getNews()
    }

    private fun setupCollectors() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                launch {
                    homeViewModel.error.collect{error->
                        error?.let { notNullError->
                            showError(notNullError)
                        }
                    }
                }
                launch {
                    homeViewModel.articles.collect{articles->
                        articles?.let { notNullArticles->
                            binding.recyclerViewNews.apply {
                                adapter = NewsAdapter(notNullArticles){item ->
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
        BottomSheetDialog(requireContext()).apply {
            val view: View = layoutInflater
                .inflate(R.layout.detail_item, this@HomeFragment.binding.root, false)
            val binding = DetailItemBinding.bind(view).apply {
                tvTitle.text = item.title
                tvBy.text = item.byline
                tvDate.text = getString(R.string.published_date,item.publishedDate)
                tvAbstract.text = item.abstract
            }
            setCancelable(true)
            setContentView(binding.root)
            show()
        }
    }


    private fun showError(error: String) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(getString(R.string.error_title))
        alertDialogBuilder.setMessage(error)
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}
