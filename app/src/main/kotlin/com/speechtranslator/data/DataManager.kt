package com.speechtranslator.data


interface DataManager {

    fun translateText(text: String, callBack: (String) -> Unit)
}