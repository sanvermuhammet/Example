package com.sanver.example.core

interface BaseCallBack<T> {
    fun onSuccess(data: T)
    fun onFail(message: String)
}