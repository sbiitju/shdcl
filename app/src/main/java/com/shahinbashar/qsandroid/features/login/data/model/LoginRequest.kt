package com.shahinbashar.qsandroid.features.login.data.model

data class LoginRequest(
    val email: String,
    val password: String,
    val fcmToken: String,
)