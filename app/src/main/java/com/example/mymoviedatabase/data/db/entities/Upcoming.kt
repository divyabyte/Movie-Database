package com.example.mymoviedatabase.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Upcoming(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val poster_path: String,
    val vote_average: String,
    val release_date: String
)