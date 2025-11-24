package com.teamelite.github.mobile;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Main activity demonstrating accessibility features
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup UI components with proper content descriptions
        EditText searchField = findViewById(R.id.searchField);
        searchField.setContentDescription("Search for repositories");
        
        Button searchButton = findViewById(R.id.searchButton);
        searchButton.setContentDescription("Execute search");
        searchButton.setOnClickListener(v -> {
            String query = searchField.getText().toString();
            TextView resultText = findViewById(R.id.resultText);
            resultText.setText("Searching for: " + query);
            resultText.setContentDescription("Search results for " + query);
        });

        TextView resultText = findViewById(R.id.resultText);
        resultText.setContentDescription("Search results will appear here");
    }
}
