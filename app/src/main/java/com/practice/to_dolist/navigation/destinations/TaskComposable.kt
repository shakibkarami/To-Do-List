package com.practice.to_dolist.navigation.destinations

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.practice.to_dolist.ui.screens.task.TaskScreen
import com.practice.to_dolist.ui.viewmodels.SharedViewModel
import com.practice.to_dolist.util.Action
import com.practice.to_dolist.util.Constants.TASK_ARGUMENT_KEY
import com.practice.to_dolist.util.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY){
            type = NavType.StringType
        })
    ){navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getString(TASK_ARGUMENT_KEY)
        val intTaskId = taskId?.toInt()
        if (intTaskId != null) {
            sharedViewModel.getSelectedTask(intTaskId)
        }
        val selectedTask by sharedViewModel.selectedTask.collectAsState()
        
        LaunchedEffect(key1 = intTaskId) {
            sharedViewModel.updateTaskFields(selectedTask)
        }
        
        TaskScreen(sharedViewModel = sharedViewModel, navigateToListScreen = navigateToListScreen, selectedTask = selectedTask)
    }
}