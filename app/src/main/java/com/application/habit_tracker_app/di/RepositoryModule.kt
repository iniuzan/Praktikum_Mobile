package com.application.habit_tracker_app.di

import com.application.habit_tracker_app.data.dao.TaskDao
import com.application.habit_tracker_app.data.repository.DefaultTaskRepository
import com.application.habit_tracker_app.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun providesTaskRepository(taskDao: TaskDao): TaskRepository = DefaultTaskRepository(taskDao)

}