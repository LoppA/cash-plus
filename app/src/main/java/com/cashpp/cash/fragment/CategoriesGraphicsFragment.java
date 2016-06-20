package com.cashpp.cash.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cashpp.cash.R;
import com.cashpp.cash.adapter.MyValueFormatter;
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

import java.util.ArrayList;


public class CategoriesGraphicsFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories_graphics, container, false);

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(4f, 0));
        entries.add(new Entry(5f, 1));
        entries.add(new Entry(6f, 2));
        entries.add(new Entry(2f, 3));
        entries.add(new Entry(18f, 4));
        entries.add(new Entry(5f, 5));

        PieDataSet dataset = new PieDataSet(entries, "Chart Categories");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Mercado");
        labels.add("Contas");
        labels.add("Escola");
        labels.add("Alimento");
        labels.add("Viagem");
        labels.add("Transporte");

        PieChart chart = (PieChart) view.findViewById(R.id.chart2);

        PieData data = new PieData(labels, dataset);

        data.setValueFormatter(new MyValueFormatter());

        chart.setData(data);


        /*Cores do PieChart*/
        ArrayList<Integer> colors = new ArrayList<Integer>();

        colors.add(ColorTemplate.getHoloBlue());
        colors.add(getResources().getColor(R.color.primary_green));
        colors.add(getResources().getColor(R.color.primary_red));
        colors.add(getResources().getColor(R.color.primary_blue));
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
