package com.practice.to_dolist.navigation.destinations

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.practice.to_dolist.ui.screens.list.ListScreen
import com.practice.to_dolist.ui.viewmodels.SharedViewModel
import com.practice.to_dolist.util.Constants.LIST_ARGUMENT_KEY
import com.practice.to_dolist.util.Constants.LIST_SCREEN
import com.practice.to_dolist.util.toAction

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskId : Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY){
            type = NavType.StringType
        })
    ){navBackStackEntry ->
        val action = navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY)?.toAction()

        LaunchedEffect(key1 = action){
            if (action != null) {
                sharedViewModel.action.value = action
            }
        }

        ListScreen(
            navigateToTaskScreen = navigateToTaskScreen,
            sharedViewModel = sharedViewModel)
    }
}