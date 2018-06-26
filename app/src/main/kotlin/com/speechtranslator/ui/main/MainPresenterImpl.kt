package com.speechtranslator.ui.main

import com.speechtranslator.data.DataManager
import com.speechtranslator.ui.AbstractPresenter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenterImpl(val dataManager: DataManager, override var view: MainView) :
        AbstractPresenter<MainView, MainPresenter>(), MainPresenter {

    override fun onSpeechRecognizerAvailable() {
        view.setUpUIComponents()
    }

    override fun onSpeechRecognizerUnavailable() {
        view.displayErrorSpeechRecognitionMissing()
    }

    override fun onTranslateSpeech(speech: String) {
        doAsync {
            dataManager.translateText(speech) {
                val translation = it
                uiThread {
                    view.displayTranslatedText(translation)
                }
            }
        }
    }

    override fun onStop() {
    }
}