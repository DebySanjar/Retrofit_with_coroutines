package com.samurai.retrofit_with_coroutines.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samurai.retrofit_with_coroutines.models.Reja
import com.samurai.retrofit_with_coroutines.models.Todo
import com.samurai.retrofit_with_coroutines.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {

    private val repository = TodoRepository()

    private val todoList = MutableLiveData<List<Todo>>()
    val list: LiveData<List<Todo>> get() = todoList

    private val loading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = loading


    private val er = MutableLiveData<String>()

    fun getAllTodos() {
        viewModelScope.launch(Dispatchers.IO) {

            loading.postValue(true)

            try {
                val todos = repository.getAllTodos()
                todoList.postValue(todos)
            } catch (e: Exception) {
                er.postValue(e.message)
            } finally {
                loading.postValue(false)
            }
        }
    }

    fun deleteTodo(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val success = repository.deleteTodo(id)
                if (success) {
                    getAllTodos()
                } else {
                    er.postValue("O‘chirish bo'lmadi!")
                }
            } catch (e: Exception) {
                er.postValue(e.message)
            }
        }
    }

    fun addTodo(reja: Reja) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.addTodo(reja)
                if (result) {
                    getAllTodos()
                } else {
                    er.postValue("Qo'shishda xatolik!")
                }
            } catch (e: Exception) {
                er.postValue(e.message)
            }
        }
    }


}
