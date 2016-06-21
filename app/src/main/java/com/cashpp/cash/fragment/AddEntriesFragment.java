package com.cashpp.cash.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.cashpp.cash.R;
import com.cashpp.cash.activity.MainActivity;
import com.cashpp.cash.db.EntryDB;
import com.cashpp.cash.model.Entry;

import java.text.NumberFormat;
import java.util.List;

import static java.lang.Double.parseDouble;


public class AddEntriesFragment extends BaseFragment {
    private EntryDB entry_db;

    private EditText title;
    private EditText value;
    private RadioGroup type;
    private EditText date;
    private Spinner recurrence;


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
        date = (EditText) view.findViewById(R.id.et_date);
        recurrence = (Spinner) view.findViewById(R.id.sp_recurrence);

        //Botão criar nova entrada
        view.findViewById(R.id.bt_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entry_db = new EntryDB((MainActivity) getActivity());

                Entry entry = new Entry();
                entry.setTitle(title.getText().toString());
                entry.setValue(parseDouble(value.getText().toString()));
                entry.setDate(date.getText().toString());
                //implementar todas as outras

                long res = entry_db.saveEntry(entry);

                if (res != -1) {
                    toast("Entrada criada com sucesso.");
                } else {
                    toast("Erro ao criar meta.");
                }

                ((MainActivity) getActivity()).replaceFragment(new SummaryFragment());
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
        private NumberFormat nf = NumberFormat.getCurrencyInstance();

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
