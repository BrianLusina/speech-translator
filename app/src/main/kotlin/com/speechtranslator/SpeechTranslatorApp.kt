package com.speechtranslator

import android.app.Application
import com.speechtranslator.di.appModules
import com.speechtranslator.di.dataModules
import com.speechtranslator.di.mainModules
import org.koin.android.ext.android.startKoin
import com.microsoft.appcenter.crashes.Crashes
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.AppCenter




class SpeechTranslatorApp : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModules, mainModules, dataModules))
        setupAppCenter()
    }

    private fun setupAppCenter(){
        AppCenter.start(this, BuildConfig.APP_CENTER_KEY,
                Analytics::class.java, Crashes::class.java
        )
    }
}