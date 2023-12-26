package com.example.foodapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodapp.MealData.Meal

@Dao
interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upset(meal: Meal)

//    meal data
    @Delete
    suspend fun delete(meal: Meal)

    @Query("SELECT * FROM mealInformation")

     fun getAllMeal(): LiveData<List<Meal>>
}
