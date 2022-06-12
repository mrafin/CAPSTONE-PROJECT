package com.bangkit2022.kulinerin.ui.navigation.camera

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bangkit2022.kulinerin.R
import com.bangkit2022.kulinerin.data.Food
import com.bangkit2022.kulinerin.databinding.BottomSheetLayoutBinding
import com.bangkit2022.kulinerin.ui.food.DetailFoodActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomDialogFragment : BottomSheetDialogFragment() {

    private var _binding: BottomSheetLayoutBinding? = null
    private val binding get() = _binding!!
    private lateinit var food: Food

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = BottomSheetLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val getData = arguments?.getString(EXTRA_DATA)
        binding.tvFoodName.text = getData

        when(binding.tvFoodName.text.toString()){
            "Rendang" -> {
                food = Food(
                    resources.getStringArray(R.array.list_food)[0],
                    resources.getStringArray(R.array.list_food_desc)[0],
                    resources.obtainTypedArray(R.array.list_food_image).getResourceId(0, 1),
                    resources.getStringArray(R.array.list_food_recipe)[0],
                )
            }
            "Es Pisang Ijo" -> {
                food = Food(
                    resources.getStringArray(R.array.list_food)[1],
                    resources.getStringArray(R.array.list_food_desc)[1],
                    resources.obtainTypedArray(R.array.list_food_image).getResourceId(1, 1),
                    resources.getStringArray(R.array.list_food_recipe)[1],
                )
            }
            "Pempek" -> {
                food = Food(
                    resources.getStringArray(R.array.list_food)[2],
                    resources.getStringArray(R.array.list_food_desc)[2],
                    resources.obtainTypedArray(R.array.list_food_image).getResourceId(2, 1),
                    resources.getStringArray(R.array.list_food_recipe)[2],
                )
            }
            "Sate Madura" -> {
                food = Food(
                    resources.getStringArray(R.array.list_food)[3],
                    resources.getStringArray(R.array.list_food_desc)[3],
                    resources.obtainTypedArray(R.array.list_food_image).getResourceId(3, 1),
                    resources.getStringArray(R.array.list_food_recipe)[3],
                )
            }
            "Bakso Malang" -> {
                food = Food(
                    resources.getStringArray(R.array.list_food)[4],
                    resources.getStringArray(R.array.list_food_desc)[4],
                    resources.obtainTypedArray(R.array.list_food_image).getResourceId(4, 1),
                    resources.getStringArray(R.array.list_food_recipe)[4],
                )
            }
            "Gudeg" -> {
                food = Food(
                    resources.getStringArray(R.array.list_food)[5],
                    resources.getStringArray(R.array.list_food_desc)[5],
                    resources.obtainTypedArray(R.array.list_food_image).getResourceId(5, 1),
                    resources.getStringArray(R.array.list_food_recipe)[5],
                )
            }
            "Kerak Telor" -> {
                food = Food(
                    resources.getStringArray(R.array.list_food)[6],
                    resources.getStringArray(R.array.list_food_desc)[6],
                    resources.obtainTypedArray(R.array.list_food_image).getResourceId(6, 1),
                    resources.getStringArray(R.array.list_food_recipe)[6],
                )
            }
            "Mie Aceh" -> {
                food = Food(
                    resources.getStringArray(R.array.list_food)[7],
                    resources.getStringArray(R.array.list_food_desc)[7],
                    resources.obtainTypedArray(R.array.list_food_image).getResourceId(7, 1),
                    resources.getStringArray(R.array.list_food_recipe)[7],
                )
            }
            "Ronde" -> {
                food = Food(
                    resources.getStringArray(R.array.list_food)[8],
                    resources.getStringArray(R.array.list_food_desc)[8],
                    resources.obtainTypedArray(R.array.list_food_image).getResourceId(8, 1),
                    resources.getStringArray(R.array.list_food_recipe)[8],
                )
            }
            "Seblak" -> {
                food = Food(
                    resources.getStringArray(R.array.list_food)[9],
                    resources.getStringArray(R.array.list_food_desc)[9],
                    resources.obtainTypedArray(R.array.list_food_image).getResourceId(9, 1),
                    resources.getStringArray(R.array.list_food_recipe)[9],
                )
            }
        }

        binding.btnDetail.setOnClickListener {
            val mIntent = Intent(requireActivity(), DetailFoodActivity::class.java)
            mIntent.putExtra(DetailFoodActivity.EXTRA_DATA, food)
            startActivity(mIntent)
            onDestroyView()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}