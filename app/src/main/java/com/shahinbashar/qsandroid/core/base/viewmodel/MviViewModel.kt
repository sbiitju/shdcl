package com.shahinbashar.qsandroid.core.base.viewmodel

import com.shahinbashar.qsandroid.core.base.widget.BaseViewState
import com.shahinbashar.qsandroid.core.extensions.cast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class MviViewModel<STATE : BaseViewState<*>, EVENT> : MvvmViewModel() {

    private val _uiState = MutableStateFlow<BaseViewState<*>>(BaseViewState.Empty)
    val uiState = _uiState.asStateFlow()

    abstract fun onTriggerEvent(eventType: EVENT)

    protected fun setState(state: STATE) = safeLaunch {
        _uiState.emit(state)
    }

    override fun startLoading() {
        super.startLoading()
        _uiState.value = BaseViewState.Loading
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception)
        _uiState.value = BaseViewState.Error(exception)
    }

    fun <T> getUiStateData():T {
        return _uiState.value.cast<BaseViewState.Data<T>>().value
    }
}