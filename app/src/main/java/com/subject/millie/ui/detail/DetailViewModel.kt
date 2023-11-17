package com.subject.millie.ui.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.subject.millie.base.MVIViewModel
import com.subject.millie.ui.detail.model.DetailSideEffect
import com.subject.millie.ui.detail.model.DetailUiState
import com.subject.millie.ui.main.CONST_HEAD_LINE_URL
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : MVIViewModel<DetailUiState, DetailSideEffect>(savedStateHandle) {
    override fun createInitialState(savedStateHandle: SavedStateHandle): DetailUiState {
        val url = savedStateHandle.get<String>(CONST_HEAD_LINE_URL)
            ?: throw IllegalArgumentException("데이터를 확인해주세요")
        return DetailUiState.empty(url)
    }
    fun updateLoading(isLoading: Boolean) {
        reduce { copy(isLoading = isLoading) }
    }
}
