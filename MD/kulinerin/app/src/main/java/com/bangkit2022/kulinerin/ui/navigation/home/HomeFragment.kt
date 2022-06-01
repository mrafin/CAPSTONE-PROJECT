package com.bangkit2022.kulinerin.ui.navigation.home

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit2022.kulinerin.R
import com.bangkit2022.kulinerin.databinding.FragmentHomeBinding


class HomeFragment : Fragment(){

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var listCityAdapter: ListCityAdapter
    private val listCity = ArrayList<City>()
    private val getList: ArrayList<City>
        get() {
            val dataCity = resources.getStringArray(R.array.list_city)
            val list = ArrayList<City>()
            for (i in dataCity.indices) {
                val city = City(dataCity[i])
                list.add(city)
            }

            return list
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listCityAdapter = ListCityAdapter()
        listCity.addAll(getList)
        showRecyclerList(listCity)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showRecyclerList(list: ArrayList<City>) {
        binding.apply {
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
                rvCity.layoutManager = GridLayoutManager(activity, 2)
            else rvCity.layoutManager = LinearLayoutManager(activity)

            rvCity.setHasFixedSize(true)
            rvCity.adapter = listCityAdapter

        }

        listCityAdapter.setList(list)
    }

}