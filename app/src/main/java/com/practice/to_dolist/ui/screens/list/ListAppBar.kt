package com.practice.to_dolist.ui.screens.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.practice.to_dolist.R
import com.practice.to_dolist.data.models.Priority
import com.practice.to_dolist.ui.theme.TOP_APP_BAR_HEIGHT
import com.practice.to_dolist.ui.viewmodels.SharedViewModel
import com.practice.to_dolist.util.SearchAppBarState
import com.practice.to_dolist.util.TrailingIconState

@Composable
fun ListAppBar(
    sharedViewModel: SharedViewModel,
    searchAppBarState: SearchAppBarState,
    searchTextState: String
) {
    when(searchAppBarState){
        SearchAppBarState.CLOSED -> {
            DefaultListAppBar(
                onSearchClicked = {
                    sharedViewModel.searchAppBarState.value = SearchAppBarState.OPENED
                },
                onSortClicked = {},
                onDeleteClicked = {}
            )
        }
        else -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = { sharedViewModel.searchTextState.value = it },
                onCloseClicked = {
                    sharedViewModel.searchAppBarState.value = SearchAppBarState.CLOSED
                    sharedViewModel.searchTextState.value = ""
                },
                onSearchClicked = {}
            )
        }
    }
    
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    var trailingIconState by remember {
        mutableStateOf(TrailingIconState.READY_TO_DELETE)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT)
    ) {
        TextField(modifier = Modifier.fillMaxWidth(), value = text, onValueChange = {onTextChange(it)},
            placeholder = { Text(modifier = Modifier.alpha(0.6f), text = stringResource(R.string.search), color = Color.White) },
            textStyle = TextStyle(fontSize = 18.sp),
            singleLine = true,
            leadingIcon = {IconButton(onClick = {}, modifier = Modifier.alpha(0.4f)) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = stringResource(R.string.search))
            }},
            trailingIcon = {
                IconButton(onClick = {
                    when(trailingIconState){
                        TrailingIconState.READY_TO_DELETE -> {
                            onTextChange("")
                            trailingIconState = TrailingIconState.READY_TO_CLOSE
                        }
                        TrailingIconState.READY_TO_CLOSE -> {
                            if (text.isNotEmpty()){
                                onTextChange("")
                            } else {
                                onCloseClicked()
                                trailingIconState = TrailingIconState.READY_TO_DELETE
                            }

                        }
                    }
                }) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = stringResource(R.string.close_icon))
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {onSearchClicked(text)}
            )
        )
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

@Composable
@Preview
fun SearchAppBarPreview() {
    SearchAppBar(
        stringResource(id = R.string.search),
        onSearchClicked = {},
        onCloseClicked = {},
        onTextChange = {})
}