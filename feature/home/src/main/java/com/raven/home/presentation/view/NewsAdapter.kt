package com.raven.home.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raven.home.R
import com.raven.home.databinding.NewsItemBinding
import com.raven.home.presentation.NewsData

class NewsAdapter(
    private val news: List<NewsData.Article>,
    private val onClick: (item: NewsData.Article) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val mView: View =
            LayoutInflater.
            from(parent.context).
            inflate(R.layout.news_item, parent, false)
        val binding = NewsItemBinding.bind(mView)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int = news.size


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(news[position], onClick)
    }

    class NewsViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NewsData.Article, onClick: (item: NewsData.Article) -> Unit) {
            binding.apply {
                title.text = item.title
                thumbnail.loadImage(item.thumbnailUrl)
                root.setOnClickListener {
                    onClick.invoke(item)
                }
            }
        }

        private fun ImageView.loadImage(url: String?) {
            Glide.with(this.context)
                .load(url)
                .into(this)
        }

    }

}