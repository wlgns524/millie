package com.subject.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class HeadLine(
    val id: Int,
    val title: String,
    val urlToImage: String?,
    val publishedAt: String,
    val url: String,
    val isViewed: Boolean,
    val imageResource: ByteArray?,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HeadLine

        if (id != other.id) return false
        if (title != other.title) return false
        if (urlToImage != other.urlToImage) return false
        if (publishedAt != other.publishedAt) return false
        if (url != other.url) return false
        if (isViewed != other.isViewed) return false
        if (imageResource != null) {
            if (other.imageResource == null) return false
            if (!imageResource.contentEquals(other.imageResource)) return false
        } else if (other.imageResource != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + urlToImage.hashCode()
        result = 31 * result + publishedAt.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + isViewed.hashCode()
        result = 31 * result + (imageResource?.contentHashCode() ?: 0)
        return result
    }
}