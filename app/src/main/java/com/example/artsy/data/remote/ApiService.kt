package com.example.artsy.data.remote
import com.example.artsy.data.model.ArtPiece
import retrofit2.http.GET
import kotlinx.serialization.Serializable

// this is where i shall be defining the API service


@Serializable
data class ArtworkResponse( // this is a wrapper
    val data: List<ArtPiece>
)

interface ApiService {
    @GET("artworks")
    suspend fun getArtwork(): ArtworkResponse

}