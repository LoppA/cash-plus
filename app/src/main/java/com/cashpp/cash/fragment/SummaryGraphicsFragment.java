package com.cashpp.cash.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cashpp.cash.R;
import com.cashpp.cash.activity.MainActivity;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;


public class SummaryGraphicsFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_summary_graphics, container, false);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(5f, 1));
        entries.add(new BarEntry(-6f, 2));
        entries.add(new BarEntry(2f, 3));
        entries.add(new BarEntry(-18f, 4));
        entries.add(new BarEntry(5f, 5));

        BarDataSet dataset = new BarDataSet(entries, "Chart Summary");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Aposta");
        labels.add("Venda");
        labels.add("Conta");
        labels.add("Jogo");
        labels.add("Mercado");
        labels.add("Concerto");

        HorizontalBarChart chart = (HorizontalBarChart) view.findViewById(R.id.chart1);

        BarData data = new BarData(labels, dataset);
        chart.setData(data);


        int[] colors = new int[dataset.getEntryCount()];

        for (int i = 0; i<colors.length; i++){
            colors[i] = getResources().getColor(R.color.primary_blue);
            if (entries.get(i).getVal() < 0) colors[i]= getResources().getColor(R.color.primary_red);
        }

        dataset.setColors(colors);

        chart.setDescription("");
        chart.getLegend().setEnabled(false);

        YAxis left = chart.getAxisLeft();
        left.setDrawLabels(false); // no axis labels
        left.setDrawAxisLine(false); // no axis line
        left.setDrawGridLines(false); // no grid lines
//        left.setDrawZeroLine(true); // draw a zero line
        chart.getAxisRight().setEnabled(false);



        return view;
    }

}
