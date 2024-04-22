package com.application.habit_tracker_app.domain.use_cases

interface UpdateShowCompleted {
    suspend operator fun invoke(show: Boolean)
}