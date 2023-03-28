package com.hope.igb.aic_student.screens.mainscreen.statistics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hope.igb.aic_student.R;
import com.hope.igb.aic_student.networking.models.Statistic;

import java.util.ArrayList;

public class StatisticsRecyclerAdapter extends RecyclerView.Adapter<StatisticsRecyclerAdapter.StatisticsViewHolder> {

    private final Context context;
    private final ArrayList<Statistic> statistics;

    public StatisticsRecyclerAdapter(Context context, ArrayList<Statistic> statistics) {
        this.context = context;
        this.statistics = statistics;
    }


    @NonNull
    @Override
    public StatisticsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root_view = LayoutInflater.from(context).inflate(R.layout.main_statistics_holder, parent, false);

        return new StatisticsViewHolder(root_view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatisticsViewHolder holder, int position) {
        Statistic statistic = statistics.get(position);


        holder.title.setText(statistic.getTitle());
        holder.value.setText(statistic.getValue());
        holder.date.setText(statistic.getDate());
    }

    @Override
    public int getItemCount() {
        return statistics.size();
    }

    protected static class StatisticsViewHolder extends RecyclerView.ViewHolder {
        private final TextView title, value, date;

        public StatisticsViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.statistics_holder_title_text);
            value = itemView.findViewById(R.id.statistics_holder_value_text);
            date = itemView.findViewById(R.id.statistics_holder_date_text);
        }
    }
}
