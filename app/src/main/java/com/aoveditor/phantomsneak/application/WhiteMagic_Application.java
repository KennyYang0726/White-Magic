package com.aoveditor.phantomsneak.application;

import android.app.Application;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatDelegate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import com.jakewharton.threetenabp.AndroidThreeTen;

import com.aoveditor.phantomsneak.Utils.FileUtil;
import com.aoveditor.phantomsneak.Utils.RootUtil;


/** app 常駐類，啟動 app 即註冊 */

public class WhiteMagic_Application extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 設定主題
        SharedPreferences sharedPref = getSharedPreferences("AppSettings", MODE_PRIVATE);
        String themePreference = sharedPref.getString("themePreference", "system");
        switch (themePreference) {
            case "dark":
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case "light":
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            default:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // 初始化 root 權限
            RootUtil.initRootAccess();
        }

        AndroidThreeTen.init(this);

        // 設置全局的未捕捉異常處理器
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                // 保存錯誤訊息或使用崩潰報告服務
                String errorMessage = Log.getStackTraceString(e);
                SaveLog(errorMessage);
            }
        });
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        // 關閉 root session
        RootUtil.closeRootAccess();
    }

    private void SaveLog(String error) {
        ((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", error));
        String currentDate = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.getDefault()).format(new Date());
        FileUtil.writeFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/Logs/ErrorLogs_" + currentDate + ".txt", error);
        Toast.makeText(getApplicationContext(), "複製錯誤完成\n請回報給開發者", Toast.LENGTH_SHORT).show();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(2);  // 非正常退出碼
    }
}
