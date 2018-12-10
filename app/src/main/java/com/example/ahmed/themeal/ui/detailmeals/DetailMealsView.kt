package com.example.ahmed.themeal.ui.detailmeals

import com.example.ahmed.themeal.model.Meals

interface DetailMealsView {
    fun showLoading()
    fun onResponse(list: ArrayList<Meals>?)
    fun onError(message: String?)
    fun hideLoading()
}