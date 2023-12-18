//package com.example.foodapp.db
//
//import android.content.Context
//i
//import com.example.foodapp.Fragment.HomeFragment
//import com.example.foodapp.MealData.Meal
//
//
//@Database(entities = [Meal::class], version = 1)
//
//@TypeConverters(MealsTypeConvertor::class)class
//
//
//abstract class MealDataBase : RoomDatabase() {
//    abstract fun mealDao(): MealDao
//
//    companion object {
//        @Volatile
//        var INSTAENC: MealDataBase? = null
//
//        @Synchronized
//        fun getInstaenc(context: Context): MealDataBase {
//
//            if (INSTAENC == null) {
//                INSTAENC = Room.databaseBuilder(
//                    context,
//                    MealDataBase::class.java,
//                    "meal - db"
//                ).fallbackToDestructiveMigration()
//                    .build()
//            }
//            return INSTAENC as MealDataBase
//        }
//    }
//
//}