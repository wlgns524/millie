package com.subject.millie.ui.detail.component

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    url: String,
    onBackPressed: () -> Unit,
    updateLoading: (Boolean) -> Unit,
) {
    BackHandler { onBackPressed() }

    AndroidView(
        modifier = modifier,
        factory = { context ->
            WebView(context).apply {
                this.webViewClient = object : WebViewClient() {
                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                        updateLoading(true)
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        updateLoading(false)
                    }
                }
                loadUrl(url)
            }
        }
    )
}