package com.shabelnikd.eatmorepizza

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shabelnikd.eatmorepizza.databinding.ItemCategoryBinding
import com.shabelnikd.eatmorepizza.databinding.ItemFoodBinding

class FoodAdapter(val foods: MutableList<Food>) :
    RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    private lateinit var recyclerView: RecyclerView

    fun setRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
    }

    class ViewHolder(val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = foods[position]

        holder.binding.ivProductCardImage.setImageResource(food.imageId)
        holder.binding.ivProductCardText.text = food.title
        holder.binding.tvPrice.text = "$${food.coast}"

        selectFood(food, holder)

        holder.itemView.setOnClickListener {
            val wasSelected = food.isSelected
            food.isSelected = !wasSelected

            foods.forEachIndexed { index, otherFood ->
                if (index != position && otherFood.isSelected) {
                    otherFood.isSelected = false
                    notifyItemChanged(index, false)
                }
            }

            if (!wasSelected && position != 0) {
                foods.removeAt(position)
                foods.add(0, food)

                notifyItemMoved(position, 0)
                notifyItemRangeChanged(0, position + 1, false)

                recyclerView.post {
                    recyclerView.scrollToPosition(0)
                }
            } else {
                notifyItemChanged(position, false)
            }
        }


    }

    private fun selectFood(food: Food, holder: ViewHolder) {

        val defaultColor = holder.itemView.context.getColor(R.color.auth2_stroke)
        val defaultColorText = holder.itemView.context.getColor(R.color.bg_light_red)
        val selectedColor = holder.itemView.context.getColor(R.color.red_primary_color)



        if (food.isSelected) {
            holder.binding.ivProductCardText.setTextColor(holder.itemView.context.getColor(R.color.white))
            holder.binding.rtbProductRating.setBackgroundColor(selectedColor)
            holder.binding.tvPrice.setTextColor(holder.itemView.context.getColor(R.color.white))
            holder.binding.tvPrice.setBackgroundColor(selectedColor)
            holder.binding.ivProductCardText.setBackgroundColor(selectedColor)
            holder.itemView.background = ContextCompat.getDrawable(
                holder.itemView.context, R.drawable.bg_item_food_selected
            )
        } else {
            holder.binding.ivProductCardText.setTextColor(defaultColor)
            holder.binding.rtbProductRating.setBackgroundColor(defaultColorText)
            holder.binding.tvPrice.setTextColor(defaultColor)
            holder.binding.tvPrice.setBackgroundColor(defaultColorText)
            holder.binding.ivProductCardText.setBackgroundColor(defaultColorText)
            holder.itemView.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.bg_item_food)

        }

    }

    override fun getItemCount(): Int = foods.size

}