package com.example.artsy.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.artsy.data.model.ArtPiece
import kotlinx.coroutines.flow.Flow


@Dao
interface ArtDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(artPiece: ArtPiece)

    @Delete
    suspend fun delete(artPiece: ArtPiece)

    @Query("SELECT * from Favourites WHERE id = :id")
    fun getItem(id: Int): Flow<ArtPiece>

    @Query("SELECT * FROM Favourites")
    fun getAllItems():Flow<List<ArtPiece>>

}