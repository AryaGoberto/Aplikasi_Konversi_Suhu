package com.example.codeerrrr

import android.os.Bundle
import android.widget.EditText
import android.widget.Spinner
import android.widget.Button
import android.widget.TextView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editInput: EditText
    private lateinit var spinnerFrom: Spinner
    private lateinit var spinnerTo: Spinner
    private lateinit var spinnerDecimal: Spinner
    private lateinit var buttonKonversi: Button
    private lateinit var textHasil: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi view
        editInput = findViewById(R.id.editInput)
        spinnerFrom = findViewById(R.id.spinnerFrom)
        spinnerTo = findViewById(R.id.spinnerTo)
        spinnerDecimal = findViewById(R.id.spinnerDecimal)
        buttonKonversi = findViewById(R.id.buttonKonversi)
        textHasil = findViewById(R.id.textHasil)

        // Data spinner
        val units = arrayOf("Celsius", "Fahrenheit", "Kelvin", "Reamur")
        val decimals = arrayOf("0", "1", "2", "3")

        val adapterUnit = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        adapterUnit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val adapterDecimal = ArrayAdapter(this, android.R.layout.simple_spinner_item, decimals)
        adapterDecimal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerFrom.adapter = adapterUnit
        spinnerTo.adapter = adapterUnit
        spinnerDecimal.adapter = adapterDecimal

        // Klik tombol
        buttonKonversi.setOnClickListener {
            val inputText = editInput.text.toString()
            if (inputText.isEmpty()) {
                Toast.makeText(this, "Masukkan nilai suhu terlebih dahulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val value = inputText.toDouble()
            val from = spinnerFrom.selectedItem.toString()
            val to = spinnerTo.selectedItem.toString()
            val decimal = spinnerDecimal.selectedItem.toString().toInt()

            val result = convertTemperature(value, from, to)
            val formattedResult = "%.${decimal}f".format(result)

            textHasil.text = "$value $from = $formattedResult $to"
        }
    }

    private fun convertTemperature(value: Double, from: String, to: String): Double {
        val inCelsius = when (from) {
            "Celsius" -> value
            "Fahrenheit" -> (value - 32) * 5 / 9
            "Kelvin" -> value - 273.15
            "Reamur" -> value * 5 / 4
            else -> value
        }

        return when (to) {
            "Celsius" -> inCelsius
            "Fahrenheit" -> inCelsius * 9 / 5 + 32
            "Kelvin" -> inCelsius + 273.15
            "Reamur" -> inCelsius * 4 / 5
            else -> inCelsius
        }
    }
}
