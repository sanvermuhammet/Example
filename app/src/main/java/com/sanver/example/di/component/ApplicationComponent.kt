package com.sanver.example.di.component

import android.content.Context
import android.content.SharedPreferences
import com.sanver.example.App
import com.sanver.example.di.module.ApplicationModule
import com.sanver.example.di.module.DatabaseModule
import com.sanver.example.ui.main.MainActivityViewModel

import com.sanver.example.di.module.NetModule
import com.sanver.example.ui.splash.SplashActivityViewModel

import dagger.Component
import javax.inject.Singleton


@Singleton

@Component(modules = arrayOf(ApplicationModule::class,NetModule::class,DatabaseModule::class))


interface ApplicationComponent {
    fun app(): App


    fun context(): Context

    fun preferences(): SharedPreferences

    fun inject(mainActivityViewModel: MainActivityViewModel)
    fun inject(splashActivityViewModel: SplashActivityViewModel)
}
