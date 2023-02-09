package com.pahadi.kotlinbasearch.ui.feed

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pahadi.kotlinbasearch.databinding.ListItemArticleBinding
import io.realworld.api.models.entities.Article

// todo: how Adapter() constructor is accessible down the class
class ArticleFeedAdapter(val onArticleClicked: (slug: String) -> Unit) :
    ListAdapter<Article, ArticleFeedAdapter.ArticleViewHolder>(
    object :DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
           return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.toString() == newItem.toString()
        }
    }
){

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ListItemArticleBinding.inflate(
            parent.context.getSystemService(LayoutInflater::class.java),
            parent,
            false
        )
        return ArticleViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        ListItemArticleBinding.bind(holder.itemView).apply {
            val article = getItem(position)

            authorTextView.text = article.author.username
            titleTextView.text = article.title
            bodySnippetTextView.text = article.body
            dateTextView.text = "Dec 15, 2020"      // todo: DATE stuff
            avatarImageView.background = ColorDrawable(Color.BLUE)      // todo: extension -- loadIMG

            root.setOnClickListener { onArticleClicked(article.slug) }



        }
    }

}