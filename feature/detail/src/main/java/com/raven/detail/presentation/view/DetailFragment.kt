package com.raven.detail.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.raven.detail.databinding.DetailItemBinding
import com.raven.detail.presentation.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : BottomSheetDialogFragment() {

    private val detailViewModel: DetailViewModel by viewModels()
    private lateinit var binding: DetailItemBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCollectors()
        arguments?.getString("id")?.let { id ->
            detailViewModel.getNews(id)
        }
    }

    private fun setupCollectors() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailViewModel.newData.collect { new ->
                    new?.let { newNotNullData ->
                        binding.apply {
                            tvTitle.text = newNotNullData.title
                            tvAbstract.text = newNotNullData.abstract
                            tvBy.text = newNotNullData.byline
                            tvDate.text = newNotNullData.publishedDate
                        }
                    }
                }
            }
        }
    }
}