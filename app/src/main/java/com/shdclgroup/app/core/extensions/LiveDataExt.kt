package com.shdclgroup.app.core.extensions

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData


@MainThread
fun <T> MutableLiveData<T>.mutate(mutator: T.() -> Unit) {
    this.value = this.value?.apply(mutator)
}

@MainThread
fun <T> MutableLiveData<T>.modifyValue(transform: T.() -> T) {
    this.value = this.value?.run(transform)
}

//fun <T> MutableStateFlow<T>.update(transform: T.() -> T) {
//    this.value = this.value.run(transform)
//}

data class Example(
    var a: String,
    var b: String
)

private fun testMutate(liveData: MutableLiveData<Example>) {
    liveData.mutate { a = "New Value" }
}

private fun testModifyValue(liveData: MutableLiveData<Example>) {
    liveData.modifyValue{ copy(a = "New Value") }
}