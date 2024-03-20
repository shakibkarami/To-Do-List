package com.practice.to_dolist.ui.screens.list

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.practice.to_dolist.R
import com.practice.to_dolist.ui.viewmodels.SharedViewModel
import com.practice.to_dolist.util.SearchAppBarState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId : Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    LaunchedEffect(key1 = true){
        sharedViewModel.getAllTasks()
    }

    val action by sharedViewModel.action

    val allTasks by sharedViewModel.allTasks.collectAsState()
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState

    sharedViewModel.handleDatabaseActions(action = action)

    Scaffold(
        topBar = { ListAppBar(
            sharedViewModel = sharedViewModel,
            searchAppBarState = searchAppBarState,
            searchTextState = searchTextState
        ) },
        content = {
                  ListContent(tasks = allTasks, navigateToTaskScreen = navigateToTaskScreen)
        },
        floatingActionButton = {
            ListFab(navigateToTaskScreen = navigateToTaskScreen)
        }
    )
}

@Composable
fun ListFab(navigateToTaskScreen: (taskId : Int) -> Unit){
    FloatingActionButton(onClick = { navigateToTaskScreen(-1) }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = stringResource(R.string.add_task))
    }
}
