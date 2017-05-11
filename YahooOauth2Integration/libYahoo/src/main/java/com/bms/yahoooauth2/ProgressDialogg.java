package com.bms.yahoooauth2;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by rahulkumar on 18/10/16.
 */

public class ProgressDialogg {

    public static ProgressDialog progressdialog;

    public static void ShowDialog(Context context) {
        progressdialog = new ProgressDialog(context, AlertDialog.THEME_HOLO_LIGHT);
        DismissDialog();
        if (!progressdialog.isShowing()) {
            progressdialog.setMessage("Loading...");
            progressdialog.setIndeterminate(true);
            progressdialog.setCancelable(false);
            progressdialog.show();
        }
    }

    public static void DismissDialog() {
        if (progressdialog != null && progressdialog.isShowing()) {
            progressdialog.dismiss();
        }
    }
}
