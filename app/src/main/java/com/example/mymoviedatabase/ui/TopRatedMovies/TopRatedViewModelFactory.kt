package com.example.mymoviedatabase.ui.TopRatedMovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymoviedatabase.data.repositories.TopRatedRepositoryRepository

class TopRatedViewModelFactory(private val repository: TopRatedRepositoryRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TopRatedMoviesViewModel(repository) as T
    }
}