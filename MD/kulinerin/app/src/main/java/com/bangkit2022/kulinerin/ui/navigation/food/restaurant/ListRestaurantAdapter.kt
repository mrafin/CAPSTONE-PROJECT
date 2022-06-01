package com.bangkit2022.kulinerin.ui.navigation.food.restaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit2022.kulinerin.databinding.RowRestaurantBinding

class ListRestaurantAdapter : RecyclerView.Adapter<ListRestaurantAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Restaurant)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    private val listRestaurant = ArrayList<Restaurant>()

    fun setList(restaurant: ArrayList<Restaurant>) {
        listRestaurant.clear()
        listRestaurant.addAll(restaurant)
        notifyDataSetChanged()
    }

    class ListViewHolder(private var binding: RowRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: Restaurant) {
            binding.tvRestaurantName.text = restaurant.name

//            itemView.setOnClickListener{
//                val mIntent = Intent(itemView.context, DetailFoodActivity::class.java)
//                mIntent.putExtra(DetailFoodActivity.EXTRA_DATA, restaurant)
//                itemView.context.startActivity(mIntent)
//
//            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val bindingContentView =
            RowRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(bindingContentView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val getRestaurant = listRestaurant[position]
        holder.bind(getRestaurant)
    }

    override fun getItemCount(): Int = listRestaurant.size
}