package com.junclabs.planner.ui

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SnackBarController(private val coroutineScope: CoroutineScope) {
    private var snackBarJob: Job? = null

    init {
        cancelActiveJob()
    }

    suspend fun showSnackBar(
        snackBarHostState: SnackbarHostState,
        message: String,
        actionLabel: String? = null,
        duration: SnackbarDuration
    ): SnackbarResult {
        var result: SnackbarResult = SnackbarResult.Dismissed
        if (snackBarJob == null) {
            snackBarJob = coroutineScope.launch {
                result = snackBarHostState.showSnackbar(
                    message = message,
                    actionLabel = actionLabel,
                    duration = duration
                )
                cancelActiveJob()
            }
        } else {
            cancelActiveJob()
            snackBarJob = coroutineScope.launch {
                result = snackBarHostState.showSnackbar(
                    message = message,
                    actionLabel = actionLabel,
                    duration = duration
                )
                cancelActiveJob()
            }
        }
        snackBarJob?.join()
        return result
    }

    fun cancelActiveJob() {
        snackBarJob?.let { job ->
            job.cancel()
            snackBarJob = Job()
        }
    }
}