package com.application.habit_tracker_app.domain.use_cases

import com.application.habit_tracker_app.domain.repository.UserPreferenceRepository
import javax.inject.Inject

class DefaultUpdateShowCompleted @Inject constructor(val repository: UserPreferenceRepository): UpdateShowCompleted {
    override suspend operator fun invoke(show: Boolean) {
        repository.updateShowCompleted(show)
    }
}