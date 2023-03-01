package com.junclabs.planner.data.repository

import com.junclabs.planner.data.Task
import com.junclabs.planner.data.TaskDao
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