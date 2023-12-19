package com.example.foodapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.AsyncListUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.MealData.Meal
import com.example.foodapp.databinding.MealItemBinding

 class FavoriteMealAdapter : RecyclerView.Adapter<FavoriteMealAdapter.FavoritesViewHolder>() {

    class FavoritesViewHolder(var binding:MealItemBinding):RecyclerView.ViewHolder(binding.root)

     private val differ = object :DiffUtil.ItemCallback<Meal>(){
         override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
             return oldItem.idMeal == newItem.idMeal
         }

         override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
             return oldItem == newItem
         }

     }

     val diffUtil = AsyncListDiffer(this,differ)

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {

         return FavoritesViewHolder(MealItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
     }

     override fun getItemCount(): Int {

         return diffUtil .currentList.size

     }

     override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
         val meal = diffUtil.currentList[position]

         Glide.with(holder.itemView).load(meal.strMealThumb).into(holder.binding.imgMeal)
         holder.binding.tvMealName.text = meal.strMeal


     }
 }