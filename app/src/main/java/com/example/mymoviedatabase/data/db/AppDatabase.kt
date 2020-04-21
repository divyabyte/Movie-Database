package com.example.mymoviedatabase.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mymoviedatabase.data.db.entities.TopRated
import com.example.mymoviedatabase.data.db.entities.Upcoming

@Database(
    entities = [TopRated::class,Upcoming::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase(){

    abstract fun getTopRatedDao(): TopRatedDao
    abstract fun getUpcomingDao(): UpcomingDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "MyDatabase.db"
            ).build()
    }
}