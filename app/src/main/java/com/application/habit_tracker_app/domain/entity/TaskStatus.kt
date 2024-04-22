package com.application.habit_tracker_app.domain.entity

enum class TaskStatus {
    PENDING, COMPLETED;

    companion object {
        fun getList(): List<String> {
            return values().map {
                it.name
            }
        }
    }
}