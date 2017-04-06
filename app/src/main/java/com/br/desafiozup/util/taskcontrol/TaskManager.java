package com.br.desafiozup.util.taskcontrol;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;

import com.br.desafiozup.R;
import com.br.desafiozup.ui.components.DialogBuilder;
import com.br.desafiozup.util.AndroidAppException;

public class TaskManager extends AsyncTask<Void, String, TaskResult> {

    private Context context;
    private ProgressDialog progressDialog;
    private int messageProgress = R.string.wait;
    private int titleProgress = R.string.empty;
    private SimpleTask task;

    public TaskManager(Context context, SimpleTask task, int messageProgress, int titleProgress) {
        this(context, task);
        this.messageProgress = messageProgress;
        this.titleProgress = titleProgress;
        if (titleProgress > 0) {
            loadProgressDialog(context, messageProgress, titleProgress);
        } else {
            loadProgressDialog(context);
        }
    }

    public TaskManager(Context context, SimpleTask task) {
        this.context = context;
        this.task = task;
        loadProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        try {
            showProgressDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected TaskResult doInBackground(Void... params) {
        TaskResult taskResult = new TaskResult();
        try {
            taskResult = task.executeTask();
        } catch (AndroidAppException e) {
            e.printStackTrace();
            taskResult.setAndroidException(e);
        }
        return taskResult;
    }

    @Override
    protected void onPostExecute(TaskResult result) {
        closeProgressDialog();
        if (result.isTaskSucess()) {
            task.processAfterTask(result);
        } else {
            task.processAfterFailTask(result.getAndroidException());
        }
    }

    private void closeProgressDialog() {
        try {
            if (progressDialog != null && progressDialog.isShowing())
                progressDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showProgressDialog() {
        progressDialog.show();
    }

    private void loadProgressDialog(Context context, int messageProgress, int titleProgress) {

        progressDialog = DialogBuilder.createDialogProgress(context, context.getString(titleProgress), Html.fromHtml(context.getString(messageProgress)).toString(), false, null);
        progressDialog.setIndeterminate(false);
    }

    private void loadProgressDialog(Context context) {
        progressDialog = DialogBuilder.createDialogProgressSimple(context, Html.fromHtml(context.getString(messageProgress)).toString(), false,null);
        progressDialog.setIndeterminate(false);
    }
}

