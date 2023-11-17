package com.subject.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "headLine",
    indices = [Index(value = ["id"], unique = true)]
)
data class HeadLineEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "sort")
    val sort: Int = 0,

    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title") val title: String,

    @ColumnInfo(name = "urlToImage") val urlToImage: String?,

    @ColumnInfo(name = "publishedAt") val publishedAt: String,

    @ColumnInfo(name = "url") val url: String,

    @ColumnInfo(name = "isViewed") val isViewed: Boolean,

    @ColumnInfo(name = "imageResource", typeAffinity = ColumnInfo.BLOB)
    val imageResource: ByteArray?,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HeadLineEntity

        if (id != other.id) return false
        if (sort != other.sort) return false
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
        result = 31 * result + sort.hashCode()
        result = 31 * result + (urlToImage?.hashCode() ?: 0)
        result = 31 * result + publishedAt.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + isViewed.hashCode()
        result = 31 * result + (imageResource?.contentHashCode() ?: 0)
        return result
    }
}
