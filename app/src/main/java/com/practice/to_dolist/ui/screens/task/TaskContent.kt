package com.practice.to_dolist.ui.screens.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.practice.to_dolist.components.PriorityDropDown
import com.practice.to_dolist.data.models.Priority
import com.practice.to_dolist.ui.theme.LARGE_PADDING
import com.practice.to_dolist.ui.theme.MEDIUM_PADDING

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskContent(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    priority: Priority,
    onPrioritySelect: (Priority) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = LARGE_PADDING)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = {onTitleChange(it)},
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Title")},
            singleLine = true)

        Divider(
            modifier = Modifier.height(MEDIUM_PADDING),
            color = Color.Transparent
        )

        PriorityDropDown(priority = priority, onPrioritySelected = onPrioritySelect)

        OutlinedTextField(
            value = description,
            onValueChange = {onDescriptionChange(it)},
            modifier = Modifier.fillMaxSize(),
            label = { Text(text = "Description")})
    }

}

@Composable
@Preview
fun Preview(){
    TaskContent(
        title = "",
        onTitleChange = {},
        description = "",
        onDescriptionChange = {},
        priority = Priority.MEDIUM,
        onPrioritySelect = {}
    )
}