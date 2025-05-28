package com.samurai.retrofit_with_coroutines.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samurai.retrofit_with_coroutines.models.Reja
import com.samurai.retrofit_with_coroutines.models.Todo
import com.samurai.retrofit_with_coroutines.repository.TodoRepository
import com.samurai.retrofit_with_coroutines.utils.Recourse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {

    private val repository = TodoRepository()

    private val todoList = MutableLiveData<List<Todo>>()
    val list: LiveData<List<Todo>> get() = todoList

    private val liveTodo = MutableLiveData<Recourse<Any>>()

    private val loading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = loading


    private val er = MutableLiveData<String>()

    fun getAllTodos() : MutableLiveData<Recourse<Any>>{
        viewModelScope.launch(Dispatchers.IO) {

            liveTodo.postValue(Recourse.loading())

            try {
                val todos = repository.getAllTodos()
                liveTodo.postValue(Recourse.success(todos))
            } catch (e: Exception) {
                liveTodo.postValue(Recourse.error(e.message))
            } finally {
//                loading.postValue(false)
            }
        }
        return liveTodo
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
