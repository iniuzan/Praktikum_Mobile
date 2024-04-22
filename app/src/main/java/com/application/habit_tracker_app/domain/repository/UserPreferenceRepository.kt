package com.application.habit_tracker_app.domain.repository

import com.application.habit_tracker_app.UserPreferences
import kotlinx.coroutines.flow.Flow

interface UserPreferenceRepository {
  val userPreferencesFlow: Flow<UserPreferences>

  suspend fun enableSortByDeadline(enable: Boolean)

  suspend fun enableSortByPriority(enable: Boolean)

  suspend fun updateShowCompleted(completed: Boolean)
}