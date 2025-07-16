package com.example.artsy.data.remote
import com.example.artsy.data.model.ArtPiece
import retrofit2.http.GET
import kotlinx.serialization.Serializable
import retrofit2.http.Path

// this is where i shall be defining the API service


@Serializable
data class ArtworkResponse( // this is a wrapper
    val data: List<ArtPiece>
)


@Serializable
data class ArtPieceResponse(
    val data: ArtPiece
)

interface ApiService {
    @GET("artworks")
    suspend fun getArtwork(): ArtworkResponse


    @GET("artworks/{id}")
    suspend fun getArtworkbyId(@Path("id") id: Int): ArtPieceResponse

}