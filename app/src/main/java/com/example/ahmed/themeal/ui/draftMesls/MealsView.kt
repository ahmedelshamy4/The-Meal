package com.example.ahmed.themeal.ui.draftMesls

import com.example.ahmed.themeal.model.Categories
import com.example.ahmed.themeal.model.Meals

interface MealsView {
    fun showLoading()
    fun hideLoading()
    fun onError(message: String?)
    fun onResponse(list: ArrayList<Meals>?)
}