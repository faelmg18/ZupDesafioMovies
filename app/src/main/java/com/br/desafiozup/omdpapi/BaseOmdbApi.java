package com.br.desafiozup.omdpapi;

import android.app.Activity;
import android.content.Context;

import com.br.desafiozup.util.taskcontrol.SimpleTask;
import com.br.desafiozup.util.taskcontrol.TaskManager;

public class BaseOmdbApi {

    Activity mContext;

    public BaseOmdbApi(Activity context){
        this.mContext = context;
    }

    public void executeTask(SimpleTask task) {
        TaskManager taskManager = new TaskManager(mContext, task);
        taskManager.execute();
    }
}
