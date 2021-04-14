package com.ubaya.adv160718006week8.model

import androidx.room.*

@Dao
interface ToDoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg todo:Todo)
    @Query("SELECT * FROM todo")
    suspend fun selectAllTodo(): List<Todo>
    @Query("SELECT * FROM todo WHERE uuid= :id")
    suspend fun selectTodo(id:Int): Todo
    @Delete
    suspend fun deleteTodo(todo:Todo)
}