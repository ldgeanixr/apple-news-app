package com.example.newsapp.data

import androidx.room.TypeConverter
import java.util.*

class Converters {

    @TypeConverter
    fun fromDate(date: Date): String {
        return date.toString()
    }

    @TypeConverter
    fun toDate(date: String): Date {
        return Date(date)
    }
}