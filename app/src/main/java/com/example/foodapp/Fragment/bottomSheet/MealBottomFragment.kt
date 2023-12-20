package com.example.foodapp.Fragment.bottomSheet

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.foodapp.Fragment.HomeFragment
import com.example.foodapp.HomeViewModel.HomeViewModel
import com.example.foodapp.activity.MainActivity
import com.example.foodapp.activity.MealActivity
import com.example.foodapp.databinding.FragmentMealBottomBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


private const val MEAL_ID = "param1"

class MealBottomFragment : BottomSheetDialogFragment() {

    private var mealId: String? = null

    private lateinit var binding: FragmentMealBottomBinding
    private lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mealId = it.getString(MEAL_ID)
        }

        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMealBottomBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mealId?.let {
            viewModel.getMealById(it)
        }

        observerBottumSheetMeal()
        onBottomSheetDialog()

    }

    private fun onBottomSheetDialog() {
        binding.bottomSheetNext.setOnClickListener {

            if (mealName != null && mealThumb != null){

                val intent = Intent(activity,MealActivity::class.java)
                intent.apply {
                    putExtra(HomeFragment . MEAL_ID,mealId)
                    putExtra(HomeFragment.MEAL_NAME ,mealName)
                    putExtra(HomeFragment.MEAL_THUMB ,mealThumb)

                }
                startActivity(intent)
            }

        }
    }
    private  var mealName: String?=null
    private  var mealThumb: String ? = null
    private fun observerBottumSheetMeal() {

        viewModel.observerBottomSheetLiveData().observe(viewLifecycleOwner, Observer { meal ->
            Glide.with(this).load(meal.strMealThumb).into(binding.imgBottomShite)

            binding.tvAreaBtm.text = meal.strArea
            binding.tvCategoryBtn.text = meal.strCategory
            binding.mealName.text = meal.strMeal

            mealName = meal.strMeal
            mealThumb = meal.strMealThumb

        })

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            MealBottomFragment().apply {
                arguments = Bundle().apply {
                    putString(MEAL_ID, param1)
                }
            }


    }
}