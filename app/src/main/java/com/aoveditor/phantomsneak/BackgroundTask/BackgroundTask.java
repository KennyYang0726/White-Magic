package com.aoveditor.phantomsneak.BackgroundTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import java.util.Objects;
import java.util.concurrent.Callable;

import com.aoveditor.phantomsneak.R;



public class BackgroundTask extends AsyncTask<Void, Void, String> {
    private Context context;
    private String message;
    private ProgressDialog progressDialog;
    private Callable<String> task;
    private OnTaskCompletedListener listener;

    public interface OnTaskCompletedListener {
        void onTaskCompleted(String result);
    }

    public BackgroundTask(Context context, Callable<String> task, String message, OnTaskCompletedListener listener) {
        this.context = context;
        this.task = task;
        this.message = message;
        this.listener = listener;
    }

    public void showDialogOnMainThread() {
        new Handler(Looper.getMainLooper()).post(() -> {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(message);
            progressDialog.setMax(100);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawableResource(R.drawable.custom_alert_dialog_bg);
            progressDialog.show();
        });
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        showDialogOnMainThread();
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            return task.call();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        if (listener != null) {
            listener.onTaskCompleted(result);
        }
    }
}


