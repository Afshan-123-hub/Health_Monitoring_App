package com.example.open_ended;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private ArrayList<HealthModel> healthList;

    public HistoryAdapter(ArrayList<HealthModel> healthList) {
        this.healthList = healthList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HealthModel model = healthList.get(position);

        // Data ko UI par set karna
        holder.tvDate.setText("Date: " + model.getDate());
        holder.tvDetails.setText("Steps: " + model.getSteps() + " | Weight: " + model.getWeight() + "kg\n" +
                "Water: " + model.getWater() + "L | Calories: " + model.getCalories());
    }

    @Override
    public int getItemCount() {
        return healthList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvDetails = itemView.findViewById(R.id.tvDetails);
        }
    }
}