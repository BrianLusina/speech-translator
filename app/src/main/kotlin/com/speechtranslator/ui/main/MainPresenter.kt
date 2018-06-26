package com.speechtranslator.ui.main

import com.speechtranslator.ui.base.BasePresenter

interface MainPresenter : BasePresenter<MainView>{

    fun onSpeechRecognizerUnavailable()

    fun onSpeechRecognizerAvailable()

    fun onTranslateSpeech(speech: String)
}