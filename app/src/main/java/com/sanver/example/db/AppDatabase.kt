package com.sanver.example.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sanver.example.db.dao.ExampleDao
import com.sanver.example.db.entities.Example

@Database(entities = arrayOf(Example::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exampleDao(): ExampleDao
}