package com.shdclgroup.app.core.network



data class RefreshTokenRequest(
    val token:String,
    val refreshToken: String
)
