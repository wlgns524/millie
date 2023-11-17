package com.subject.millie.ui.list

import android.content.Intent
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.subject.domain.model.HeadLine
import com.subject.domain.usecase.GetHeadLineListUseCase
import com.subject.domain.usecase.LoadHeadLineListUseCase
import com.subject.domain.usecase.UpdateImageResourceUseCase
import com.subject.domain.usecase.UpdateIsViewedUseCase
import com.subject.millie.base.MVIViewModel
import com.subject.millie.ui.list.model.ListSideEffect
import com.subject.millie.ui.list.model.ListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val getHeadLineListUseCase: GetHeadLineListUseCase,
    val loadHeadLineListUseCase: LoadHeadLineListUseCase,
    val updateIsViewedUseCase: UpdateIsViewedUseCase,
    val updateImageResourceUseCase: UpdateImageResourceUseCase,
) : MVIViewModel<ListUiState, ListSideEffect>(savedStateHandle) {
    override fun createInitialState(savedStateHandle: SavedStateHandle): ListUiState =
        ListUiState.empty()

    private val PAGE_SIZE = 20
    fun getHeadLineList() {
        launch {
            getHeadLineListUseCase().collect { headLineList ->
                reduce { copy(headLineList = headLineList) }
            }
        }
    }

    fun onLoadPage(page: Int) {
        launch {
            updateLoading(true)
            loadHeadLineListUseCase(page = page, pageSize = PAGE_SIZE)
                .onSuccess {
                    val newList = it.articles
                    val oldList = uiState.value.listMetaState.headLineList
                    reduce {
                        copy(
                            listMetaState = ListUiState.ListMetaState(
                                pageable = newList.size == PAGE_SIZE && !oldList.containsAll(newList),
                                page = page + 1,
                                headLineList = if (page == 1) newList else oldList.toMutableList()
                                    .apply { addAll(newList) }
                            ))
                    }
                }
                .onFailure {
                    reduce { copy(listMetaState = listMetaState.copy(pageable = false)) }
                    val errorMessage = it.message?.let { message ->
                        Log.d("logtag", message)
                        if (Pattern.matches("^[ㄱ-ㅎ가-힣]*\$ ", message)) message else null
                    } ?: "오류가 발생했습니다"
                    postSideEffect(ListSideEffect.ShowToast(errorMessage))
                }
                .also { updateLoading(false) }
        }
    }

    fun updateIsViewed(headLine: HeadLine) {
        launch { updateIsViewedUseCase(headLine) }
    }

    fun updateImageResource(headLine: HeadLine) {
        launch { updateImageResourceUseCase(headLine) }
    }

    override fun postSideEffect(sideEffect: ListSideEffect) = launch {
        super.postSideEffect(sideEffect)
    }

    fun startActivity(intent: Intent) = launch {
        postSideEffect(ListSideEffect.StartActivity(intent))
    }

    private fun updateLoading(isLoading: Boolean) {
        reduce { copy(isLoading = isLoading) }
    }
}