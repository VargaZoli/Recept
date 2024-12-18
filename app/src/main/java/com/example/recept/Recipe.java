package com.example.recept;

public class Recipe {

    private String name;
    private int quality;
    private int difficulty;

    public Recipe(String name, int quality, int difficulty) {
        this.name = name;
        this.quality = quality;
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public int getQuality() {
        return quality;
    }

    public int getDifficulty() {
        return difficulty;
    }

    @Override
    public String toString() {
        return "Recept: " + name + "\nMinőség: " + quality + "\nNehézség: " + difficulty;
    }
}
