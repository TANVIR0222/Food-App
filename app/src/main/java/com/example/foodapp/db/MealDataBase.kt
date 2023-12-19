package com.example.foodapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodapp.MealData.Meal


@Database(entities = [Meal::class], version = 1)
@TypeConverters(MealsTypeConvertor::class)
abstract class MealDataBase : RoomDatabase(){

    abstract fun mealDao(): MealDao

    companion object {
        @Volatile
        private var INSTANCE: MealDataBase? = null
        fun getInstance (context: Context):MealDataBase {

            if (INSTANCE == null){

                synchronized(this){

                    INSTANCE =Room.databaseBuilder(
                        context,
                        MealDataBase::class.java,
                        "Meal da"
                    ).build()

                }

            }

            return INSTANCE !!
        }


    }

}