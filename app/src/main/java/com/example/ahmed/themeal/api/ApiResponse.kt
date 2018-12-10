package com.example.ahmed.themeal.api

import com.example.ahmed.themeal.model.Categories
import com.example.ahmed.themeal.model.Meals
import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("categories")
    val categories: ArrayList<Categories>,

    @SerializedName("meals")
    val meals: ArrayList<Meals>
)
