package com.example.codeerrrr

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var spinnerFrom: Spinner
    private lateinit var spinnerTo: Spinner

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // ⬅️ Panggil ini dulu!

        spinnerFrom = findViewById(R.id.spinnerFrom)
        spinnerTo = findViewById(R.id.spinnerTo)

        val units = resources.getStringArray(R.array.suhu_array)

        val adapterUnit = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)

        adapterUnit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerFrom.adapter = adapterUnit // ⬅️ Tampilkan isinya
        spinnerTo.adapter = adapterUnit

    }
}
