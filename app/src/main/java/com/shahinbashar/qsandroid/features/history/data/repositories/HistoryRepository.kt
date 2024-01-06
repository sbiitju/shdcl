package com.shahinbashar.qsandroid.features.history.data.repositories

import com.shahinbashar.qsandroid.core.data.BaseRepository
import com.shahinbashar.qsandroid.features.history.data.datasources.HistoryApi
import com.shahinbashar.qsandroid.features.history.data.models.HistoryRequest
import javax.inject.Inject

class HistoryRepository @Inject constructor(
    private val historyApi: HistoryApi
) : BaseRepository {
    suspend fun getHistory() = historyApi.getHistory(HistoryRequest(date = "2021-09-01"))
}