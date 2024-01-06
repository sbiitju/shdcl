package com.shdclgroup.app.features.history.presentation.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shdclgroup.app.core.base.widget.BaseViewState
import com.shdclgroup.app.core.base.widget.EmptyView
import com.shdclgroup.app.core.base.widget.LoadingView
import com.shdclgroup.app.core.extensions.cast
import com.shdclgroup.app.features.history.presentation.HistoryViewModel
import com.shdclgroup.app.features.history.presentation.blocs.HistoryEvent
import com.shdclgroup.app.features.history.presentation.blocs.HistoryState
import com.shdclgroup.app.features.home.view.DashBoardState
import com.shdclgroup.app.login.view.login.MyErrorView


@Composable
fun HistoryScreen(
    navController: NavController, viewModel: HistoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    viewModel.onTriggerEvent(HistoryEvent.GetHistory)
    when (uiState) {
        is BaseViewState.Loading -> {
            LoadingView()
        }

        is BaseViewState.Data -> {
            val data =  uiState.cast<BaseViewState.Data<HistoryState>>().value
            data.historyResponse
        }

        is BaseViewState.Error -> {
            MyErrorView(
                e = (uiState as BaseViewState.Error).throwable,
                resetInput = { },
                navController = navController
            )
        }

        is BaseViewState.Empty -> {
            EmptyView()
        }

    }


}