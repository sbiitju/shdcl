package com.shahinbashar.qsandroid.features.history.presentation.blocs

import com.shahinbashar.qsandroid.features.home.view.ScreenContent

sealed class HistoryEvent {
   object GetHistory: HistoryEvent()

}
