package com.practice.to_dolist.ui.screens.task

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.practice.to_dolist.R
import com.practice.to_dolist.data.models.Task
import com.practice.to_dolist.util.Action

@Composable
fun TaskAppBar(
    navigateToListScreen: (Action) -> Unit
){
    NewTaskAppBar(navigateToListScreen = navigateToListScreen)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskAppBar(
    navigateToListScreen: (Action) -> Unit
){
    TopAppBar(navigationIcon = {
        BackAction(onBackClicked = navigateToListScreen)
    },
        title = {
            Text(text = stringResource(R.string.add_task))
        },
        actions = {
            AddAction(onAddClicked = navigateToListScreen)
        }

    )
}

@Composable
fun BackAction(
    onBackClicked: (Action) -> Unit
){
    IconButton(onClick = { onBackClicked(Action.NO_ACTION) }) {
        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(R.string.back_arrow))

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExistingTaskAppBar(
    navigateToListScreen: (Action) -> Unit,
    selectedTask: Task
){
    TopAppBar(navigationIcon = {
                               CloseAction(onCloseClicked = navigateToListScreen)
    },
        title = {
            Text(text = selectedTask.title, maxLines = 1, overflow = TextOverflow.Ellipsis)
        },
        actions = {
            DeleteAction(onDeleteClicked = navigateToListScreen)
            UpdateAction(onUpdateClicked = navigateToListScreen)
        }

    )
}

@Composable
fun CloseAction(
    onCloseClicked: (Action) -> Unit
){
    IconButton(onClick = { onCloseClicked(Action.NO_ACTION) }) {
        Icon(imageVector = Icons.Filled.Close, contentDescription = stringResource(R.string.close_icon))

    }
}

@Composable
fun DeleteAction(
    onDeleteClicked: (Action) -> Unit
){
    IconButton(onClick = { onDeleteClicked(Action.NO_ACTION) }) {
        Icon(imageVector = Icons.Filled.Delete, contentDescription = stringResource(R.string.delete_icon))

    }
}

@Composable
fun UpdateAction(
    onUpdateClicked: (Action) -> Unit
){
    IconButton(onClick = { onUpdateClicked(Action.NO_ACTION) }) {
        Icon(imageVector = Icons.Filled.Check, contentDescription = stringResource(R.string.update_icon))

    }
}

@Composable
fun AddAction(
    onAddClicked: (Action) -> Unit
){
    IconButton(onClick = { onAddClicked(Action.ADD) }) {
        Icon(imageVector = Icons.Filled.Check, contentDescription = stringResource(R.string.add_task))

    }
}

@Composable
@Preview
fun NewTaskAppBarPreview(){
    NewTaskAppBar(navigateToListScreen = {})
}