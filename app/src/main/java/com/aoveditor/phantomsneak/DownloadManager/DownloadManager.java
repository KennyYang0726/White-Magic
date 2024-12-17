package com.aoveditor.phantomsneak.DownloadManager;

import static android.content.Context.DOWNLOAD_SERVICE;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.webkit.CookieManager;
import android.webkit.URLUtil;

import androidx.annotation.RequiresApi;

import com.aoveditor.phantomsneak.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

public class DownloadManager {

    // 定義回調接口，用於背景下載結果
    public interface DownloadCallback {
        void onDownloadInBackgroundComplete(String filePath);  // 背景下載完成
        void onDownloadFailed(String error); // 下載失敗
    }
    // 定義回調接口，用於UI下載結果
    public interface UIDownloadCallback {
        void onDownloadComplete(String filePath);  // 背景下載完成
        void onDownloadFailed(String error); // 下載失敗
    }



    // 背景下載 圖片資源 / 語言包 (無 UI)
    public static void downloadInBackground(Context context, String urlString, String filePath, String fileName, DownloadCallback callback) {
        Runnable downloadTask = new Runnable() {
            @Override
            public void run() {
                try {
                    ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isConnected()) {
                        URL url = new URL(urlString);
                        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
                        long completeFileSize = httpConnection.getContentLength();
                        // String filename = URLUtil.guessFileName(urlString, null, null);
                        String filename = fileName;
                        // 初始化流
                        BufferedInputStream in = new BufferedInputStream(httpConnection.getInputStream());
                        FileOutputStream fos = new FileOutputStream(filePath + filename);
                        BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
                        byte[] data = new byte[1024];
                        int bytesRead;
                        // 文件下載循環
                        while ((bytesRead = in.read(data, 0, 1024)) >= 0) {
                            bout.write(data, 0, bytesRead);
                        }
                        // 關閉資源
                        bout.close();
                        in.close();
                        // 回調下載完成
                        callback.onDownloadInBackgroundComplete(filePath + filename);
                    } else {
                        callback.onDownloadFailed("網路連線失敗");
                    }
                } catch (IOException e) {
                    callback.onDownloadFailed(e.getMessage());
                }
            }
        };
        // 啟動下載執行緒
        new Thread(downloadTask).start();
    }

    // 下載插件 ( 包含 UI 更新的邏輯 )
    public static void downloadPlugins(Context context, String urlString, String filePath, String title, String fileName, UIDownloadCallback callback) {
        // 創建 ProgressDialog
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setIcon(R.drawable.phantom_logo_small);
        progressDialog.setTitle(title);
        progressDialog.setMax(100);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawableResource(R.drawable.custom_alert_dialog_bg);

        // 創建主線程 Handler
        Handler handler = new Handler(Looper.getMainLooper());

        Runnable downloadTask = new Runnable() {
            @Override
            public void run() {
                try {
                    ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isConnected()) {
                        URL url = new URL(urlString);
                        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
                        long completeFileSize = httpConnection.getContentLength();
                        // String filename = URLUtil.guessFileName(urlString, null, null);
                        String filename = fileName;
                        // 初始化流
                        BufferedInputStream in = new BufferedInputStream(httpConnection.getInputStream());
                        FileOutputStream fos = new FileOutputStream(filePath + filename);
                        BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
                        byte[] data = new byte[1024];
                        long downloadedFileSize = 0;
                        int x = 0;
                        while ((x = in.read(data, 0, 1024)) >= 0) {
                            downloadedFileSize += x;
                            final int currentProgress = (int) ((((double)downloadedFileSize) / ((double)completeFileSize)) * 100000d);
                            handler.post(() -> {
                                progressDialog.setMessage("正在下載 " + filename + "\n下載進度：" + (currentProgress / 1000) + "%");
                                progressDialog.show();
                            });
                            bout.write(data, 0, x);
                        }
                        // 關閉資源
                        bout.close();
                        in.close();
                        progressDialog.dismiss();
                        // 回調下載完成
                        callback.onDownloadComplete(filePath + filename);
                    } else {
                        callback.onDownloadFailed("網路連線失敗");
                        progressDialog.dismiss();
                    }
                } catch (IOException e) {
                    callback.onDownloadFailed(e.getMessage());
                    progressDialog.dismiss();
                }
            }
        };
        // 啟動下載執行緒
        new Thread(downloadTask).start();
    }


    // DownloadManager 讓使用者可以從狀態欄取消下載
    // 參數路徑不包含 /storage/emulated/0/
    // 無法調用下載完成 callback，用於語音插件下載，並且未設定為自動啟用
    public static void downloadManager(Context context, String urlString, String filePath, String fileName) {
        Runnable downloadTask = new Runnable() {
            @SuppressLint("Range")
            @Override
            public void run() {
                // 準備下載請求
                android.app.DownloadManager.Request request = new android.app.DownloadManager.Request(Uri.parse(urlString));
                request.setMimeType(context.getContentResolver().getType(Uri.parse(urlString)));
                String cookies = CookieManager.getInstance().getCookie(urlString);
                request.addRequestHeader("cookie", cookies);
                request.setDescription("正在下載插件");
                request.setTitle(fileName);
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(android.app.DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    Uri destinationUri = Uri.fromFile(new File("/storage/emulated/0/" + filePath + fileName));
                    request.setDestinationUri(destinationUri);
                } else {
                    request.setDestinationInExternalPublicDir(filePath, fileName);
                }
                // 獲取 DownloadManager 並執行下載
                android.app.DownloadManager dm = (android.app.DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                dm.enqueue(request);
            }
        };
        // 啟動下載執行緒
        new Thread(downloadTask).start();
    }

}
