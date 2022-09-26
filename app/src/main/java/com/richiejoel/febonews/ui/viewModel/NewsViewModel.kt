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


    fun getNews(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getNewsUseCase()
            Log.d("Response", result.toString())
            if(result != null){
                newsModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

    fun setNewSelected(news: Articles){

        newsSelected.postValue(news)

    }


}