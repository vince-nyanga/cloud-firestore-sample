package com.vinarah.daggerkotlin.repository


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import com.google.firebase.firestore.*
import com.vinarah.daggerkotlin.model.Todo
import com.vinarah.daggerkotlin.vo.Resource
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * @author Vincent
 * *
 * @since 2017/10/04
 */

class FirestoreRepository : Repository {

    private val db by lazy { FirebaseFirestore.getInstance() }

    override fun saveTodo(todo: Todo) {
        val documentRef = db.collection(TODOS_REF).document()
        todo.id = documentRef.id
        documentRef.set(todo)
    }

    override fun loadTodos(): LiveData<Resource<List<Todo>>> {
        return LiveDataReactiveStreams.fromPublisher(getTodos().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()))
    }

    private fun getTodos(): Flowable<Resource<List<Todo>>> {
        return Flowable.create<Resource<List<Todo>>>({ emitter ->
            emitter.onNext(Resource.loading())
            db.collection(TODOS_REF).orderBy("done").addSnapshotListener(EventListener { documentSnapshots, firebaseFirestoreException ->
                if (firebaseFirestoreException != null) {
                    emitter.onNext(Resource.error(firebaseFirestoreException.message))
                    return@EventListener
                }
                val todos = ArrayList<Todo>()
                documentSnapshots?.mapTo(todos) { it.toObject(Todo::class.java) }
                emitter.onNext(if (todos.isEmpty()) Resource.empty() else Resource.success<List<Todo>>(todos))
            })
        }, BackpressureStrategy.BUFFER)
    }

    override fun updateTodo(todo: Todo) {
        db.collection(TODOS_REF).document(todo.id).update("done", todo.done)
    }

    override fun deleteTodo(todo: Todo) {
        db.collection(TODOS_REF).document(todo.id).delete()
    }

    companion object {
        private val TODOS_REF = "todos"
    }
}
