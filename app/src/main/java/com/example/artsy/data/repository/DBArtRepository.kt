package com.example.artsy.data.repository
import com.example.artsy.data.model.ArtPiece
import kotlinx.coroutines.flow.Flow



interface DBArtRepository
{
    fun getArtworks(): Flow<List<ArtPiece>>
    fun getArtworkById(id: Int): Flow<ArtPiece>
    suspend fun insertItem(item: ArtPiece)
    suspend fun deleteItem(item: ArtPiece)
}