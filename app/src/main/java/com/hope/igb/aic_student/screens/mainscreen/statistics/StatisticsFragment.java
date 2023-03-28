package com.hope.igb.aic_student.screens.mainscreen.statistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hope.igb.aic_student.R;
import com.hope.igb.aic_student.networking.models.Statistic;

import java.util.ArrayList;

public class StatisticsFragment extends Fragment {


    private ArrayList<Statistic> statistics;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root_view = inflater.inflate(R.layout.main_fragment_statistics, container, false);


        initTestData();


        RecyclerView recyclerView = root_view.findViewById(R.id.statisticsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        StatisticsRecyclerAdapter adapter = new StatisticsRecyclerAdapter(getContext(), statistics);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        return root_view;
    }







    private void initTestData() {

        statistics = new ArrayList<>();



        Statistic statistic1 = new Statistic(getString(R.string.statistic_hour_points_test1),
                getString(R.string.statistic_value_test1), getString(R.string.statistic_date_test1));


        Statistic statistic2 = new Statistic(getString(R.string.statistic_hour_points_test2),
                getString(R.string.statistic_value_test2), getString(R.string.statistic_date_test2));


        Statistic statistic3 = new Statistic(getString(R.string.statistic_hour_points_test3),
                getString(R.string.statistic_value_test3), getString(R.string.statistic_date_test3));


        Statistic statistic4 = new Statistic(getString(R.string.statistic_hour_points_test4),
                getString(R.string.statistic_value_test4), getString(R.string.statistic_date_test4));


        Statistic statistic5 = new Statistic(getString(R.string.statistic_hour_points_test5),
                getString(R.string.statistic_value_test5), getString(R.string.statistic_date_test5));





        statistics.add(statistic1);
        statistics.add(statistic2);
        statistics.add(statistic3);

        statistics.add(statistic4);
        statistics.add(statistic5);


    }


}
