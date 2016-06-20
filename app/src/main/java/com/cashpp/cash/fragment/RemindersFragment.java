package com.cashpp.cash.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.cashpp.cash.R;
import com.cashpp.cash.activity.MainActivity;
import com.cashpp.cash.adapter.ReminderAdapter;
import com.cashpp.cash.db.GoalDB;
import com.cashpp.cash.db.ReminderDB;
import com.cashpp.cash.model.Reminder;

import java.util.List;


public class RemindersFragment extends BaseFragment {
    private ListView list;
    private List<Reminder> reminders_list;
    private ReminderDB reminder_db;
    private ReminderAdapter reminder_adapter;

    private int position_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        (getActivity()).setTitle(R.string.reminders);
        View view = inflater.inflate(R.layout.fragment_reminders_list, container, false);

        //Botão para adicionar uma nova meta
        view.findViewById(R.id.bt_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).replaceFragment(new AddRemindersFragment());
            }
        });

        reminder_db = new ReminderDB((MainActivity) getActivity());
        reminders_list = reminder_db.listReminders();
        reminder_adapter = new ReminderAdapter((MainActivity) getActivity(), reminders_list);

        list = (ListView) view.findViewById(R.id.lvReminders);
        list.setAdapter(reminder_adapter);

        //list.setOnItemClickListener();

        return view;
    }

}
