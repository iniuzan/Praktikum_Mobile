package com.application.habit_tracker_app.presentation.screens

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.application.habit_tracker_app.MainActivity
import com.application.habit_tracker_app.presentation.navigation.TaskScreens
import com.application.habit_tracker_app.presentation.theme.HabitTrackerTheme
import com.application.habit_tracker_app.util.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class TaskListTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @OptIn(ExperimentalComposeUiApi::class)
    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @OptIn(ExperimentalComposeUiApi::class)
    @Before
    fun setup() {
        hiltRule.inject()
        composeRule.setContent {
            HabitTrackerTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = TaskScreens.ListScreen.name) {
                    composable(route = TaskScreens.ListScreen.name) {
                        TaskList(navController = navController)
                    }
                }
            }

        }
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Test
    fun toggleShowCompletedSwitch() {
        composeRule.onNodeWithTag(TestTags.SHOW_COMPLETED_SWITCH).assertIsOff()
        composeRule.onNodeWithTag(TestTags.SHOW_COMPLETED_SWITCH).performClick()
        composeRule.onNodeWithTag(TestTags.SHOW_COMPLETED_SWITCH).assertIsOn()
    }

}