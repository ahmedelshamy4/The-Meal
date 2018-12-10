package com.example.ahmed.themeal.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.ahmed.themeal.R
import com.example.ahmed.themeal.model.Categories
import kotlinx.android.synthetic.main.layout_list_categories.view.*

class AdapterCategories(
    private var context: Context,
    private val list: ArrayList<Categories>,
    private val listener: (Categories) -> Unit
) : RecyclerView.Adapter<AdapterCategories.myViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): myViewHolder =
        AdapterCategories.myViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_list_categories, p0, false))


    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(p0: myViewHolder, p1: Int) {
        p0.bindItem(context, list[p1], listener)
    }

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(context: Context, categories: Categories, listener: (Categories) -> Unit) {
            itemView.tv_category.text = categories.strCategory
            Glide.with(context).load(categories.strCategoryThumb).into(itemView.iv_image)
            itemView.setOnClickListener { listener(categories) }
        }
    }

}