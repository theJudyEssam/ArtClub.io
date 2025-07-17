package com.example.artsy.data.repository

import com.example.artsy.data.model.ArtPiece

interface NetworkArtRepository {
    suspend fun getArtworks(): List<ArtPiece>
    suspend fun getArtworkById(id:Int): ArtPiece
}

