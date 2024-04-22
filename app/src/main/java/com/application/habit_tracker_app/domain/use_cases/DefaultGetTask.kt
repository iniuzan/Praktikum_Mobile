package com.application.habit_tracker_app.domain.use_cases

import com.application.habit_tracker_app.domain.entity.Task
import com.application.habit_tracker_app.domain.repository.TaskRepository
import javax.inject.Inject

class DefaultGetTask @Inject constructor(val repository: TaskRepository): GetTask {
    override suspend operator fun invoke(id: Int?): Task? {
        return repository.getTask(id)
    }
}