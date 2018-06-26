package com.speechtranslator.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.speechtranslator.R
import com.speechtranslator.di.Params.MAIN_VIEW
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MainView {

    override val presenter: MainPresenter by inject { mapOf(MAIN_VIEW to this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
