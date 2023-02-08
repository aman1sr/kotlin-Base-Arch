package com.pahadi.kotlinbasearch.ui.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.pahadi.kotlinbasearch.R
import com.pahadi.kotlinbasearch.databinding.FragmentSettingBinding
import com.pahadi.kotlinbasearch.ui.auth.AuthViewModel

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding?  = null
    private val authViewModel by activityViewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater,container,false)


        // Inflate the layout for this fragment
        return _binding!!.root
    }

    /*
    * Elvis operator (?:) is used to return the not null value even the conditional expression is null
    * eg--
    *       val l = b?.length ?: -1
    * */

    /*
    * Kotlin apply is an extension function on a type. It runs on the object reference (also known as receiver)
    * into the expression and returns the object reference on completion
    * eg--
    *   data class GFG(var name1 : String, var name2 : String,var name3 : String)
        var gfg = GFG("Geeks","for","hi")
         gfg.apply { this.name3 = "Geeks" }      // apply function invoked to change the name3 value
         println(gfg)
    *
    * */

    /*
       * isNotBlank() -- Returns true if this char sequence is not empty and contains some characters except of whitespace characters.
       * takeIf -- takeIf will proceed in the chain only if the condition inside is true whereas takeUnless is the counter of takeIf
       * */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* if User is LoggedIn & we got the User details, fill the required fields */
        authViewModel.user.observe({lifecycle}){
            _binding?.apply {
                bioEditText.setText(it?.bio ?: "")
                emailEditText.setText(it?.email ?: "")
                usernameEditText.setText(it?.username ?: "")
                imageEditText.setText(it?.image?:"")
            }
        }


        _binding?.apply {
            submitButton.setOnClickListener {
                authViewModel.update(
                    bio = bioEditText.text.toString(),
                    username = usernameEditText.text.toString().takeIf { it.isNotBlank() },
                    image = imageEditText.text.toString(),
                    email = emailEditText.text.toString().takeIf { it.isNotBlank() },
                    password = passwordEditText.text.toString().takeIf { it.isNotBlank() }

                )
            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding  = null
    }
}