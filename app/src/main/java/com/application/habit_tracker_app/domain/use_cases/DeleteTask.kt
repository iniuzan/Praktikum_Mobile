package com.application.habit_tracker_app.domain.use_cases

import com.application.habit_tracker_app.domain.entity.Task

interface DeleteTask {
    suspend operator fun invoke(task: Task)
}