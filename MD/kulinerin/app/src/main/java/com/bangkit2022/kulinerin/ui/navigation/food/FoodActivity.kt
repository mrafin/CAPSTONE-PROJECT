package com.bangkit2022.kulinerin.ui.navigation.food

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit2022.kulinerin.R
import com.bangkit2022.kulinerin.databinding.ActivityFoodBinding
import com.bangkit2022.kulinerin.ui.navigation.food.restaurant.ListRestaurantAdapter
import com.bangkit2022.kulinerin.ui.navigation.food.restaurant.Restaurant
import com.bangkit2022.kulinerin.ui.navigation.home.HomeFragment

class FoodActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "user"
    }


    private lateinit var binding: ActivityFoodBinding
    private lateinit var listFoodAdapter: ListFoodAdapter
    private lateinit var listRestaurantAdapter: ListRestaurantAdapter
    private var listFood = ArrayList<Food>()
    private var listRestaurant = ArrayList<Restaurant>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listFoodAdapter = ListFoodAdapter()
        listFood.addAll(getList)
        showRecyclerList(listFood)

        listRestaurantAdapter = ListRestaurantAdapter()
        listRestaurant.addAll(getListRest)
        showRestaurantList(listRestaurant)
    }

    private fun showSelectedFood(food: Food) {
        val moveWithParcelableIntent = Intent(this@FoodActivity, DetailFoodActivity::class.java)
        moveWithParcelableIntent.putExtra(DetailFoodActivity.EXTRA_DATA, food)
        startActivity(moveWithParcelableIntent)
    }

    private val getList: ArrayList<Food>
        get() {
            val nameFood = resources.getStringArray(R.array.list_food)
            val descFood = resources.getStringArray(R.array.list_food_desc)
            val imageFood = resources.obtainTypedArray(R.array.list_food_image)
            val list = ArrayList<Food>()
            for (i in nameFood.indices) {
                val food = Food(nameFood[i], descFood[i],imageFood.getResourceId(i, 1))
                list.add(food)
            }

            return list
        }

    private val getListRest: ArrayList<Restaurant>
        get() {
            val nameRestaurant = resources.getStringArray(R.array.list_restaurant)
            val list = ArrayList<Restaurant>()
            for (i in nameRestaurant.indices) {
                val restaurant = Restaurant(nameRestaurant[i])
                list.add(restaurant)
            }

            return list
        }


    private fun showRecyclerList(list: ArrayList<Food>) {
        binding.apply {
            val layoutManager =
                GridLayoutManager(this@FoodActivity, 2, GridLayoutManager.VERTICAL, false)
            rvFood.layoutManager = layoutManager

            rvFood.setHasFixedSize(true)
            rvFood.adapter = listFoodAdapter

            listFoodAdapter.setOnItemClickCallback(object : ListFoodAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Food) {
                    showSelectedFood(data)
                }
            })
        }

        listFoodAdapter.setList(list)
    }

    private fun showRestaurantList(list: ArrayList<Restaurant>) {
        binding.apply {
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
                rvRestaurant.layoutManager = GridLayoutManager(this@FoodActivity, 2)
            else rvRestaurant.layoutManager = LinearLayoutManager(this@FoodActivity)

            rvRestaurant.setHasFixedSize(true)
            rvRestaurant.adapter = listRestaurantAdapter

        }

        listRestaurantAdapter.setList(list)
    }

}