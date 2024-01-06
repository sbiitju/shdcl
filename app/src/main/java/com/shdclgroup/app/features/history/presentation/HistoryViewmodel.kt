package com.shdclgroup.app.features.history.presentation

import com.shdclgroup.app.core.base.viewmodel.MviViewModel
import com.shdclgroup.app.core.base.widget.BaseViewState
import com.shdclgroup.app.core.extensions.mapSuccess
import com.shdclgroup.app.features.history.data.repositories.HistoryRepository
import com.shdclgroup.app.features.history.presentation.blocs.HistoryEvent
import com.shdclgroup.app.features.history.presentation.blocs.HistoryState
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