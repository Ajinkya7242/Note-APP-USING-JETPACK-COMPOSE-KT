package com.example.notebookjetpackcompose.utils

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.UUID

class UUIdConverter {

    @TypeConverter
    fun fromUUId(uuid:UUID):String?{
        return uuid.toString()
    }

    @TypeConverter
    fun uuidFromString(uuid:String):UUID?{
        return UUID.fromString(uuid )
    }
}