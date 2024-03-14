package com.practice.to_dolist.ui.screens.list

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.practice.to_dolist.R
import com.practice.to_dolist.data.models.Priority

@Composable
fun ListAppBar() {
    DefaultListAppBar(
        onSearchClicked = {}, onSortClicked = {}, onDeleteClicked = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListAppBar(onSearchClicked: () -> Unit, onSortClicked: (Priority) -> Unit, onDeleteClicked: () -> Unit) {
    TopAppBar(
        title = {Text(text = "Tasks")},
        actions = {
            ListAppBarActions(onSearchClicked = onSearchClicked, onSortClicked = onSortClicked, onDeleteClicked = onDeleteClicked)
        },

    )
}

@Composable
fun ListAppBarActions(onSearchClicked: () -> Unit, onSortClicked: (Priority) -> Unit, onDeleteClicked: () -> Unit) {
    searchAction(onSearchClicked = onSearchClicked)
    sortAction(onSortClicked = onSortClicked)
    deleteAllActions(onDeleteClicked = onDeleteClicked)

}

@Composable
fun searchAction(
    onSearchClicked: () -> Unit
) {
    IconButton(onClick = onSearchClicked) {
        Icon(imageVector = Icons.Filled.Search, contentDescription = stringResource(R.string.search_task))
    }
}

@Composable
fun sortAction(
    onSortClicked: (Priority) -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    IconButton(onClick = { expanded = true }) {
        Icon(painter = painterResource(id = R.drawable.ic_filter_list), contentDescription = stringResource(
            R.string.sort_action
        )

        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }) {
            DropdownMenuItem(text = { Priority.LOW }, onClick = {
                expanded = false
                onSortClicked(Priority.LOW)}
            )
            DropdownMenuItem(text = { Priority.MEDIUM }, onClick = {
                expanded = false
                onSortClicked(Priority.MEDIUM)}
            )
            DropdownMenuItem(text = { Priority.HIGH }, onClick = {
                expanded = false
                onSortClicked(Priority.HIGH)}
            )
        }
        
    }

}


@Composable
fun deleteAllActions(
    onDeleteClicked: () -> Unit
){

    var expanded by remember {
        mutableStateOf(false)
    }

    IconButton(onClick = { expanded = true }) {
        Icon(painter = painterResource(id = R.drawable.ic_vertical_menu), contentDescription = stringResource(
            R.string.delete_all_action
        )

        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }) {
            DropdownMenuItem(text = { stringResource(R.string.delete_all_action) }, onClick = {
                expanded = false
                onDeleteClicked()}
            )
        }

    }

}

@Composable
@Preview
fun ListAppBarPreview() {
    DefaultListAppBar(
        onSearchClicked = {},
        onSortClicked = {},
        onDeleteClicked = {}
    )
}