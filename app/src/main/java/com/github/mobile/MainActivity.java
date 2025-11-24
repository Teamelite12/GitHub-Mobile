package com.github.mobile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Main activity demonstrating accessibility best practices for Android.
 * This activity includes proper content descriptions, labels, and touch targets.
 */
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

        // Set up button click listener
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameInput.getText().toString();
                if (!username.isEmpty()) {
                    welcomeText.setText("Welcome, " + username + "!");
                    welcomeText.setVisibility(View.VISIBLE);
                    // Announce to screen readers
                    welcomeText.announceForAccessibility("Welcome, " + username);
                } else {
                    welcomeText.setText("Please enter a username");
                    welcomeText.setVisibility(View.VISIBLE);
                    welcomeText.announceForAccessibility("Please enter a username");
                }
            }
        });
    }
}
