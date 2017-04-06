package com.br.desafiozup.ui.components;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.br.desafiozup.R;

public class DialogBuilder {

    public final static String STRING_EMPTY = "";
    public final static int VALUE_EMPTY = 0;

    static AlertDialog.Builder builder;

    public static void createDialogAlert(Context context, int message, int title, ButtonCallback buttonCallback) {
        createDialogAlert(context, getValueResource(context, message), getValueResource(context, title), buttonCallback);
    }

    public static void createDialogAlert(Context context, String message, String title, ButtonCallback buttonCallback, boolean showDialog) {
        createDialog(context, context.getString(R.string.ok), STRING_EMPTY, title, message, buttonCallback, showDialog);
    }

    public static void createDialogAlert(Context context, String message, String title, ButtonCallback buttonCallback) {
        createDialogAlert(context, message, title, buttonCallback, true);
    }

    public static void createDialogDecision(Context context, String title, String message) {
        createDialogDecision(context, title, message, context.getString(R.string.ok), STRING_EMPTY, null);
    }

    public static void createDialogDecision(Context context, String message,ButtonCallback buttonCallback) {
        createDialogDecision(context, STRING_EMPTY, message, context.getString(R.string.ok), STRING_EMPTY, buttonCallback);
    }

    public static void createDialogDecision(Context context, String message) {
        createDialogDecision(context, STRING_EMPTY, message, context.getString(R.string.ok), STRING_EMPTY, null);
    }

    public static void createDialogDecision(Context context, int title, int message, int titleButtonPositive, int titleButtonNegative, ButtonCallback buttonCallback) {
        createDialogDecision(context, context.getString(title), context.getString(message),
                context.getString(titleButtonPositive), context.getString(titleButtonNegative), buttonCallback);
    }

    public static void createDialogDecision(Context context, int title, int message, int titleButtonPositive, int titleButtonNegative, ButtonCallback buttonCallback, boolean cancelable) {
        createDialogDecision(context, context.getString(title), context.getString(message),
                context.getString(titleButtonPositive), context.getString(titleButtonNegative), buttonCallback, cancelable);
    }

    public static void createDialogDecision(Context context, String title, String message, int titleButtonPositive, int titleButtonNegative, ButtonCallback buttonCallback) {
        createDialogDecision(context, title, message,
                context.getString(titleButtonPositive), context.getString(titleButtonNegative), buttonCallback);
    }

    public static void createDialogDecision(Context context, int title, int message, ButtonCallback buttonCallback) {
        createDialogDecision(context, title, message, R.string.button_ok, R.string.cancel, buttonCallback);
    }

    public static void createDialogDecision(Context context, String title, String message, ButtonCallback buttonCallback) {
        createDialogDecision(context, title, message, R.string.button_ok, R.string.cancel, buttonCallback);
    }

    public static void createDialogDecision(Context context, String title, String message, String titleButtonPositive, String titleButtonNegative, ButtonCallback buttonCallback) {
        createDialog(context, titleButtonPositive, titleButtonNegative, title, message, buttonCallback);
    }

    public static void createDialogDecision(Context context, String title, String message, String titleButtonPositive, String titleButtonNegative, ButtonCallback buttonCallback, boolean cancelable) {
        createDialog(context, titleButtonPositive, titleButtonNegative, title, message, buttonCallback, cancelable);
    }

    public static void showDialogConfirm(Context context, int title, int description, ButtonCallback callback) {
        showDialogPositiveNegative(context, context.getString(R.string.ok), STRING_EMPTY, context.getString(title), context.getString(description), callback);
    }

    public static void showDialogConfirm(Context context, String title, String description, ButtonCallback callback) {
        showDialogPositiveNegative(context, context.getString(R.string.ok), STRING_EMPTY, title, description, callback);
    }

    public static void showDialogPositiveNegative(Context context, int title, int content, ButtonCallback callback) {
        showDialogPositiveNegative(context, context.getString(title), context.getString(content), callback);
    }

    public static void showDialogPositiveNegative(Context context, String title, String content, ButtonCallback callback) {
        showDialogPositiveNegative(context, R.string.button_ok, R.string.cancel, title, content, callback);
    }

    public static void showDialogPositiveNegative(Context context, int titlePositiveButton, int titleNegativeButton, String title, String content, ButtonCallback callback) {
        showDialogPositiveNegative(context, context.getString(titlePositiveButton), context.getString(titleNegativeButton), title, content, callback);
    }

    public static void showDialogPositiveNegative(Context context, String titlePositiveButton, String titleNegativeButton, String title, String content, ButtonCallback callback) {
        createDialog(context, titlePositiveButton, titleNegativeButton, title, content, callback, true);
    }

    public static void createDialog(Context context, String titlePositiveButton, String titleNegativeButton, String title, String content, ButtonCallback callback, boolean cancelable) {
        createDialog(context, titlePositiveButton, titleNegativeButton, title, content, callback, true, cancelable);
    }

    public static void createDialog(Context context, String titlePositiveButton, String titleNegativeButton, String title, String content, ButtonCallback callback) {
        createDialog(context, titlePositiveButton, titleNegativeButton, title, content, callback, true, true);
    }

    public static void createDialog(Context context, String titlePositiveButton, String titleNegativeButton, String title, String content, ButtonCallback callback, boolean showDialog, boolean cancelable) {

        builder = createAlertDialogBuilder(context, title, content, cancelable);
        putOnClickListenerButton(titlePositiveButton, titleNegativeButton, callback, builder);


        if (showDialog) {
            showDialogAlertDialogBuilder();
        }
    }

    public static ProgressDialog createDialogProgressSimple(Context context, String content, boolean cancelable, ProgressCallback progressCallback) {
        return createDialogProgress(context, STRING_EMPTY, content, cancelable, progressCallback);
    }

    public static ProgressDialog createDialogProgress(Context context, String title, String content, boolean cancelable, final ProgressCallback progressCallback) {

        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(cancelable);

        if (!title.isEmpty()) {
            progressDialog.setTitle(title);
        }

        if (!content.isEmpty()) {
            progressDialog.setMessage(content);
        }

        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                progressCallback.onCancel(dialog);
            }
        });
        progressDialog.setProgressStyle(R.style.ProgressDialog);
        return progressDialog;
    }

    private static AlertDialog.Builder createAlertDialogBuilder(Context context, String title, String content, boolean cancelable) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);

        if (!title.isEmpty()) {
            builder.setTitle(title);
        }

        if (!content.isEmpty()) {
            builder.setMessage(content);
        }

        builder.setCancelable(cancelable);

        return builder;
    }

    private static void showDialogAlertDialogBuilder() {
        builder.show();
    }

    public static AlertDialog.Builder getDialog() {
        return builder;
    }

    private static void putOnClickListenerButton(String titleButtonPositive, String titleNegativeButton, ButtonCallback buttonCallback, AlertDialog.Builder builder) {
        putOnClickListener(titleButtonPositive, titleNegativeButton, buttonCallback, builder);
    }

    private static void putOnClickListener(String titleButtonPositive, String titleNegativeButton, final ButtonCallback buttonCallback,
                                           final AlertDialog.Builder builder) {
        if (!titleButtonPositive.equals(STRING_EMPTY)) {

            builder.setPositiveButton(titleButtonPositive, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if (buttonCallback != null) {
                        buttonCallback.onPositive(builder, dialog);
                        buttonCallback.onAny(builder, dialog);
                    } else {
                        dialog.dismiss();
                    }
                }
            });
        }

        if (!titleNegativeButton.equals(STRING_EMPTY)) {

            builder.setNegativeButton(titleNegativeButton, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if (buttonCallback != null) {
                        buttonCallback.onNegative(builder, dialog);
                        buttonCallback.onAny(builder, dialog);

                    } else {
                        dialog.dismiss();
                    }
                }
            });

            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {

                    if (buttonCallback != null) {
                        buttonCallback.OnDismissListener(builder, dialog);
                    }
                }
            });
        }
    }

    private static String getValueResource(Context context, int value) {

        if (value > VALUE_EMPTY) {
            return context.getString(value);
        } else {
            return STRING_EMPTY;
        }
    }

    public static void showErrorServerInformation(Context context) {
        showDialogConfirm(context, R.string.app_name, R.string.error_server_infomation, new ButtonCallback() {
            @Override
            protected void onPositive(AlertDialog.Builder builder, DialogInterface dialogInterface) {
                dialogInterface.dismiss();
            }
        });
    }

    public static abstract class ButtonCallback {

        protected void onPositive(AlertDialog.Builder builder, DialogInterface dialogInterface) {
        }

        protected void onNegative(AlertDialog.Builder builder, DialogInterface dialogInterface) {
        }

        protected void onAny(AlertDialog.Builder builder, DialogInterface dialogInterface) {
        }

        protected void OnDismissListener(AlertDialog.Builder builder, DialogInterface dialogInterface) {
        }
    }

    public static abstract class ProgressCallback {

        protected void onCancel(DialogInterface dialog) {
        }
    }
}

