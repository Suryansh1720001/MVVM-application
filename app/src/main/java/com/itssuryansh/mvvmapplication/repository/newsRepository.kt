package com.itssuryansh.mvvmapplication.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.itssuryansh.mvvmapplication.entities.News
import com.itssuryansh.mvvmapplication.local.ArticleDatabase
import com.itssuryansh.mvvmapplication.remote.NewsAPI
import com.itssuryansh.mvvmapplication.sealedClass.Response
import com.itssuryansh.mvvmapplication.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class newsRepository @Inject constructor(
    private val articleDatabase: ArticleDatabase,
    private val NewsAPI: NewsAPI,
    private val applicationContext: Context
) {


    //liveData
    private val newsLiveDataResponse = MutableLiveData<Response<News>>()
    val newsReponse: LiveData<Response<News>>
        get() = newsLiveDataResponse


    suspend fun getNews(domain: String, APIKey: String) {
        //Loading
        newsLiveDataResponse.postValue(Response.Loading())

        if (NetworkUtils.isOnline(applicationContext)) {
            try {
                // load the data from the net when net connected.
                val result = NewsAPI.getNews(domain, APIKey)
                if (result?.body() != null) {
                    withContext(Dispatchers.IO) {
                        articleDatabase.articleDao().insertNews(result?.body()!!.articles)
                    }
                    // data is load from the remote
                    val articleList = News("true", 1, result.body()!!.articles)
                    newsLiveDataResponse.postValue(Response.Success(articleList))

                } else {
                    newsLiveDataResponse.postValue(Response.Error("API call failed with code: ${result.code()}"))
                }

            } catch (e: Exception) {
                newsLiveDataResponse.postValue(Response.Error("An Error Occured : ${e.message}"))
            }

        } else {
            try {
                // load data from database when net is not connected
                val article = withContext(Dispatchers.IO) {
                    articleDatabase.articleDao().getNews()
                }
                val articleList = News("true", 1, article)
                newsLiveDataResponse.postValue(Response.Success(articleList))
            } catch (e: Exception) {
                newsLiveDataResponse.postValue(Response.Error("Failed to load data from database: ${e.message}"))
            }
        }
    }
}