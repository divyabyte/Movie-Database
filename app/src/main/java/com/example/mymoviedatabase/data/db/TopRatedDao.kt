package com.example.mymoviedatabase.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mymoviedatabase.data.db.entities.TopRated

@Dao
interface TopRatedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllTopRated(topRated : List<TopRated>)

    @Query("SELECT * FROM TopRated")
    fun getTopRated() : LiveData<List<TopRated>>

}