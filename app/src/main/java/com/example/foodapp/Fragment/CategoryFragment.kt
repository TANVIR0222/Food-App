package com.example.foodapp.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.HomeViewModel.HomeViewModel
import com.example.foodapp.MealData.Category
import com.example.foodapp.MealData.FvCategoryList
import com.example.foodapp.MealData.categoryMeal
import com.example.foodapp.activity.MainActivity
import com.example.foodapp.adapter.CategoryMealAdapter
import com.example.foodapp.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    private lateinit var categoryMealAdapter: CategoryMealAdapter
    private lateinit var viewModel:HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryRecyclearView()
        observerCategory()
    }

    private fun observerCategory() {

//        viewModel.observerCategoriesLiveData().observe(viewLifecycleOwner, Observer { mealList ->
//            // Assuming mealList is of type List<Category>
//            val convertedList: List<Unit> = mealList!!.map {   }
//
//            // Now set the converted list in your adapter
//            categoryMealAdapter.setMealList(mealList = convertedList)
//        })

    }

    private fun categoryRecyclearView() {

        categoryMealAdapter = CategoryMealAdapter()

        binding.rvCategory.apply {
            layoutManager = GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
            adapter = categoryMealAdapter
        }

    }

}