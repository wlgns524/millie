package com.subject.millie.ui.list.component

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.subject.millie.util.bitmapToByteArray
import com.subject.millie.util.byteArrayToBitmap


@Composable
fun ImageLoader(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    byteArray: ByteArray?,
    updateResource: (ByteArray) -> Unit,
) {
    var loadBitmap: Bitmap? by remember { mutableStateOf(byteArray?.byteArrayToBitmap()) }
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        if (loadBitmap == null)
            imageUrl?.let {
                getBitmap(
                    context = context,
                    url = it,
                    updateBitmap = { bitmap ->
                        updateResource(bitmap.bitmapToByteArray())
                        loadBitmap = bitmap
                    },
                )
            }
    }

    Image(
        modifier = modifier,
        contentScale = ContentScale.Crop,
        bitmap = loadBitmap?.asImageBitmap() ?: ImageBitmap(1, 1),
        contentDescription = ""
    )
}

fun getBitmap(
    context: Context,
    url: String,
    updateBitmap: (Bitmap) -> Unit,
) {
    Glide.with(context)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(
                resource: Bitmap,
                transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?,
            ) {
                updateBitmap(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }
        })
}
