package com.shdclgroup.app.core.extensions



import com.shdclgroup.app.core.domain.error.toException
import retrofit2.Response

inline fun <T> repoCall(
    block: () -> Response<T>
): T {
    val response = block()
    val body = response.body()
    return when (response.isSuccessful && body != null) {
        true -> body
        false -> throw response.toException()
    }
}

inline fun <reified T, R> Response<T>.mapSuccess(
    crossinline block: (T) -> R
): R {
    val safeBody = body()
    if (this.isSuccessful && safeBody != null) {
        return block(safeBody)
    }
    else if(this.isSuccessful && this.code() == 204){
        return block(T::class.java.newInstance())
    }

    else {
        throw toException()
    }
}