package com.aoveditor.phantomsneak.ZipManager;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import com.aoveditor.phantomsneak.R;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipManager {

    public interface UnzipCallback {
        void onUnzipComplete();
    }

    public static void UnzipWithoutPwd(Context context, boolean showProgress, String Title, String _fileZip, String _destDir, UnzipCallback callback) {

        final ProgressDialog[] progressDialog = new ProgressDialog[1];
        CountDownLatch latch = new CountDownLatch(1);

        if (showProgress) {
            new Handler(Looper.getMainLooper()).post(() -> {
                progressDialog[0] = new ProgressDialog(context);
                progressDialog[0].setIcon(R.drawable.phantom_logo_small);
                progressDialog[0].setMax(100);
                progressDialog[0].setIndeterminate(true);
                progressDialog[0].setCancelable(false);
                progressDialog[0].setCanceledOnTouchOutside(false);
                progressDialog[0].setProgressStyle(ProgressDialog.STYLE_SPINNER);
                Objects.requireNonNull(progressDialog[0].getWindow()).setBackgroundDrawableResource(R.drawable.custom_alert_dialog_bg);
                progressDialog[0].setMessage(Title);
                progressDialog[0].show();
                latch.countDown(); // 完成初始化後解鎖
            });
        } else {
            progressDialog[0] = null;
            latch.countDown(); // 不用顯示進度條也解鎖
        }

        new Thread(() -> {
            try {
                latch.await(); // 等待主執行緒完成初始化
                File outdir = new File(_destDir);
                ZipInputStream zin = new ZipInputStream(new FileInputStream(_fileZip));
                ZipEntry entry;
                int totalEntries = 0;
                int entryCount = 0;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) { // 重要！！！
                    dalvik.system.ZipPathValidator.clearCallback();
                }

                // Count entries for progress tracking
                while ((entry = zin.getNextEntry()) != null) {
                    totalEntries++;
                }
                zin.close();

                zin = new ZipInputStream(new FileInputStream(_fileZip)); // Reset the input stream
                while ((entry = zin.getNextEntry()) != null) {
                    String name = entry.getName();
                    if (entry.isDirectory()) {
                        mkdirs(outdir, name);
                        continue;
                    }
                    String dir = dirpart(name);
                    if (dir != null) {
                        mkdirs(outdir, dir);
                    }
                    extractFile(zin, outdir, name);
                    entryCount++;

                    // Update progress
                    if (showProgress && progressDialog[0] != null) {
                        int progress = (int) (((float) entryCount / totalEntries) * 100);
                        progressDialog[0].setProgress(progress);
                    }
                }
                zin.close();

                // Dismiss the progress dialog
                if (showProgress && progressDialog[0] != null) {
                    new Handler(Looper.getMainLooper()).post(() -> progressDialog[0].dismiss());
                }

                // Trigger the callback once unzipping completes
                if (callback != null) {
                    callback.onUnzipComplete();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void extractFile(ZipInputStream in, File outdir, String name) throws IOException {
        byte[] buffer = new byte[4096];
        BufferedOutputStream out = new BufferedOutputStream(new java.io.FileOutputStream(new File(outdir, name)));
        int count;
        while ((count = in.read(buffer)) != -1) {
            out.write(buffer, 0, count);
        }
        out.close();
    }

    private static void mkdirs(File outdir, String path) {
        File d = new File(outdir, path);
        if (!d.exists()) {
            d.mkdirs();
        }
    }

    private static String dirpart(String name) {
        int s = name.lastIndexOf(File.separatorChar);
        return s == -1 ? null : name.substring(0, s);
    }
}
