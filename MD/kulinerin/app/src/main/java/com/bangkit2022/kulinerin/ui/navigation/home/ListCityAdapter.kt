package com.bangkit2022.kulinerin.ui.navigation.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit2022.kulinerin.databinding.RowCityBinding
import com.bangkit2022.kulinerin.data.City
import com.bangkit2022.kulinerin.ui.food.FoodActivity

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

            itemView.setOnClickListener {
                val mIntent = Intent(itemView.context, FoodActivity::class.java)
                mIntent.putExtra(FoodActivity.EXTRA_DATA, city.name)
                itemView.context.startActivity(mIntent)
            }
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