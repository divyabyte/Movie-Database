package com.example.mymoviedatabase.ui.UpcomingMovies

import com.example.mymoviedatabase.R
import com.example.mymoviedatabase.data.db.entities.Upcoming
import com.example.mymoviedatabase.databinding.ItemUpcomingBinding
import com.xwray.groupie.databinding.BindableItem

class UpcomingItem(private val upcomingItem : Upcoming): BindableItem<ItemUpcomingBinding>() {
    override fun getLayout() = R.layout.item_upcoming


    override fun bind(viewBinding: ItemUpcomingBinding, position: Int) {
        viewBinding.upcoming
    }
}