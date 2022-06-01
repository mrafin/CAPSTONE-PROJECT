package com.bangkit2022.kulinerin.ui.navigation.food

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bangkit2022.kulinerin.R
import com.bangkit2022.kulinerin.databinding.ActivityDetailFoodBinding

class DetailFoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listFood : Food = intent.getParcelableExtra<Food>(EXTRA_DATA) as Food

        Log.d("detail", "onCreate: $listFood")
        binding.apply {
            detailTvFood.text = listFood.name
            detailTvDescription.text = listFood.description
            detailImgFood.setImageResource(listFood.image)
        }
    }
    companion object {
        const val EXTRA_DATA = "extra_user"
    }
}