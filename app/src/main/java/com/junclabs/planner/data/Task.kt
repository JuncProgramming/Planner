package com.junclabs.planner.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Task(
    val taskName: String, val categoryName: String?, @PrimaryKey val id: Int? = null
)