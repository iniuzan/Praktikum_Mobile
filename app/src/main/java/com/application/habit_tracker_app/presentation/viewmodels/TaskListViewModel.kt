package com.application.habit_tracker_app.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.habit_tracker_app.UserPreferences
import com.application.habit_tracker_app.UserPreferences.SortOrder
import com.application.habit_tracker_app.domain.entity.Task
import com.application.habit_tracker_app.domain.entity.TaskStatus
import com.application.habit_tracker_app.domain.use_cases.*
import com.application.habit_tracker_app.presentation.events.TaskListEvent
import com.application.habit_tracker_app.presentation.states.TaskUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val getTasks: GetTasks,
    private val deleteTask: DeleteTask,
    private val updateShowCompleted: UpdateShowCompleted,
    private val enableSortByPriority: EnableSortByPriority,
    private val enableSortByDeadline: DefaultEnableSortByDeadline,
    private val getUserPreferences: GetUserPreferences
) : ViewModel() {

    private lateinit var userPreferencesFlow: Flow<UserPreferences>
    private lateinit var tasksFlow: Flow<List<Task>>

    init {
        viewModelScope.launch {
            userPreferencesFlow = getUserPreferences()
            tasksFlow = getTasks()
        }
    }

    private val tasksUiModelFlow = combine(
        tasksFlow,
        userPreferencesFlow
    ) { tasks: List<Task>, userPreferences: UserPreferences ->
        return@combine TaskUiModel(
            tasks = filterSortTasks(
                tasks,
                userPreferences.showCompleted,
                userPreferences.sortOrder
            ),
            showCompleted = userPreferences.showCompleted,
            sortOrder = userPreferences.sortOrder
        )
    }

    var tasksUiModelStateFlow = tasksUiModelFlow.stateIn(
        viewModelScope, SharingStarted.Lazily, TaskUiModel(
            emptyList(), false, SortOrder.NONE
        )
    )

    private fun filterSortTasks(
        tasks: List<Task>,
        showCompleted: Boolean,
        sortOrder: SortOrder
    ): List<Task> {
        val filteredTasks = if (showCompleted) {
            tasks
        } else {
            tasks.filter { it.status == TaskStatus.PENDING }
        }
        return when (sortOrder) {
            SortOrder.UNSPECIFIED -> filteredTasks
            SortOrder.NONE -> filteredTasks
            SortOrder.BY_DEADLINE -> filteredTasks.sortedBy { it.deadline }
            SortOrder.BY_PRIORITY -> filteredTasks.sortedBy { it.priority.ordinal }
            SortOrder.BY_DEADLINE_AND_PRIORITY -> filteredTasks.sortedWith(
                compareBy<Task> { it.deadline }.thenBy { it.priority.ordinal }
            )
           else -> throw UnsupportedOperationException("$sortOrder not supported")
        }
    }

    fun onEvent(event: TaskListEvent) {
        when (event) {
            is TaskListEvent.DeleteTask ->
                viewModelScope.launch { deleteTask(event.task) }
            is TaskListEvent.ShowCompletedTasks -> viewModelScope.launch {
                updateShowCompleted(event.show)
            }
            is TaskListEvent.ChangeSortByDeadline -> viewModelScope.launch {
                enableSortByDeadline(event.enable)
            }
            is TaskListEvent.ChangeSortByPriority -> viewModelScope.launch {
                enableSortByPriority(event.enable)
            }
        }
    }


}