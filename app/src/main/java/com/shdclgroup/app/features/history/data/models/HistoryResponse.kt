package com.shdclgroup.app.features.history.data.models



data class HistoryResponse(
    val success: Boolean,
    val data: List<History>,
)

data class History(
    val _id: String,
    val userId: String?,
    val project: Project,
    val date: String,
    val income: List<Income>,
)

data class Project(
    val id: String,
    val projectName: String,
    val projectAddress: String,
)

data class Income(
    val title: String,
    val amount: Long,
    val description: String,
    val id: String,
)
