package com.shahinbashar.qsandroid.features.login.data.remote


import com.shahinbashar.qsandroid.features.login.data.model.LoginRequest
import com.shahinbashar.qsandroid.features.login.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {



    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse?>


}