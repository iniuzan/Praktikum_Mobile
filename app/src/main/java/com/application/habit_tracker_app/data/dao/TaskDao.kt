package com.application.habit_tracker_app.data.dao

import androidx.room.*
import com.application.habit_tracker_app.data.model.MyTask
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_tbl ORDER BY id DESC")
    fun getTasks(): Flow<List<MyTask>>

    @Query("SELECT * FROM task_tbl where id = :id")
    suspend fun getTask(id: Int?): MyTask?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: MyTask)

    @Query("DELETE FROM task_tbl")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteTask(task: MyTask)
}
