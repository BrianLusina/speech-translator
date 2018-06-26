package com.speechtranslator.ui.base

interface BasePresenter<V>{
    fun onStop()

    var view : V
}