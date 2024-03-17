package com.practice.to_dolist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.practice.to_dolist.navigation.destinations.listComposable
import com.practice.to_dolist.navigation.destinations.taskComposable
import com.practice.to_dolist.ui.viewmodels.SharedViewModel
import com.practice.to_dolist.util.Constants.LIST_SCREEN

@Composable
fun SetupNavigation (
    navController: NavHostController,
    sharedViewModel: SharedViewModel
){
    val screen = remember(navController) {
        Screens(navController = navController)
    }
    
    NavHost(navController = navController,
        startDestination = LIST_SCREEN
    ){
        listComposable(
            navigateToTaskScreen = screen.task,
            sharedViewModel = sharedViewModel
        )
        taskComposable(
            navigateToListScreen = screen.list,
            sharedViewModel = sharedViewModel)
    }

}