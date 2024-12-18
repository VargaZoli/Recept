package com.example.recept;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextQuality, editTextDifficulty;
    private Button buttonAdd;
    private ListView listViewRecipes;
    private ArrayList<Recipe> recipeList;
    private ArrayAdapter<Recipe> recipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextQuality = findViewById(R.id.editTextQuality);
        editTextDifficulty = findViewById(R.id.editTextDifficulty);
        buttonAdd = findViewById(R.id.buttonAdd);
        listViewRecipes = findViewById(R.id.listViewRecipes);

        recipeList = new ArrayList<>();
        recipeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, recipeList);
        listViewRecipes.setAdapter(recipeAdapter);

        buttonAdd.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String qualityStr = editTextQuality.getText().toString();
            String difficultyStr = editTextDifficulty.getText().toString();

            if (name.isEmpty() || qualityStr.isEmpty() || difficultyStr.isEmpty()) {
                Toast.makeText(this, "Minden mezőt ki kell tölteni!", Toast.LENGTH_SHORT).show();
                return;
            }

            int quality, difficulty;
            try {
                quality = Integer.parseInt(qualityStr);
                difficulty = Integer.parseInt(difficultyStr);

                if (quality < 1 || quality > 100) {
                    Toast.makeText(this, "A minőség 1 és 100 között lehet!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (difficulty < 0 || difficulty > 3) {
                    Toast.makeText(this, "A nehézség 0 és 3 között lehet!", Toast.LENGTH_SHORT).show();
                    return;
                }

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Érvényes számot kell megadni!", Toast.LENGTH_SHORT).show();
                return;
            }

            Recipe newRecipe = new Recipe(name, quality, difficulty);
            recipeList.add(newRecipe);
            recipeAdapter.notifyDataSetChanged();

            editTextName.setText("");
            editTextQuality.setText("");
            editTextDifficulty.setText("");
        });

        listViewRecipes.setOnItemClickListener((parent, view, position, id) -> {
            Recipe selectedRecipe = recipeList.get(position);
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra("recipeName", selectedRecipe.getName());
            intent.putExtra("recipeQuality", selectedRecipe.getQuality());
            intent.putExtra("recipeDifficulty", selectedRecipe.getDifficulty());
            startActivity(intent);
        });

        listViewRecipes.setOnItemLongClickListener((parent, view, position, id) -> {
            Recipe selectedRecipe = recipeList.get(position);

            recipeList.remove(position);
            recipeAdapter.notifyDataSetChanged();

            return true;
        });
    }
}
