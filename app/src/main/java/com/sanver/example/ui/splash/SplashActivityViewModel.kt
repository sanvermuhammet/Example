package com.sanver.example.ui.splash

import android.app.Application
import androidx.databinding.ObservableField
import com.sanver.example.App
import com.sanver.example.core.BaseViewModel
import com.sanver.example.db.AppDatabase
import javax.inject.Inject

class SplashActivityViewModel(app: Application) : BaseViewModel(app) {
    // private var examples: LiveData<List<Example>>? = null

    @Inject
    lateinit var db: AppDatabase

    init {
        (app as? App)?.component?.inject(this)
    }

    var appName = ObservableField<String>()


}