package com.example.foodapp.HomeViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.MealData.CategoriList
import com.example.foodapp.MealData.Category
import com.example.foodapp.MealData.FvCategoryList
import com.example.foodapp.MealData.Meal
import com.example.foodapp.MealData.categoryMeal
import com.example.foodapp.MealData.mealsData
import com.example.foodapp.db.MealDataBase
import com.example.foodapp.retrofit.RetrofitIns
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel (
    private var mealsData: MealDataBase
):ViewModel() {

    private val randomMealLiveData      =   MutableLiveData<Meal?>()
    private val popularItemLivedata     =   MutableLiveData<List<categoryMeal>?>()
    private val categoryLiveData        =   MutableLiveData<List<Category>?>()
    private val searchCategoryLiveData  =   MutableLiveData<List<CategoriList>?>()
    private val fvMealSLiveData         =   mealsData.mealDao().getAllMeal()
    private val bottomSheetLiveData     =   MutableLiveData<Meal>()


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
                    searchCategoryLiveData.postValue(it as List<CategoriList>?)
                }

            }

            override fun onFailure(call: Call<mealsData>, t: Throwable) {
                Log.d("TAG", "onFailure: " )
            }

        }
    )

    fun deleteMeal(meal: Meal){
        viewModelScope.launch {
            mealsData.mealDao().delete(meal)
        }
    }

    fun insertMeal(meal: Meal){

        viewModelScope.launch {
            mealsData.mealDao().upset(meal)
        }

    }


//    fun observerSearchMealsData() : MutableLiveData<List<CategoriList>> =  searchCategoryLiveData

    fun getMealById(id : String){

        RetrofitIns.api.getMealDetails(id).enqueue(object : Callback<mealsData>{
            override fun onResponse(call: Call<mealsData>, response: Response<mealsData>) {

                val meal = response.body()?.meals?.first()

                meal?.let { meal->
                    bottomSheetLiveData.postValue(meal)
                }


            }

            override fun onFailure(call: Call<mealsData>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }


    fun observerCategoriesLiveData (): MutableLiveData<List<Category>?> {

        return categoryLiveData

    }


    fun observerFvMealsLiveData ():LiveData<List<Meal>>{
        return fvMealSLiveData
    }


    fun observerBottomSheetLiveData (): LiveData<Meal> = bottomSheetLiveData




}