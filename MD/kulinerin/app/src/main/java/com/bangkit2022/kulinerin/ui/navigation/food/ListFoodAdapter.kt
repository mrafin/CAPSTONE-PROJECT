package com.bangkit2022.kulinerin.ui.navigation.food

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bangkit2022.kulinerin.databinding.RowCityBinding
import com.bangkit2022.kulinerin.databinding.RowFoodBinding
import com.bangkit2022.kulinerin.ui.navigation.home.City
import com.bangkit2022.kulinerin.ui.navigation.home.ListCityAdapter
import com.bumptech.glide.Glide

class ListFoodAdapter : RecyclerView.Adapter<ListFoodAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Food)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    private val listFood = ArrayList<Food>()

    fun setList(food: ArrayList<Food>) {
        listFood.clear()
        listFood.addAll(food)
        notifyDataSetChanged()
    }

    class ListViewHolder(private var binding: RowFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(food: Food) {
            binding.tvFood.text = food.name
            binding.imgFood.setImageResource(food.image)

            itemView.setOnClickListener{
                val mIntent = Intent(itemView.context, DetailFoodActivity::class.java)
                mIntent.putExtra(DetailFoodActivity.EXTRA_DATA, food)
                itemView.context.startActivity(mIntent)

            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val bindingContentView =
            RowFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(bindingContentView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val getFood = listFood[position]
        holder.bind(getFood)
    }

    override fun getItemCount(): Int = listFood.size
}