package com.practice.to_dolist.navigation

import android.util.Log
import androidx.navigation.NavHostController
import com.practice.to_dolist.util.Action
import com.practice.to_dolist.util.Constants.LIST_SCREEN

class Screens(navController: NavHostController) {
    val list: (Action) -> Unit = { action ->
        navController.navigate("list/${action.name}") {
            popUpTo(LIST_SCREEN) {inclusive = true}
        }
    }

    val task: (Int) -> Unit = { taskId ->
        navController.navigate("task/$taskId")
    }
}