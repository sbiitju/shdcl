package com.shahinbashar.qsandroid.features.login.data.model

import com.shahinbashar.qsandroid.core.util.User

data class LoginResponse(
    val success: Boolean,
    val data: Data,
)

data class  Data(
    val token: String,
    val user: User
)