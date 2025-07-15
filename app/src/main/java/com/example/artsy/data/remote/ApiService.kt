package com.example.artsy.data.remote
import com.example.artsy.data.model.ArtPiece
import retrofit2.http.GET

// this is where i shall be defining the API service
interface ApiService {


    @GET("artworks")
    suspend fun getArtwork(): List<ArtPiece>

}