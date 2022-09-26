package com.richiejoel.febonews.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.richiejoel.febonews.data.domain.GetNewsUseCase
import com.richiejoel.febonews.data.model.Articles
import com.richiejoel.febonews.data.model.NewsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
): ViewModel() {

    val newsModel = MutableLiveData<NewsModel?>()
    val isLoading = MutableLiveData<Boolean>()
    var newsSelected = MutableLiveData<Articles?>()

    private val _isLoadingScroll = MutableLiveData<Boolean>()
    val isLoadingScroll = _isLoadingScroll
    private val _isAllVideoLoaded = MutableLiveData<Boolean>()
    val isAllVideoLoaded = _isAllVideoLoaded

    var currentNews: Int = 0


    fun getNews(page: String = "1"){
        viewModelScope.launch {
            isLoading.postValue(true)
            isLoadingScroll.value = true
            currentNews = page.toInt()
            val result = getNewsUseCase(page= page)
            Log.d("Response", result.toString())
            if(result != null){
                newsModel.postValue(result)
                isLoading.postValue(false)
                isLoadingScroll.value = false
            }
        }
    }

    fun setNewSelected(news: Articles){

        newsSelected.postValue(news)

    }


}