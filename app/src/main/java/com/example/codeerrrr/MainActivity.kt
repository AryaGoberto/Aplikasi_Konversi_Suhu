package com.example.codeerrrr

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var spinnerFrom: Spinner
    private lateinit var spinnerTo: Spinner
    private lateinit var resultText: TextView
    private lateinit var buttonHasil: Button
    private lateinit var inputSuhu : EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //daftar semua elemen
        spinnerFrom = findViewById(R.id.spinnerFrom)
        spinnerTo = findViewById(R.id.spinnerTo)
        resultText = findViewById(R.id.hasilText)
        buttonHasil = findViewById(R.id.buttonKalkulasi)
        inputSuhu = findViewById(R.id.inputSuhu)

        //daftar units yang telah dibuat
        val units = resources.getStringArray(R.array.suhu_array)
        val adapterUnit = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)

        adapterUnit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFrom.adapter = adapterUnit
        spinnerTo.adapter = adapterUnit

        buttonHasil.setOnClickListener{
            val input = inputSuhu.text.toString().toDouble()
            val from = spinnerFrom.selectedItem.toString()
            val to = spinnerTo.selectedItem.toString()

            if(input == null){
                resultText.text = "Enter a valid temperature"
                return@setOnClickListener
            }

            val hasil = when{
                from == "Celcius (C)" && to == "Celcius (C)" -> input
                from == "Celcius (C)" && to == "Kelvin (K)" -> input + 273
                from == "Celcius (C)" && to == "Fahrenheit (F)" -> input * 9/5 + 32
                from == "Celcius (C)" && to == "Reamur (R)" -> input * 4/5

                from == "Reamur (R)" && to == "Reamur (R)" -> input
                from == "Reamur (R)" && to == "Celcius (C)" -> input * 5/4
                from == "Reamur (R)" && to == "Kelvin (K)" -> (input * 5/4) + 273
                from == "Reamur (R)" && to == "Fahrenheit (F)" -> (input * 9/4) + 32

                from == "Fahrenheit (F)" && to == "Fahrenheit (F)" -> input
                from == "Fahrenheit (F)" && to == "Celcius (C)" -> (input - 32) * 5/9
                from == "Fahrenheit (F)" && to == "Kelvin (K)" -> (input - 32) * 5/9 + 273
                from == "Fahrenheit (F)" && to == "Reamur (R)" -> (input - 32) * 4/9

                from == "Kelvin (K)" && to == "Kelvin (K)" -> input
                from == "Kelvin (K)" && to == "Celcius (C)" -> input - 273
                from == "Kelvin (K)" && to == "Fahrenheit (F)" -> (input - 273) * 9/5 + 32
                from == "Kelvin (K)" && to == "Reamur (R)" -> (input - 273) * 4/5

                else -> {null}
            }
            if(input !=null){
                val satuan = to.substringAfter("(").substringBefore(")")
                resultText.text = "Conversion result: %.2f %s".format(hasil, satuan)
            } else{
                resultText.text = "Conversion from $from to $to is not yet supported."
            }
        }
    }
}
