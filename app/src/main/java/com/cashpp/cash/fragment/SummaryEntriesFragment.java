package com.cashpp.cash.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cashpp.cash.R;
import com.cashpp.cash.activity.MainActivity;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;


public class SummaryEntriesFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_summary_entries, container, false);

        /* Pega data */
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;

        /* Muda a string data no fragment */
        TextView t = (TextView) view.findViewById(R.id.date_entries);
        t.setText(mes((new Integer(month))) + " " + (new Integer(year)).toString());
        return view;
    }

    String mes (int month) {
        if (month == 1) {
            return "Janeiro";
        } else if (month == 2) {
            return "Fevereiro";
        } else if (month == 3) {
            return "Março";
        } else if (month == 4) {
            return "Abril";
        } else if (month == 5) {
            return "Maio";
        } else if (month == 6) {
            return "Junho";
        } else if (month == 7) {
            return "Julho";
        } else if (month == 8) {
            return "Agosto";
        } else if (month == 9) {
            return "Setembro";
        } else if (month == 10) {
            return "Outubro";
        } else if (month == 11) {
            return "Novembro";
        } else if (month == 12) {
            return "Dezembro";
        } else {
            return "Mês Inválido";
        }
    }
}
