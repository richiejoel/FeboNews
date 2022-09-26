package com.richiejoel.febonews.data.domain

import com.richiejoel.febonews.data.model.NewsModel
import com.richiejoel.febonews.data.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    suspend operator fun invoke(page: String): NewsModel? {
        return repository.getAllNews(page = page)
    }
}