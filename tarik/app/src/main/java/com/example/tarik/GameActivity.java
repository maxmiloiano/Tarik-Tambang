package com.example.tarik;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    private TextView team1ScoreText, team2ScoreText;
    private ImageView combinedTeamsImage;
    private int team1Score = 0;
    private int team2Score = 0;
    private Handler handler = new Handler();
    private Runnable checkWinner;
    private float translationX = 0f;  // Initial position for combinedTeamsImage

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        team1ScoreText = findViewById(R.id.team1Score);
        team2ScoreText = findViewById(R.id.team2Score);
        combinedTeamsImage = findViewById(R.id.combinedTeamsImage);

        View leftClickArea = findViewById(R.id.leftClickArea);
        View rightClickArea = findViewById(R.id.rightClickArea);

        // Click listener for Team 1 (moves the image to the right when clicked)
        leftClickArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                team1Score++;
                team1ScoreText.setText(String.valueOf(team1Score));
                moveTeamsImage(20f);  // Move image 20 pixels to the right
            }
        });

        // Click listener for Team 2 (moves the image to the left when clicked)
        rightClickArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                team2Score++;
                team2ScoreText.setText(String.valueOf(team2Score));
                moveTeamsImage(-20f);  // Move image 20 pixels to the left
            }
        });

        // Check for the winner periodically
        checkWinner = new Runnable() {
            @Override
            public void run() {
                if (team1Score >= 10) {
                    Toast.makeText(GameActivity.this, "Team 1 Wins!", Toast.LENGTH_SHORT).show();
                    resetGame();
                } else if (team2Score >= 10) {
                    Toast.makeText(GameActivity.this, "Team 2 Wins!", Toast.LENGTH_SHORT).show();
                    resetGame();
                } else {
                    handler.postDelayed(this, 100);  // Check every 100 milliseconds
                }
            }
        };
        handler.post(checkWinner);
    }

    // Method to move the teams image based on score
    private void moveTeamsImage(float delta) {
        translationX += delta;  // Update the translation position
        ObjectAnimator animator = ObjectAnimator.ofFloat(combinedTeamsImage, "translationX", translationX);
        animator.setDuration(200);  // Set animation duration (in milliseconds)
        animator.start();
    }

    private void resetGame() {
        team1Score = 0;
        team2Score = 0;
        team1ScoreText.setText(String.valueOf(team1Score));
        team2ScoreText.setText(String.valueOf(team2Score));
        translationX = 0f;  // Reset the image position
        combinedTeamsImage.setTranslationX(translationX);  // Move image back to center

        // Go back to the main activity
        Intent intent = new Intent(GameActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Close GameActivity to prevent going back to it with the back button
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(checkWinner);
    }
}
