package com.br.desafiozup.util.taskcontrol;


import com.br.desafiozup.util.AndroidAppException;

public interface SimpleTask {

    TaskResult executeTask() throws AndroidAppException;

    void processAfterTask(TaskResult taskResult);

    void processAfterFailTask(AndroidAppException e);
}
