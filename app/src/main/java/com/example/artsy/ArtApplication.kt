package com.example.artsy

import android.app.Application
import com.example.artsy.data.AppContainer
import com.example.artsy.data.defaultAppContainer


class ArtApplication : Application() {
    lateinit var container: AppContainer
        override fun onCreate() {
            super.onCreate()
            container = defaultAppContainer(this)
        }
    }

