package com.example.open_ended;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class StepsActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private TextView tvSteps;
    private Button btnStart, btnStop, btnReset;

    private int stepCount = 0;
    private boolean isTracking = false;
    private double magnitudePrevious = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        tvSteps = findViewById(R.id.tvStepsDisplay);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnReset = findViewById(R.id.btnReset);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        btnStart.setOnClickListener(v -> {
            isTracking = true;
            Toast.makeText(this, "Tracking Started", Toast.LENGTH_SHORT).show();
        });

        btnStop.setOnClickListener(v -> {
            isTracking = false;
            Toast.makeText(this, "Tracking Stopped", Toast.LENGTH_SHORT).show();
        });

        btnReset.setOnClickListener(v -> {
            stepCount = 0;
            tvSteps.setText("0");
            Toast.makeText(this, "Steps Reset", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event != null && isTracking) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            // Calculate movement magnitude
            double magnitude = Math.sqrt(x * x + y * y + z * z);
            double magnitudeDelta = magnitude - magnitudePrevious;
            magnitudePrevious = magnitude;

            // Agar magnitude change threshold se zyada ho (hath hilaney par)
            if (magnitudeDelta > 6) {
                stepCount++;
                tvSteps.setText(String.valueOf(stepCount));
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}