package com.vinarah.daggerkotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vinarah.daggerkotlin.R
import com.vinarah.daggerkotlin.model.Todo
import kotlinx.android.synthetic.main.todo_item.*

/**
 * @author Vincent
 * @since 2017/10/04
 */
class TodosAdapter: RecyclerView.Adapter<TodosAdapter.TodoViewHolder>() {

    var todos: List<Todo> = emptyList()

    fun setList(list: List<Todo>){
        todos = list
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: TodoViewHolder?, position: Int) {
        holder?.bindTo(todos.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TodoViewHolder=
            TodoViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.todo_item,
                    parent,false))

    override fun getItemCount(): Int {
        return todos.size
    }

    class TodoViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val taskNameTextView = itemView.findViewById<TextView>(R.id.taskNameTv)

        fun bindTo(todo: Todo?){
            taskNameTextView.text = "${todo?.name}"
        }
    }
}