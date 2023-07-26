package com

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.junclabs.planner.ui.task_list.TasksListScreen
import org.junit.Rule
import org.junit.Test

class Test {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun enterTask_showsTask() {
        rule.setContent { TasksListScreen(onNavigate = {  }) }

        rule.onNodeWithText("+").performClick()
    }
}