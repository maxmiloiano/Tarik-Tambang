package com.example.tarik;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button startGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi tombol mulai permainan
        startGameButton = findViewById(R.id.startGameButton);

        // Set onClickListener untuk tombol mulai permainan
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
    }

    // Metode untuk memulai game
    private void startGame() {
        // Mengarahkan ke GameActivity
        Intent intent = new Intent(MainActivity.this, com.example.tarik.GameActivity.class);
        startActivity(intent);
    }
}
