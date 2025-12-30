package com.example.open_ended;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddHealthDataActivity extends AppCompatActivity {

    EditText etSteps, etWeight, etWater, etCalories;
    Button btnSave;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_health_data);

        dbHelper = new DatabaseHelper(this);

        etSteps = findViewById(R.id.etSteps);
        etWeight = findViewById(R.id.etWeight);
        etWater = findViewById(R.id.etWater);
        etCalories = findViewById(R.id.etCalories);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            try {
                int steps = Integer.parseInt(etSteps.getText().toString());
                float weight = Float.parseFloat(etWeight.getText().toString());
                float water = Float.parseFloat(etWater.getText().toString());
                int calories = Integer.parseInt(etCalories.getText().toString());
                String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                boolean success = dbHelper.insertData(steps, weight, water, calories, date);
                if (success) {
                    Toast.makeText(this, "Data Saved to History!", Toast.LENGTH_SHORT).show();
                    finish(); // Isse wapis Dashboard par jayenge
                } else {
                    Toast.makeText(this, "Failed to save", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Please enter valid data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}