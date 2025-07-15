package com.example.artsy.data

import com.example.artsy.data.remote.ApiService
import com.example.artsy.data.repository.ArtRepository
import com.example.artsy.data.repository.RemoteArtRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

// here will be the app container


interface AppContainer{
    val artRepository: ArtRepository
}


class defaultAppContainer: AppContainer {

    private val baseURL = "https://openaccess-api.clevelandart.org/api/"
    val json = Json {
        ignoreUnknownKeys = true // helps avoid crash on extra fields
    }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseURL)
        .build()


    private val retrofitService: ApiService by lazy{
        retrofit.create(ApiService::class.java)
    }

    override val artRepository: ArtRepository by lazy{
        RemoteArtRepository(retrofitService)
    }


}