package com.cashpp.cash.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cashpp.cash.R;
import com.cashpp.cash.activity.MainActivity;
import com.cashpp.cash.db.GoalDB;
import com.cashpp.cash.model.Goal;

import java.util.List;


public class GoalsFragment extends BaseFragment {
    private ListView list;
    private List<Goal> goals_list;
    private GoalDB goal_db;

    private int position_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        goal_db = new GoalDB(this);



        ((MainActivity) getActivity()).setTitle(R.string.goals);
        View view = inflater.inflate(R.layout.fragment_goals, container, false);
        return view;
    }



}
