package com.example.ahmed.themeal.ui.draftMesls

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.ahmed.themeal.R
import com.example.ahmed.themeal.adapter.AdapterMeals
import com.example.ahmed.themeal.api.ApiConfig
import com.example.ahmed.themeal.model.Meals
import com.example.ahmed.themeal.ui.detailmeals.DetailMealsActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class DaftarMealsActivity : AppCompatActivity(), MealsView {


    private lateinit var mealsPresenter: MealsPresenter
    private lateinit var adapterMeals: AdapterMeals
    private val listMeals: MutableList<Meals> = mutableListOf()
    private var category: String? = null //get intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftarmeals)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        }
        title = "Food List"

        ApiConfig.getApi(this)
        mealsPresenter = MealsPresenter(this)

        var intent = intent
        intent.let {
            category = it.getStringExtra("category")
        }
        swipeRefresh.post {

            loadData(category)
        }
        swipeRefresh.setOnRefreshListener {

            loadData(category)
        }

    }

    private fun loadData(category: String?) {
        mealsPresenter.getMeals(category)
        adapterMeals = AdapterMeals(this, listMeals) {
            val intent = Intent(this, DetailMealsActivity::class.java)
            intent.putExtra("id", it.idMeal)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapterMeals
    }


    override fun showLoading() {
        swipeRefresh.isRefreshing = true

    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }

    override fun onError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

    }

    override fun onResponse(list: ArrayList<Meals>?) {
      listMeals.clear()
        list.let {
            if (list != null) {
                listMeals.addAll(list)
            }
        }
        adapterMeals.notifyDataSetChanged()
    }
}
