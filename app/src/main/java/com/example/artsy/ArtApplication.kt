package com.example.artsy

import android.app.Application
import com.example.artsy.data.AppContainer
import com.example.artsy.data.defaultAppContainer


class ArtApplication : Application() {
        /** AppContainer instance used by the rest of classes to obtain dependencies */
        lateinit var container: AppContainer
        override fun onCreate() {
            super.onCreate()
            container = defaultAppContainer()
        }
    }

