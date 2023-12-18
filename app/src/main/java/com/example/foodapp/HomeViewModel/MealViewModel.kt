package com.example.foodapp.HomeViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.MealData.Meal
import com.example.foodapp.MealData.mealsData
import com.example.foodapp.retrofit.RetrofitIns
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel (
) : ViewModel(){

    private val mealDetailsLivedata = MutableLiveData<Meal>()

    fun getMealDetails(id :String){
        RetrofitIns.api.getMealDetails(id).enqueue(object : Callback<mealsData>{
            override fun onResponse(call: Call<mealsData>, response: Response<mealsData>) {

                if (response.body() != null){
                    mealDetailsLivedata.value = response.body()!!.meals?.get(0)
                }else{
                    return
                }

            }

            override fun onFailure(call: Call<mealsData>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun observerMealDetailsLiveData () : LiveData<Meal>{

        return mealDetailsLivedata
    }

    fun insertMeal(meal: Meal){

        viewModelScope.launch {


        }
    }

    fun deleteMeal(meal: Meal){
        viewModelScope.launch {
        }
    }

}