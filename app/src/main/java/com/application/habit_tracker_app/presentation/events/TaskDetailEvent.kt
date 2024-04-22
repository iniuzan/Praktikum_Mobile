package com.application.habit_tracker_app.presentation.events

import com.application.habit_tracker_app.domain.entity.TaskPriority
import com.application.habit_tracker_app.domain.entity.TaskStatus
import java.util.*

sealed class TaskDetailEvent {
    data class ChangeDeadline(val newDate: Date): TaskDetailEvent()
    data class ChangeName(val newName: String): TaskDetailEvent()
    data class ChangePriority(val newPriority: TaskPriority): TaskDetailEvent()
    data class ChangeStatus(val newStatus: TaskStatus): TaskDetailEvent()
    object SaveTask: TaskDetailEvent()
}