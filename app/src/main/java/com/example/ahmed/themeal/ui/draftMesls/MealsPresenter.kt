package com.example.ahmed.themeal.ui.draftMesls

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.ahmed.themeal.api.ApiResponse

class MealsPresenter(private val mealsView: MealsView) {
    fun getMeals(category: String?) {
        mealsView.showLoading()
        AndroidNetworking.get("https://www.themealdb.com/api/json/v1/1/filter.php?c={category}")
            .addPathParameter("category", category)
            .setTag(this)
            .setPriority(Priority.LOW)
            .build()
            .getAsObject(ApiResponse::class.java, object : ParsedRequestListener<ApiResponse> {
                override fun onResponse(response: ApiResponse?) {
                    response.let {
                        if (response != null) {
                            mealsView.onResponse(response.meals)
                        }
                    }
                    mealsView.hideLoading()
                }

                override fun onError(anError: ANError?) {
                    mealsView.onError(anError!!.message)
                    mealsView.hideLoading()

                }

            })
    }
}