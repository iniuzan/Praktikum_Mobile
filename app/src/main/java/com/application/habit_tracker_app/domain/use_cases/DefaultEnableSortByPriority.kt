package com.application.habit_tracker_app.domain.use_cases

import com.application.habit_tracker_app.domain.repository.UserPreferenceRepository
import javax.inject.Inject

class DefaultEnableSortByPriority @Inject constructor(val repository: UserPreferenceRepository): EnableSortByPriority {
    override suspend operator fun invoke(enable: Boolean) {
        repository.enableSortByPriority(enable)
    }
}