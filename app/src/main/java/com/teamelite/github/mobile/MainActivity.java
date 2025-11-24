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
        searchField.setContentDescription(getString(R.string.search_field_description));
        
        Button searchButton = findViewById(R.id.searchButton);
        searchButton.setContentDescription(getString(R.string.search_button_description));
        searchButton.setOnClickListener(v -> {
            String query = searchField.getText().toString();
            TextView resultText = findViewById(R.id.resultText);
            resultText.setText(getString(R.string.searching_for, query));
            resultText.setContentDescription(getString(R.string.result_description_query, query));
        });

        TextView resultText = findViewById(R.id.resultText);
        resultText.setContentDescription(getString(R.string.result_description_initial));
    }
}
