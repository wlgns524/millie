package com.subject.millie.ui.detail

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.subject.millie.ui.component.LoadingDialog
import com.subject.millie.ui.detail.model.DetailSideEffect
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DetailRoute(
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val activity = context as DetailActivity

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.isLoading) LoadingDialog()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                is DetailSideEffect.ShowToast -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    DetailScreen(
        onBackPressed = { if (!navController.popBackStack()) activity.finish() },
        uiState = uiState,
        updateLoading = viewModel::updateLoading
    )
}