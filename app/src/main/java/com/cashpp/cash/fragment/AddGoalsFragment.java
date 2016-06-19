package com.cashpp.cash.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.cashpp.cash.R;
import com.cashpp.cash.activity.MainActivity;
import com.cashpp.cash.db.GoalDB;
import com.cashpp.cash.model.Goal;

import java.util.List;

import static java.lang.Double.parseDouble;


public class AddGoalsFragment extends BaseFragment {
    private GoalDB goal_db;

    private EditText title;
    private EditText value;
    private EditText date;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setTitle(R.string.goals);
        View view = inflater.inflate(R.layout.fragment_goals_add, container, false);

        //Botão voltar
        view.findViewById(R.id.bt_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).replaceFragment(new GoalsFragment());
            }
        });

        title = (EditText) view.findViewById(R.id.et_title);
        value = (EditText) view.findViewById(R.id.et_value);
        date = (EditText) view.findViewById(R.id.et_date);

        //Botão criar nova meta
        view.findViewById(R.id.bt_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goal_db = new GoalDB((MainActivity) getActivity());

                Goal goal = new Goal();
                goal.setTitle(title.getText().toString());
                goal.setValue(parseDouble(value.getText().toString()));
                goal.setDate(date.getText().toString());

                long res = goal_db.saveGoal(goal);

                if (res != -1) {
                    toast("Meta criada com sucesso.");
                } else {
                    toast("Erro ao criar meta.");
                }

                ((MainActivity) getActivity()).replaceFragment(new GoalsFragment());
            }
        });

        return view;
    }

}
