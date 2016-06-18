package com.cashpp.cash.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cashpp.cash.R;
import com.cashpp.cash.activity.MainActivity;


public class CategoriesFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setTitle(R.string.categories);
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        return view;
    }

}
