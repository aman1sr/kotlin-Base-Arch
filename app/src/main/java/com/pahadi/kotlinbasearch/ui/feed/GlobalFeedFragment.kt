package com.pahadi.kotlinbasearch.ui.feed

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pahadi.kotlinbasearch.R

class GlobalFeedFragment : Fragment() {

    companion object {
        fun newInstance() = GlobalFeedFragment()
    }

    private lateinit var viewModel: GlobalFeedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_global_feed, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GlobalFeedViewModel::class.java)
        // TODO: Use the ViewModel
    }

}