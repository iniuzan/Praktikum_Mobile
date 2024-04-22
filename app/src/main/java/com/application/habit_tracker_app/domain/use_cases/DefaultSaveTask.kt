package com.application.habit_tracker_app.domain.use_cases

import com.application.habit_tracker_app.domain.entity.Task
import com.application.habit_tracker_app.domain.repository.TaskRepository
import javax.inject.Inject

class DefaultSaveTask @Inject constructor(val repository: TaskRepository): SaveTask {
    override suspend operator fun invoke(task: Task) {
        repository.saveTask(task)
    }
}