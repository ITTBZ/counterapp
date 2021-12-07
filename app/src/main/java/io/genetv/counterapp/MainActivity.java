package io.genetv.counterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnIncrement;
    private Button btnDecrement;
    private TextView displayText;

    private int currentCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIncrement = findViewById(R.id.btnIncrement);
        btnDecrement = findViewById(R.id.btnDecrement);
        displayText = findViewById(R.id.displayText);

        btnIncrement.setOnClickListener(v -> {
            this.currentCount++;
            updateView(currentCount);
            //Toast.makeText(this, "Added 1", Toast.LENGTH_SHORT).show();
        });

        btnDecrement.setOnClickListener(v -> {
            if(currentCount == 0) return;
            this.currentCount--;
            updateView(currentCount);
            //Toast.makeText(this, "Removed 1", Toast.LENGTH_SHORT).show();
        });

        updateView(currentCount);
    }

    private void updateView(int newCount) {
        displayText.setText(String.valueOf(newCount));
    }
}