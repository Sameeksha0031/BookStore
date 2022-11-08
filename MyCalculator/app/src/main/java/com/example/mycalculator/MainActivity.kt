package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun print(view: View) {
        view.setOnClickListener{
            var firstStart =findViewById<Button>(R.id.firstStart)
            firstStart.text = "2343534"
            //firstStart.setText("rtrtr")
        }
    }
}