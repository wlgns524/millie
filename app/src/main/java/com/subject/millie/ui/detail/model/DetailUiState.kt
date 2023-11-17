package com.subject.millie.ui.detail.model

import androidx.compose.runtime.Immutable

@Immutable
data class DetailUiState(
    val isLoading: Boolean,
    val url: String,
) {
    companion object {
        fun empty(url: String) = DetailUiState(
            isLoading = true,
            url = url,
        )
    }
}
