package com.example.task31c;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private QuizQuestion[] questions;
    private int currentQuestionIndex = 0;
    private int selectedAnswerIndex = -1;
    private int score = 0;

    TextView txtQuestion, txtQuestionNumber;
    Button btnAnswer1, btnAnswer2, btnAnswer3, btnSubmit;
    ProgressBar progressBar;

    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        txtQuestion = findViewById(R.id.txtQuestion);
        txtQuestionNumber = findViewById(R.id.txtQuestionNumber);
        btnAnswer1 = findViewById(R.id.btnAnswer1);
        btnAnswer2 = findViewById(R.id.btnAnswer2);
        btnAnswer3 = findViewById(R.id.btnAnswer3);
        btnSubmit = findViewById(R.id.btnSubmit);
        progressBar = findViewById(R.id.progressBar);

        userName = getIntent().getStringExtra("USER_NAME");

        // Sample quiz questions - you can add more
        questions = new QuizQuestion[]{
                new QuizQuestion("What is 2 + 2?", new String[]{"3", "4", "5"}, 1),
                new QuizQuestion("What is the capital of France?", new String[]{"London", "Berlin", "Paris"}, 2),
                new QuizQuestion("Who wrote '1984'?", new String[]{"George Orwell", "Mark Twain", "J.K. Rowling"}, 0),
                new QuizQuestion("Android is developed by?", new String[]{"Apple", "Google", "Microsoft"}, 1),
                new QuizQuestion("What color is the sky?", new String[]{"Blue", "Green", "Red"}, 0)
        };

        loadQuestion();

        btnAnswer1.setOnClickListener(v -> selectAnswer(0));
        btnAnswer2.setOnClickListener(v -> selectAnswer(1));
        btnAnswer3.setOnClickListener(v -> selectAnswer(2));

        btnSubmit.setOnClickListener(v -> checkAnswer());
    }

    private void loadQuestion() {
        resetButtonColors();
        selectedAnswerIndex = -1;

        QuizQuestion q = questions[currentQuestionIndex];
        txtQuestion.setText(q.getQuestion());
        btnAnswer1.setText(q.getAnswers()[0]);
        btnAnswer2.setText(q.getAnswers()[1]);
        btnAnswer3.setText(q.getAnswers()[2]);
        txtQuestionNumber.setText("Question " + (currentQuestionIndex + 1) + "/" + questions.length);

        progressBar.setMax(questions.length);
        progressBar.setProgress(currentQuestionIndex);
        btnSubmit.setEnabled(true);
    }

    private void selectAnswer(int index) {
        resetButtonColors();
        selectedAnswerIndex = index;
        switch (index) {
            case 0: btnAnswer1.setBackgroundColor(Color.LTGRAY); break;
            case 1: btnAnswer2.setBackgroundColor(Color.LTGRAY); break;
            case 2: btnAnswer3.setBackgroundColor(Color.LTGRAY); break;
        }
    }

    private void checkAnswer() {
        if (selectedAnswerIndex == -1) {
            Toast.makeText(this, "Please select an answer!", Toast.LENGTH_SHORT).show();
            return;
        }

        btnSubmit.setEnabled(false);

        int correct = questions[currentQuestionIndex].getCorrectAnswerIndex();

        // Show correct/incorrect colors
        if (selectedAnswerIndex == correct) {
            getAnswerButton(correct).setBackgroundColor(Color.GREEN);
            score++;
        } else {
            getAnswerButton(selectedAnswerIndex).setBackgroundColor(Color.RED);
            getAnswerButton(correct).setBackgroundColor(Color.GREEN);
        }

        // Wait for 1 second, then go to next question or results
        btnSubmit.postDelayed(() -> {
            currentQuestionIndex++;
            if (currentQuestionIndex < questions.length) {
                loadQuestion();
            } else {
                // Pass name and score to result screen
                Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                intent.putExtra("USER_NAME", userName);
                intent.putExtra("SCORE", score);
                intent.putExtra("TOTAL", questions.length);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }

    private Button getAnswerButton(int index) {
        switch (index) {
            case 0: return btnAnswer1;
            case 1: return btnAnswer2;
            default: return btnAnswer3;
        }
    }

    private void resetButtonColors() {
        btnAnswer1.setBackgroundColor(Color.parseColor("#E0E0E0"));
        btnAnswer2.setBackgroundColor(Color.parseColor("#E0E0E0"));
        btnAnswer3.setBackgroundColor(Color.parseColor("#E0E0E0"));
    }
}
