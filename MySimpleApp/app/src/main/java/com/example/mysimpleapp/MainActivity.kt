package com.example.mysimpleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    var canAddOperation = false
    var canAddDecimal = true
    var btnDelete = findViewById<Button>(R.id.btnDelete)
    var btnOne = findViewById<Button>(R.id.btnOne)
    var btnTwo = findViewById<Button>(R.id.btnTwo)
    var btnThree = findViewById<Button>(R.id.btnThree)
    var btnFour = findViewById<Button>(R.id.btnFour)
    var btnFive = findViewById<Button>(R.id.btnFive)
    var btnSix = findViewById<Button>(R.id.btnSix)
    var btnSeven = findViewById<Button>(R.id.btnSeven)
    var btnEight = findViewById<Button>(R.id.btnEight)
    var btnNine= findViewById<Button>(R.id.btnNine)
    var btnZero = findViewById<Button>(R.id.btnZero)
    var workingTv = findViewById<TextView>(R.id.workingTv)
    var resultTv = findViewById<TextView>(R.id.resultTv)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDelete.setOnClickListener {
            workingTv.text = " "
            resultTv.text = " "
        }
        btnOne.setOnClickListener {
            workingTv.text = "1"
            Toast.makeText(this,"you press 1",Toast.LENGTH_SHORT)
        }
        btnTwo.setOnClickListener {
            workingTv.text = "2"
        }
        btnThree.setOnClickListener {
            workingTv.text = "3"
        }
        btnFour.setOnClickListener {
            workingTv.text = "4"
        }
        btnFive.setOnClickListener {
            workingTv.text = "5"
        }
        btnSix.setOnClickListener {
            workingTv.text = "6"
        }
        btnSeven.setOnClickListener {
            workingTv.text = "7"
        }
        btnEight.setOnClickListener {
            workingTv.text = "8"
        }
        btnNine.setOnClickListener {
            workingTv.text = "9"
        }
        btnZero.setOnClickListener {
            workingTv.text = "0"
        }
    }
}