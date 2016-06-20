package com.cashpp.cash.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.cashpp.cash.R;
import com.cashpp.cash.activity.MainActivity;
import com.cashpp.cash.adapter.GoalAdapter;
import com.cashpp.cash.db.GoalDB;
import com.cashpp.cash.model.Goal;

import java.util.List;


public class GoalsFragment extends BaseFragment {
    private ListView list;
    private List<Goal> goals_list;
    private GoalDB goal_db;
    private GoalAdapter goal_adapter;

    private int position_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        (getActivity()).setTitle(R.string.goals);
        View view = inflater.inflate(R.layout.fragment_goals_list, container, false);

        //Botão para adicionar uma nova meta
        view.findViewById(R.id.bt_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).replaceFragment(new AddGoalsFragment());
            }
        });

        goal_db = new GoalDB((MainActivity) getActivity());
        goals_list = goal_db.listGoals();
        goal_adapter = new GoalAdapter((MainActivity) getActivity(), goals_list);

        list = (ListView) view.findViewById(R.id.lvGoals);
        list.setAdapter(goal_adapter);

        //list.setOnItemClickListener();

        return view;
    }

}
