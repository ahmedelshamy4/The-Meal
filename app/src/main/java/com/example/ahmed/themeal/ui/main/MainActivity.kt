package com.example.ahmed.themeal.ui.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.widget.ImageView
import android.widget.Toast
import com.example.ahmed.themeal.R
import com.example.ahmed.themeal.adapter.AdapterCategories
import com.example.ahmed.themeal.api.ApiConfig
import com.example.ahmed.themeal.model.Categories
import com.example.ahmed.themeal.ui.draftMesls.DaftarMealsActivity
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var mainPresenter: MainPresenter
    private lateinit var adapterCategories: AdapterCategories
    private val listCategories: MutableList<Categories> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ApiConfig.getApi(this)
        mainPresenter = MainPresenter(this)

        setSliderImage()
        swipeRefresh.post {

            loadData()
        }
        swipeRefresh.setOnRefreshListener {

            loadData()
        }
    }

    private fun loadData() {
        mainPresenter.getCategories()
        adapterCategories = AdapterCategories(this, listCategories as ArrayList<Categories>) {
            val intent = Intent(this, DaftarMealsActivity::class.java)
            intent.putExtra("category", it.strCategory)
            startActivity(intent)

        }
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapterCategories
    }

    override fun onError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onResponse(list: ArrayList<Categories>?) {
        listCategories.clear()
        list.let {
            if (list != null) {
                listCategories.addAll(list)
            }

        }
        adapterCategories.notifyDataSetChanged()
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }

    private fun setSliderImage() {
        for (i in 0..3) {
            val sliderView = SliderView(this)
            when (i) {
                0 -> {
                    sliderView.imageUrl = "https://www.themealdb.com/images/media/meals/sypxpx1515365095.jpg"
                }

                1 -> {
                    sliderView.imageUrl = "https://www.themealdb.com/images/media/meals/wrssvt1511556563.jpg"
                }

                2 -> {
                    sliderView.imageUrl = "https://www.themealdb.com/images/media/meals/uyqrrv1511553350.jpg"
                }

                3 -> {
                    sliderView.imageUrl = "https://www.themealdb.com/images/media/meals/1529444830.jpg"
                }
            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP)
            //at last add this view in your layout :
            imageSlider.addSliderView(sliderView)
        }
    }
}
