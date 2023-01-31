package com.example.planner.data

import androidx.lifecycle.LiveData


class TaskRepository(private val dao: TaskDao) {

    val readAllData: LiveData<List<Task>> = dao.readAllData()

    suspend fun addTask(user: Task){
        dao.addTask(user)
    }

    suspend fun updateTask(user: Task){
        dao.updateTask(user)
    }

    suspend fun deleteTask(user: Task){
        dao.deleteTask(user)
    }
}