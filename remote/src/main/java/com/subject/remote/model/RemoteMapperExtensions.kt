package com.subject.remote.model

import com.subject.data.model.HeadLineData
import com.subject.data.model.HeadLineResponseData

fun HeadLineDto.toData() = HeadLineData(
    id = hashCode(),
    title = this.title,
    urlToImage = this.urlToImage,
    publishedAt = this.publishedAt,
    url = this.url,
    isViewed = false,
    imageResource = null
)

fun HeadLineResponseDto.toData() = HeadLineResponseData(
    totalResults = totalResults,
    articles = articles.map(HeadLineDto::toData)
)
