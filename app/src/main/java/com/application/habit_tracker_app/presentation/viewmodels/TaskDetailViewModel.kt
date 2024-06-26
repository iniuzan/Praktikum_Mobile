package com.application.habit_tracker_app.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.habit_tracker_app.domain.entity.Task
import com.application.habit_tracker_app.domain.entity.TaskPriority
import com.application.habit_tracker_app.domain.entity.TaskStatus
import com.application.habit_tracker_app.domain.use_cases.GetTask
import com.application.habit_tracker_app.domain.use_cases.SaveTask
import com.application.habit_tracker_app.presentation.events.TaskDetailEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val getTask: GetTask,
    private val saveTask: SaveTask
) : ViewModel() {

    private val _taskState = mutableStateOf(
        Task(
            0,
            "",
            Date(Calendar.getInstance().timeInMillis),
            TaskPriority.LOW,
            TaskStatus.PENDING
        )
    )

    val taskState: State<Task> = _taskState

    fun onEvent(event: TaskDetailEvent) {
        when (event) {
            is TaskDetailEvent.SaveTask -> viewModelScope.launch { saveTask(taskState.value) }
            is TaskDetailEvent.ChangeName -> _taskState.value = taskState.value.copy(name = event.newName)
            is TaskDetailEvent.ChangeDeadline -> _taskState.value = taskState.value.copy(deadline = event.newDate)
            is TaskDetailEvent.ChangePriority -> _taskState.value = taskState.value.copy(priority = event.newPriority)
            is TaskDetailEvent.ChangeStatus -> _taskState.value = taskState.value.copy(status = event.newStatus)
        }
    }

    fun fetchTask(taskId: Int) {
        if (taskId != -1) {
            viewModelScope.launch {
                getTask(taskId)?.also {
                    _taskState.value = it
                }
            }
        }
    }

}