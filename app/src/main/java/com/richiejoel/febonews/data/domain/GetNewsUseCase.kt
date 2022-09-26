package com.richiejoel.febonews.data.domain

import com.richiejoel.febonews.data.model.NewsModel
import com.richiejoel.febonews.data.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    suspend operator fun invoke(): NewsModel? {
        return repository.getAllNews()
    }
}