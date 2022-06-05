package com.bangkit2022.kulinerin.ui.food

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bangkit2022.kulinerin.R
import com.bangkit2022.kulinerin.databinding.ActivityFoodBinding
import com.bangkit2022.kulinerin.data.Food

class FoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFoodBinding
    private lateinit var listFoodAdapter: ListFoodAdapter
    private var listFood = ArrayList<Food>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getCity = intent.getStringExtra(EXTRA_DATA)
        supportActionBar?.title = getCity
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        listFoodAdapter = ListFoodAdapter()
        listFood.addAll(getList)
        showFoodList(listFood)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
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
                val food = Food(nameFood[i], descFood[i], imageFood.getResourceId(i, 1))
                list.add(food)
            }

            return list
        }

    private fun showFoodList(list: ArrayList<Food>) {
        binding.apply {
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                rvFood.layoutManager = GridLayoutManager(this@FoodActivity, 3)
            } else {
                rvFood.layoutManager = GridLayoutManager(this@FoodActivity, 2)
            }

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

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}