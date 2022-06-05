package com.bangkit2022.kulinerin.ui.food

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bangkit2022.kulinerin.R
import com.bangkit2022.kulinerin.databinding.ActivityDetailFoodBinding
import com.bangkit2022.kulinerin.data.Food
import com.bangkit2022.kulinerin.ui.food.restaurant.RestaurantMapsActivity

class DetailFoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailFoodBinding
    private var listFood: Food? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listFood = intent.getParcelableExtra(EXTRA_DATA)

        if (listFood == null) {
            listFood = Food(
                name = "Food",
                description = "loremjnvjdvndnkjfvndfvnjnjvkvkdvksjvndsjvs",
                image = R.drawable.bakso
            )
        }

        supportActionBar?.title = listFood?.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.apply {
            detailTvDescription.text = listFood?.description
            listFood?.image?.let { detailImgFood.setImageResource(it) }
            btnLocationRestaurant.setOnClickListener {
                val mIntent = Intent(this@DetailFoodActivity, RestaurantMapsActivity::class.java)
                startActivity(mIntent)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}