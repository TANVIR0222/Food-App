package com.example.foodapp.retrofit

import com.example.foodapp.MealData.CategoriList
import com.example.foodapp.MealData.FvCategoryList
import com.example.foodapp.MealData.mealsData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("random.php")
    fun getRandomMeal ():Call<mealsData>


    @GET("lookup.php")
    fun getMealDetails(@Query("i") id : String ) :Call<mealsData>


    @GET("filter.php")
    fun getPopularItem(@Query("c")  categoryName: String) :Call<CategoriList>

    @GET("categories.php")
    fun getCategories() : Call<FvCategoryList>

    @GET("filter.php")
    fun getMeatsByCategory(@Query("c")categoryName: String) : Call<CategoriList>


    @GET("search.php")
    fun searchMeals(@Query("s") searchQuery: String ) :Call<mealsData>

}