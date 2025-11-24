package com.github.mobile;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText usernameInput;
    private Button loginButton;
    private TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        usernameInput = findViewById(R.id.username_input);
        loginButton = findViewById(R.id.login_button);
        welcomeText = findViewById(R.id.welcome_text);

        // Set click listener
        loginButton.setOnClickListener(v -> {
            String username = usernameInput.getText().toString();
            if (!username.isEmpty()) {
                welcomeText.setText("Welcome, " + username + "!");
            } else {
                welcomeText.setText("Please enter a username");
            }
        });
    }
}
