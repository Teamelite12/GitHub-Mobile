package com.github.mobile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    
    private lateinit var searchInput: EditText
    private lateinit var searchButton: Button
    private lateinit var resultText: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        setupListeners()
    }
    
    private fun initializeViews() {
        searchInput = findViewById(R.id.searchInput)
        searchButton = findViewById(R.id.searchButton)
        resultText = findViewById(R.id.resultText)
    }
    
    private fun setupListeners() {
        searchButton.setOnClickListener {
            performSearch()
        }
    }
    
    private fun performSearch() {
        val query = searchInput.text.toString().trim()
        resultText.text = if (query.isNotEmpty()) {
            "Searching for: $query"
        } else {
            "Please enter a search term"
        }
    }
}
