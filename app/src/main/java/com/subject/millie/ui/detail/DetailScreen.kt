package com.subject.millie.ui.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.subject.millie.ui.detail.component.DetailContent
import com.subject.millie.ui.detail.model.DetailUiState

@Composable
fun DetailScreen(
    onBackPressed: () -> Unit,
    uiState: DetailUiState,
    updateLoading: (Boolean) -> Unit,
) {
    Scaffold { paddingValues ->
        DetailContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            url = uiState.url,
            onBackPressed = onBackPressed,
            updateLoading = updateLoading
        )
    }
}