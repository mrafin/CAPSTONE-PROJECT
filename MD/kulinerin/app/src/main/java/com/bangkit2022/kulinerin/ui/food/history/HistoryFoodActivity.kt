package com.bangkit2022.kulinerin.ui.food.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit2022.kulinerin.databinding.ActivityHistoryFoodBinding

class HistoryFoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}