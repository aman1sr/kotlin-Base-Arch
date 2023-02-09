package com.pahadi.kotlinbasearch.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pahadi.kotlinbasearch.R
import com.pahadi.kotlinbasearch.databinding.FragmentFeedBinding

class MyFeedFragment : Fragment() {

    private var _binding: FragmentFeedBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val TAG = "GlobalFeedFrg_d"

    private lateinit var viewModel: FeedViewModel
    private lateinit var feedAdapter: ArticleFeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val feedViewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        feedAdapter = ArticleFeedAdapter{
            openArticle(it)
        }        // todo: flow ? , how via constructor we get slug
        _binding?.feedRecyclerView?.layoutManager = LinearLayoutManager(context)
        _binding?.feedRecyclerView?.adapter = feedAdapter

        viewModel.fetchMyFeed()
        viewModel.feed.observe({lifecycle}){
            feedAdapter.submitList(it)
        }


        return binding.root
    }

    private fun openArticle(articleId: String) {
        findNavController().navigate(
            R.id.action_globalFeed_openArticle, bundleOf(
                resources.getString(R.string.arg_article_id) to articleId
            )
        )

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}