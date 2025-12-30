package com.example.open_ended;

public class HealthModel {
    private int id, steps, calories;
    private float weight, water;
    private String date;

    public HealthModel(int id, int steps, float weight, float water, int calories, String date) {
        this.id = id;
        this.steps = steps;
        this.weight = weight;
        this.water = water;
        this.calories = calories;
        this.date = date;
    }

    public int getSteps() { return steps; }
    public float getWeight() { return weight; }
    public float getWater() { return water; }
    public int getCalories() { return calories; }
    public String getDate() { return date; }
}