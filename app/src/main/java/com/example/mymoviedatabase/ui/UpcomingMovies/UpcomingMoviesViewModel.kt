package com.example.mymoviedatabase.ui.UpcomingMovies

import androidx.lifecycle.ViewModel
import com.example.mymoviedatabase.data.repositories.UpcomingRepository
import com.example.mymoviedatabase.util.lazyDeferred

class UpcomingMoviesViewModel(upcomingRepository: UpcomingRepository) : ViewModel() {
    val upcoming by lazyDeferred {
        upcomingRepository.getUpcoming()
    }
}
