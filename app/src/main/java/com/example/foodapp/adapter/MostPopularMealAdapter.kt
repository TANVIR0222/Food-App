package com.example.foodapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.MealData.categoryMeal
import com.example.foodapp.databinding.PoplurItemBinding

class MostPopularMealAdapter : RecyclerView.Adapter<MostPopularMealAdapter.PopularViewHolder>() {

    lateinit var onItemClick :((categoryMeal) -> Unit)

    private var mealList = ArrayList<categoryMeal>()

    fun setMeals(mealList: ArrayList<categoryMeal>) {

        this.mealList =mealList

        notifyDataSetChanged()


    }

    class PopularViewHolder(val binding: PoplurItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        return PopularViewHolder(
            PoplurItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        Glide.with(holder.itemView).
        load(mealList[position].strMealThumb)
            .into(holder.binding.imgPopularMeal)


        holder.itemView.setOnClickListener {
            onItemClick.invoke(mealList[position])
        }


    }

}