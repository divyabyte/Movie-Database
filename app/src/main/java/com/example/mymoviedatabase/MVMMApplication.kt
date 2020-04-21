package com.example.mymoviedatabase

import android.app.Application
import com.example.mymoviedatabase.data.db.AppDatabase
import com.example.mymoviedatabase.data.network.MyApi
import com.example.mymoviedatabase.data.network.NetworkConnectionInterceptor
import com.example.mymoviedatabase.data.preferences.PreferenceProvider
import com.example.mymoviedatabase.data.repositories.TopRatedRepositoryRepository
import com.example.mymoviedatabase.data.repositories.UpcomingRepository
import com.example.mymoviedatabase.ui.TopRatedMovies.TopRatedViewModelFactory
import com.example.mymoviedatabase.ui.UpcomingMovies.UpcomingMoviesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class MVMMApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVMMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { TopRatedRepositoryRepository (instance(), instance(), instance()) }
        bind() from singleton { UpcomingRepository (instance(), instance(), instance()) }
        bind() from singleton { TopRatedViewModelFactory( instance()) }
        bind() from singleton { UpcomingMoviesViewModelFactory(instance()) }
    }
}