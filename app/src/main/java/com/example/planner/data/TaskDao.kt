package com.example.planner.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: Task)

    @Query("SELECT * from task ORDER BY id ASC")
    fun readAllData(): LiveData<List<Task>>

    @Query("SELECT * from task WHERE id = :id")
    fun getItem(id: Int): Flow<Task>

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

}