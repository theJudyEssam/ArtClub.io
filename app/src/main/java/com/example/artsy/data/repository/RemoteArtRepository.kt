package com.example.artsy.data.repository

import com.example.artsy.data.model.ArtPiece
import com.example.artsy.data.remote.ApiService

class RemoteArtRepository(private val apiService: ApiService): NetworkArtRepository {
    override suspend fun getArtworks(): List<ArtPiece> = apiService.getArtwork().data
    override suspend fun getArtworkById(id: Int): ArtPiece = apiService.getArtworkbyId(id).data
}