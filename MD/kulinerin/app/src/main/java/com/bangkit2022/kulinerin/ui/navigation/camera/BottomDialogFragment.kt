package com.bangkit2022.kulinerin.ui.navigation.camera

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bangkit2022.kulinerin.databinding.BottomSheetLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomDialogFragment : BottomSheetDialogFragment() {

    private var _binding: BottomSheetLayoutBinding? = null
    private val binding get() = _binding!!

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}