package com.subject.millie.ui.list

import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.subject.millie.ui.component.LoadingDialog
import com.subject.millie.ui.detail.DetailActivity
import com.subject.millie.ui.list.model.ListSideEffect
import com.subject.millie.ui.main.CONST_HEAD_LINE_URL
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ListRoute(
    navController: NavController,
    viewModel: ListViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.isLoading) LoadingDialog()

    LaunchedEffect(Unit) {
        viewModel.getHeadLineList()
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                is ListSideEffect.ShowToast -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                }

                is ListSideEffect.StartActivity -> {
                    context.startActivity(sideEffect.intent)
                }
            }
        }
    }



    ListScreen(
        uiState = uiState,
        onDetail = {
            it.copy(isViewed = true).run {
                viewModel.updateIsViewed(this)
//                navController.navigateWebView(this)
                viewModel.startActivity(Intent(context, DetailActivity::class.java).apply {
                    putExtra(CONST_HEAD_LINE_URL, it.url)
                })
            }
        },
        onLoadPage = viewModel::onLoadPage,
        updateImageResource = viewModel::updateImageResource
    )


}