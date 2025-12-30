package com.example.open_ended;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;

public class DashboardActivity extends AppCompatActivity {

    MaterialButton btnAddData, btnViewHistory, btnSteps;
    TextView tvHealthQuote; // API data ke liye TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize views
        btnAddData = findViewById(R.id.btnAddData);
        btnViewHistory = findViewById(R.id.btnViewHistory);
        btnSteps = findViewById(R.id.btnSteps);
        tvHealthQuote = findViewById(R.id.tvHealthQuote); // Make sure ye ID layout mein ho

        // 1. Fetch Health Quote from REST API
        fetchHealthQuote();

        // Add Data button click
        btnAddData.setOnClickListener(v ->
                startActivity(new Intent(DashboardActivity.this, AddHealthDataActivity.class)));

        // View History button click
        btnViewHistory.setOnClickListener(v ->
                startActivity(new Intent(DashboardActivity.this, ViewHistoryActivity.class)));

        // Steps Sensor button click
        btnSteps.setOnClickListener(v ->
                startActivity(new Intent(DashboardActivity.this, StepsActivity.class)));
    }

    private void fetchHealthQuote() {
        // API URL (Quotes about health/wellness)
        String url = "https://api.quotable.io/random?tags=health";

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        String quote = response.getString("content");
                        String author = response.getString("author");
                        tvHealthQuote.setText("\"" + quote + "\"\n— " + author);
                    } catch (Exception e) {
                        tvHealthQuote.setText("Your health is your wealth!");
                    }
                }, error -> {
            // Agar internet na ho toh default text dikhaye
            tvHealthQuote.setText("Stay hydrated and keep moving!");
        });

        queue.add(jsonObjectRequest);
    }
}