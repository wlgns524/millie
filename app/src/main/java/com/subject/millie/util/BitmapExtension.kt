package com.subject.millie.util

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream

fun Bitmap.bitmapToByteArray(): ByteArray {
    val stream = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 100, stream)
    return stream.toByteArray()
}
