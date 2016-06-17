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
        list.add(new NavDrawerMenuItem(R.string.resumo, R.drawable.ic_menu_camera));
        list.add(new NavDrawerMenuItem(R.string.categorias, R.drawable.ic_menu_gallery));
        list.add(new NavDrawerMenuItem(R.string.metas, R.drawable.ic_menu_manage));
        list.add(new NavDrawerMenuItem(R.string.lembretes, R.drawable.ic_menu_send));
        list.add(new NavDrawerMenuItem(R.string.configuracoes, R.drawable.ic_menu_share));
        return list;
    }
}