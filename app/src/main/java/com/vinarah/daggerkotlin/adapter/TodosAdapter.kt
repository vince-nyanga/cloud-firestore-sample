package com.vinarah.daggerkotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.vinarah.daggerkotlin.R
import com.vinarah.daggerkotlin.model.Todo

/**
 * @author Vincent
 * @since 2017/10/04
 */
class TodosAdapter: RecyclerView.Adapter<TodosAdapter.TodoViewHolder>() {

    var todos: List<Todo> = emptyList()

    var todoClickListener: OnTodoClickListener ? =  null

    fun setList(list: List<Todo>){
        todos = list
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: TodoViewHolder?, position: Int) {
        holder?.bindTo(todos.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TodoViewHolder {
        val holder =TodoViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.todo_item,
        parent, false))
        holder.todoClickListener  = todoClickListener
        return holder
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    class TodoViewHolder(view: View): RecyclerView.ViewHolder(view){

        var todoClickListener: OnTodoClickListener ? =  null

        private val taskNameTextView =itemView.findViewById<TextView>(R.id.taskNameTv)
        private val doneImageView = itemView.findViewById<ImageView>(R.id.doneIv)
        private val deleteImageView = itemView.findViewById<ImageView>(R.id.deleteIv)

        fun bindTo(todo: Todo){
            taskNameTextView.text = "${todo.name}"
            doneImageView.visibility = if (todo.done) View.VISIBLE else View.GONE
            itemView.setOnClickListener { view -> todoClickListener?.onTodoClicked(todo) }
            deleteImageView.setOnClickListener{view -> todoClickListener?.onDeleteTodoClicked(todo) }
        }
    }

    interface OnTodoClickListener {
        fun onTodoClicked(todo: Todo)
        fun onDeleteTodoClicked(todo: Todo)
    }
}