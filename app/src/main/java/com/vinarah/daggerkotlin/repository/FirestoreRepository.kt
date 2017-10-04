package com.vinarah.daggerkotlin.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams

import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.*
import com.vinarah.daggerkotlin.model.Todo
import com.vinarah.daggerkotlin.vo.Resource


import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import java.util.*

/**
 * @author Vincent
 * *
 * @since 2017/10/04
 */

class FirestoreRepository : Repository {

    override fun saveTodo(todo: Todo) {
        val db = FirebaseFirestore.getInstance()
        db.collection(TODOS_REF).add(todo)
    }

    override fun loadTodos(): LiveData<Resource<List<Todo>>> {
        return LiveDataReactiveStreams.fromPublisher(getTodos())
    }

    private fun getTodos(): Flowable<Resource<List<Todo>>>{
        return Flowable.create<Resource<List<Todo>>>({ emitter ->
            val db = FirebaseFirestore.getInstance()
            emitter.onNext(Resource.loading())
            db.collection(TODOS_REF).addSnapshotListener(EventListener { documentSnapshots, firebaseFirestoreException ->
                if(firebaseFirestoreException != null){
                    emitter.onNext(Resource.error(firebaseFirestoreException.message))
                    return@EventListener
                }
                val todos = ArrayList<Todo>()
                if (documentSnapshots != null) {
                    for (documentSnapshot in documentSnapshots) {
                        val todo = documentSnapshot.toObject(Todo::class.java)
                        todos.add(todo)
                    }
                }
                emitter.onNext(if (todos.isEmpty()) Resource.empty()
                else Resource.success<List<Todo>>(todos))
            })
        }, BackpressureStrategy.BUFFER)
    }

    companion object {
        private val TODOS_REF = "todos"
    }
}
