package com.cashpp.cash.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cashpp.cash.R;
import com.cashpp.cash.model.Category;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    private Context context;
    private List<Category> list;

    public CategoryAdapter(Context ctx, List<Category> categories) {
        this.context = ctx;
        this.list = categories;
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
        Category category = list.get(position);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.categories, null);
        }

        TextView txtTitle = (TextView) view.findViewById(R.id.categories_list_title);
        txtTitle.setText(category.getTitle());

        return view;

    }

}
