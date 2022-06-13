package com.bangkit2022.kulinerin.ui.food

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bangkit2022.kulinerin.databinding.ActivityDetailFoodBinding
import com.bangkit2022.kulinerin.data.Food

class DetailFoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailFoodBinding
    private var listFood: Food? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listFood = intent.getParcelableExtra(EXTRA_DATA)

        supportActionBar?.title = listFood?.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.apply {
            detailTvDescription.text = listFood?.description
            listFood?.image?.let { detailImgFood.setImageResource(it) }
            tvRecipeList.text = listFood?.recipe

            btnLocationRestaurant.setOnClickListener {
                val gmmIntentUri = Uri.parse("geo:0,0?q=${listFood?.name}+restaurants")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
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