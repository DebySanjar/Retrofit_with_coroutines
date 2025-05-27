package com.samurai.retrofit_with_coroutines.repository

import com.samurai.retrofit_with_coroutines.models.Reja
import com.samurai.retrofit_with_coroutines.models.Todo
import com.samurai.retrofit_with_coroutines.rertrofit.RetrofitClient

class TodoRepository {

    suspend fun getAllTodos(): List<Todo> {
        return RetrofitClient.api.getAllTodos()
    }

    suspend fun deleteTodo(id: Int): Boolean {
        val response = RetrofitClient.api.deleteTodo(id)
        return response.isSuccessful
    }

    suspend fun addTodo(reja: Reja): Boolean {
        val response = RetrofitClient.api.addTodo(reja)
        return response.isSuccessful
    }


}
