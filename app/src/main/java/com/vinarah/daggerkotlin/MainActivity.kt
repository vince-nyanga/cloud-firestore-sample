package com.vinarah.daggerkotlin

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.vinarah.daggerkotlin.adapter.TodosAdapter
import com.vinarah.daggerkotlin.di.Injectable
import com.vinarah.daggerkotlin.model.Todo
import com.vinarah.daggerkotlin.viewmodel.MainViewModel
import com.vinarah.daggerkotlin.viewmodel.ViewModelFactory
import com.vinarah.daggerkotlin.vo.Resource
import java.lang.Exception
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Injectable {


    @Inject lateinit var preferences: SharedPreferences

    @Inject lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    private val taskNameEditText by lazy { findViewById<TextInputEditText>(R.id.taskNameEt) }

    private val adapter by lazy { TodosAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter
        subscribeToData()
    }

    private fun subscribeToData() {
        viewModel.todos.observe(this, Observer<Resource<List<Todo>>> { todoResource ->
            when (todoResource?.status) {
                Resource.EMPTY -> {
                    Toast.makeText(applicationContext, "Empty list", Toast.LENGTH_LONG).show()
                }
                Resource.LOADING -> {
                   // update ui
                }
                Resource.SUCCESS -> {
                    adapter.setList(todoResource.value)
                }
                Resource.ERROR -> {
                    Toast.makeText(applicationContext, "ERROR : ${todoResource.message}", Toast.LENGTH_LONG).show()
                }
            }
        })

    }


    fun saveTodo(view: View) {

        val taskName = taskNameEditText.text.toString()
        if(!TextUtils.isEmpty(taskName)){
            val todo = Todo(taskName)
            viewModel.saveTodo(todo)
            taskNameEditText.setText("")
        }
    }

}
