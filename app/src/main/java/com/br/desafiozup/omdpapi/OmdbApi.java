package com.br.desafiozup.omdpapi;


import android.app.Activity;

import com.br.desafiozup.R;
import com.br.desafiozup.connection.RestWebservice;
import com.br.desafiozup.model.Movie;
import com.br.desafiozup.ui.components.DialogBuilder;
import com.br.desafiozup.util.AndroidAppException;
import com.br.desafiozup.util.taskcontrol.SimpleTask;
import com.br.desafiozup.util.taskcontrol.TaskResult;

public class OmdbApi extends BaseOmdbApi {

    private OnFinderMovie finderMovie;
    public OmdbApi(Activity context) {
        super(context);
    }

    public void findMovie(final String titleMovie, final OmdApiFinderImp omdApiFinder) {

        finderMovie = new OnFinderMovie() {
            @Override
            void execute() {

                try {
                    final Movie movie = RestWebservice.getInstance().getMovie(titleMovie);

                    mContext.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            omdApiFinder.onFindMovie((movie));
                        }
                    });

                } catch (final Exception e) {
                    mContext.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            omdApiFinder.onError(e);
                        }
                    });
                }
            }
        };

        executeOmdbApi();
    }

    private void executeOmdbApi() {
        SimpleTask task = new SimpleTask() {
            @Override
            public TaskResult executeTask() throws AndroidAppException {
                finderMovie.execute();
                return new TaskResult();
            }

            @Override
            public void processAfterTask(TaskResult taskResult) {

                if (taskResult.isTaskSucess()) {
                }
            }

            @Override
            public void processAfterFailTask(AndroidAppException e) {
                DialogBuilder.createDialogDecision(mContext, mContext.getString(R.string.error_server_infomation));
            }
        };

        executeTask(task);
    }

    public abstract class OnFinderMovie {
        abstract void execute();
    }
}
