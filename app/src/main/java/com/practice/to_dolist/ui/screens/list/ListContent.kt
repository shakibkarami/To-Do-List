package com.practice.to_dolist.ui.screens.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practice.to_dolist.data.models.Priority
import com.practice.to_dolist.data.models.Task
import com.practice.to_dolist.ui.theme.LARGE_PADDING
import com.practice.to_dolist.ui.theme.PRIORITY_INDICATOR_SIZE
import com.practice.to_dolist.ui.theme.TASK_ITEM_ELEVATION

@Composable
fun ListContent(){

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskItem(
    toDoTask: Task,
    navigateToTaskScreen: (taskId: Int) -> Unit
){

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape,
        shadowElevation = TASK_ITEM_ELEVATION,
        onClick = {
            navigateToTaskScreen(toDoTask.id)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(all = LARGE_PADDING)
                .fillMaxWidth()
        ) {
            Row {
                Text(text = toDoTask.title, color = Color.Black, fontWeight = FontWeight.Bold, maxLines = 1, modifier = Modifier.weight(9f))
                Box(modifier = Modifier.fillMaxWidth().weight(1f), contentAlignment = Alignment.TopEnd){
                    Canvas(modifier = Modifier
                        .width(PRIORITY_INDICATOR_SIZE)
                        .height(PRIORITY_INDICATOR_SIZE)){
                        drawCircle(color = toDoTask.priority.color)
                    }
                }
            }
            Text(modifier = Modifier.fillMaxWidth(), text = toDoTask.description, color = Color.LightGray, maxLines = 2, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Composable
@Preview
fun TaskItemPreview(){
    TaskItem(toDoTask = Task(0,"New Task", "This is the description for the new task", Priority.HIGH), navigateToTaskScreen = {})
}