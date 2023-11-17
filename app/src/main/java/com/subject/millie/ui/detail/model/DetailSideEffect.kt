package com.subject.millie.ui.detail.model

sealed class DetailSideEffect {
    data class ShowToast(val message: String) : DetailSideEffect()
}
