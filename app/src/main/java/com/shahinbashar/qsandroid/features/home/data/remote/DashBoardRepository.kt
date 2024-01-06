package com.shahinbashar.qsandroid.features.home.data.remote

import com.shahinbashar.qsandroid.core.data.BaseRepository
import javax.inject.Inject

class DashBoardRepository @Inject constructor(
    private val projectApi: ProjectApi
) : BaseRepository {
    suspend fun getProject() = projectApi.getProject()

    suspend fun submitIncome(
        amount: String,
        description: String,
        projectID: String,
        userID: String,
        expenseDate: String,
    ) = projectApi.submitIncome(
        ExpenseRequest(
            projectId = projectID, userId = userID, date = expenseDate, income = listOf(
                IncomeBody(
                    title = description, amount = amount, description = description
                )
            )
        )
    )

    suspend fun submitExpense(
        amount: String,
        description: String,
        projectID: String,
        userID: String,
        expenseDate: String,
    ) = projectApi.submitExpense(
        ExpenseRequest(
            projectId = projectID, userId = userID, date = expenseDate, income = listOf(
                IncomeBody(
                    title = description, amount = amount, description = description
                )
            )
        )
    )

}