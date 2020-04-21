package com.example.mymoviedatabase.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymoviedatabase.data.db.AppDatabase
import com.example.mymoviedatabase.data.db.entities.Upcoming
import com.example.mymoviedatabase.data.network.MyApi
import com.example.mymoviedatabase.data.network.SafeApi
import com.example.mymoviedatabase.data.preferences.PreferenceProvider
import com.example.mymoviedatabase.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private val MINIMUM_INTERVAL = 6

class UpcomingRepository(private val api: MyApi,
                         private val db: AppDatabase,
                         private val prefs: PreferenceProvider
): SafeApi() {

    private val upcoming = MutableLiveData<List<Upcoming>>()

    init {
        upcoming.observeForever {
            saveUpcoming(it)
        }
    }

    suspend fun getUpcoming(): LiveData<List<Upcoming>> {
        return withContext(Dispatchers.IO) {
            fetchUpcoming()
            db.getUpcomingDao().getUpcoming()
        }
    }

    private suspend fun fetchUpcoming() {
        val lastSavedAt = prefs.getLastSavedAt()

        if (lastSavedAt == null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))) {
            try {
                val response = apiRequest { api.getUpcoming() }
                upcoming.postValue(response.upcomingResponse)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun isFetchNeeded(savedAt: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(savedAt, LocalDateTime.now()) > MINIMUM_INTERVAL
    }


    private fun saveUpcoming(upcoming:  List<Upcoming>) {
        Coroutines.io {
            prefs.savelastSavedAt(LocalDateTime.now().toString())
            db.getUpcomingDao().saveAllUpcoming(upcoming)
        }
    }

}