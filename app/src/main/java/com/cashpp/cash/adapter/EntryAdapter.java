package com.cashpp.cash.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cashpp.cash.R;
import com.cashpp.cash.model.Entry;

import java.util.List;

public class EntryAdapter extends BaseAdapter {
    private Context context;
    private List<Entry> list;

    public EntryAdapter(Context ctx, List<Entry> entries) {
        this.context = ctx;
        this.list = entries;
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
        Entry entry = list.get(position);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.entries, null);
        }

        TextView txtTitle = (TextView) view.findViewById(R.id.entries_list_title);
        TextView txtValue = (TextView) view.findViewById(R.id.entries_list_value);
        TextView txtType = (TextView) view.findViewById(R.id.entries_list_type);
        TextView txtDate = (TextView) view.findViewById(R.id.entries_list_date);
        TextView txtRecurrence = (TextView) view.findViewById(R.id.entries_list_recurrence);
        txtTitle.setText(entry.getTitle());
        txtValue.setText(entry.getValue().toString());
        txtType.setText(entry.getType());
        txtDate.setText(entry.getDate());
        txtRecurrence.setText(entry.getRecurrence().toString());

        return view;

    }

}
