package com.junclabs.planner

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.junclabs.planner.data.UserPreferencesRepository
import dagger.hilt.android.HiltAndroidApp

private const val MODE_PREFERENCE_NAME = "mode_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = MODE_PREFERENCE_NAME
)

@HiltAndroidApp
class TaskApp : Application() {
    lateinit var userPreferencesRepository: UserPreferencesRepository

    override fun onCreate() {
        super.onCreate()
        instance = this
        userPreferencesRepository = UserPreferencesRepository(dataStore)
    }

    val context: Context
        get() = this

    companion object {
        var instance: TaskApp? = null
            private set
    }
}