package com.github.mobile;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.view.View;
import android.view.ViewGroup;

/**
 * Main Activity for GitHub Mobile application
 * Demonstrates accessibility best practices
 */
public class MainActivity extends AppCompatActivity {
    private TextView messageText;
    private Button actionButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Create a vertical layout to hold our views
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        ));
        layout.setPadding(16, 16, 16, 16);
        
        // Create TextView with accessibility features
        messageText = new TextView(this);
        messageText.setText("Welcome to GitHub Mobile");
        messageText.setContentDescription("Welcome message");
        messageText.setId(View.generateViewId());
        messageText.setTextSize(18);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        );
        textParams.setMargins(0, 0, 0, 32);
        messageText.setLayoutParams(textParams);
        
        // Create Button with accessibility features
        actionButton = new Button(this);
        actionButton.setText("Click Me");
        actionButton.setContentDescription("Action button that shows a message");
        actionButton.setId(View.generateViewId());
        // Ensure minimum touch target size (48dp)
        actionButton.setMinimumWidth(dpToPx(48));
        actionButton.setMinimumHeight(dpToPx(48));
        
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageText.setText("Button clicked!");
                messageText.setContentDescription("Button was clicked message");
            }
        });
        
        // Add views to layout
        layout.addView(messageText);
        layout.addView(actionButton);
        
        // Set the layout as content view
        setContentView(layout);
    }
    
    /**
     * Convert dp to pixels
     */
    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}
