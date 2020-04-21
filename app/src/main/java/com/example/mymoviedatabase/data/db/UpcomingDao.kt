package com.example.mymoviedatabase.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mymoviedatabase.data.db.entities.Upcoming

@Dao
interface UpcomingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllUpcoming(upcoming : List<Upcoming>)

    @Query("SELECT * FROM Upcoming")
    fun getUpcoming() : LiveData<List<Upcoming>>

}