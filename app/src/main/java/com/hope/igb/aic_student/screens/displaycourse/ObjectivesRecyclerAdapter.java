package com.hope.igb.aic_student.screens.displaycourse;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.hope.igb.aic_student.R;
import java.util.ArrayList;

class ObjectivesRecyclerAdapter extends RecyclerView.Adapter<ObjectivesRecyclerAdapter.ObjectiveViewHolder> {

     private final ArrayList<String> objectives;

    ObjectivesRecyclerAdapter(ArrayList<String> objectives) {
        this.objectives = objectives;
    }

    @NonNull
    @Override
    public ObjectiveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_course_objective_holder,
                parent, false);
        return new ObjectiveViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ObjectiveViewHolder holder, int position) {
        holder.objective.setText(objectives.get(position));
    }

    @Override
    public int getItemCount() {
        return objectives.size();
    }

     static class ObjectiveViewHolder extends RecyclerView.ViewHolder {
        private final TextView objective;
        public ObjectiveViewHolder(@NonNull View itemView) {
            super(itemView);
            objective = itemView.findViewById(R.id.course_objective);
        }
    }
}
