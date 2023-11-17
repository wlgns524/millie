package com.subject.millie.ui.list.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.subject.domain.model.HeadLine
import com.subject.millie.ui.list.model.ListUiState
import com.subject.millie.util.getScreenWidth

@Composable
fun HeadLineContent(
    modifier: Modifier = Modifier,
    headLineList: List<HeadLine>,
    metaState: ListUiState.ListMetaState,
    onDetail: (HeadLine) -> Unit,
    onLoadPage: (Int) -> Unit,
    updateImageResource: (HeadLine) -> Unit,
) {
    val listState = rememberLazyGridState()
    val layoutInfo by remember { derivedStateOf { listState.layoutInfo } }
    val shouldStartPaginate by remember(layoutInfo) {
        derivedStateOf {
            (layoutInfo.visibleItemsInfo.lastOrNull()?.index
                ?: 0) >= headLineList.size - 1 && metaState.pageable
        }
    }
    LaunchedEffect(shouldStartPaginate) {
        if (shouldStartPaginate) {
            onLoadPage(metaState.page)
        }
    }

    if (headLineList.isNotEmpty()) {
        LazyVerticalGrid(
            modifier = modifier,
            state = listState,
            columns = GridCells.Fixed(if (getScreenWidth() >= 600) 3 else 1),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(items = headLineList) { item ->
                HeadLineItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onDetail(item) },
                    item = item,
                    updateImageResource = updateImageResource,
                )
            }
        }
    } else {
        EmptyContent(modifier = modifier)
    }
}