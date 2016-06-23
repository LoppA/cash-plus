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
import com.cashpp.cash.db.GoalDB;
import com.cashpp.cash.model.Goal;
import com.cashpp.cash.notification.NotificationEventReceiver;
import com.cashpp.cash.notification.NotificationEventReceiver2;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.lang.Double.parseDouble;


public class AddGoalsFragment extends BaseFragment {
    private GoalDB goal_db;

    private EditText title;
    private EditText value;
    private EditText date;
    private String data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setTitle(R.string.goals);
        View view = inflater.inflate(R.layout.fragment_goals_add, container, false);

        //Botão voltar
        view.findViewById(R.id.bt_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).replaceFragment(new GoalsFragment());
            }
        });

        title = (EditText) view.findViewById(R.id.et_title);
        value = (EditText) view.findViewById(R.id.et_value);
        value.addTextChangedListener(new MascaraMonetaria(value));
        date = (EditText) view.findViewById(R.id.et_date);

        //Botão criar nova meta
        view.findViewById(R.id.bt_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goal_db = new GoalDB((MainActivity) getActivity());

                Goal goal = new Goal();

                Boolean flag = false;

                if (title.getText().toString().isEmpty()) flag = true;
                if (value.getText().toString().isEmpty()) flag = true;
                if (date.getText().toString().length() != 10) flag = true;

                if (!flag) {
                    goal.setTitle(title.getText().toString());

                    String value_string = value.getText().toString();
                    if (!value_string.isEmpty())
                        value_string = value_string.substring(2, value_string.length());
                    value_string = value_string.replace(".", "");
                    value_string = value_string.replaceAll(",", ".");
                    goal.setValue(parseDouble(value_string));

                    goal.setDate(date.getText().toString());

                    long res = goal_db.saveGoal(goal);

                    if (res != -1) {
                        toast("Meta criada com sucesso.");
                        data = date.getText().toString();
                        String data2;
                        data = data.substring(0, 2) + '-' + data.substring(2+1);
                        data = data.substring(0, 5) + '-' + data.substring(5+1);
                        data = data + ":00";
                        onSendNotificationsButtonClick2();
                    } else {
                        toast("Erro ao criar meta.");
                    }

                    ((MainActivity) getActivity()).replaceFragment(new GoalsFragment());
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
    public void onSendNotificationsButtonClick2( ) {
        long time = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date date;
        try {
            date = sdf.parse(data);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            time = calendar.getTimeInMillis();
            NotificationEventReceiver2.setupAlarm(getActivity().getApplicationContext(), time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
