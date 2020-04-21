package com.example.mymoviedatabase.ui.UpcomingMovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymoviedatabase.data.repositories.UpcomingRepository

class UpcomingMoviesViewModelFactory(private val upcomingRepository: UpcomingRepository): ViewModelProvider.NewInstanceFactory()   {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UpcomingMoviesViewModel(upcomingRepository) as T
    }
}