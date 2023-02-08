package com.pahadi.kotlinbasearch.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pahadi.kotlinbasearch.data.ArticlesRepo
import io.realworld.api.models.entities.Article
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {


/*

    fun getTestingNewsFeed() = viewModelScope.launch {
        ArticlesRepo.getTestNews().body()?.let {
            Log.d("testingNews_d", "getTestingNewsFeed: "+it.toString())
        }
    }
*/


}