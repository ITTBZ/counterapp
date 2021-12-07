package io.genetv.counterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnIncrement;
    private Button btnDecrement;
    private Button btnReset;
    private TextView displayText;

    private int currentCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIncrement = findViewById(R.id.btnIncrement);
        btnDecrement = findViewById(R.id.btnDecrement);
        btnReset = findViewById(R.id.btnReset);
        displayText = findViewById(R.id.displayCurrentCount);

        btnIncrement.setOnClickListener(v -> {
            this.currentCount++;
            updateView(currentCount);
        });

        btnDecrement.setOnClickListener(v -> {
            if(currentCount == 0) return;
            this.currentCount--;
            updateView(currentCount);
        });

        btnReset.setOnClickListener(v -> {
            this.currentCount = 0;
            updateView(currentCount);
            Toast.makeText(this, R.string.btn_message_reset, Toast.LENGTH_SHORT).show();
        });

        SharedPreferences counterPreference = getSharedPreferences("counter", Context.MODE_PRIVATE);
        currentCount = counterPreference.getInt("last_count", 0);

        updateView(currentCount, false);
    }


    private void updateView(int newCount) { updateView(newCount, true); }
    private void updateView(int newCount, boolean save) {
        if(save) {
            SharedPreferences.Editor sharedPref = getSharedPreferences("counter", Context.MODE_PRIVATE).edit();
            sharedPref.putInt("last_count", newCount);
            sharedPref.apply();
        }

        displayText.setText(String.valueOf(newCount));

        if(currentCount == 0) {
            btnDecrement.setEnabled(false);
            btnReset.setEnabled(false);
        } else if (!btnDecrement.isEnabled() || !btnReset.isEnabled()){
            btnDecrement.setEnabled(true);
            btnReset.setEnabled(true);
        }
    }
}