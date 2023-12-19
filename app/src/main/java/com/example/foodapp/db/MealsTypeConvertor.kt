package com.example.foodapp.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters

@TypeConverters
class MealsTypeConvertor {

    @TypeConverter
    fun forAnyToString(attribute: Any?): String {

        if (attribute == null)

            return " "
        return attribute as String

    }


    @TypeConverter

    fun forStringToAny(attribute: String?): Any {

        if (attribute == null)

            return " "
        return attribute

    }


}