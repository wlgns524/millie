package com.subject.remote.model

import com.google.gson.annotations.SerializedName

data class HeadLineResponseDto(
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("articles")
    val articles: List<HeadLineDto>,
)