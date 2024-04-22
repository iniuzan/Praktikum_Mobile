package com.application.habit_tracker_app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.application.habit_tracker_app.data.DateConverter
import com.application.habit_tracker_app.data.dao.TaskDao
import com.application.habit_tracker_app.data.model.MyTask

@Database(entities = [MyTask::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}