package com.cashpp.cash.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cashpp.cash.R;
import com.cashpp.cash.activity.MainActivity;
import com.cashpp.cash.adapter.CategoryAdapter;
import com.cashpp.cash.db.CategoryDB;
import com.cashpp.cash.model.Category;

import java.util.List;


public class CategoriesCategoriesFragment extends BaseFragment {
    private ListView list;
    private List<Category> categories_list;
    private CategoryDB category_db;
    private CategoryAdapter category_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories_categories, container, false);

        /* Botão para adicionar uma nova categoria */
        view.findViewById(R.id.bt_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).replaceFragment(new AddCategoriesFragment());
            }
        });

        //Pegando os dados do BD para popular o ListView
        category_db = new CategoryDB((MainActivity) getActivity());
        categories_list = category_db.listCategories();
        category_adapter = new CategoryAdapter((MainActivity) getActivity(), categories_list);

        //ListView do layout
        list = (ListView) view.findViewById(R.id.lvCategories);
        list.setAdapter(category_adapter);

        //list.setOnItemClickListener();

        return view;
    }

}
