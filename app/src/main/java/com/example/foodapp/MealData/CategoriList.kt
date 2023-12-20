package com.example.foodapp.MealData


import com.google.gson.annotations.SerializedName

data class CategoriList(
    @SerializedName("meals")
    var meals: List<categoryMeal>
)
data class categoryMeal(
        @SerializedName("idMeal")
        var idMeal: String?,
        @SerializedName("strMeal")
        var strMeal: String?,
        @SerializedName("strMealThumb")
        var strMealThumb: String?
    )
