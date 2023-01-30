package com.pahadi.kotlinbasearch.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.pahadi.kotlinbasearch.R
import com.pahadi.kotlinbasearch.databinding.FragmentLoginSignupBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginSignupBinding? = null
    val authViewModel: AuthViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginSignupBinding.inflate(inflater, container, false)
        _binding?.usernameEditText?.isVisible = false

        return _binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.apply {

            submitButton.setOnClickListener {
                authViewModel.login(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}