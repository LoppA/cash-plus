package com.cashpp.cash.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cashpp.cash.R;
import com.cashpp.cash.activity.MainActivity;


public class GoalsFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setTitle(R.string.goals);
        View view = inflater.inflate(R.layout.fragment_goals, container, false);
        return view;
    }

}
