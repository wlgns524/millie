package com.subject.millie.ui.list.model

import androidx.compose.runtime.Immutable
import com.subject.domain.model.HeadLine

@Immutable
data class ListUiState(
    val isLoading: Boolean,
    val headLineList: List<HeadLine>,
    val listMetaState: ListMetaState,
) {
    @Immutable
    data class ListMetaState(
        val pageable: Boolean,
        val page: Int,
        val headLineList: List<HeadLine>,
    )

    companion object {
        fun empty() = ListUiState(
            isLoading = false,
            headLineList = emptyList(),
            listMetaState = ListMetaState(true, 1, emptyList())
        )
    }
}
