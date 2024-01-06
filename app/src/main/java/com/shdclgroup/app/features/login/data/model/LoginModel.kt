package com.shdclgroup.app.features.login.data.model

data class LoginModel(
//    val isDeviceRegistered: Boolean = false,
//    val isFirstTimePassword: Boolean = false,
    var userName: String = "",
    var userID: String = "",
    var password: String = "",
    var token: String = "",
    var refreshToken: String = "",
    val isLoggedIn: Boolean = false,
    val isRememberMe: Boolean = false,
)