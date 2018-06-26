package com.speechtranslator.ui.base

interface BaseView<out P: BasePresenter<*>>{
    val presenter: P
}