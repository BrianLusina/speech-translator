package com.speechtranslator.data.aws

import com.amazonaws.handlers.AsyncHandler
import com.amazonaws.services.translate.AmazonTranslateAsyncClient
import com.amazonaws.services.translate.model.TranslateTextRequest
import com.amazonaws.services.translate.model.TranslateTextResult

class AWSTranslateService(awsService: AWSService) {
    private val translateClient = AmazonTranslateAsyncClient(awsService.credentialsProvider)

    /**
     * Translate some text, returning it in a callback
     */
    fun translate(textToTranslate: String, callback: (String) -> Unit, errorCallBack: (Exception?) -> Unit) {
        val translateRequest = TranslateTextRequest().apply {
            sourceLanguageCode = "en"
            targetLanguageCode = "es"
            text = textToTranslate
        }

        translateClient.translateTextAsync(translateRequest, object : AsyncHandler<TranslateTextRequest, TranslateTextResult> {
            override fun onSuccess(request: TranslateTextRequest?, result: TranslateTextResult?) {
                callback(result!!.translatedText)
            }

            override fun onError(exception: Exception?) {
                errorCallBack(exception)
            }
        })
    }
}