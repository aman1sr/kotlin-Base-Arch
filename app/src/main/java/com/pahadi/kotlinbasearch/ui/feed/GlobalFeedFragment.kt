package com.pahadi.kotlinbasearch.ui.feed

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.pahadi.kotlinbasearch.R
import com.pahadi.kotlinbasearch.databinding.FragmentGlobalFeedBinding

class GlobalFeedFragment : Fragment() {

    private var _binding: FragmentGlobalFeedBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val TAG = "GlobalFeedFrg_d"

    private lateinit var viewModel: GlobalFeedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val feedViewModel = ViewModelProvider(this).get(GlobalFeedViewModel::class.java)
        _binding = FragmentGlobalFeedBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(GlobalFeedViewModel::class.java)

        binding.button.setOnClickListener {
            viewModel.fetchGlobalFeel()
            binding.textView.text = "FEtching"
        }

        viewModel.feed.observe({lifecycle}){
            binding.textView.text = "FETCHED!!!"
        }



        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}