package com.example.artsy.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


// todo: this is will be the Art Piece Model

@Serializable
data class ArtPiece (
    val id: Int,
    val title: String,

    @SerialName("creation_date")
    val creationDate: String? = null,

    val tombstone: String? = null,
    val url: String, // link to CMA
    val images: ImageVariants? = null
)

@Serializable
data class ImageVariants(
    val web: ImageInfo? = null
)

@Serializable
data class ImageInfo(
    val url: String
)
