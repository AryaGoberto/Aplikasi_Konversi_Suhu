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
            val input = inputSuhu.text.toString().toDoubleOrNull()
            val from = spinnerFrom.selectedItem.toString()
            val to = spinnerTo.selectedItem.toString()

            if(input == null){
                resultText.text = "Masukkan suhu yang valid"
                return@setOnClickListener
            }

            val hasil = when{
                from == "Celcius (C)" && to == "Kelvin (K)" -> input + 273
                from == "Celcius (C)" && to == "Fahrenheit (F)" -> input * 9/5 + 32
                from == "Celcius (C)" && to == "Reamur (R)" -> input * 4/5
                else -> {null}
            }
            if(input !=null){
                val satuan = to.substringAfter("(").substringBefore(")")
                resultText.text = "Hasil konversi: %.2f %s".format(hasil, satuan)
            } else{
                resultText.text = "Konversi dari $from ke $to belum didukung."
            }
        }
    }
}
