package com.example.recept;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class DetailsActivity extends AppCompatActivity {

    private TextView textViewName, textViewQuality, textViewDifficulty, textViewYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Initialize TextViews
        textViewName = findViewById(R.id.textViewName);
        textViewQuality = findViewById(R.id.textViewQuality);
        textViewDifficulty = findViewById(R.id.textViewDifficulty);
        textViewYear = findViewById(R.id.textViewYear);

        // Get data from Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("recipeName");
        int quality = intent.getIntExtra("recipeQuality", 0);
        int difficulty = intent.getIntExtra("recipeDifficulty", 0);

        // Set data to TextViews
        textViewName.setText("Recept neve: " + name);
        textViewQuality.setText("Minőség: " + quality);
        textViewDifficulty.setText("Nehézség: " + difficulty);

        // Generate a random year
        Random random = new Random();
        int year = 2000 + random.nextInt(25); //
        textViewYear.setText("Év: " + year);
    }
}
