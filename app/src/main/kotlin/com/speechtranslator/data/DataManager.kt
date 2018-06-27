package com.speechtranslator.data


interface DataManager {

    /**
     * Translates the given text, this will then pass the translated text to a callback and if that
     * fails will call the errorCallback passing in the exception
     * @param text [String] Text to translate
     * @param callBack [Function] success callback that is triggered when there is successful
     * translation
     * @param errorCallback [Function] error callback that is called when there is an exception
     * while translating the given text
     */
    fun translateText(text: String, callBack: (String) -> Unit, errorCallback: (Exception?) -> Unit)
}