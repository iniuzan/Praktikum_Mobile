package com.application.habit_tracker_app.data.repository

import com.application.habit_tracker_app.domain.entity.Task
import com.application.habit_tracker_app.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeTaskRepository: TaskRepository {
    private val tasks = mutableListOf<Task>()

    override suspend fun saveTask(task: Task) {
       tasks.add(task)
    }

    override suspend fun deleteTask(task: Task) {
        tasks.remove(task)
    }

    override suspend fun getTask(id: Int?): Task? {
        return tasks.find { it.id == id }
    }

    override fun getAllTasks(): Flow<List<Task>> {
       return flow { emit(tasks) }
    }
}