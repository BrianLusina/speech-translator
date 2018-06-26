package com.speechtranslator.ui

import com.speechtranslator.ui.base.BasePresenter
import com.speechtranslator.ui.base.BaseView

abstract class AbstractPresenter<V : BaseView<P>, out P : BasePresenter<V>> : BasePresenter<V> {

    override lateinit var view: V
}