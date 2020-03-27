package com.sanver.example.ui.splash


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import android.widget.Toast

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.sanver.example.R
import com.sanver.example.core.BaseActivity

import com.sanver.example.databinding.ActivitySplashBinding
import com.sanver.example.ui.main.MainActivity


class SplashActivity :
    BaseActivity<SplashActivityViewModel, ActivitySplashBinding>(SplashActivityViewModel::class.java) {

    override fun initViewModel(viewModel: SplashActivityViewModel) {
        binding.viewModel = viewModel
    }

    lateinit var remoteConfig: FirebaseRemoteConfig
    lateinit var handler: Handler

    override fun getLayoutRes() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        remoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(3600)
            .build()
        remoteConfig.setConfigSettingsAsync(configSettings)

        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val updated = task.result
                    handler = Handler()
                    viewModel.appName.set(remoteConfig.getString("app_name"))
                    handler.postDelayed(
                        {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        },
                        3000
                    )

                } else {
                    Toast.makeText(
                        this, "Failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }




        super.onCreate(savedInstanceState)
    }

    override fun init() {
        super.init()
    }
}
