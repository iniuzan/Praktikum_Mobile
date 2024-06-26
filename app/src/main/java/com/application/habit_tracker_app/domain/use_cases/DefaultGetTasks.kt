package com.application.habit_tracker_app.domain.use_cases

import com.application.habit_tracker_app.domain.entity.Task
import com.application.habit_tracker_app.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultGetTasks @Inject constructor(val repository: TaskRepository): GetTasks {
     override suspend operator fun invoke(): Flow<List<Task>> {
        return repository.getAllTasks()
    }
}