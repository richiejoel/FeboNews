package com.richiejoel.febonews.data.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsProvider @Inject constructor() {
    var news:NewsModel? = null
}