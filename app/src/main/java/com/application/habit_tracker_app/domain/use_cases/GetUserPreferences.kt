package com.application.habit_tracker_app.domain.use_cases

import com.application.habit_tracker_app.UserPreferences
import kotlinx.coroutines.flow.Flow

interface GetUserPreferences {
    suspend operator fun invoke(): Flow<UserPreferences>
}