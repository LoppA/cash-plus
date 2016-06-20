package com.cashpp.cash.activity;

import android.content.DialogInterface;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.cashpp.cash.R;
import com.cashpp.cash.adapter.NavDrawerMenuAdapter;
import com.cashpp.cash.adapter.NavDrawerMenuItem;
import com.cashpp.cash.fragment.CategoriesFragment;
import com.cashpp.cash.fragment.GoalsFragment;
import com.cashpp.cash.fragment.RemindersFragment;
import com.cashpp.cash.fragment.SettingsFragment;
import com.cashpp.cash.fragment.SummaryFragment;

import java.util.List;

import livroandroid.lib.fragment.NavigationDrawerFragment;

import android.support.v4.app.Fragment;

public class MainActivity extends BaseActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    private NavigationDrawerFragment mNavDrawerFragment;
    private NavDrawerMenuAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();
        // Navigation Drawer
        mNavDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.nav_drawer_fragment);
        // Configura o Drawer Layout
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavDrawerFragment.setUp(drawerLayout);
        // Cor do fundo da barra de status
        drawerLayout.setStatusBarBackground(R.color.primary_dark);

    }


    @Override
    public NavigationDrawerFragment.NavDrawerListView getNavDrawerView(NavigationDrawerFragment navDrawerFrag, LayoutInflater layoutInflater, ViewGroup container) {
        View view = layoutInflater.inflate(R.layout.nav_drawer_listview, container, false);

        //navDrawerFrag.setHeaderValues(view, R.id.listViewContainer, R.drawable.nav_drawer_header, R.drawable.ic_logo_user, R.string.nav_drawer_username, R.string.nav_drawer_email);

        return new NavigationDrawerFragment.NavDrawerListView(view, R.id.listView);
    }

    @Override
    public ListAdapter getNavDrawerListAdapter(NavigationDrawerFragment navigationDrawerFragment) {
        List<NavDrawerMenuItem> list = NavDrawerMenuItem.getList();
        // Deixa o primeiro item selecionado
        list.get(0).selected = true;
        this.listAdapter = new NavDrawerMenuAdapter(this, list);
        return listAdapter;
    }

    @Override
    public void onNavDrawerItemSelected (NavigationDrawerFragment navigationDrawerFragment, int position) {
        List<NavDrawerMenuItem> list = NavDrawerMenuItem.getList();
        NavDrawerMenuItem selectedItem = list.get(position);
        // Seleciona a linha
        if (position != list.size() - 1) setTitle(selectedItem.title);
        this.listAdapter.setSelected(position, true);
        if (position == 0) {
            replaceFragment(new SummaryFragment());
        } else if (position == 1) {
            replaceFragment(new CategoriesFragment());
        } else if (position == 2) {
            replaceFragment(new GoalsFragment());
        } else if (position == 3) {
            replaceFragment(new RemindersFragment());
        } else if (position == 4) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Sair de Cash++")
                    .setMessage("Deseja realmente sair?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .setNegativeButton("Não", null)
                    .show();

            //replaceFragment(new SettingsFragment());
        } else {
            Log.e("cashapp", "Item de menu inválido");
        }
//        toast("Clicou no item: " + getString(selectedItem.title));
    }

    private void replaceFragment(Fragment frag) {
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_drawer_container, frag, "TAG").commit();
    }
    
}
