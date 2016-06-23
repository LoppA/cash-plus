package com.cashpp.cash.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cashpp.cash.R;
import com.cashpp.cash.model.Goal;

import java.util.List;

public class GoalAdapter extends BaseAdapter {
    private Context context;
    private List<Goal> list;

    public GoalAdapter(Context ctx, List<Goal> goals) {
        this.context = ctx;
        this.list = goals;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Goal goal = list.get(position);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.goals, null);
        }

        TextView txtTitle = (TextView) view.findViewById(R.id.goals_list_name);
        TextView txtValue = (TextView) view.findViewById(R.id.goals_list_value);
        TextView txtDate = (TextView) view.findViewById(R.id.goals_list_date);
        txtTitle.setText(goal.getTitle());
        txtValue.setText("Valor a ser atingido: " + goal.getValue().toString());
        txtDate.setText("Data limite:" + goal.getDate());

        return view;

    }

}
