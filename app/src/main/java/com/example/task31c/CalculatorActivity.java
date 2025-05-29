package com.example.task31c;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    EditText editNumber1, editNumber2;
    Button btnAdd, btnSubtract;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        editNumber1 = findViewById(R.id.editNumber1);
        editNumber2 = findViewById(R.id.editNumber2);
        btnAdd = findViewById(R.id.btnAdd);
        btnSubtract = findViewById(R.id.btnSubtract);
        txtResult = findViewById(R.id.txtResult);

        btnAdd.setOnClickListener(v -> calculate('+'));
        btnSubtract.setOnClickListener(v -> calculate('-'));
    }

    private void calculate(char op) {
        String s1 = editNumber1.getText().toString();
        String s2 = editNumber2.getText().toString();

        if (s1.isEmpty() || s2.isEmpty()) {
            txtResult.setText("Please enter both numbers");
            return;
        }

        int n1 = Integer.parseInt(s1);
        int n2 = Integer.parseInt(s2);
        int res = (op == '+') ? (n1 + n2) : (n1 - n2);

        txtResult.setText("Result: " + res);
    }
}
