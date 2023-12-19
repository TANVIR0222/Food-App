package com.example.foodapp.HomeViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.MealData.CategoriList
import com.example.foodapp.MealData.Category
import com.example.foodapp.MealData.FvCategoryList
import com.example.foodapp.MealData.Meal
import com.example.foodapp.MealData.categoryMeal
import com.example.foodapp.MealData.mealsData
import com.example.foodapp.db.MealDataBase
import com.example.foodapp.retrofit.RetrofitIns
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query
import kotlin.math.log

class HomeViewModel (
    private var mealsData: MealDataBase
):ViewModel() {

    private val randomMealLiveData = MutableLiveData<Meal?>()
    private val popularItemLivedata = MutableLiveData<List<categoryMeal>?>()
    private val categoryLiveData =MutableLiveData<List<Category>?>()
    private val searchCategoryLiveData = MutableLiveData<List<Meal>>()
    private val fvMealSLiveData = mealsData.mealDao().getAllMeal()


    fun getRandomModel(){
        RetrofitIns.api.getRandomMeal().enqueue(object  : Callback<mealsData> {
            override fun onResponse(call: Call<mealsData>, response: Response<mealsData>) {
                if (response.body() != null){
                    val randomMeal : Meal? = response.body()!!.meals?.get(0)

                    randomMealLiveData.value = randomMeal

                }else{
                    return
                }
            }

            override fun onFailure(call: Call<mealsData>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getPopularItem(){
        RetrofitIns.api.getPopularItem("Seafood").enqueue(object : Callback<CategoriList>{
            override fun onResponse(call: Call<CategoriList>, response: Response<CategoriList>) {

                if (response.body() != null){
                    popularItemLivedata.value = response.body()!!.meals
                }
            }

            override fun onFailure(call: Call<CategoriList>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }


    fun getCategories(){
        RetrofitIns.api.getCategories().enqueue(object  : Callback<FvCategoryList>{
            override fun onResponse(
                call: Call<FvCategoryList>, response: Response<FvCategoryList>) {

                if (response.body() !=null){


                    categoryLiveData.value = response.body()!!.categories

                }


            }

            override fun onFailure(call: Call<FvCategoryList>, t: Throwable) {
                Log.d("TAG", "onFailure:  " )            }
        })
    }


    // change data
    fun observerRandomMealLiveData (): LiveData<Meal?> {

        return  randomMealLiveData

    }
    fun observerPopularMealLivedata (): MutableLiveData<List<categoryMeal>?> {

        return  popularItemLivedata

    }

    fun searchMeal(searchQuery: String ) = RetrofitIns.api.searchMeals(searchQuery).enqueue(
        object : Callback<mealsData>{
            override fun onResponse(call: Call<mealsData>, response: Response<mealsData>) {

                val mealList = response.body()?.meals

                mealList?.let {
                    searchCategoryLiveData.postValue(it as List<Meal>?)
                }

            }

            override fun onFailure(call: Call<mealsData>, t: Throwable) {
                Log.d("TAG", "onFailure: " )
            }

        }
    )


    fun observerSearchMealsData() : MutableLiveData<List<Meal>> =  searchCategoryLiveData


    fun observerCategoriesLiveData (): LiveData<List<Category>?>{

        return categoryLiveData

    }




}