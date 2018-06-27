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
        // perform the operation in an async thread. This will translate the text, calling the
        // data manager layer to handle translation. A successful callback is passes in the form of
        // a lambda function and will update the UI on the uiThread, updating the translated text
        // this will also pass in the exception callback to handle any form of errors and update the
        // ui with the relevant error message
        doAsync {
            dataManager.translateText(speech, {translation ->
                uiThread {
                    view.displayTranslatedText(translation)
                }
            }){
                // on exception
                view.displayError()
            }
        }
    }

    override fun onStop() {
    }
}