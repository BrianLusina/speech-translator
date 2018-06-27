package com.speechtranslator.ui.main

import com.speechtranslator.ui.base.BaseView

interface MainView : BaseView<MainPresenter>{

    /**
     * Sets up UI components
     */
    fun setUpUIComponents()

    /**
     * Displays an error if there is no speech recognition
     */
    fun displayErrorSpeechRecognitionMissing()

    /**
     * Displays the translated text
     * @param translation [String] translated text
     */
    fun displayTranslatedText(translation: String)

    /**
     * Displays an error on failed translation of text
     */
    fun displayError()
}