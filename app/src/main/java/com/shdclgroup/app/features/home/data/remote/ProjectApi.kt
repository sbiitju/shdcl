package com.shdclgroup.app.features.home.data.remote

import com.shdclgroup.app.features.home.data.model.ProjectResponse
import retrofit2.Response
import retrofit2.http.Body

import retrofit2.http.GET
import retrofit2.http.POST

interface ProjectApi {
    @GET("project")
    suspend fun getProject(): Response<ProjectResponse?>

    @POST("income")
    suspend fun submitIncome(@Body expenseRequest: ExpenseRequest): Response<Any>

    @POST("expense")
    suspend fun submitExpense(@Body expenseRequest: ExpenseRequest): Response<Any>
}

data class ExpenseRequest(
    val projectId: String,
    val userId: String,
    val date: String,
    val income: List<IncomeBody>
)

data class IncomeBody(
    val title: String,
    val amount: String,
    val description: String
)