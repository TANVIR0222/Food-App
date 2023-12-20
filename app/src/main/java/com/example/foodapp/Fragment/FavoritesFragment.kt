package com.example.foodapp.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.HomeViewModel.HomeViewModel
import com.example.foodapp.activity.MainActivity
import com.example.foodapp.adapter.FavoriteMealAdapter
import com.example.foodapp.databinding.FragmentFavoritesBinding
import com.google.android.material.snackbar.Snackbar

class FavoritesFragment : Fragment() {



    private lateinit var binding: FragmentFavoritesBinding
    lateinit var favoriteMealAdapter: FavoriteMealAdapter
   private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRV()
        observerFavoritesMeals()

        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT

        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = true


            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                viewModel.deleteMeal(favoriteMealAdapter.diffUtil.currentList[position])

                Snackbar.make(requireView(), "Meal deleted" ,Snackbar.LENGTH_LONG).setAction(
                    "Undo",


                   View.OnClickListener {

                       viewModel.insertMeal(favoriteMealAdapter.diffUtil.currentList[position])

                   } ).show()
            }

        }
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.RvFavorite)

    }

    private fun prepareRV() {
        favoriteMealAdapter = FavoriteMealAdapter()
        binding.RvFavorite.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter = favoriteMealAdapter
        }

    }

    private fun observerFavoritesMeals() {
        viewModel.observerFvMealsLiveData().observe(viewLifecycleOwner , Observer { meals->

            favoriteMealAdapter.diffUtil.submitList(meals)

        })
    }

}