package com.example.open_ended;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class HealthTipsActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tips);

        textView = findViewById(R.id.textViewTips);

        String url = "https://mocki.io/v1/2d90c8b2-5f90-4c6d-8b49-1b65b9a2b6bc"; // Example JSON

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    StringBuilder tips = new StringBuilder();
                    for(int i=0;i<response.length();i++){
                        try {
                            JSONObject obj = response.getJSONObject(i);
                            tips.append("- ").append(obj.getString("tip")).append("\n");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    textView.setText(tips.toString());
                },
                error -> Toast.makeText(this, "Failed to fetch tips", Toast.LENGTH_SHORT).show()
        );

        queue.add(request);
    }
}
