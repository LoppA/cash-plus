package com.cashpp.cash.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cashpp.cash.R;
import com.cashpp.cash.model.Reminder;

import java.util.List;

public class ReminderAdapter extends BaseAdapter {
    private Context context;
    private List<Reminder> list;

    public ReminderAdapter(Context ctx, List<Reminder> reminders) {
        this.context = ctx;
        this.list = reminders;
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
        Reminder reminder = list.get(position);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.reminders, null);
        }

        TextView txtTitle = (TextView) view.findViewById(R.id.reminders_list_title);
        TextView txtValue = (TextView) view.findViewById(R.id.reminders_list_value);
        TextView txtDate = (TextView) view.findViewById(R.id.reminders_list_date);
        txtTitle.setText(reminder.getTitle());
        txtValue.setText("Valor: " + reminder.getValue().toString());
        txtDate.setText("Data: " + reminder.getDate());

        return view;

    }

}
