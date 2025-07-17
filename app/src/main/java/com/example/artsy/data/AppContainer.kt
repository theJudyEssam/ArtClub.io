package com.example.artsy.data

import android.content.Context
import com.example.artsy.data.local.ArtDatabase
import com.example.artsy.data.remote.ApiService
import com.example.artsy.data.repository.DBArtRepository
import com.example.artsy.data.repository.LocalArtRepository
import com.example.artsy.data.repository.NetworkArtRepository
import com.example.artsy.data.repository.RemoteArtRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

// here will be the app container


interface AppContainer{
    val NetworkartRepository: NetworkArtRepository
    val DBArtRepository : DBArtRepository
}


class defaultAppContainer(context: Context): AppContainer {


    // for logging purposes
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()


    // Connecting to RetroFit
    private val baseURL = "https://openaccess-api.clevelandart.org/api/"
    val json = Json {
        ignoreUnknownKeys = true // helps avoid crash on extra fields
    }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseURL)
        .client(client)
        .build()

    private val retrofitService: ApiService by lazy{
        retrofit.create(ApiService::class.java)
    }

    override val NetworkartRepository: NetworkArtRepository by lazy{
        RemoteArtRepository(retrofitService)
    }


    // Connected to the Database
    override val DBArtRepository: DBArtRepository  by lazy {
        LocalArtRepository(ArtDatabase.getDatabase(context).itemDao())
    }


}