package com.example.foodapp.HomeViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.MealData.CategoriList
import com.example.foodapp.MealData.Category
import com.example.foodapp.MealData.categoryMeal
import com.example.foodapp.MealData.mealsData
import com.example.foodapp.retrofit.RetrofitIns
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CategoryMealViewModel : ViewModel() {
    var mealLiveData = MutableLiveData<List<categoryMeal>?>()

    fun getMealsByCategory(categoryName: String) {
        RetrofitIns.api.getMeatsByCategory(categoryName).enqueue(object : Callback<CategoriList> {
            override fun onResponse(call: Call<CategoriList>, response: Response<CategoriList>) {
                response.body()?.let { mealList ->
                    mealLiveData.postValue(mealList.meals)
                }
            }

            override fun onFailure(call: Call<CategoriList>, t: Throwable) {
                // Handle failure if needed
            }
        })
    }

    fun observeMealsLiveData(): MutableLiveData<List<categoryMeal>?> {
        return mealLiveData
    }
}




