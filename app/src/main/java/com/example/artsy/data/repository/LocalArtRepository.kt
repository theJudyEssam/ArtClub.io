package com.example.artsy.data.repository
import com.example.artsy.data.local.ArtDAO
import com.example.artsy.data.model.ArtPiece
import kotlinx.coroutines.flow.Flow


// AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
class LocalArtRepository(private val artDao: ArtDAO): DBArtRepository {
    override  fun getArtworks():  Flow<List<ArtPiece>> = artDao.getAllItems()

    override  fun getArtworkById(id: Int): Flow<ArtPiece> =artDao.getItem(id)

    override suspend fun insertItem(item: ArtPiece) = artDao.insert(item)

    override suspend fun deleteItem(item: ArtPiece) = artDao.delete(item)
}