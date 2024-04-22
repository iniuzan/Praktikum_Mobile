package com.application.habit_tracker_app.presentation.events

import com.application.habit_tracker_app.domain.entity.Task

sealed class TaskListEvent {
    data class DeleteTask(val task: Task): TaskListEvent()
    data class ShowCompletedTasks(val show: Boolean): TaskListEvent()
    data class ChangeSortByPriority(val enable: Boolean): TaskListEvent()
    data class ChangeSortByDeadline(val enable: Boolean): TaskListEvent()
}