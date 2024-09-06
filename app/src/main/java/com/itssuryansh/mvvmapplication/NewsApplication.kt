package com.itssuryansh.mvvmapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class NewsApplication : Application()

//    lateinit var newsRepository: newsRepository
//
//    override fun onCreate() {
//        super.onCreate()
//        intialize()
//    }
//
//    private fun intialize() {
//        val newsService  = RetrofitHelpter.getInstance().create(NewsAPI::class.java)
//        val database = ArticleDatabase.getDatabase(applicationContext)
//        newsRepository = newsRepository(database,newsService,applicationContext)
//
//    }
