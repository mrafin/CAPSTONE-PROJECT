package com.bangkit2022.kulinerin.ui.navigation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit2022.kulinerin.databinding.RowCityBinding

class ListCityAdapter : RecyclerView.Adapter<ListCityAdapter.ListViewHolder>() {

    private val listCity = ArrayList<City>()

    fun setList(city: ArrayList<City>) {
        listCity.clear()
        listCity.addAll(city)
        notifyDataSetChanged()
    }

    class ListViewHolder(private var binding: RowCityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(city: City) {
            binding.tvCity.text = city.name
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val bindingContentView =
            RowCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(bindingContentView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val getCity = listCity[position]
        holder.bind(getCity)
    }

    override fun getItemCount(): Int = listCity.size
}