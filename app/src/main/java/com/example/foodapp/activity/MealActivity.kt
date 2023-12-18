package com.example.foodapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.foodapp.Fragment.HomeFragment
import com.example.foodapp.HomeViewModel.MealViewModel
import com.example.foodapp.R
import com.example.foodapp.databinding.ActivityMealBinding

class MealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealBinding
    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    lateinit var youtubeLink : String
    private lateinit var mealMvvm : MealViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMealBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        val mealDataBase = MealDataBase.getInstaenc(this)
//        val viewModelFactory = MealViewModelFactory(mealDataBase)
        mealMvvm = ViewModelProvider(this)[MealViewModel::class.java]


        getMealInformationForeIntent()
        setInfromationView()
        loadingCase ()
        mealMvvm.getMealDetails(mealId)


        onYoutubeImageClick()

        observerMealDetailsLiveData()

        onFavoriteClick()


    }

    private fun onFavoriteClick() {
//        binding.favoritesBtn.setOnClickListener {
//            mealToSave?.let {
//
//                mealMvvm.insertMeal(it)
//
//                Toast.makeText(this, " Meal save ", Toast.LENGTH_SHORT).show()
//
//            }
//        }
    }

    private fun onYoutubeImageClick() {

        binding.youtube.setOnClickListener {

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
            startActivity(intent)
        }

    }

    @SuppressLint("SetTextI18n")


    private fun observerMealDetailsLiveData() {

        mealMvvm.observerMealDetailsLiveData().observe(this
        ) { value ->

            onResponseCase()


            val meal = value

            binding.tvCategory.text = "Category : ${meal.strCategory}"
            binding.tvArea.text = "Area ${meal.strArea}"
            binding.instrucationsTv.text = meal.strInstructions

            youtubeLink = meal.strYoutube.toString()

        }

    }

    private fun setInfromationView() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.imgMealDetail)

        binding.collapsingToolbarLayout.title = mealName
        // tool bar color change || color work nested color

        binding.collapsingToolbarLayout.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        // tool bar color change
        binding.collapsingToolbarLayout.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun getMealInformationForeIntent() {

        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!


    }

    fun loadingCase (){

        binding.favoritesBtn.visibility = View.INVISIBLE
        binding.instrucationsTv.visibility = View.INVISIBLE
        binding.tvArea.visibility = View.INVISIBLE
        binding.tvCategory.visibility = View.INVISIBLE
        binding.youtube.visibility = View.INVISIBLE

    }

    fun onResponseCase(){

        binding.favoritesBtn.visibility = View.VISIBLE
        binding.instrucationsTv.visibility = View.VISIBLE
        binding.tvArea.visibility = View.VISIBLE
        binding.tvCategory.visibility = View.VISIBLE
        binding.youtube.visibility = View.VISIBLE

    }
}