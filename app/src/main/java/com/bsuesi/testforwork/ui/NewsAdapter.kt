package com.bsuesi.testforwork.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bsuesi.testforwork.data.model.Article
import com.bsuesi.testforwork.databinding.ItemBinding

class NewsAdapter() : ListAdapter<Article, NewsAdapter.MyViewHolder>(DiffCallBack()) {
    private var onItemClick: ((Article) -> Unit)? = null
    fun setOnItemClick(onItemClick: ((Article) -> Unit)?) {
        this.onItemClick = onItemClick

    }

    inner class MyViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.arrowRight.setOnClickListener {

                onItemClick?.invoke(article)
            }
            binding.newsTitle.text = article.title
            binding.newsText.text = article.description
            binding.newsSource.text = article.source.name
            binding.time.text = article.publishedAt.replace("T", "\n").replace("Z", "")

            if (article.urlToImage.isNullOrBlank()) {
                binding.image.visibility = View.GONE
            } else binding.image.load(article.urlToImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(currentList[position])

    }
}

private class DiffCallBack() : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article) =
        oldItem.title == newItem.title


    override fun areContentsTheSame(oldItem: Article, newItem: Article) =
        oldItem.title == newItem.title

}