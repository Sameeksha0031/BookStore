package com.example.spinnercalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.spinnercalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val firstFragment = FirstFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, firstFragment)
            .commit()

    }

}
    /*binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        var arraySpinnerOne = arrayListOf<String>("Add", "Minus", "Multiply")
        var adapterViewOfSpinnerOne =
            ArrayAdapter(applicationContext, R.layout.spinner_one, arraySpinnerOne)
        binding.spinner.adapter = adapterViewOfSpinnerOne

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                binding.editTextTextPerson.text.clear()
                binding.editTextTextPersonName2.text.clear()
                result = binding.spinner.selectedItem.toString()
                binding.button.setOnClickListener {
                    var firstValue = binding.editTextTextPerson.text.toString().toInt()
                    var secondValue = binding.editTextTextPersonName2.text.toString().toInt()
                    binding.editTextPerson.text = computingOperation(result,firstValue,secondValue).toString()
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    fun computingOperation(value: String, firstValue: Int, secondValue: Int): Int {
        if (value.equals("Add")) {
            return firstValue + secondValue
        }
        if (value.equals("Minus")) {
            return firstValue - secondValue
        }
        if (value.equals("Multiply")) {
            return firstValue * secondValue
        }
        return 0
       }*/





