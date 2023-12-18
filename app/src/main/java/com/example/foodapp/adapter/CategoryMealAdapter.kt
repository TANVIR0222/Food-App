package com.example.foodapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.MealData.categoryMeal
import com.example.foodapp.databinding.MealItemBinding

class CategoryMealAdapter : RecyclerView.Adapter<CategoryMealAdapter.CategoryMealVH>() {

     var mealList = ArrayList<categoryMeal>()

    @SuppressLint("NotifyDataSetChanged")
    fun setMealList(mealList: List<categoryMeal>) {

        this.mealList = mealList as ArrayList<categoryMeal>

        notifyDataSetChanged()

    }

    class CategoryMealVH( var binding: MealItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMealVH {

        return CategoryMealVH(
            MealItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    override fun onBindViewHolder(holder: CategoryMealVH, position: Int) {

        Glide.with(holder.itemView).load(mealList[position].strMealThumb).into(holder.binding.imgMeal)
        holder.binding.tvMealName.text = mealList[position].strMeal

    }




}