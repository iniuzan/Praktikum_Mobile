package com.application.habit_tracker_app.domain.use_cases

import com.application.habit_tracker_app.domain.repository.UserPreferenceRepository
import javax.inject.Inject

class DefaultEnableSortByDeadline @Inject constructor(val repository: UserPreferenceRepository): EnableSortByDeadline {
    override suspend operator fun invoke(enable: Boolean) {
        repository.enableSortByDeadline(enable)
    }
}