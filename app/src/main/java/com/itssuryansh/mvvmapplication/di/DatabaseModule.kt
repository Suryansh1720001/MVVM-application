package com.itssuryansh.mvvmapplication.di

import android.content.Context
import androidx.room.Room
import com.itssuryansh.mvvmapplication.local.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun getDatabase(@ApplicationContext context: Context): ArticleDatabase {
        return Room.databaseBuilder(context, ArticleDatabase::class.java, "article_db")
            .build()
    }

}