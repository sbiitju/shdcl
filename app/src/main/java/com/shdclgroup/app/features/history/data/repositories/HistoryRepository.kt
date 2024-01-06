package com.shdclgroup.app.features.history.data.repositories

import com.shdclgroup.app.core.data.BaseRepository
import com.shdclgroup.app.features.history.data.datasources.HistoryApi
import com.shdclgroup.app.features.history.data.models.HistoryRequest
import javax.inject.Inject

class HistoryRepository @Inject constructor(
    private val historyApi: HistoryApi
) : BaseRepository {
    suspend fun getHistory() = historyApi.getHistory(HistoryRequest(date = "2021-09-01"))
}