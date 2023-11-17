package com.subject.millie.ui.list.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.subject.domain.model.HeadLine

@Composable
fun HeadLineItem(
    modifier: Modifier = Modifier,
    item: HeadLine,
    updateImageResource: (HeadLine) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        ImageLoader(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            imageUrl = item.urlToImage,
            byteArray = item.imageResource,
            updateResource = { updateImageResource(item.copy(imageResource = it)) },
        )
        Text(
            text = item.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = if (item.isViewed) Color.Red else Color.Black
        )
        Text(
            text = item.publishedAt,
            style = TextStyle(
                fontWeight = FontWeight.Light,
                fontSize = 10.sp
            )
        )
    }
}