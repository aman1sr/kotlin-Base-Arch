package com.pahadi.kotlinbasearch.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pahadi.kotlinbasearch.data.UserRepo
import io.realworld.api.models.entities.User
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    fun login(email: String, password: String) = viewModelScope.launch {
        UserRepo.login(email,password)?.let {
            Log.d("test_d", "API: result for: $email : "+it)
            _user.postValue(it.user)
        }
    }

    fun signup(username: String, email: String, password: String) = viewModelScope.launch {
        UserRepo.signup(username, email, password)?.let {
            _user.postValue(it)
        }
    }

    fun logout() {
        _user.postValue(null)
    }

/*
* let takes the object it is invoked upon as the parameter and returns the result of the lambda expression
*   eg --
*       variable = "value"
        variable?.let { it ->
    	            println(it)
                     }
* */
    fun update(
        bio: String?,
        username: String?,
        image: String? ,
        email: String?,
        password: String?
    ) = viewModelScope.launch {
        UserRepo.updateUser(bio, username, image, email, password)?.let {
            _user.postValue(it)
        }
    }



}