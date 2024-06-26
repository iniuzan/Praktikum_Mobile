package com.application.habit_tracker_app.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.application.habit_tracker_app.R
import com.application.habit_tracker_app.domain.entity.TaskPriority
import com.application.habit_tracker_app.domain.entity.TaskStatus
import com.application.habit_tracker_app.presentation.components.*
import com.application.habit_tracker_app.presentation.events.TaskDetailEvent
import com.application.habit_tracker_app.presentation.viewmodels.TaskDetailViewModel
import com.application.habit_tracker_app.util.TestTags
import com.application.habit_tracker_app.util.getDateWithoutTime

@ExperimentalComposeUiApi
@Composable
fun TaskDetail(navController: NavController, taskId: Int?, viewModel: TaskDetailViewModel = hiltViewModel()) {
    val context = LocalContext.current

    taskId?.let {
        LaunchedEffect(key1 = taskId) {
            viewModel.fetchTask(taskId)
        }
    }

    val task = viewModel.taskState.value

    val name = task.name
    val selectedDate = task.deadline
    val status = task.status
    var priorityExpanded by remember { mutableStateOf(false) }
    val defaultPriorityIndex = TaskPriority.values().indexOf(task.priority)
    var selectedPriorityIndex = if (defaultPriorityIndex != -1) defaultPriorityIndex else 0

    Column {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        }, backgroundColor = MaterialTheme.colors.primary)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.Start
        ) {

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                TaskInputText(
                    text = name,
                    label = stringResource(R.string.label_add_task),
                    testTag = TestTags.TASK_NAME,
                    onTextChange = { viewModel.onEvent(TaskDetailEvent.ChangeName(it)) })
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.task_deadline),
                color = MaterialTheme.colors.onBackground
            )
            TaskDatePicker(selectedDate,
                testTag = TestTags.TASK_DEADLINE) { date ->
                viewModel.onEvent(TaskDetailEvent.ChangeDeadline(date.getDateWithoutTime()))
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.task_priority),
                color = MaterialTheme.colors.onBackground
            )

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TaskMenu(
                    menuItems = TaskPriority.getList(),
                    menuExpandedState = priorityExpanded,
                    selectedIndex = selectedPriorityIndex,
                    testTag = TestTags.TASK_PRIORITY,
                    updateMenuExpandStatus = {
                        priorityExpanded = true
                    },
                    onDismissMenuView = {
                        priorityExpanded = false
                    },
                    onMenuItemClick = { index ->
                        selectedPriorityIndex = index
                        priorityExpanded = false
                        viewModel.onEvent(TaskDetailEvent.ChangePriority(TaskPriority.values()[selectedPriorityIndex]))
                    }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.completed),
                    modifier = Modifier.padding(end = 8.dp)
                )
                Checkbox(
                    checked = status == TaskStatus.COMPLETED,
                    onCheckedChange = { isChecked ->
                        viewModel.onEvent(TaskDetailEvent.ChangeStatus(if (isChecked) TaskStatus.COMPLETED else TaskStatus.PENDING))
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colors.primary,
                        uncheckedColor = MaterialTheme.colors.onBackground
                    ),
                    modifier = Modifier.testTag(TestTags.TASK_STATUS)
                )
            }


            Spacer(modifier = Modifier.height(20.dp))

            val taskUpdatedMsg = stringResource(R.string.task_updated)
            val taskAddedMsg = stringResource(R.string.task_added)
            val addTaskMsg = stringResource(R.string.add_task_first)

            TaskButton(text = stringResource(R.string.save), modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
                onClick = {
                    val toastMessage: String
                    if (task.name.isNotEmpty()) {
                        toastMessage = if (task.id != 0) {
                            taskUpdatedMsg
                        } else {
                            taskAddedMsg
                        }
                        viewModel.onEvent(TaskDetailEvent.SaveTask)
                        navController.navigateUp()
                    } else {
                        toastMessage = addTaskMsg
                    }
                    Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
                })
        }

    }

}
