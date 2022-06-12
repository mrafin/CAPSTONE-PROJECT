package com.bangkit2022.kulinerin.ui.navigation.home

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit2022.kulinerin.R
import com.bangkit2022.kulinerin.data.City
import com.bangkit2022.kulinerin.data.User
import com.bangkit2022.kulinerin.databinding.FragmentHomeBinding
import com.bangkit2022.kulinerin.helper.regex
import com.bangkit2022.kulinerin.ui.auth.UserPreference

class HomeFragment : Fragment(){

    private lateinit var preference: UserPreference
    private lateinit var listCityAdapter: ListCityAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
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
    private lateinit var user: User

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

        preference = UserPreference(requireContext())
        user = preference.getUser()

        listCityAdapter = ListCityAdapter()
        listCity.addAll(getList)
        showRecyclerList(listCity)

        binding.apply {
            tvGreeting.setText(getString(R.string.greeting, regex(user.email)))
        }
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
