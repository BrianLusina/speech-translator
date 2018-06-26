package com.speechtranslator.ui.main

import com.speechtranslator.ui.base.BaseView

interface MainView : BaseView<MainPresenter>{

    fun setUpUIComponents()

    fun displayErrorSpeechRecognitionMissing()

    fun displayTranslatedText(translation: String)
}