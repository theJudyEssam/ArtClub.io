package com.example.artsy.data.repository

import com.example.artsy.data.model.ArtPiece
import com.example.artsy.data.remote.ApiService

class RemoteArtRepository(private val apiService: ApiService): ArtRepository {
    override suspend fun getArtworks(): List<ArtPiece> = apiService.getArtwork().data
}