package com.shdclgroup.app.features.history.presentation.blocs

import com.shdclgroup.app.features.home.view.ScreenContent

sealed class HistoryEvent {
   object GetHistory: HistoryEvent()

}
