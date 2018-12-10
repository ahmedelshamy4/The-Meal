package com.example.ahmed.themeal.ui.main

import com.example.ahmed.themeal.model.Categories

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun onError(message: String?)
    fun onResponse(list: ArrayList<Categories>?)
}