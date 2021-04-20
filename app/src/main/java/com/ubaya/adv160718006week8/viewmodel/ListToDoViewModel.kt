package com.ubaya.adv160718006week8.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.ubaya.adv160718006week8.model.Todo
import com.ubaya.adv160718006week8.model.TodoDatabase
import com.ubaya.adv160718006week8.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListToDoViewModel(application: Application):AndroidViewModel(application),CoroutineScope {
    val todoLD = MutableLiveData<List<Todo>>()
    val todoLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    fun refresh(){
        loadingLD.value = true
        todoLoadErrorLD.value = false
        launch {
            val db = buildDB(getApplication())
            todoLD.value = db.todoDao().selectAllTodo()
        }
    }
    fun clearTask(todo: Todo) {
        launch {
            val db = buildDB(getApplication())
            db.todoDao().deleteTodo(todo)
            todoLD.value = db.todoDao().selectAllTodo()
        }
    }
    fun isDone(uuid:Int) {
        launch {
            val db = buildDB(getApplication())
            db.todoDao().is_done(uuid)
            todoLD.value = db.todoDao().selectAllTodo()
        }
    }
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}