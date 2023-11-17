package com.subject.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class HeadLineResponse(
    val totalResults: Int,
    val articles: List<HeadLine>,
)