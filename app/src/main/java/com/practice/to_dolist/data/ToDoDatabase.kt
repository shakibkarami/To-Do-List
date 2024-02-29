package com.practice.to_dolist.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.practice.to_dolist.data.models.Task

@Database(entities = [Task::class], version = 1)
abstract class ToDoDatabase: RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
}