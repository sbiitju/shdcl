package com.shahinbashar.qsandroid.features.login.data.remote

import com.shahinbashar.qsandroid.features.login.data.model.LoginRequest
import com.shahinbashar.qsandroid.core.data.BaseRepository
import com.shahinbashar.qsandroid.features.login.data.remote.LoginApi
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginApi: LoginApi
): BaseRepository {
    suspend fun login(request: LoginRequest) = loginApi.login(request)

}