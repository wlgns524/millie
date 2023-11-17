package com.subject.local.model

import com.subject.data.model.HeadLineData

fun HeadLineData.toEntity() = HeadLineEntity(
    id = id,
    title = title,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    url = url,
    isViewed = isViewed,
    imageResource = imageResource
)

fun HeadLineEntity.toData() = HeadLineData(
    id = id,
    title = title,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    url = url,
    isViewed = isViewed,
    imageResource = imageResource
)
