package com.subject.data.model

import com.subject.domain.model.HeadLineResponse
import kotlinx.serialization.Serializable

@Serializable
data class HeadLineResponseData(
    val totalResults: Int,
    val articles: List<HeadLineData>,
) {
    fun toDomain(): HeadLineResponse {
        return HeadLineResponse(
            totalResults = totalResults,
            articles = articles.map(HeadLineData::toDomain)
        )
    }
}
