package com.practice.to_dolist.data.repositories

import com.practice.to_dolist.data.ToDoDao
import com.practice.to_dolist.data.models.Task
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class ToDoRepository @Inject constructor(private val toDoDao: ToDoDao) {

    val getAllTasks: Flow<List<Task>> = toDoDao.getAllTasks()
    val sortByLowPriority: Flow<List<Task>> = toDoDao.sortByLowPriority()
    val sortByHighPriority: Flow<List<Task>> = toDoDao.sortByHighPriority()

    fun getTask(taskId: Int): Flow<Task>{
        return toDoDao.getTask(taskId)
    }

    suspend fun addTask(task: Task) {
        toDoDao.addTask(task)
    }

    suspend fun updateTask(task: Task) {
        toDoDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task) {
        toDoDao.deleteTask(task)
    }

    suspend fun deleteAllTasks() {
        toDoDao.deleteAllTasks()
    }

    fun searchDatabase(searchQuery: String) {
        toDoDao.searchDatabase(searchQuery)
    }

}