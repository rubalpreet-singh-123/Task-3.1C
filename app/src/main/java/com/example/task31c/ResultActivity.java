package com.example.task31c;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView txtFinalScore, txtCongrats;
    Button btnNewQuiz, btnFinish;
    String userName;
    int score, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        txtFinalScore = findViewById(R.id.txtFinalScore);
        txtCongrats = findViewById(R.id.txtCongrats);
        btnNewQuiz = findViewById(R.id.btnNewQuiz);
        btnFinish = findViewById(R.id.btnFinish);

        userName = getIntent().getStringExtra("USER_NAME");
        score = getIntent().getIntExtra("SCORE", 0);
        total = getIntent().getIntExtra("TOTAL", 0);

        txtCongrats.setText("Congratulations " + userName + "!");
        txtFinalScore.setText("Your score: " + score + "/" + total);

        btnNewQuiz.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            intent.putExtra("USER_NAME", userName);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        btnFinish.setOnClickListener(v -> finishAffinity());
    }
}
