package com.example.planner.data.repository

import com.example.planner.data.Task
import com.example.planner.data.TaskDao
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImplementation(private val dao: TaskDao) : TaskRepository {
    override suspend fun addTask(task: Task) {
        dao.addTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        dao.deleteTask(task)
    }

    override suspend fun getTaskById(id: Int): Task? {
        return dao.getTaskById(id)
    }

    override fun getTasks(): Flow<List<Task>> {
        return dao.getTasks()
    }
}