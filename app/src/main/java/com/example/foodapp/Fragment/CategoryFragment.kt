package com.example.foodapp.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodapp.HomeViewModel.HomeViewModel
import com.example.foodapp.activity.MainActivity
import com.example.foodapp.adapter.CategoryMealAdapter
import com.example.foodapp.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    private lateinit var categoryMealAdapter: CategoryMealAdapter
    private lateinit var viewModel:HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryRecyclearView()
        observeCategory()
    }

    private fun observeCategory() {
        viewModel.observerCategoriesLiveData().observe(viewLifecycleOwner, Observer { categories ->

          //  categoryMealAdapter.setMealList(categories)

        })
    }


    private fun categoryRecyclearView() {

        categoryMealAdapter = CategoryMealAdapter()

        binding.rvCategory.apply {
            layoutManager = GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
            adapter = categoryMealAdapter
        }

    }

}