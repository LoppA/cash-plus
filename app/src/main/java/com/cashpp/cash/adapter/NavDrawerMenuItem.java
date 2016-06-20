package com.cashpp.cash.adapter;


import com.cashpp.cash.R;

import java.util.ArrayList;
import java.util.List;

public class NavDrawerMenuItem {
    // Título: R.string.xxx
    public int title;
    // Figura: R.drawable.xxx
    public int img;
    // Para colocar um fundo cinza quando a linha está selecionada
    public boolean selected;
    public NavDrawerMenuItem(int title, int img) {
        this.title = title;
        this.img = img;
    }
    // Cria a lista com os itens de menu
    public static List<NavDrawerMenuItem> getList() {
        List<NavDrawerMenuItem> list = new ArrayList<NavDrawerMenuItem>();
        list.add(new NavDrawerMenuItem(R.string.summary, R.drawable.ic_menu_summary));
        list.add(new NavDrawerMenuItem(R.string.categories, R.drawable.ic_menu_categories));
        list.add(new NavDrawerMenuItem(R.string.goals, R.drawable.ic_menu_goals));
        list.add(new NavDrawerMenuItem(R.string.reminders, R.drawable.ic_menu_reminders));
        list.add(new NavDrawerMenuItem(R.string.exit, R.drawable.ic_menu_exit));
        return list;
    }
}