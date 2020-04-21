package com.example.mymoviedatabase.data.network.responses

import com.example.mymoviedatabase.data.db.entities.TopRated

data class TopRatedResponse (
    val isSuccessful: Boolean,
    val topRated: List<TopRated>
)
