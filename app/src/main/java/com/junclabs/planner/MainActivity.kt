package com.junclabs.planner

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.junclabs.planner.ui.theme.PlannerTheme
import com.junclabs.planner.navigation.Navigation
import com.junclabs.planner.ui.task_list.TaskListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val uiState = TaskListViewModel.TaskListUiState()
            val isDarkMode = uiState.isDarkMode
            PlannerTheme(darkTheme = isDarkMode) {
                Surface(
                    color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()
                ) {
                    navController = rememberNavController()
                    Navigation(navController = navController)
                }
            }
        }
    }
}