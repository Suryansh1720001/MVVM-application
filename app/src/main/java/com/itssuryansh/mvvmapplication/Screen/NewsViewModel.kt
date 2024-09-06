package com.itssuryansh.mvvmapplication.Screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itssuryansh.mvvmapplication.entities.News
import com.itssuryansh.mvvmapplication.repository.newsRepository
import com.itssuryansh.mvvmapplication.sealedClass.Response
import com.itssuryansh.mvvmapplication.utils.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(val repository: newsRepository) : ViewModel() {


    private val newsLiveData = repository.newsReponse
    val news : LiveData<Response<News>>
        get() = newsLiveData


    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getNews("wsj.com", Constant.API_KEY)
        }
    }




}