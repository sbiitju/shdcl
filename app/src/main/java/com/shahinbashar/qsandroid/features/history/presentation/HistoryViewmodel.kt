package com.shahinbashar.qsandroid.features.history.presentation

import com.shahinbashar.qsandroid.core.base.viewmodel.MviViewModel
import com.shahinbashar.qsandroid.core.base.widget.BaseViewState
import com.shahinbashar.qsandroid.core.extensions.mapSuccess
import com.shahinbashar.qsandroid.features.history.data.repositories.HistoryRepository
import com.shahinbashar.qsandroid.features.history.presentation.blocs.HistoryEvent
import com.shahinbashar.qsandroid.features.history.presentation.blocs.HistoryState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyRepository: HistoryRepository
):
    MviViewModel<BaseViewState<HistoryState>, HistoryEvent>() {
    override fun onTriggerEvent(eventType: HistoryEvent) {
        when(eventType){
            is HistoryEvent.GetHistory -> {
               safeLaunch {
                     historyRepository.getHistory().mapSuccess {
                         setState(BaseViewState.Data(HistoryState( it)))
                     }
               }
            }
        }
    }
}