package com.itssuryansh.mvvmapplication.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.itssuryansh.mvvmapplication.entities.Article

@Dao
interface articleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news : List<Article>)

    @Query("Select * from news")
    suspend fun getNews() : List<Article>

}