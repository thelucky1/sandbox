package com.shopopop.sandbox

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class InputActivity : AppCompatActivity() {

    companion object {
        const val BTN_CLICKED_KEY = "btnClicked"
        const val SENT_MESSAGE = "sentMessage"
        const val FIRST_BUTTON_CLICKED = "1"
        const val SECOND_BUTTON_CLICKED = "2"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        val submit = findViewById<Button>(R.id.submit)

        val btnClicked =  intent.getStringExtra(BTN_CLICKED_KEY)
        submit.setOnClickListener {

            val editText = findViewById<EditText>(R.id.inputMessage)
            val intent = Intent(this, MainActivity::class.java)
            if (btnClicked == SECOND_BUTTON_CLICKED) {
                intent.putExtra(SENT_MESSAGE, editText.text.toString())
                setResult(Activity.RESULT_OK,intent)
                finish()
            } else {
                setResult(Activity.RESULT_CANCELED, null)
                finish()
            }
        }
    }
}
