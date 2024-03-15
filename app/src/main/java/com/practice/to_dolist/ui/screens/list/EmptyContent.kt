package com.practice.to_dolist.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.practice.to_dolist.R

@Composable
fun EmptyContent(){
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "No Tasks Found", fontWeight = FontWeight.Medium)
    }
}