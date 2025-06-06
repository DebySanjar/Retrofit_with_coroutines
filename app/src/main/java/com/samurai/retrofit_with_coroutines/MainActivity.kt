package com.samurai.retrofit_with_coroutines

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.samurai.retrofit_with_coroutines.adapters.TodoAdapter
import com.samurai.retrofit_with_coroutines.databinding.ActivityMainBinding
import com.samurai.retrofit_with_coroutines.models.Reja
import com.samurai.retrofit_with_coroutines.models.Todo
import com.samurai.retrofit_with_coroutines.utils.Status
import com.samurai.retrofit_with_coroutines.vm.TodoViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var todoAdapter: TodoAdapter
    private lateinit var todoViewModel: TodoViewModel

    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        todoAdapter = TodoAdapter(emptyList(), onDeleteClick = { todo ->
            todoViewModel.deleteTodo(todo.id)
        })


        binding.recy.adapter = todoAdapter


        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]

        todoViewModel.getAllTodos()

       todoViewModel.getAllTodos().observe(this) {
           when(it.status){
               Status.ERROR -> {

               }
               Status.LOADING -> {
                   binding.swipeRefresh.isRefreshing = true
               }

               Status.SUCCESS -> {
                   binding.swipeRefresh.isRefreshing = false
                   todoAdapter.updateList(it.body as List<Todo>)
               }

           }
       }


        binding.swipeRefresh.setOnRefreshListener {
            todoViewModel.getAllTodos()
        }

//        todoViewModel.isLoading.observe(this) { isLoading ->
//            binding.swipeRefresh.isRefreshing = isLoading
//        }


        binding.swipeRefresh.setColorSchemeResources(
            R.color.refresh_color4
        )

        binding.swipeRefresh.setProgressViewOffset(
            true,
            0,
            150
        )





        binding.fab.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.dialog_add_todo, null)
            val dialog = AlertDialog.Builder(this)
                .setTitle("Yangi Todo qo'shish")
                .setView(dialogView)
                .setPositiveButton("Saqlash", null)
                .setNegativeButton("Bekor qilish", null)
                .create()

            dialog.setOnShowListener {
                val btnSave = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                btnSave.setOnClickListener {
                    val title = dialogView.findViewById<EditText>(R.id.etTitle).text.toString()
                    val desc = dialogView.findViewById<EditText>(R.id.etDesc).text.toString()
                    val isDone = dialogView.findViewById<CheckBox>(R.id.cbDone).isChecked

                    if (title.isNotBlank() && desc.isNotBlank()) {
                        val todo = Reja(title, desc, isDone)
                        todoViewModel.addTodo(todo)
                        dialog.dismiss()
                    } else {
                        Snackbar.make(
                            binding.root,
                            "Maydonlarni to'ldiring!",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }

                }
            }

            dialog.show()
        }

    }

}
