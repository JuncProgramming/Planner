package com.example.planner.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "task")
    var taskName: String,
    @ColumnInfo(name = "category")
    var categoryName: String
)