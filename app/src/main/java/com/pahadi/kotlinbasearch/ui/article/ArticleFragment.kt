package com.pahadi.kotlinbasearch.ui.article

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pahadi.kotlinbasearch.R
import com.pahadi.kotlinbasearch.databinding.FragmentArticleBinding

class ArticleFragment : Fragment() {

    companion object {
        fun newInstance() = ArticleFragment()
        private val TAG: String = "ArticleFrg_d"
    }


    private lateinit var articleViewModel: ArticleViewModel
    private var _binding : FragmentArticleBinding? = null
    private var articleId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        articleViewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)

        arguments?.let {
            articleId = it.getString(resources.getString(R.string.arg_article_id))
        }
        Log.d(TAG, "articleId: "+articleId)

        articleId?.let {
            articleViewModel.fetchArticle(it)
        }


        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articleViewModel.article.observe({ lifecycle }) {
            _binding?.apply {
                titleTextView.text = it.title
                bodyTextView.text = it.body
                authorTextView.text = it.author.username
//                dateTextView.timeStamp = it.createdAt         // todo: UPDATE date & time
//                avatarImageView.loadImage(it.author.image, true)
            }
        }
        }



    override fun onDestroy() {
        super.onDestroy()
        _binding =null
    }
}