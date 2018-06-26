package com.speechtranslator.ui.main

import com.speechtranslator.data.aws.AWSTranslateService
import com.speechtranslator.ui.AbstractPresenter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenterImpl(val awsTranslateService: AWSTranslateService, mainView: MainView) :
        AbstractPresenter<MainView, MainPresenter>(), MainPresenter {

    override fun onSpeechRecognizerAvailable() {
        view.setUpUIComponents()
    }

    override fun onSpeechRecognizerUnavailable() {
        view.displayErrorSpeechRecognitionMissing()
    }

    override fun onTranslateSpeech(speech: String) {
        doAsync {
            awsTranslateService.translate(speech) {
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