package com.shabelnikd.eatmorepizza

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.shabelnikd.eatmorepizza.databinding.FragmentMainScreenBinding


class MainScreenFragment : Fragment() {

    private lateinit var binding: FragmentMainScreenBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MainScreenFragment()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(binding.root)

        val categoryHamburger = Category(R.string.category_hamburger, R.drawable.ic_hamburger)
        val categoryPizza = Category(R.string.category_pizza, R.drawable.ic_pizza)
        val categoryChicken = Category(R.string.category_chicken, R.drawable.ic_chiken)
        val categorySalad = Category(R.string.category_salad, R.drawable.ic_salad)


        val categories: MutableList<Category> = mutableListOf(categoryHamburger, categoryPizza, categoryChicken, categorySalad)

        val categoryAdapter = CategoryAdapter(categories)
        val recyclerViewCategory: RecyclerView = binding.rvCategory
        recyclerViewCategory.adapter = categoryAdapter


        val hamburgerOne = Food("Salad Burger", R.drawable.burger, 12, 5, categoryHamburger)
        val hamburgerTwo = Food("Chicken Burger", R.drawable.burger, 15, 5, categoryHamburger)
        val hamburgerThree = Food("Meat Burger", R.drawable.burger, 14, 5, categoryHamburger)

        val foods: MutableList<Food> = mutableListOf(hamburgerOne, hamburgerTwo, hamburgerThree)
        val foodAdapter = FoodAdapter(foods)
        val recyclerViewFood: RecyclerView = binding.rvFood
        foodAdapter.setRecyclerView(recyclerViewFood)
        recyclerViewFood.adapter = foodAdapter



        binding.searchFood.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int){}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val searchText = s.toString()

                foodAdapter.foods.forEachIndexed { index, food ->
                    if (food.title.contains(searchText, ignoreCase = true)) {
                        food.isSelected = !food.isSelected
                        foodAdapter.notifyItemChanged(index)

                        if (food.isSelected && index != 0) {
                            foodAdapter.foods.removeAt(index)
                            foodAdapter.foods.add(0, food)
                            foodAdapter.notifyItemMoved(index, 0)
                            foodAdapter.notifyItemRangeChanged(0, index + 1)
                            binding.rvFood.scrollToPosition(0)
                        }
                        return
                    }
                }

                categoryAdapter.categories.forEachIndexed { index, category ->
                    if (context?.getString(category.titleId)?.contains(searchText, ignoreCase = true) == true) {

                        category.isSelected = !category.isSelected
                        categoryAdapter.notifyItemChanged(index)

                        if (category.isSelected && index != 0) {
                            categoryAdapter.categories.removeAt(index)
                            categoryAdapter.categories.add(0, category)
                            categoryAdapter.notifyItemMoved(index, 0)
                            categoryAdapter.notifyItemRangeChanged(0, index + 1)
                            binding.rvCategory.scrollToPosition(0)
                        }
                        return
                    }
                }

            }

        })






    }
}