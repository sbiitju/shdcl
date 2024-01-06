package com.shahinbashar.qsandroid.features.login.view;

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import com.shahinbashar.qsandroid.core.base.viewmodel.MviViewModel
import com.shahinbashar.qsandroid.core.base.widget.BaseViewState
import com.shahinbashar.qsandroid.core.extensions.mapSuccess

import com.shahinbashar.qsandroid.features.login.data.model.LoginModel
import com.shahinbashar.qsandroid.features.login.data.model.LoginRequest
import com.shahinbashar.qsandroid.features.login.data.model.LoginResponse
import com.shahinbashar.qsandroid.features.login.data.remote.LoginRepository
import com.shahinbashar.qsandroid.prefs
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class LoginNewViewModel @Inject constructor(
    private val repository: LoginRepository
) : MviViewModel<BaseViewState<LoginNewViewState>, LoginNewEvent>() {

    var data = LoginNewViewState(
        userName = "",
        password = "",
        screenContent = ScreenContent.NEW_LOGIN,
        loginModel = LoginModel(),
        isRememberMe = false,
    )

    init {
        init()
    }

    override fun onTriggerEvent(eventType: LoginNewEvent) {
        when (eventType) {
            is LoginNewEvent.Init -> {
                init()
            }

            is LoginNewEvent.ProcessLogin -> {
                login(eventType.context)
            }


            is LoginNewEvent.OnUserNameChanged -> {
                onUserChanged(eventType.userName)
            }

            is LoginNewEvent.OnPasswordChanged -> {
                onPasswordChanged(eventType.password)
            }

            is LoginNewEvent.OnRememberMeClicked -> {
                data = data.copy(isRememberMe = !data.isRememberMe)
                setState(BaseViewState.Data(data))
            }


            else -> {}
        }
    }


    private fun init() {
        val model = prefs.loginModel
        data = data.copy(
            userName = model.userName,
            password = model.password,
            loginModel = model,
            isOpenDashboard = model.isLoggedIn,
            isRememberMe = model.isRememberMe,
        )
        setState(BaseViewState.Data(data))
    }


    fun onUserChanged(text: String) {
        data = data.copy(userName = text)
        setState(BaseViewState.Data(data))
    }

    fun onPasswordChanged(text: String) {
        data = data.copy(password = text)
        setState(BaseViewState.Data(data))

    }


    fun login(context: Context) {

        if (data.userName.isEmpty() || data.password.isEmpty()) {
            Toast.makeText(context, "Please enter username and password", Toast.LENGTH_SHORT).show()
            return
        }

        login(
            LoginRequest(
                data.userName, data.password, ""
            )

        )
    }


    fun login(request: LoginRequest) = safeLaunch {
        repository.login(request).mapSuccess {
            println("LoginResponse: $it")
            if (it != null) {
                updateLoginSuccessful(it)
            }
        }
    }

    private fun updateLoginSuccessful(response: LoginResponse) {
        prefs.loginModel = prefs.loginModel.copy(
            token = response.data.token,
            userName = data.userName,
            password = data.password,
            isRememberMe = data.isRememberMe,
            isLoggedIn = true,
            userID = response.data.user._id,
        )

        data = data.copy(
            loginResponse = response,
            isOpenDashboard = true,
        )

        setState(BaseViewState.Data(data))
    }


    private fun getDeviceName(): String {
        return Build.MODEL.toString()
    }

    fun uniqueId(): String = UUID.randomUUID().toString()


    @SuppressLint("HardwareIds")
    fun androidId(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID);
    }


}