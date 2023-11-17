package com.subject.millie.base

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subject.data.model.exception.DataException
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class MVIViewModel<State, SideEffect>(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val initialState: State by lazy { createInitialState(savedStateHandle) }
    abstract fun createInitialState(savedStateHandle: SavedStateHandle): State

    private val _uiState = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SideEffect>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    val sideEffect = _sideEffect.asSharedFlow()

    val ceh = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is DataException -> launch {
                Log.e("MVIViewModel", throwable.message)
            }

            else -> launch {
                Log.e("MVIViewModel", throwable.toString())
            }
        }
    }

    protected fun reduce(action: State.() -> State) {
        _uiState.update(action)
    }

    protected open fun postSideEffect(sideEffect: SideEffect) = launch {
        _sideEffect.emit(sideEffect)
    }

    inline fun launch(@ViewModelScoped crossinline action: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(Dispatchers.IO + ceh) {
            action(this)
        }
    }
}
