package com.example.notebookjetpackcompose.utils

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.Date

class DateConvert {

    @TypeConverter
    fun timeStampFromDate(date: Date):Long{
        return date.time
    }

    @TypeConverter
    fun dateFromTimeStamp(timeStamp:Long):Date{
        return Date(timeStamp)
    }
}
