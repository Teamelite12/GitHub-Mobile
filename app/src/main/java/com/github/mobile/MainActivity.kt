package com.github.mobile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchInput = findViewById<EditText>(R.id.searchInput)
        val searchButton = findViewById<Button>(R.id.searchButton)
        val resultText = findViewById<TextView>(R.id.resultText)

        searchButton.setOnClickListener {
            val query = searchInput.text.toString()
            if (query.isNotEmpty()) {
                resultText.text = "Searching for: $query"
            } else {
                resultText.text = "Please enter a search term"
            }
        }
    }
}
