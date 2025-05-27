package com.samurai.retrofit_with_coroutines.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samurai.retrofit_with_coroutines.databinding.ItemRecyBinding
import com.samurai.retrofit_with_coroutines.models.Todo
import androidx.core.graphics.toColorInt

class TodoAdapter(
    private var todos: List<Todo>,  val onDeleteClick: (Todo) -> Unit
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(val binding: ItemRecyBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemRecyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun getItemCount(): Int = todos.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todos[position]
        holder.binding.itemTitle.text = todo.sarlavha
        holder.binding.itemDesc.text = todo.sarlavha
        holder.binding.itemTime.text = todo.sana

        var rang = if (todo.bajarildi) "#4CAF50" else "#F44336"
        holder.binding.parentbg.setBackgroundColor(rang.toColorInt())

        holder.binding.btnDelete.setOnClickListener {
            onDeleteClick(todo)
        }
    }

    fun updateList(newList: List<Todo>) {
        todos = newList
        notifyDataSetChanged()
    }
}
