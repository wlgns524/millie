package com.subject.millie.ui.list.model

import android.content.Intent

sealed class ListSideEffect {
    data class ShowToast(val message: String) : ListSideEffect()
    data class StartActivity(val intent: Intent) : ListSideEffect()
}
