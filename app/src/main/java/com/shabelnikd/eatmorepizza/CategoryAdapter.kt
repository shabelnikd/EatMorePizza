package com.shabelnikd.eatmorepizza

import android.content.res.ColorStateList
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shabelnikd.eatmorepizza.databinding.ItemCategoryBinding

class CategoryAdapter(val categories: MutableList<Category>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var selectedItemPosition = -1

    class ViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]

        holder.binding.ivProductImage.setImageResource(category.iconId)
        val spannableString = SpannableString(holder.itemView.context.getString(category.titleId))

        spannableString.setSpan(UnderlineSpan(), 0, spannableString.length, 0)
        holder.binding.ivProductText.text = spannableString

        val defaultColor = holder.itemView.context.getColor(R.color.auth2_stroke)
        val selectedColor = holder.itemView.context.getColor(R.color.red_primary_color)


        holder.binding.ivProductImage.imageTintList = ColorStateList.valueOf(
            if (category.isSelected) selectedColor else defaultColor
        )

        holder.binding.ivProductText.setTextColor(
            ColorStateList.valueOf(
                if (category.isSelected) selectedColor else defaultColor
            )
        )

        holder.itemView.background = if (category.isSelected) {
            ContextCompat.getDrawable(
                holder.itemView.context, R.drawable.bg_item_category_selected
            )
        } else {
            ContextCompat.getDrawable(holder.itemView.context, R.drawable.bg_item_category)
        }


        holder.itemView.setOnClickListener {

            categories.forEachIndexed { index, category ->
                if (index != position && category.isSelected) {
                    category.isSelected = false
                    notifyItemChanged(index, false)
                }
            }

            category.isSelected = !category.isSelected

            notifyItemChanged(position, false)

        }


    }

    override fun getItemCount(): Int = categories.size

}