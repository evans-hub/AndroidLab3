package com.example.androidlab3.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidlab3.Model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class DB_Movie : RoomDatabase() {

    abstract fun movieDao(): Dao_Movie

    companion object {
        @Volatile
        private var INSTANCE: DB_Movie? = null

        fun getDatabase(context: Context): DB_Movie {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DB_Movie::class.java,
                    "film_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
