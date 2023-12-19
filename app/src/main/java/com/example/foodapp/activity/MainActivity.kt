package com.example.foodapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.foodapp.HomeViewModel.HomeViewModel
import com.example.foodapp.HomeViewModel.HomeViewModelFactory
import com.example.foodapp.R
import com.example.foodapp.db.MealDataBase
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

     val viewModel: HomeViewModel by lazy {

         val mealDataBase = MealDataBase.getInstance(this)
         val homeViewModelFactory = HomeViewModelFactory(mealDataBase)
         ViewModelProvider(this,homeViewModelFactory)[HomeViewModel::class.java]
     }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomnavigation)
        val navController = Navigation.findNavController(this, R.id.fragmentCon)
        NavigationUI.setupWithNavController(bottomNavigation,navController)
    }
}