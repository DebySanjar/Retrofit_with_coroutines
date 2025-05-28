package com.samurai.retrofit_with_coroutines.utils

class Recourse<T> {
    var body:T? = null
    var message: String? = null
    var status: Status = Status.LOADING

    constructor(body: T?, message: String?, status: Status) {
        this.body = body
        this.message = message
        this.status = status
    }

    companion object{
        fun loading() = Recourse<Any>(null, null, Status.LOADING)
        fun error(message: String?) = Recourse<Any>(null, message, Status.ERROR)
        fun <T>success(body:T) = Recourse<T>(body, null, Status.SUCCESS)
    }
}