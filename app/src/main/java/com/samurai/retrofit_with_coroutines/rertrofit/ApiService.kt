package com.samurai.retrofit_with_coroutines.rertrofit

import com.samurai.retrofit_with_coroutines.models.Reja
import com.samurai.retrofit_with_coroutines.models.Todo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("rejalar/")
    suspend fun getAllTodos(): List<Todo>

    @DELETE("rejalar/{id}/")
    suspend fun deleteTodo(@Path("id") id: Int): Response<Unit>

    @POST("rejalar/")
    suspend fun addTodo(@Body reja: Reja): Response<Todo>

}