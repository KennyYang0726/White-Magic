package com.aoveditor.phantomsneak;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.*;

import com.aoveditor.phantomsneak.Utils.FileUtil;




public class Activity3_Settings extends AppCompatActivity {

    /**Element*/
    private Toolbar _toolbar;
    private Spinner Theme_Spinner;
    private Switch autoApplyPlugin;
    private TextView bug_cnt;
    private Button Sending_Bug;
    private Button button_back_to_white_magic;

    /**Components*/
    private Intent page = new Intent();
    private SharedPreferences AppSettings; // 儲存 主題色, SAF授權目錄, AccessMethod, ...

    /**Variable*/
    private boolean quit = false;
    private final ArrayList<String> files_in_Bug = new ArrayList<>();
    private String app_ver = "";
    private String Device_Info = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_settings);
        initialize(savedInstanceState);
        initializeLogic();
    }
    @Override
    public void onBackPressed() {
        if (!quit) { // 詢問退出程序
            showMessage(getResources().getString(R.string.back_again));
            new Timer(true).schedule(new TimerTask() { // 啟動定時任務
                @Override
                public void run() {
                    quit = false; // 重置退出標示
                }
            }, 2000); // 2秒後執行run()方法
            quit = true;
        } else { // 確認退出應用
            super.onBackPressed();
            finishAffinity();
        }
    }


    private void initialize(Bundle savedInstanceState) {
        // 初始化 sp
        AppSettings = getSharedPreferences("AppSettings", Activity.MODE_PRIVATE);
        // 元件
        _toolbar = findViewById(R.id._toolbar);
        Theme_Spinner = findViewById(R.id.Theme_Spinner);
        autoApplyPlugin = findViewById(R.id.autoApplyPlugin);
        bug_cnt = findViewById(R.id.Bug_Cnt);
        Sending_Bug = findViewById(R.id.Sending_Bug);
        button_back_to_white_magic = findViewById(R.id.button_back_to_white_magic);

        // 設置 Toolbar
        setSupportActionBar(_toolbar);
        // 主題設置 Adapter 建立
        ArrayAdapter<CharSequence> Theme_Adapter = ArrayAdapter.createFromResource(this, R.array.theme_array, android.R.layout.simple_spinner_item);
        Theme_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Theme_Spinner.setAdapter(Theme_Adapter);
        // 設置當前選項
        try {
            String theme = AppSettings.getString("themePreference", "");
            if (theme.contains("light")) {
                Theme_Spinner.setSelection(1, false);
            } else if (theme.contains("dark")) {
                Theme_Spinner.setSelection(2, false);
            } else {
                Theme_Spinner.setSelection(0, false);
            }
        } catch (Exception e) {
            Theme_Spinner.setSelection(0, false);
        }
        // 設置當前開關狀態
        try {
            autoApplyPlugin.setChecked(AppSettings.getBoolean("AutoMod", false));
        } catch (Exception e) {
            autoApplyPlugin.setChecked(false);
        }

        // 事件
        Theme_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    AppSettings.edit().putString("themePreference", "System").apply();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                } else if (position == 1) {
                    AppSettings.edit().putString("themePreference", "light").apply();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else if (position == 2) {
                    AppSettings.edit().putString("themePreference", "dark").apply();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        autoApplyPlugin.setOnCheckedChangeListener((buttonView, isChecked) -> {
            AppSettings.edit().putBoolean("AutoMod", isChecked).apply();
        });
        // Bug 回報按鈕
        Sending_Bug.setOnClickListener(v -> {
            if (files_in_Bug.isEmpty()) {
                showMessage("無可回報的Bug");
            } else {
                // 將路徑分組，每 5 筆為一組
                List<List<String>> groupedPaths = new ArrayList<>();
                int groupSize = 5;
                for (int i = 0; i < files_in_Bug.size(); i += groupSize) {
                    groupedPaths.add(files_in_Bug.subList(i, Math.min(i + groupSize, files_in_Bug.size())));
                }

                // 發送每組訊息，間隔 5 秒
                Handler handler = new Handler();
                int totalGroups = groupedPaths.size(); // 計算總組數
                for (int groupIndex = 0; groupIndex < totalGroups; groupIndex++) {
                    final List<String> group = groupedPaths.get(groupIndex);
                    // 別問為何不整合成一則訊息，因為可能超出長度限制
                    handler.postDelayed(() -> {
                        for (String path : group) {
                            String content =
                                    "App版本：" + app_ver +
                                            "\\n" + Device_Info +
                                            "--------" + "\\n";
                            // 取得檔名
                            File file = new File(path);
                            String fileName = file.getName(); // 例如：ErrorLogs_2024-12-07_21:12:33.txt
                            fileName = fileName.split("\\.")[0]; // ErrorLogs_2024-12-07_21:12:33
                            String dateTime = fileName.substring(10); // 2024-12-07_21:12:33
                            content += dateTime + "\\n```";

                            String file_content = FileUtil.readFile(path);
                            file_content = file_content
                                    .replace("\\", "\\\\")
                                    .replace("\"", "\\\"")
                                    .replace("/", "\\/")
                                    .replace("\b", "\\b")
                                    .replace("\f", "\\f")
                                    .replace("\n", "\\n")
                                    .replace("\r", "\\r")
                                    .replace("\t", "\\t")
                                    .replace("{", "\\\\{")
                                    .replace("}", "\\\\}")
                                    .replace("[", "\\\\[")
                                    .replace("]", "\\\\]");
                            content += file_content + "```\\n--------";
                            sendBugReport(content);
                        }
                        // 如果是最後一組，刪除 Log 目錄，並恢復 UI 狀態
                        if (group == groupedPaths.get(totalGroups - 1)) {
                            // 延遲執行以確保所有檔案都處理完成
                            handler.postDelayed(() -> {
                                FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/Logs"); // 刪除目錄
                                bug_cnt.setText("   0   ");
                                Sending_Bug.setBackgroundResource(R.drawable.custom_alert_dialog_btn_gray);
                                files_in_Bug.clear();
                                showMessage("回報完成！");
                            }, 1000); // 再額外等待 1 秒
                        }
                    }, groupIndex * 5000L); // 每組間隔 5 秒
                }
            }
        });

        button_back_to_white_magic.setOnClickListener(v -> {
            page.setClass(getApplicationContext(), Activity2_Base.class);
            startActivity(page);
            finish();
            overridePendingTransition(0, 0);
        });

    }

    @SuppressLint("SetTextI18n")
    private void initializeLogic() {
        // 取得 app/裝置 資訊
        app_ver = AppSettings.getString("App_Ver", "");
        Device_Info = getDeviceInformation();
        // 設定標題
        setActionBarTitle(getResources().getString(R.string.Settings));
        // 取得 Bug 數量
        files_in_Bug.clear();
        FileUtil.listDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/Logs/", files_in_Bug);
        if (!files_in_Bug.isEmpty()) {
            Sending_Bug.setBackgroundResource(R.drawable.custom_alert_dialog_btn_orange);
            bug_cnt.setText("   " + files_in_Bug.size() + "   ");
        }
    }


    // 設標題
    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    private void sendBugReport(String message) {
        OkHttpClient client = new OkHttpClient();

        // 設定要發送的 JSON 資料
        String json = "{\"message\":\"" + message + "\"}";
        RequestBody body = RequestBody.create(json, MediaType.parse("application/json"));

        // 建立請求
        Request request = new Request.Builder()
                .url("http://140.83.86.0:443/report-bug")
                .post(body)
                .build();

        // 發送請求並處理回應
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showMessage("Bug 回報失敗：" + e.getMessage());
                        Log.e("BugReport", Objects.requireNonNull(e.getMessage()));
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws java.io.IOException {
                if (response.isSuccessful()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // showMessage("Bug 回報成功");
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showMessage("Bug 回報失敗");
                        }
                    });
                }
            }
        });
    }

    private String getDeviceInformation() {
        return "裝置名稱：" + Build.DEVICE + "\\n" +
                // 製造商
                "製造商：" + Build.MANUFACTURER + "\\n" +
                // 型號
                "型號：" + Build.MODEL + "\\n" +
                // 品牌
                "品牌：" + Build.BRAND + "\\n" +
                // 安卓版本
                "Android 版本：" + Build.VERSION.RELEASE + "\\n" +
                // API等級
                "API等級：" + Build.VERSION.SDK_INT + "\\n";
    }


    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}