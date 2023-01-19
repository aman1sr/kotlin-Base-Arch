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

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _feed = MutableLiveData<List<Article>>()
    val feed : LiveData<List<Article>> = _feed

    fun fetchGlobalFeel() = viewModelScope.launch {
        ArticlesRepo.getGlobalFeed().body()?.let {
            _feed.postValue(it.articles)
            Log.d("HomeFrg_d", "fetchGlobalFeel size : "+it.articlesCount)
        }
    }


}