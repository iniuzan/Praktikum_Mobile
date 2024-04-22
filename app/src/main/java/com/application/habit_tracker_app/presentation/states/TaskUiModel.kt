package com.application.habit_tracker_app.presentation.states

import com.application.habit_tracker_app.UserPreferences.SortOrder
import com.application.habit_tracker_app.domain.entity.Task

data class TaskUiModel(
    val tasks: List<Task>,
    val showCompleted: Boolean,
    val sortOrder: SortOrder
)
