package com.application.habit_tracker_app.domain.use_cases

import com.application.habit_tracker_app.domain.entity.Task
import kotlinx.coroutines.flow.Flow

interface GetTasks {
    suspend operator fun invoke(): Flow<List<Task>>
}