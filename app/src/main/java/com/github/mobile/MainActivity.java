package com.github.mobile;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

/**
 * Main Activity for GitHub Mobile application
 * Demonstrates accessibility best practices
 */
public class MainActivity extends Activity {
    private TextView messageText;
    private Button actionButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Create simple UI programmatically for demonstration
        messageText = new TextView(this);
        messageText.setText("Welcome to GitHub Mobile");
        messageText.setContentDescription("Welcome message");
        messageText.setId(View.generateViewId());
        
        actionButton = new Button(this);
        actionButton.setText("Click Me");
        actionButton.setContentDescription("Action button that shows a message");
        actionButton.setId(View.generateViewId());
        
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageText.setText("Button clicked!");
                messageText.setContentDescription("Button was clicked message");
            }
        });
    }
}
