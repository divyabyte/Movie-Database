package com.example.mymoviedatabase.ui.TopRatedMovies

import com.example.mymoviedatabase.R
import com.example.mymoviedatabase.data.db.entities.TopRated
import com.example.mymoviedatabase.databinding.ItemTopRatedBinding
import com.xwray.groupie.databinding.BindableItem

class TopItem(private val topItem : TopRated) : BindableItem<ItemTopRatedBinding>() {

    override fun getLayout() = R.layout.item_top_rated

    override fun bind(viewBinding: ItemTopRatedBinding, position: Int) {
        viewBinding.topRated
    }
}