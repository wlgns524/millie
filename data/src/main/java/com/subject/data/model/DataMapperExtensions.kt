package com.subject.data.model

import com.subject.domain.model.HeadLine

fun HeadLineData.toDomain() = HeadLine(
    id = id,
    title = title,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    url = url,
    isViewed = isViewed,
    imageResource = imageResource,
)

fun HeadLine.toData() = HeadLineData(
    id = id,
    title = title,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    url = url,
    isViewed = isViewed,
    imageResource = imageResource
)