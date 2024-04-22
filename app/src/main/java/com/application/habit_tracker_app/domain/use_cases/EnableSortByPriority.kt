package com.application.habit_tracker_app.domain.use_cases

interface EnableSortByPriority {
    suspend operator fun invoke(enable: Boolean)
}