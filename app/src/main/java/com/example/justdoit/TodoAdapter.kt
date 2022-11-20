package com.example.justdoit

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.icu.text.CaseMap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_row.view.*

class TodoAdapter(private val todos: ArrayList<Todo>): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentToDo = todos[position]
        holder.itemView.apply {
            todoTitle.text = currentToDo.title
            checkBox.isChecked = currentToDo.isChecked
            StrikeThrough(todoTitle, currentToDo.isChecked)

            //checkbox'a her tıklandıgında isChecked bilgisini guncellemek
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                currentToDo.isChecked = !currentToDo.isChecked
                StrikeThrough(todoTitle, isChecked)
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }


    //to-do'nun ustunu cizmek
    private fun StrikeThrough(todoTitle: TextView, isChecked: Boolean){
        if(isChecked){
            todoTitle.paintFlags = todoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else{
            todoTitle.paintFlags = todoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    //isChecked true ise onu listeden silmek
    fun DeleteTodo(){
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    //listeye eklemek
    fun AddTodo(todo: Todo){
        todos.add(todo)
        notifyItemInserted(todos.size - 1) //listenin son elemanının index'i verilir
    }



}