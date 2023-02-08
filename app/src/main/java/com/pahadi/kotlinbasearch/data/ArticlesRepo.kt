package com.pahadi.kotlinbasearch.data

import io.realworld.api.ConduitClient

object ArticlesRepo {
    val api = ConduitClient.publicApi
    val authApi = ConduitClient.authApi

    suspend fun getGlobalFeed() = api.getArticles()
    suspend fun getTestNews() = api.getNews("in",null,"6341819298cd4ad9a778ce6d643e4871")       //todo: (NEWS testing)
    suspend fun getMyFeed() = authApi.getFeedArticles().body()?.articles        // TODO: check auth error

}