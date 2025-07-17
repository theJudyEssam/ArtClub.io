package com.example.artsy.data.remote
import com.example.artsy.data.model.ArtPiece
import retrofit2.http.GET
import kotlinx.serialization.Serializable
import retrofit2.http.Path


// Wrappers for the API, since the responses are nested in "data"
@Serializable
data class ArtworkResponse(
    val data: List<ArtPiece>
)
@Serializable
data class ArtPieceResponse(
    val data: ArtPiece
)

interface ApiService { // the API service
    @GET("artworks")
    suspend fun getArtwork(): ArtworkResponse

    @GET("artworks/{id}")
    suspend fun getArtworkbyId(@Path("id") id: Int): ArtPieceResponse

}