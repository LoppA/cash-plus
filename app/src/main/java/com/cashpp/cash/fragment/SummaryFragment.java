package com.cashpp.cash.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cashpp.cash.R;
import com.cashpp.cash.activity.MainActivity;


public class SummaryFragment extends BaseFragment {
    private FragmentTabHost mTabHost;

    //Mandatory Constructor
    public SummaryFragment () {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setTitle(R.string.summary);
        View rootView = inflater.inflate(R.layout.fragment_summary,container, false);


        mTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.summarytabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("summaryentriesfragment").setIndicator(getString(R.string.summary_entries)),
                SummaryEntriesFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("summarygraphicsfragment").setIndicator(getString(R.string.summary_graphics)),
                SummaryGraphicsFragment.class, null);


        /*Mudar a cor das string das tabs*/
        for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {

            final TextView tv = (TextView) mTabHost.getTabWidget().getChildAt(i)
                    .findViewById(android.R.id.title);

            // Look for the title view to ensure this is an indicator and not a divider.(I didn't know, it would return divider too, so I was getting an NPE)
            if (tv == null)
                continue;
            else
                tv.setTextColor(getResources().getColor(R.color.black));
        }

        return rootView;
    }

}
