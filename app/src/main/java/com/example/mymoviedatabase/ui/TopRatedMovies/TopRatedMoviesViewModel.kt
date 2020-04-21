package com.example.mymoviedatabase.ui.TopRatedMovies

import androidx.lifecycle.ViewModel
import com.example.mymoviedatabase.data.repositories.TopRatedRepositoryRepository
import com.example.mymoviedatabase.util.lazyDeferred

class TopRatedMoviesViewModel( repository: TopRatedRepositoryRepository) : ViewModel() {

    val topRated by lazyDeferred {
        repository.getTopRated()
    }

}
