package com.speechtranslator.ui.main

import com.speechtranslator.ui.base.BasePresenter

interface MainPresenter : BasePresenter<MainView>{

    /**
     * Callback for when there is speech recognition available
     */
    fun onSpeechRecognizerUnavailable()

    /**
     * Callback for when there is no speech recognition available
     */
    fun onSpeechRecognizerAvailable()

    /**
     * Callback when we can translate the speech text
     * @param speech [String] Speech text to translate
     */
    fun onTranslateSpeech(speech: String)
}