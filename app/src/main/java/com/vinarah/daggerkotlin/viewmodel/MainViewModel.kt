package com.vinarah.daggerkotlin.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.content.SharedPreferences
import com.vinarah.daggerkotlin.model.Todo
import com.vinarah.daggerkotlin.repository.Repository
import com.vinarah.daggerkotlin.vo.Resource
import javax.inject.Inject

/**
 * @author Vincent
 * @since 2017/10/03
 */
class MainViewModel @Inject constructor(val repository: Repository): ViewModel(){

    val todos by lazy { repository.loadTodos() }

    fun saveTodo(todo: Todo)=repository.saveTodo(todo)
}