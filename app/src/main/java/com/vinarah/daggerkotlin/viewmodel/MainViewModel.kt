package com.vinarah.daggerkotlin.viewmodel

import android.arch.lifecycle.ViewModel
import com.vinarah.daggerkotlin.model.Todo
import com.vinarah.daggerkotlin.repository.Repository
import javax.inject.Inject

/**
 * @author Vincent
 * @since 2017/10/03
 */
class MainViewModel @Inject constructor(val repository: Repository): ViewModel(){

    val todos by lazy { repository.loadTodos() }

    fun saveTodo(todo: Todo)=repository.saveTodo(todo)

    fun  updateTodo(todo: Todo) = repository.updateTodo(todo)
}