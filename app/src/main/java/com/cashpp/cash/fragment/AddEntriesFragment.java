package com.cashpp.cash.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.cashpp.cash.R;
import com.cashpp.cash.activity.MainActivity;
import com.cashpp.cash.db.CategoryDB;
import com.cashpp.cash.db.EntryDB;
import com.cashpp.cash.model.Category;
import com.cashpp.cash.model.Entry;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;


public class AddEntriesFragment extends BaseFragment {
    private EntryDB entry_db;
    private CategoryDB category_db;

    private EditText title;
    private EditText value;
    private RadioGroup type;
    private RadioButton expense;
    private RadioButton income;
    private EditText date;
    private Spinner category;
    int category_id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setTitle(R.string.summary);
        View view = inflater.inflate(R.layout.fragment_summary_entries_add, container, false);

        //Botão voltar
        view.findViewById(R.id.bt_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).replaceFragment(new SummaryFragment());
            }
        });

        title = (EditText) view.findViewById(R.id.et_title);
        value = (EditText) view.findViewById(R.id.et_value);
        value.addTextChangedListener(new MascaraMonetaria(value));
        type = (RadioGroup) view.findViewById(R.id.rg_type);
        expense = (RadioButton) view.findViewById(R.id.expense);
        income = (RadioButton) view.findViewById(R.id.income);
        category = (Spinner) view.findViewById(R.id.sp_category);
        date = (EditText) view.findViewById(R.id.et_date);

        category_db = new CategoryDB((MainActivity) getActivity());
        Cursor c = category_db.getCursorForSpinner();
        String[] from = new String[] {"title"};
        int[] to = new int[] {android.R.id.text1};
        SimpleCursorAdapter sca = new SimpleCursorAdapter((MainActivity) getActivity(),
                android.R.layout.simple_spinner_dropdown_item, c, from, to);
        category.setAdapter(sca);
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                category_id = ((int) id);
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        //Botão criar nova entrada
        view.findViewById(R.id.bt_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entry_db = new EntryDB((MainActivity) getActivity());

                Entry entry = new Entry();

                Boolean flag = false;
                if (title.getText().toString().isEmpty()) flag = true;
                if (value.getText().toString().isEmpty()) flag = true;
                if (date.getText().toString().isEmpty()) flag = true;
                if (date.getText().toString().length() != 10) flag = true;

                if (!flag) {
                    entry.setTitle(title.getText().toString());

                    String value_string = value.getText().toString();
                    if (!value_string.isEmpty())
                        value_string = value_string.substring(2, value_string.length());
                    value_string = value_string.replace(".", "");
                    value_string = value_string.replaceAll(",", ".");
                    entry.setValue(parseDouble(value_string));

                    int type_int = type.getCheckedRadioButtonId();
                    if (type_int == expense.getId()) {
                        entry.setType("expense");
                    } else {
                        entry.setType("income");
                    }

                    entry.setCategory_id(category_id);

                    entry.setDate(date.getText().toString());

                    entry.setRecurrence(0);

                    long res = entry_db.saveEntry(entry);

                    if (res != -1) {
                        toast("Entrada criada com sucesso.");
                    } else {
                        toast("Erro ao criar entrada.");
                    }

                    ((MainActivity) getActivity()).replaceFragment(new SummaryFragment());
                } else {
                    toast("Todos os campos são obrigatórios e a data deve estar no formato.");
                }
            }
        });

        return view;
    }

    private class MascaraMonetaria implements TextWatcher {

        final EditText campo;

        public MascaraMonetaria(EditText campo) {
            super();
            this.campo = campo;
        }

        private boolean isUpdating = false;
        // Pega a formatacao do sistema, se for brasil R$ se EUA US$
        private NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int after) {
            // Evita que o método seja executado varias vezes.
            // Se tirar ele entre em loop
            if (isUpdating) {
                isUpdating = false;
                return;
            }

            isUpdating = true;
            String str = s.toString();
            // Verifica se já existe a máscara no texto.
            boolean hasMask = ((str.indexOf("R$") > -1 || str.indexOf("$") > -1) &&
                    (str.indexOf(".") > -1 || str.indexOf(",") > -1));
            // Verificamos se existe máscara
            if (hasMask) {
                // Retiramos a máscara.
                str = str.replaceAll("[R$]", "").replaceAll("[,]", "")
                        .replaceAll("[.]", "");
            }

            try {
                // Transformamos o número que está escrito no EditText em
                // monetário.
                str = nf.format(Double.parseDouble(str) / 100);
                campo.setText(str);
                campo.setSelection(campo.getText().length());
            } catch (NumberFormatException e) {
                s = "";
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // Não utilizado
        }

        @Override
        public void afterTextChanged(Editable s) {
            // Não utilizado
        }
    }

}
