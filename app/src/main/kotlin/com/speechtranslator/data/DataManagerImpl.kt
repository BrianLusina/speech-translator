package com.speechtranslator.data

import com.speechtranslator.data.aws.AWSTranslateService


class DataManagerImpl(val awsTranslateService: AWSTranslateService) : DataManager{

    override fun translateText(text: String, callBack: (String) -> Unit, errorCallback: (Exception?) -> Unit) {
        awsTranslateService.translate(text, callBack, errorCallback)
    }
}