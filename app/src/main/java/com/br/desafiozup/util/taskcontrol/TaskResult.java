package com.br.desafiozup.util.taskcontrol;


import android.os.Bundle;

import com.br.desafiozup.util.AndroidAppException;

public class TaskResult {

    private AndroidAppException androidException;

    public TaskResult(AndroidAppException androidException, Bundle bundle) {
        super();
        this.androidException = androidException;
    }

    public TaskResult() {
    }

    public boolean isTaskSucess() {
        if(androidException == null) {
            return true;
        }
        return false;
    }

    public AndroidAppException getAndroidException() {
        return androidException;
    }

    public void setAndroidException(AndroidAppException androidException) {
        this.androidException = androidException;
    }
}
