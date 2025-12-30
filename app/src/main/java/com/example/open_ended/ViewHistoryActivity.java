package com.example.open_ended;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ViewHistoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    HistoryAdapter adapter;
    DatabaseHelper dbHelper;
    ArrayList<HealthModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DatabaseHelper(this);
        list = new ArrayList<>();

        Cursor cursor = dbHelper.getAllData();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Indices dhyan se check karein: ID=0, STEPS=1, WEIGHT=2, WATER=3, CALORIES=4, DATE=5
                list.add(new HealthModel(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getFloat(2),
                        cursor.getFloat(3),
                        cursor.getInt(4),
                        cursor.getString(5)
                ));
            } while (cursor.moveToNext());
            cursor.close();
        } else {
            Toast.makeText(this, "History is Empty!", Toast.LENGTH_SHORT).show();
        }

        adapter = new HistoryAdapter(list);
        recyclerView.setAdapter(adapter);
    }
}