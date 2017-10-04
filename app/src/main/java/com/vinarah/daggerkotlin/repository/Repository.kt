package com.vinarah.daggerkotlin.repository

import android.arch.lifecycle.LiveData
import com.vinarah.daggerkotlin.model.Todo
import com.vinarah.daggerkotlin.vo.Resource

/**
 * @author Vincent
 * @since 2017/10/04
 */
interface Repository {
    fun saveTodo(todo: Todo)
    fun loadTodos(): LiveData<Resource<List<Todo>>>
}