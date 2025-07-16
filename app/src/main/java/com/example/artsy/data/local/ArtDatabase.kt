package com.example.artsy.data.local
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.artsy.data.model.ArtPiece


@Database(entities = [ArtPiece::class], version = 1, exportSchema = false)
abstract class ArtDatabase: RoomDatabase() {  // returns an instance of a database
    abstract fun itemDao(): ArtDAO
    companion object{
        @Volatile
        private var Instance: ArtDatabase?= null

        fun getDatabase(context: Context): ArtDatabase{
            return Instance?: synchronized(this) {
                Room.databaseBuilder(context, ArtDatabase::class.java, "art_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }

            }
        }

    }
}