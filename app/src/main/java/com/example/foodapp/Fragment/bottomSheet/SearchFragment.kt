package com.example.foodapp.Fragment.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodapp.HomeViewModel.HomeViewModel
import com.example.foodapp.activity.MainActivity
import com.example.foodapp.adapter.MealAdapter
import com.example.foodapp.databinding.FragmentSearchBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private lateinit var biding: FragmentSearchBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var searchAdapter: MealAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        biding = FragmentSearchBinding.inflate(layoutInflater)
        return biding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        praparRv()

        biding.nextBtn.setOnClickListener {
            searchMeal()
        }
        observerSearchMealLiveData()

        var searchJob: Job? = null
        biding.searchBox.addTextChangedListener {searchQuery ->
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                delay(200)
                viewModel.searchMeal(searchQuery.toString())

            }
        }


    }

    private fun observerSearchMealLiveData() {
        viewModel.observerSearchMealsData().observe(viewLifecycleOwner, Observer { mealList ->
            searchAdapter.diffUtil.submitList(/* newList = */ mealList)
        })
    }


    private fun searchMeal() {
        val searchQuery = biding.searchBox.text.toString().trim()
        if (searchQuery.isNotEmpty()) {
            viewModel.searchMeal(searchQuery)
        }
    }

    private fun praparRv() {
        searchAdapter = MealAdapter()

        biding.rvSearch.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = searchAdapter
        }

    }


}