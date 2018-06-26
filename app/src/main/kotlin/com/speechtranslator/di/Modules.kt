package com.speechtranslator.di

import com.speechtranslator.data.aws.AWSService
import com.speechtranslator.data.aws.AWSTranslateService
import com.speechtranslator.di.Params.MAIN_VIEW
import com.speechtranslator.ui.main.MainPresenter
import com.speechtranslator.ui.main.MainPresenterImpl
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

val appModules : Module = applicationContext {
    bean { AWSService(get()) }
    bean { AWSTranslateService(get()) }
}

val mainModules : Module = applicationContext {
    factory { params -> MainPresenterImpl(get(), params[MAIN_VIEW]) as MainPresenter }
}

object Params{
    const val MAIN_VIEW = "MAIN_VIEW"
}