package com.sanver.example

import com.google.firebase.FirebaseApp
import com.sanver.example.di.component.DaggerApplicationComponent
import com.sanver.example.di.module.ApplicationModule

class App : android.app.Application() {

    val component by lazy {
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}

