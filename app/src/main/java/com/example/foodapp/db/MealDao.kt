//package com.example.foodapp.db
//
//import androidx.room.Dao
//import androidx.room.Delete
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.example.foodapp.MealData.Meal
//
//@Dao
//interface MealDao {
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun upset(meal: Meal)
//
//    @Delete
//    suspend fun delete(meal: Meal)
//
//    @Query("SELECT * FROM mealDatabaseTable")
//
//    suspend fun getAllMeal(): List<Meal>
//}