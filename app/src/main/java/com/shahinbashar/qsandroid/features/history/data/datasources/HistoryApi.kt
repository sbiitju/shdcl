package com.shahinbashar.qsandroid.features.history.data.datasources

import com.shahinbashar.qsandroid.features.history.data.models.HistoryRequest
import com.shahinbashar.qsandroid.features.history.data.models.HistoryResponse
import com.shahinbashar.qsandroid.features.home.data.remote.ExpenseRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface HistoryApi {
    @GET("income/my_history")
    suspend fun getHistory(@Body historyRequest: HistoryRequest): Response<HistoryResponse>
}