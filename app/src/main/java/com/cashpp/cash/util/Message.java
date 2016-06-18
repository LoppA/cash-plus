package com.cashpp.cash.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

public class Message {
    public static void toastMsg(Activity activity, String title, String msg, int icon) {
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
    }

    public static void addMsgOk(Activity activity, String title, String msg, int icon) {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle(title);
        alert.setMessage(msg);
        alert.setNeutralButton("OK", null);
        alert.setIcon(icon);
        alert.show();
    }

    public static void confirmMsg(Activity activity, String title, String msg, int icon,
                                  DialogInterface.OnClickListener listener) {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle(title);
        alert.setMessage(msg);
        alert.setPositiveButton("Sim", listener);
        alert.setNegativeButton("Não", null);
        alert.setIcon(icon);
        alert.show();
    }

    public static AlertDialog createAlertDialog(Activity activity) {
        final CharSequence[] items = {
                "Editar",
                "Excluir"
        };

        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle("Opções");
        alert.setItems(items, (DialogInterface.OnClickListener) activity);
        return alert.create();
    }

    public static AlertDialog createDialogConfirmation(Activity activity) {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setMessage("Deseja realmente excluir?");
        alert.setPositiveButton("Sim", (DialogInterface.OnClickListener) activity);
        alert.setPositiveButton("Não", (DialogInterface.OnClickListener) activity);

        return alert.create();
    }
}