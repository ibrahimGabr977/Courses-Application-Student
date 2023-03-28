package com.hope.igb.aic_student.screens.mainscreen.rank;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hope.igb.aic_student.databinding.MainRankHolderBinding;
import com.hope.igb.aic_student.networking.models.RankedStudent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


 class RankRecyclerAdapter extends RecyclerView.Adapter<RankRecyclerAdapter.RankViewHolder> implements
        RankItemViewMvc.RankItemListener{


    protected static class RankViewHolder extends RecyclerView.ViewHolder {
        private final RankItemViewMvc viewMvc;

        public RankViewHolder(@NonNull MainRankHolderBinding view_binding) {
            super(view_binding.getRoot());

            viewMvc = new RankItemViewMvc(view_binding);
        }
    }


    protected interface RankAdapterListener{
        void onRankItemClicked(String student_id);
    }





    private final LayoutInflater inflater;
    private final Set<RankAdapterListener> listeners = Collections.newSetFromMap(
            new ConcurrentHashMap<>(1));

    private final ArrayList<RankedStudent> students;


    protected RankRecyclerAdapter(LayoutInflater inflater, ArrayList<RankedStudent> students) {
        this.inflater = inflater;
        this.students = students;
    }


    @Override
    public void onViewAttachedToWindow(@NonNull RankViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.viewMvc.registerListener(this);
    }


    @Override
    public void onViewDetachedFromWindow(@NonNull RankViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.viewMvc.registerListener(this);
    }

    @NonNull
    @Override
    public RankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MainRankHolderBinding view_binding = MainRankHolderBinding.inflate(inflater, parent, false);
        //TODO: leaking registerListener here may cause a problem
        return new RankViewHolder(view_binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RankViewHolder holder, int position) {
        holder.viewMvc.bindViewHolder(students.get(position), position);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    protected void registerAdapterListener(RankRecyclerAdapter.RankAdapterListener listener){
        listeners.add(listener);
    }

    protected void unregisterAdapterListener(RankRecyclerAdapter.RankAdapterListener listener){
        listeners.remove(listener);
    }


    @Override
    public void onRankItemClicked(String student_id) {
//        Toast.makeText(inflater.getContext(), "student "+student_id+" clicked", Toast.LENGTH_SHORT).show();

        for (RankAdapterListener listener : listeners)
            listener.onRankItemClicked(student_id);

    }


}
