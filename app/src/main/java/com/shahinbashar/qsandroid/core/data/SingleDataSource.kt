package com.shahinbashar.qsandroid.core.data

interface SingleDataSource<T> {
    suspend fun get(): T?
    suspend fun add(item: T)
    suspend fun clear()
}