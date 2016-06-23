package com.cashpp.cash.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cashpp.cash.R;
import com.cashpp.cash.adapter.MyValueFormatter;
import com.cashpp.cash.db.CategoryDB;
import com.cashpp.cash.db.EntryDB;
import com.cashpp.cash.model.Category;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class CategoriesGraphicsFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories_graphics, container, false);

        ArrayList<Entry> entries = new ArrayList<>();

        CategoryDB category_db = new CategoryDB(getActivity());
        List<Category> categories_list = category_db.listCategories();
        EntryDB entry_db = new EntryDB(getActivity());

        for (int i = 0; i < categories_list.size(); i++) {
            float num = entry_db.getExpenseByCategory(categories_list.get(i).get_id()).floatValue();
            entries.add(new Entry(num, i));
        }

        PieDataSet dataset = new PieDataSet(entries, "Chart Categories");

        ArrayList<String> labels = new ArrayList<String>();
        for (int i = 0; i < categories_list.size(); i++) {
            labels.add(categories_list.get(i).getTitle());
        }

        PieChart chart = (PieChart) view.findViewById(R.id.chart2);

        PieData data = new PieData(labels, dataset);

        data.setValueFormatter(new MyValueFormatter());


        final float textSize = getContext().getResources().getDisplayMetrics().density;
        int Textpixels = (int) (9 * textSize + 0.5f);

        data.setValueTextSize(Textpixels);

        chart.setData(data);

        /*Cores do PieChart*/
        ArrayList<Integer> colors = new ArrayList<Integer>();

        colors.add(ColorTemplate.getHoloBlue());
        colors.add(getResources().getColor(R.color.primary_green));
        colors.add(getResources().getColor(R.color.primary_red));
        colors.add(getResources().getColor(R.color.primary_light_blue));
        colors.add(getResources().getColor(R.color.primary_orange));

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        dataset.setColors(colors);

        chart.setDescription("");
        chart.getLegend().setEnabled(false);

        return view;
    }
}