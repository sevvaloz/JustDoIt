package com.example.justdoit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoadapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoadapter = TodoAdapter(arrayListOf()) //recyclerview'un ilk basta bos olması icin arrayListOf degiskenini verdik
        recyclerView.adapter =  todoadapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        addButton.setOnClickListener {
            val todotitle = todoText.text.toString()
            if(todotitle.isNotEmpty()){
                val todo = Todo(todotitle)
                todoadapter.AddTodo(todo)
                todoText.text.clear()
            }
        }

        deleteButton.setOnClickListener {
            todoadapter.DeleteTodo()
        }

    }
}