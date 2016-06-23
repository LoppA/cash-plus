package com.cashpp.cash.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cashpp.cash.R;
import com.cashpp.cash.db.CategoryDB;
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

        CategoryDB category_db = new CategoryDB(context);

        TextView txtTitle = (TextView) view.findViewById(R.id.entries_list_title);
        TextView txtValue = (TextView) view.findViewById(R.id.entries_list_value);
        TextView txtType = (TextView) view.findViewById(R.id.entries_list_type);
        TextView txtDate = (TextView) view.findViewById(R.id.entries_list_date);
        TextView txtCategory = (TextView) view.findViewById(R.id.entries_list_category);
        txtTitle.setText(entry.getTitle());
        txtValue.setText("Valor: " + entry.getValue().toString());
        if (entry.getType().equals("expense")) txtType.setText("Tipo: Despesa");
        else txtType.setText("Tipo: Receita");
        txtDate.setText("Data: " + entry.getDate());
        String category_title;
        if (category_db.findCategoryById(entry.getCategory_id()) != null)
            category_title = category_db.findCategoryById(entry.getCategory_id()).getTitle();
        else
            category_title = "Sem categoria";
        txtCategory.setText("Categoria: " + category_title);

        return view;

    }

}
