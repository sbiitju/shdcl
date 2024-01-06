package com.shdclgroup.app.features.history.data.datasources

import com.shdclgroup.app.features.history.data.models.HistoryRequest
import com.shdclgroup.app.features.history.data.models.HistoryResponse
import com.shdclgroup.app.features.home.data.remote.ExpenseRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface HistoryApi {
    @GET("income/my_history")
    suspend fun getHistory(@Body historyRequest: HistoryRequest): Response<HistoryResponse>
}