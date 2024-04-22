package com.application.habit_tracker_app.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import com.application.habit_tracker_app.domain.repository.UserPreferenceRepository
import com.application.habit_tracker_app.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException
import javax.inject.Inject


class DefaultUserPreferencesRepository @Inject constructor(private val userPreferencesStore: DataStore<UserPreferences>) :
    UserPreferenceRepository {

    private val TAG: String = "UserPrefRepoImpl"

    override val userPreferencesFlow: Flow<UserPreferences> = userPreferencesStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error reading sort order preferences.", exception)
                emit(UserPreferences.getDefaultInstance())
            } else {
                throw exception
            }
        }

    override suspend fun enableSortByDeadline(enable: Boolean) {
       userPreferencesStore.updateData { currentPreferences ->
            val currentOrder = currentPreferences.sortOrder
            val newSortOrder =
                if (enable) {
                    if (currentOrder == UserPreferences.SortOrder.BY_PRIORITY) {
                        UserPreferences.SortOrder.BY_DEADLINE_AND_PRIORITY
                    } else {
                        UserPreferences.SortOrder.BY_DEADLINE
                    }
                } else {
                    if (currentOrder == UserPreferences.SortOrder.BY_DEADLINE_AND_PRIORITY) {
                        UserPreferences.SortOrder.BY_PRIORITY
                    } else {
                        UserPreferences.SortOrder.NONE
                    }
                }
            currentPreferences.toBuilder().setSortOrder(newSortOrder).build()
        }
    }


    override suspend fun enableSortByPriority(enable: Boolean) {
       userPreferencesStore.updateData { currentPreferences ->
            val currentOrder = currentPreferences.sortOrder
            val newSortOrder =
                if (enable) {
                    if (currentOrder == UserPreferences.SortOrder.BY_DEADLINE) {
                        UserPreferences.SortOrder.BY_DEADLINE_AND_PRIORITY
                    } else {
                        UserPreferences.SortOrder.BY_PRIORITY
                    }
                } else {
                    if (currentOrder == UserPreferences.SortOrder.BY_DEADLINE_AND_PRIORITY) {
                        UserPreferences.SortOrder.BY_DEADLINE
                    } else {
                        UserPreferences.SortOrder.NONE
                    }
                }
            currentPreferences.toBuilder().setSortOrder(newSortOrder).build()
        }
    }

    override suspend fun updateShowCompleted(completed: Boolean) {
        userPreferencesStore.updateData { currentPreferences ->
            currentPreferences.toBuilder().setShowCompleted(completed).build()
        }
    }
}