package com.pahadi.kotlinbasearch.data

import android.util.Log
import io.realworld.api.ConduitClient
import io.realworld.api.models.entities.LoginData
import io.realworld.api.models.entities.SignupData
import io.realworld.api.models.entities.User
import io.realworld.api.models.entities.UserUpdateData
import io.realworld.api.models.requests.LoginRequest
import io.realworld.api.models.requests.SignupRequest
import io.realworld.api.models.requests.UserUpdateRequest
import io.realworld.api.models.responses.UserResponse

object UserRepo {
    val api = ConduitClient.publicApi
    val authAPI = ConduitClient.authApi

    suspend fun login(email:String,password:String): UserResponse?{
        val response = api.loginUser(LoginRequest(LoginData(email, password)))
        Log.d("test_d", "API :"+response.message())
        ConduitClient.authToken = response.body()?.user?.token
        return response.body()
    }
    suspend fun getUserProfile() = authAPI.getCurrentUser().body()?.user

    suspend fun signup(username: String, email: String, password: String): User?{
        val response = api.signupUser(SignupRequest(SignupData(
            email, password, username
        )))
        ConduitClient.authToken = response.body()?.user?.token

        return response.body()?.user
    }

    suspend fun updateUser(
        bio:String?,
        username: String?,
        image:String?,
        email:String?,
        password: String?
    ): User? {
        val response = authAPI.updateCurrentUser(UserUpdateRequest(UserUpdateData(bio, email, image, username, password)))
    return response.body()?.user
    }



}