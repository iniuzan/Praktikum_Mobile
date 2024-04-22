package com.application.habit_tracker_app.domain.entity

import androidx.compose.ui.graphics.Color
import com.application.habit_tracker_app.presentation.theme.BlueDark
import com.application.habit_tracker_app.presentation.theme.Green500
import com.application.habit_tracker_app.presentation.theme.BlueSea
import com.application.habit_tracker_app.presentation.theme.BlueBaby
import java.util.Date

data class Task(
    val id: Int,
    var name: String,
    var deadline: Date,
    var priority: TaskPriority,
    var status: TaskStatus
) {
    val bgColor: Color
        get() {
            return if (status == TaskStatus.COMPLETED) {
                Green500
            } else when (priority) {
                TaskPriority.LOW -> BlueDark
                TaskPriority.MEDIUM -> BlueSea
                TaskPriority.HIGH -> BlueBaby
            }
        }

    val contentColor: Color
        get() = Color.White
}