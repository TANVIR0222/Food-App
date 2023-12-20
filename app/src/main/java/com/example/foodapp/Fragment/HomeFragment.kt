package com.example.foodapp.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.foodapp.Fragment.bottomSheet.MealBottomFragment
import com.example.foodapp.HomeViewModel.HomeViewModel
import com.example.foodapp.MealData.Category
import com.example.foodapp.MealData.Meal
import com.example.foodapp.MealData.categoryMeal
import com.example.foodapp.R
import com.example.foodapp.activity.CategoryMealsActivity
import com.example.foodapp.activity.MainActivity
import com.example.foodapp.activity.MealActivity
import com.example.foodapp.adapter.CategoriesAdapter
import com.example.foodapp.adapter.MostPopularMealAdapter
import com.example.foodapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel // homeMVVm
    private lateinit var  randomeMeal :Meal
    private lateinit var popularMealAdapter : MostPopularMealAdapter
    private lateinit var categoriesAdapter: CategoriesAdapter


    companion object {
        const val MEAL_ID = " com.example.foodapp.Fragment.idMeal"
        const val MEAL_NAME = " com.example.foodapp.Fragment.nameMeal"
        const val MEAL_THUMB = " com.example.foodapp.Fragment.thumbMeal"
        const val CATEGORY_NAME = " com.example.foodapp.Fragment.categoryName"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        popularMealAdapter = MostPopularMealAdapter()


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        populaeItemRecyView()

        viewModel.getRandomModel()
        ORM()
        onRandomClick()


        viewModel.getPopularItem()
        observerPopularLiveData()
        onPopularItemClick()


        CategoryRecyclerView()
        viewModel.getCategories()
        observerCategoriesLiveData()
        onCategoryClick()

        onPopularLoneClick()
        onSearchItemClick()


    }

    private fun onSearchItemClick() {
        binding.imgSearch.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
    }

    private fun onPopularLoneClick() {
        popularMealAdapter.onItemClick = { meal ->
            val mealBottomSheet = meal.idMeal?.let { MealBottomFragment.newInstance(it) }

            mealBottomSheet!!.show(childFragmentManager,"meal info")
        }
    }

    private fun onCategoryClick() {
        categoriesAdapter.onclickItem = {
            val intent = Intent(activity,CategoryMealsActivity::class.java)
            intent.putExtra(CATEGORY_NAME,it.strCategory)
            startActivity(intent)
        }
    }


    private fun observerCategoriesLiveData() {

        viewModel.observerCategoriesLiveData().observe(viewLifecycleOwner
        ) { categoryList->

            categoriesAdapter.setCategoryList(categoryList = categoryList as ArrayList<Category>)

        }
    }

    private fun CategoryRecyclerView() {
        categoriesAdapter = CategoriesAdapter()

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context,3,GridLayoutManager.VERTICAL, false)
            adapter = categoriesAdapter
        }
    }



    // data trans
    private fun onPopularItemClick() {
        popularMealAdapter.onItemClick = { meal ->

            val intent =  Intent(activity,MealActivity::class.java)
            intent.putExtra(MEAL_ID,meal.idMeal)
            intent.putExtra(MEAL_NAME,meal.strMeal)
            intent.putExtra(MEAL_THUMB,meal.strMealThumb)

            startActivity(intent)

        }
    }

    private fun populaeItemRecyView() {
        binding.recViewMealsPopular.apply {
            layoutManager  = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
            adapter = popularMealAdapter
        }
    }

    private fun observerPopularLiveData() {
        viewModel.observerPopularMealLivedata().observe(viewLifecycleOwner
        ) { mealList ->

            popularMealAdapter.setMeals(mealList = mealList as  ArrayList<categoryMeal>)

        }

    }

    private fun onRandomClick() {
        binding.randomMealCard.setOnClickListener {
            val intent = Intent(activity,MealActivity::class.java)
            intent.putExtra(MEAL_ID,randomeMeal.idMeal)
            intent.putExtra(MEAL_NAME,randomeMeal.strMeal)
            intent.putExtra(MEAL_THUMB,randomeMeal.strMealThumb)
            startActivity(intent)

        }
    }

    private fun ORM() {
         viewModel.observerRandomMealLiveData().observe(viewLifecycleOwner
         ) { value ->
             Glide.with(this@HomeFragment)
                 .load(value!!.strMealThumb)
                 .into(binding.imgRandomMeal)

             this.randomeMeal = value
         }
    }


}

