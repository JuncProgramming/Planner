package com.junclabs.planner.dI

import android.app.Application
import androidx.room.Room
import com.junclabs.planner.data.TaskDatabase
import com.junclabs.planner.data.repository.TaskRepositoryImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTaskDatabase(app: Application): TaskDatabase {
        return Room.databaseBuilder(app, TaskDatabase::class.java, "task_database").build()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(database: TaskDatabase): TaskRepositoryImplementation {
        return TaskRepositoryImplementation(database.dao)
    }
}