package com.application.habit_tracker_app.data.repository

import com.application.habit_tracker_app.data.dao.TaskDao
import com.application.habit_tracker_app.data.model.toDBTask
import com.application.habit_tracker_app.data.model.toTask
import com.application.habit_tracker_app.domain.entity.Task
import com.application.habit_tracker_app.domain.repository.TaskRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DefaultTaskRepository @Inject constructor(
    private val taskDao: TaskDao) : TaskRepository {
    override suspend fun saveTask(task: Task) = taskDao.insert(task.toDBTask())
    override suspend fun deleteTask(task: Task) = taskDao.deleteTask(task.toDBTask())
    override suspend fun getTask(id: Int?): Task? = taskDao.getTask(id).toTask()
    override fun getAllTasks(): Flow<List<Task>> = taskDao.getTasks().map { list ->
        list.map { (it.toTask())!! }
    }
}