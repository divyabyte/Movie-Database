package com.example.mymoviedatabase.data.network.responses

import com.example.mymoviedatabase.data.db.entities.TopRated
import com.example.mymoviedatabase.data.db.entities.Upcoming

data class UpcomingResponse (
    val isSuccessful: Boolean,
    val upcomingResponse: List<Upcoming>
)
