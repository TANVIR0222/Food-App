package com.example.foodapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.MealData.CategoriList
import com.example.foodapp.MealData.Category
import com.example.foodapp.databinding.CategoryItemBinding

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {


    lateinit var onclickItem : ((Category) -> Unit)

    private var categoriesList = ArrayList<Category>()

    fun setCategoryList(categoryList: ArrayList<Category>) {

        this.categoriesList = categoryList

        notifyDataSetChanged()

    }

    class CategoriesViewHolder(var binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {

        return CategoriesViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {

        Glide.with(holder.itemView).load(categoriesList[position].strCategoryThumb)
            .into(holder.binding.imqCategory)
        holder.binding.tvCategoryName.text = categoriesList[position].strCategory


        holder.itemView.setOnClickListener {
            onclickItem.invoke(categoriesList[position])
        }


    }


}