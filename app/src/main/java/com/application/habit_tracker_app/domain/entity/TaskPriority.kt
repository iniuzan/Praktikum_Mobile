package com.application.habit_tracker_app.domain.entity

enum class TaskPriority {
    HIGH, MEDIUM, LOW;

    companion object {
        fun getList(): List<String> {
            return values().map {
                it.name
            }
        }
    }
}