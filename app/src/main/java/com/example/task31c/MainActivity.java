package com.example.task31c;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextName;
    Button btnStart, btnCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        btnStart = findViewById(R.id.btnStart);
        btnCalculator = findViewById(R.id.btnCalculator);

        btnStart.setOnClickListener(v -> {
            String userName = editTextName.getText().toString().trim();
            if (!userName.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                intent.putExtra("USER_NAME", userName);
                startActivity(intent);
            } else {
                editTextName.setError("Enter your name");
            }
        });

        btnCalculator.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CalculatorActivity.class));
        });
    }
}
