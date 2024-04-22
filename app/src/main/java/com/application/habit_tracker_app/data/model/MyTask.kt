package com.application.habit_tracker_app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.application.habit_tracker_app.domain.entity.Task
import com.application.habit_tracker_app.domain.entity.TaskPriority
import com.application.habit_tracker_app.domain.entity.TaskStatus
import java.util.Date

@Entity(tableName = "task_tbl")
data class MyTask(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String,
    var deadline: Date,
    var priority: TaskPriority,
    var status: TaskStatus
)

fun MyTask?.toTask(): Task? = this?.let {
    Task(id = it.id, name = it.name, deadline = it.deadline, priority = it.priority, status = it.status)
}

fun Task.toDBTask(): MyTask = MyTask(id = this.id, name = this.name, deadline = this.deadline, priority = this.priority, status = this.status)
