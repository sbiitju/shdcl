package com.shdclgroup.app.features.login.data.model

import com.shdclgroup.app.core.util.User

data class LoginResponse(
    val success: Boolean,
    val data: Data,
)

data class  Data(
    val token: String,
    val user: User
)