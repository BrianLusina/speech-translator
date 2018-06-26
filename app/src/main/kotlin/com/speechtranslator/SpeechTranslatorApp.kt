package com.speechtranslator

import android.app.Application
import com.speechtranslator.di.appModules
import com.speechtranslator.di.mainModules
import org.koin.android.ext.android.startKoin


class SpeechTranslatorApp : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModules, mainModules))
    }
}