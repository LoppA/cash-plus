package com.cashpp.cash.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.cashpp.cash.R;
import com.cashpp.cash.activity.MainActivity;
import com.cashpp.cash.db.CategoryDB;
import com.cashpp.cash.model.Category;

import java.text.NumberFormat;
import java.util.List;

public class AddCategoriesFragment extends BaseFragment {
    private CategoryDB category_db;

    private EditText title;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setTitle(R.string.categories);
        View view = inflater.inflate(R.layout.fragment_categories_categories_add, container, false);

        //Botão voltar
        view.findViewById(R.id.bt_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).replaceFragment(new CategoriesCategoriesFragment());
            }
        });

        title = (EditText) view.findViewById(R.id.et_title);

        //Botão criar nova meta
        view.findViewById(R.id.bt_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category_db = new CategoryDB((MainActivity) getActivity());

                Category category = new Category();

                if (title.getText().toString().isEmpty()) {
                    toast("Campo obrigatório.");
                } else {
                    category.setTitle(title.getText().toString());

                    long res = category_db.saveCategory(category);

                    if (res != -1) {
                        toast("Categoria criada com sucesso.");
                    } else {
                        toast("Erro ao criar categoria.");
                    }

                    ((MainActivity) getActivity()).replaceFragment(new CategoriesFragment());
                }
            }
        });

        return view;
    }

}
