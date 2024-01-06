package com.shdclgroup.app.features.login.view;

import android.content.Context
import com.shdclgroup.app.features.login.data.model.LoginModel
import com.shdclgroup.app.features.login.data.model.LoginResponse

sealed class LoginNewEvent {
    object Init : LoginNewEvent()
    data class ProcessLogin(val context: Context) : LoginNewEvent()

    data class OnUserNameChanged(val userName: String) : LoginNewEvent()
    data class OnPasswordChanged(val password: String) : LoginNewEvent()


    data object OnRememberMeClicked: LoginNewEvent()
}

data class LoginNewViewState(
    val userName: String,
    val password: String,
    val loginModel: LoginModel,
    val loginResponse: LoginResponse? = null,
    val screenContent: ScreenContent,

    val isOpenDashboard: Boolean = false,
    var isRememberMe: Boolean = false,

)

enum class ScreenContent{
    NEW_LOGIN,
}