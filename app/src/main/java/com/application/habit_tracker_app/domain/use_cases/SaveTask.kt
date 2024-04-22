package com.application.habit_tracker_app.domain.use_cases

import com.application.habit_tracker_app.domain.entity.Task

interface SaveTask {
    suspend operator fun invoke(task: Task)
}