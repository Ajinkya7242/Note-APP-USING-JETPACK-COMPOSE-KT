package com.example.notebookjetpackcompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.notebookjetpackcompose.model.Note
import com.example.notebookjetpackcompose.utils.DateConvert
import com.example.notebookjetpackcompose.utils.UUIdConverter

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConvert::class,UUIdConverter::class)
abstract class NoteDatabase:RoomDatabase() {
    abstract fun noteDao():NoteDatabaseDao
}