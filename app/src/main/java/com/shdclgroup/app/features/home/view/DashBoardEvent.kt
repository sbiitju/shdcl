package com.shdclgroup.app.features.home.view

import android.content.Context
import com.shdclgroup.app.features.home.data.model.Project

sealed class DashBoardEvent {
    data class OnSubmit(
        val amount: String,
        val description: String,
        val projectID: String,
        val userID: String,
        val screenContent: ScreenContent,
        val expenseDate: String,
        val context:Context
    ) : DashBoardEvent()

    data class OnAmountChanged(val amount: String) : DashBoardEvent()
    data class OnTitleChanged(val title: String) : DashBoardEvent()
    data class OnDescriptionChanged(val description: String) : DashBoardEvent()
    data class OnProjectIDChanged(val projectID: String) : DashBoardEvent()
    data class OnUserIDChanged(val userID: String) : DashBoardEvent()
    data class OnExpenseDateChanged(val expenseDate: String) : DashBoardEvent()
    data class  OnScreenContentChanged(val screenContent: ScreenContent) : DashBoardEvent()

}


data class  DashBoardState(
    var amount: String="",
    var title: String="",
    var description: String="",
    var projectID: String="",
    var userID: String="",
    var screenContent: ScreenContent=ScreenContent.Expense,
    var expenseDate: String="",
    var listOfProject: List<Project> = emptyList(),
)
enum class ScreenContent {
    Expense, Income
}
