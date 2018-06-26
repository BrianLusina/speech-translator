package com.speechtranslator.ui.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.speechtranslator.R
import com.speechtranslator.di.Params.MAIN_VIEW
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MainView, View.OnClickListener {

    override val presenter: MainPresenter by inject { mapOf(MAIN_VIEW to this) }

    private val requestSpeechRecognizerCode = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_start_recognizer.setOnClickListener(this)

        // The button is (by default) disabled, so you can't click it.  Enable it if the
        // isRecognitionAvailable() supported
        if (SpeechRecognizer.isRecognitionAvailable(this)) {
            presenter.onSpeechRecognizerAvailable()
        } else {
            presenter.onSpeechRecognizerUnavailable()
        }
    }

    override fun onClick(v: View?) {
        when(v){
            btn_start_recognizer -> {
                val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                    putExtra(
                            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                    )
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE, true)
                    }
                }
                startActivityForResult(intent, requestSpeechRecognizerCode)
            }
        }
    }

    override fun displayErrorSpeechRecognitionMissing() {
        toast("Speech Recognition unavailable")
    }

    override fun setUpUIComponents() {
        btn_start_recognizer.isEnabled = true
    }

    override fun displayTranslatedText(translation: String) {
        text_translated.text = translation
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == requestSpeechRecognizerCode) {
            if (resultCode == RESULT_OK) {
                data?.let {
                    val results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    txt_recorded_speech.text = results[0]
                    presenter.onTranslateSpeech(results[0])
                }
            }
        }
    }

    override fun onDestroy() {
        presenter.onStop()
        super.onDestroy()
    }
}
