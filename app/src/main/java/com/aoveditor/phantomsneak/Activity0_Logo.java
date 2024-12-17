package com.aoveditor.phantomsneak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import rikka.shizuku.Shizuku;

import com.aoveditor.phantomsneak.NetworkChecker.NetworkChecker;
import com.aoveditor.phantomsneak.Utils.FileUtil;
import com.aoveditor.phantomsneak.Utils.RootUtil;
import com.aoveditor.phantomsneak.AlertDialog.CustomAlertDialog2;



public class Activity0_Logo extends AppCompatActivity {

    /**Components*/
    private Intent page = new Intent();
    private SharedPreferences AppSettings; // 儲存 主題色, SAF授權目錄, AccessMethod, ...
    private NetworkChecker networkChecker;

    /**Variable*/
    private String Last_Ver = "";
    private String Test_Ver = "";
    private String App_Ver = "";
    private String Game_Ver = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity0_logo);
        initialize(savedInstanceState);
        // 請求權限
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                    || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                CustomAlertDialog2.showDialog(
                        this,
                        false,
                        getResources().getString(R.string.Permission_WriteFiles_Title),
                        getResources().getString(R.string.Permission_WriteFiles_Message),
                        getResources().getString(R.string.DialogCancel),
                        getResources().getString(R.string.DialogOK),
                        0,
                        new CustomAlertDialog2.DialogCallback() {
                            @Override
                            public void onResult(boolean isConfirmed) {
                                if (isConfirmed) {
                                    // 使用者按下 OK
                                    ActivityCompat.requestPermissions(Activity0_Logo.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
                                } else {
                                    // 使用者按下 Cancel
                                    showMessage(getResources().getString(R.string.Permission_Denied));
                                    finishAffinity();
                                }
                            }
                        }
                );

            } else {
                // 已有權限，執行 initializeLogic()
                initializeLogic();
            }
        } else {
            // 安卓11以上無需請求
            initializeLogic();
        }
    }
    // 處理權限請求結果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            // 確認是否所有權限都被授權
            boolean allPermissionsGranted = true;
            for (int result : grantResults) {
                if (result == PackageManager.PERMISSION_DENIED) {
                    allPermissionsGranted = false;
                    break;
                }
            }
            if (allPermissionsGranted) {
                // 所有權限被授權，執行 initializeLogic()
                initializeLogic();
            } else {
                // 權限被拒絕，提示訊息並結束應用程式
                showMessage(getResources().getString(R.string.Permission_Denied));
                finishAffinity();
            }
        }
    }
    @Override
    public void onBackPressed() {
        if (false) { // 為了讓這裡沒法按返回退出應用
            super.onBackPressed();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        networkChecker.stopChecking(); // 停止檢查
    }


    private void initialize(Bundle savedInstanceState) {
        // 初始化 NetworkChecker
        networkChecker = new NetworkChecker(this);
        networkChecker.startChecking(); // 開始檢查網路狀態
        // 初始化 sp
        AppSettings = getSharedPreferences("AppSettings", Activity.MODE_PRIVATE);
    }

    @SuppressLint("SdCardPath")
    private void initializeLogic() {
        // sp 初始值設定 主題色
        if (!AppSettings.contains("themePreference")) {
            AppSettings.edit().putString("themePreference", "system").apply();
        }
        if (!AppSettings.contains("AutoMod")) {
            AppSettings.edit().putBoolean("AutoMod", false).apply();
        }
        if (!AppSettings.contains("showInterstitialAdCnt")) {
            AppSettings.edit().putInt("showInterstitialAdCnt", 0).apply();
        }
        // Firebase 初始化
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                PlayIntegrityAppCheckProviderFactory.getInstance());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference Version = database.getReference("version");
        // 取得最新版本
        Version.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Last_Ver = dataSnapshot.child("app/v1").getValue(String.class);
                Test_Ver = dataSnapshot.child("app/v2").getValue(String.class);
                Game_Ver = dataSnapshot.child("game").getValue(String.class);
                // 將最新版本存入 sp
                AppSettings.edit().putString("Latest_Ver", Last_Ver).apply();
                // 遊戲版本放入 sp
                AppSettings.edit().putString("Game_Ver", Game_Ver).apply();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        // 取得當前應用程式版本
        try {
            android.content.pm.PackageInfo pinfo = getPackageManager().getPackageInfo("com.aoveditor.phantomsneak", android.content.pm.PackageManager.GET_ACTIVITIES);
            App_Ver = pinfo.versionName;
            // 將版本號存入 sp
            AppSettings.edit().putString("App_Ver", App_Ver).apply();
        }catch (Exception e){
        }
        // 安卓13以上，要求通知權限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 8964);
            }
        }
        // 創建底層目錄 存放必要檔案
        FileUtil.makeDir("/data/user/0/com.aoveditor.phantomsneak/files/texture");
        FileUtil.makeDir("/data/user/0/com.aoveditor.phantomsneak/files/texture/1-Home");
        FileUtil.makeDir("/data/user/0/com.aoveditor.phantomsneak/files/texture/3-Voice");
        FileUtil.makeDir("/data/user/0/com.aoveditor.phantomsneak/files/texture/4-Lobby");
        FileUtil.makeDir("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other");
        FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()));
        FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext())+"/2-Skin");
        FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext())+"/3-Voice");
        FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext())+"/3-Voice/JP");
        FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext())+"/3-Voice/EN");
        FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext())+"/4-Lobby");
        FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext())+"/5-Other");
        // 3 秒後檢查 version，即可跳轉畫面
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (!Last_Ver.isEmpty()) {
                    if (App_Ver.equals(Last_Ver) || App_Ver.equals(Test_Ver)) {
                        Next_Page();
                    } else {
                        // 跳更新視窗
                        Update_App();
                    }
                } else {
                    // 最新版本取得未成功，重新執行此部分
                    initializeLogic();
                }
            }
        }, 3000);
    }

    // 跳下一頁，判斷是否要選擇存取模式
    private void Next_Page() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!AppSettings.contains("AccessMethod")) {
                // 若沒有紀錄存取模式存取，直接跳轉選擇
                page = new Intent(getApplicationContext(), Activity1_ChooseUtils.class);
                startActivity(page);
                overridePendingTransition(0, 0);
                finish();
            } else {
                // 有紀錄，開始判斷是否遺失權限
                String AccessMethod = AppSettings.getString("AccessMethod", "");
                if (AccessMethod.equals("Root") && !RootUtil.isRootAvailable()) {
                    // 遺失 root 權限，跳轉到選擇畫面
                    page = new Intent(getApplicationContext(), Activity1_ChooseUtils.class);
                    startActivity(page);
                    overridePendingTransition(0, 0);
                    finish();
                } else if (AccessMethod.equals("SAF") && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    // 若是 SAF，檢查安卓版本是否 >= 13，是的話跳選擇
                    page = new Intent(getApplicationContext(), Activity1_ChooseUtils.class);
                    startActivity(page);
                    overridePendingTransition(0, 0);
                    finish();
                } else if (AccessMethod.equals("Shizuku")) {
                    /** 重要，如果關閉服務，需要先判斷ping才能檢測是否granted，否則異常 */
                    if (Shizuku.pingBinder()) { // 關閉 shizuku 服務就 ping 不到了
                        if (Shizuku.checkSelfPermission() != PackageManager.PERMISSION_GRANTED) {
                            // 若當時選擇僅允許一次，會跑到這裡，因為有紀錄方法，也 ping 成功 (服務未關閉)，但沒有權限
                            page = new Intent(getApplicationContext(), Activity1_ChooseUtils.class);
                            startActivity(page);
                            overridePendingTransition(0, 0);
                            finish();
                        } else {
                            // ping 成功，也有權限，跳轉主頁
                            page = new Intent(getApplicationContext(), Activity2_Base.class);
                            startActivity(page);
                            overridePendingTransition(0, 0);
                            finish();
                        }
                    } else {
                        // ping 失敗
                        showMessage(getResources().getString(R.string.PingShizukuErrToast));
                        page = new Intent(getApplicationContext(), Activity1_ChooseUtils.class);
                        startActivity(page);
                        overridePendingTransition(0, 0);
                        finish();
                    }
                } else {
                    // 檢查都沒問題，跳轉主頁
                    page = new Intent(getApplicationContext(), Activity2_Base.class);
                    startActivity(page);
                    overridePendingTransition(0, 0);
                    finish();
                }
            }
        } else {
            // 安卓11以下，直接跳轉主頁
            page = new Intent(getApplicationContext(), Activity2_Base.class);
            startActivity(page);
            overridePendingTransition(0, 0);
            finish();
        }
    }

    // 更新 app
    private void Update_App() {
        final AlertDialog db = new AlertDialog.Builder(Activity0_Logo.this).create();
        LayoutInflater inflater = getLayoutInflater();
        View convertView = inflater.inflate(R.layout.app_update, null);
        db.setView(convertView);
        db.requestWindowFeature(Window.FEATURE_NO_TITLE);
        db.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
        LinearLayout over_ly01 = convertView.findViewById(R.id.over_ly01);
        LinearLayout main_bgd1 = convertView.findViewById(R.id.main_bgd1);
        TextView ttext01 = convertView.findViewById(R.id.ttext01);
        TextView ttext03 = convertView.findViewById(R.id.ttext03);
        ttext03.setText(getResources().getString(R.string.App_Update_Ver) + Last_Ver);
        Button bt01 = convertView.findViewById(R.id.bt01);
        Button bt02 = convertView.findViewById(R.id.bt02);

        db.setCancelable(false);
        ObjectAnimator anim = ObjectAnimator.ofFloat(ttext01, "Alpha", 0, 1);
        anim.setDuration(8000);
        anim.start();
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(bt01, "Alpha", 0, 1);
        anim2.setDuration(2500);
        anim2.start();
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(bt02, "Alpha", 0, 1);
        anim3.setDuration(2500);
        anim3.start();
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(over_ly01, "ScaleY", 0, 1);
        anim.setInterpolator(new BounceInterpolator());
        anim4.setDuration(500);
        anim4.start();
        ObjectAnimator anim5 = ObjectAnimator.ofFloat(ttext03, "Alpha", 0, 1);
        anim5.setDuration(7000);
        anim5.start();
        bt01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                Next_Page();
            }
        });
        bt02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                // 下載 play商店 更新 app
                Intent playstore = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.aoveditor.phantomsneak"));
                startActivity(playstore);
            }
        });
        Shape(20, 20, 20, 20, R.color.app_update_btn, 5, R.color.black, 5, bt01, getApplicationContext());
        Shape(20, 20, 20, 20, R.color.app_update_btn, 5, R.color.black, 5, bt02, getApplicationContext());
        db.show();
    }

    private void Shape(final double _t1, final double _t2, final double _b1, final double _b2, final int _BackgroundRes, final double _Stroke, final int _strokeRes, final double _Elevation, final View _view, Context context) {
        GradientDrawable gs = new GradientDrawable();
        int backgroundColor = ContextCompat.getColor(context, _BackgroundRes);
        int strokeColor = ContextCompat.getColor(context, _strokeRes);
        gs.setColor(backgroundColor);
        gs.setStroke((int)_Stroke, strokeColor);
        gs.setCornerRadii(new float[]{(int)_t1, (int)_t1, (int)_t2, (int)_t2, (int)_b1, (int)_b1, (int)_b2, (int)_b2});
        _view.setBackground(gs);
        _view.setElevation((int)_Elevation);
    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}