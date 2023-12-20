package com.example.foodapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodapp.Fragment.HomeFragment
import com.example.foodapp.HomeViewModel.CategoryMealViewModel
import com.example.foodapp.adapter.CategoryMealAdapter
import com.example.foodapp.databinding.ActivityCategoryMealsBinding

class CategoryMealsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryMealsBinding
    private lateinit var categoryMealViewModel: CategoryMealViewModel
    private lateinit var categoryMealAdapter: CategoryMealAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        pepareRecyclerView()

        categoryMealViewModel = ViewModelProviders.of(this)[CategoryMealViewModel::class.java]

        categoryMealViewModel.getMealsByCategory(intent.getStringExtra(HomeFragment.CATEGORY_NAME)!!)


        categoryMealViewModel.observeMealsLiveData().observe(this,Observer{ cate->
            binding.tvCategoryCount.text = cate!!.size.toString()

            categoryMealAdapter.setMealList(cate)

        })




    }

    private fun pepareRecyclerView() {
        categoryMealAdapter = CategoryMealAdapter()
        binding.rvMeals.apply {
            layoutManager = GridLayoutManager(context ,2,GridLayoutManager.VERTICAL,false)
            adapter = categoryMealAdapter
        }
    }
}