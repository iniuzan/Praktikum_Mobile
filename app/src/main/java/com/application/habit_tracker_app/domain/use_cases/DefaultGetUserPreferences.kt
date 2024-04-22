package com.application.habit_tracker_app.domain.use_cases

import com.application.habit_tracker_app.UserPreferences
import com.application.habit_tracker_app.domain.repository.UserPreferenceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultGetUserPreferences @Inject constructor(val repository: UserPreferenceRepository): GetUserPreferences {
    override suspend operator fun invoke(): Flow<UserPreferences> {
        return repository.userPreferencesFlow
    }
}