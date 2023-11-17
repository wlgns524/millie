package com.subject.millie.ui.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.subject.domain.model.HeadLine
import com.subject.millie.ui.list.component.HeadLineContent
import com.subject.millie.ui.list.model.ListUiState

@Composable
fun ListScreen(
    uiState: ListUiState,
    onDetail: (HeadLine) -> Unit,
    onLoadPage: (Int) -> Unit,
    updateImageResource: (HeadLine) -> Unit,
) {
    Scaffold { paddingValues ->
        HeadLineContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            headLineList = uiState.headLineList,
            metaState = uiState.listMetaState,
            onDetail = onDetail,
            onLoadPage = onLoadPage,
            updateImageResource = updateImageResource
        )
    }
}