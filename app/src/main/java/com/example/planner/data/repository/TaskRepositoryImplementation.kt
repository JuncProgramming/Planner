package com.example.planner.data.repository

import com.example.planner.data.Task
import com.example.planner.data.TaskDao
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val dao: TaskDao) {
    suspend fun addTask(task: Task) {
        dao.addTask(task)
    }

    suspend fun deleteTask(task: Task) {
        dao.deleteTask(task)
    }

    suspend fun getTaskById(id: Int): Task? {
        return dao.getTaskById(id)
    }

    fun getTasks(): Flow<List<Task>> {
        return dao.getTasks()
    }
}