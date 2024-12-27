package com.shabelnikd.eatmorepizza

data class Food(val title: String, val imageId: Int, val coast: Int, var rating: Int,
                val category: Category, var isSelected: Boolean = false)