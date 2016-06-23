package com.cashpp.cash.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cashpp.cash.R;
import com.cashpp.cash.activity.MainActivity;
import com.cashpp.cash.adapter.MyValueFormatter;
import com.cashpp.cash.adapter.MyValueFormatter2;
import com.cashpp.cash.db.EntryDB;
import com.cashpp.cash.model.Entry;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;


public class SummaryGraphicsFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_summary_graphics, container, false);

        ArrayList<BarEntry> entries = new ArrayList<>();

        EntryDB entry_db = new EntryDB(getActivity());
        List<Entry> entries_list = entry_db.listEntries();

        for (int i = 0; i < entries_list.size(); i++) {
            if (entries_list.get(i).getType().equals("expense"))
                entries.add(new BarEntry(-1 * entries_list.get(i).getValue().floatValue(), i));
            else
                entries.add(new BarEntry(entries_list.get(i).getValue().floatValue(), i));
        }

        BarDataSet dataset = new BarDataSet(entries, "Chart Summary");


        ArrayList<String> labels = new ArrayList<>();
        labels.add("Aposta");
        for (int i = 0; i < entries_list.size(); i++)
            labels.add(entries_list.get(i).getTitle());

        HorizontalBarChart chart = (HorizontalBarChart) view.findViewById(R.id.chart1);

        BarData data = new BarData(labels, dataset);


        final float textSize = getContext().getResources().getDisplayMetrics().density;
        int Textpixels = (int) (9 * textSize + 0.5f);

        data.setValueTextSize(Textpixels);
        data.setValueTextColor(getResources().getColor(R.color.black));

        data.setValueFormatter(new MyValueFormatter());

        chart.setData(data);


        int[] colors = new int[dataset.getEntryCount()];

        for (int i = 0; i<colors.length; i++){
            colors[i] = getResources().getColor(R.color.primary_light_blue);
            if (entries.get(i).getVal() < 0.0) colors[i]= getResources().getColor(R.color.primary_red);
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

        chart.setDrawValueAboveBar(false);

        final float scale = getContext().getResources().getDisplayMetrics().density;
        int pixels = (int) (70*labels.size() * scale + 0.5f);

        chart.setMinimumHeight(pixels);

        View v1 = view.findViewById(R.id.graphview);

        int minpixels = (int) (380 * scale + 0.5f);

        v1.setMinimumHeight(minpixels);

        return view;
    }

}