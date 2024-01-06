package com.shahinbashar.qsandroid.core.network



data class RefreshTokenRequest(
    val token:String,
    val refreshToken: String
)
