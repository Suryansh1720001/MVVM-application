package com.itssuryansh.mvvmapplication.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.itssuryansh.mvvmapplication.entities.Article


@Database(entities = [Article::class], version = 1)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun articleDao() : articleDao
//
//
//    companion object{
//        @Volatile
//        private var INSTANCE : ArticleDatabase?=null
//
//        fun getDatabase(context: Context): ArticleDatabase{
//            if(INSTANCE==null) {
//                synchronized(this){
//                INSTANCE = Room.databaseBuilder(
//                    context,
//                    ArticleDatabase::class.java,
//                    "article_db",
//                ).build()
//            }
//            }
//            return INSTANCE!!
//        }
//    }


}