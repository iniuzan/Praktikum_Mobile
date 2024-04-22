package com.application.habit_tracker_app.domain.use_cases

import com.application.habit_tracker_app.domain.entity.Task
import com.application.habit_tracker_app.domain.repository.TaskRepository
import javax.inject.Inject

class DefaultDeleteTask @Inject constructor(val repository: TaskRepository): DeleteTask{
    override suspend operator fun invoke(task: Task) {
        repository.deleteTask(task)
    }
}