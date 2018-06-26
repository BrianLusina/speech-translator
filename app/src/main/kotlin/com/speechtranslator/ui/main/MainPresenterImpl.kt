package com.speechtranslator.ui.main

import com.speechtranslator.data.aws.AWSTranslateService
import com.speechtranslator.ui.AbstractPresenter

class MainPresenterImpl(val awsTranslateService: AWSTranslateService,
                        var mainView: MainView) :
        AbstractPresenter<MainView, MainPresenter>(), MainPresenter {

    override fun onStop() {
    }
}