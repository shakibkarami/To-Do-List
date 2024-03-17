package com.practice.to_dolist.ui.screens.task

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.practice.to_dolist.data.models.Task
import com.practice.to_dolist.util.Action

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    selectedTask: Task?,
    navigateToListScreen: (Action) -> Unit
){
    Scaffold(
        topBar = {
                 TaskAppBar(navigateToListScreen = navigateToListScreen, selectedTask = selectedTask)
        },
        content = {}
    )
}