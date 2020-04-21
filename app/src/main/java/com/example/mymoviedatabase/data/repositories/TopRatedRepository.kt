package com.example.mymoviedatabase.data.repositories

import com.example.mymoviedatabase.data.db.entities.TopRated
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymoviedatabase.data.db.AppDatabase
import com.example.mymoviedatabase.data.network.MyApi
import com.example.mymoviedatabase.data.network.SafeApi
import com.example.mymoviedatabase.data.preferences.PreferenceProvider
import com.example.mymoviedatabase.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private val MINIMUM_INTERVAL = 6

class TopRatedRepositoryRepository(private val api: MyApi,
                         private val db: AppDatabase,
                         private val prefs: PreferenceProvider
): SafeApi() {

    private val topRated = MutableLiveData<List<TopRated>>()

    init {
        topRated.observeForever {
            saveTopRated(it)
        }
    }

    suspend fun getTopRated(): LiveData<List<TopRated>> {
        return withContext(Dispatchers.IO) {
            fetchTopRated()
            db.getTopRatedDao().getTopRated()
        }
    }

    private suspend fun fetchTopRated() {
        val lastSavedAt = prefs.getLastSavedAt()

        if (lastSavedAt == null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))) {
            try {
                val response = apiRequest { api.getTopRated() }
                topRated.postValue(response.topRated)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun isFetchNeeded(savedAt: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(savedAt, LocalDateTime.now()) > MINIMUM_INTERVAL
    }


    private fun saveTopRated(topRated:  List<TopRated>) {
        Coroutines.io {
            prefs.savelastSavedAt(LocalDateTime.now().toString())
            db.getTopRatedDao().saveAllTopRated(topRated)
        }
    }

}