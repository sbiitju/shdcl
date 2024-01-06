package com.shahinbashar.qsandroid.core.network

import retrofit2.http.Body
import retrofit2.http.POST


interface ApiServiceNoneAuth {
    @POST("api/apptokens/refresh")
    suspend fun refreshToken(
        @Body token: RefreshTokenRequest,
    ): RefreshTokenResponse

}