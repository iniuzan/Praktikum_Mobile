package com.application.habit_tracker_app.domain.use_cases

interface EnableSortByDeadline {
    suspend operator fun invoke(enable: Boolean)
}