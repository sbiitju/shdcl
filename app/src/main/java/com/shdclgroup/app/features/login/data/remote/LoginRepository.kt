package com.shdclgroup.app.features.login.data.remote

import com.shdclgroup.app.features.login.data.model.LoginRequest
import com.shdclgroup.app.core.data.BaseRepository
import com.shdclgroup.app.features.login.data.remote.LoginApi
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginApi: LoginApi
): BaseRepository {
    suspend fun login(request: LoginRequest) = loginApi.login(request)

}