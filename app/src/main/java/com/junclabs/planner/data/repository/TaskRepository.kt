package com.junclabs.planner.data.repository

import com.junclabs.planner.data.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun addTask(task: Task)

    suspend fun deleteTask(task: Task)

    suspend fun getTaskById(id: Int): Task?

    fun getTasks(): Flow<List<Task>>
}