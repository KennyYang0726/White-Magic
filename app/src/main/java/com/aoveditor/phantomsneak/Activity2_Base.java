package com.aoveditor.phantomsneak;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import com.aoveditor.phantomsneak.NetworkChecker.NetworkChecker;



/** 此 Activity 為底，承載 5 個 Fragment 畫面，此 Activity 作用是這 5 個 Fragment 共通項可於此完成，如：網路檢測，Firebase DB 各功能狀態...等 */
public class Activity2_Base extends AppCompatActivity {

    /**Element*/
    private Toolbar _toolbar;
    private BottomNavigationView bottomNavigationView;

    /**Components*/
    private Intent page = new Intent();
    private NetworkChecker networkChecker;
    private SharedPreferences AppSettings; // 儲存 主題色, SAF授權目錄, AccessMethod, ...
    // 各功能 Fragments
    private Activity2_Fragment1_Home Home = new Activity2_Fragment1_Home();
    private Activity2_Fragment2_Skin Skin = new Activity2_Fragment2_Skin();
    private Activity2_Fragment3_Voice Voice = new Activity2_Fragment3_Voice();
    private Activity2_Fragment4_Lobby Lobby = new Activity2_Fragment4_Lobby();
    private Activity2_Fragment5_Other Others = new Activity2_Fragment5_Other();

    /**Variable*/
    private boolean quit = false;
    private boolean ShowMenu = true; // 僅在主畫面顯示 Menu
    private String AccessMethod = "";
    private String State_Skin = "";
    private String State_Lobby = "";
    private String State_Other = "";

    /** 使選項內Icon與文字並存 */
    private CharSequence menuIconWithText(Drawable r, String title) {
        r.setBounds(0, 0, r.getIntrinsicWidth(), r.getIntrinsicHeight());
        SpannableString sb = new SpannableString("    " + title);
        ImageSpan imageSpan = new ImageSpan(r, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }
    /** 程式中新增MenuItem選項 */
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /** itemId為稍後判斷點擊事件要用的 */
        if (ShowMenu) {
            menu.add(0,0,0,menuIconWithText(Objects.requireNonNull(getDrawable(R.drawable.youtube)),getResources().getString(R.string.YouTube)));
            menu.add(0,1,1,menuIconWithText(Objects.requireNonNull(getDrawable(R.drawable.discord)),getResources().getString(R.string.Discord)));
            menu.add(0,2,2,menuIconWithText(Objects.requireNonNull(getDrawable(R.drawable.github)),getResources().getString(R.string.Github_OpenSources)));
            menu.add(0,3,3,menuIconWithText(Objects.requireNonNull(getDrawable(R.drawable.rate)),getResources().getString(R.string.Rate)));
            menu.add(0,4,4,menuIconWithText(Objects.requireNonNull(getDrawable(R.drawable.more_apps)),getResources().getString(R.string.MoreApps)));
            menu.add(0,5,5,menuIconWithText(Objects.requireNonNull(getDrawable(R.drawable.settings)),getResources().getString(R.string.Settings)));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (Objects.equals(AccessMethod, "SAF"))
                    menu.add(0,6,6,menuIconWithText(Objects.requireNonNull(getDrawable(R.drawable.saf)),getResources().getString(R.string.ChangeAccessMode)));
                if (Objects.equals(AccessMethod, "Root"))
                    menu.add(0,6,6,menuIconWithText(Objects.requireNonNull(getDrawable(R.drawable.superuser)),getResources().getString(R.string.ChangeAccessMode)));
                if (Objects.equals(AccessMethod, "Shizuku"))
                    menu.add(0,6,6,menuIconWithText(Objects.requireNonNull(getDrawable(R.drawable.shizuku)),getResources().getString(R.string.ChangeAccessMode)));
            }
        }
        return super.onCreateOptionsMenu(menu);
    }
    /** 此處為設置點擊事件 */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 0:
                Intent browserintent0 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/channel/UCrBaHnJwilrSZ87hGU4AVfg"));
                startActivity(browserintent0);
                break;
            case 1:
                Intent browserintent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://discord.gg/DkBFVFB6NQ"));
                startActivity(browserintent1);
                break;
            case 2:
                Intent browserintent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/KennyYang0726/White-Magic"));
                startActivity(browserintent2);
                break;
            case 3:
                Intent browserintent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.aoveditor.phantomsneak"));
                startActivity(browserintent3);
                break;
            case 4:
                Intent browserintent4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/KennyYang0726/"));
                startActivity(browserintent4);
                break;
            case 5:
                page.setClass(getApplicationContext(), Activity3_Settings.class);
                startActivity(page);
                finish();
                overridePendingTransition(0, 0);
                break;
            case 6:
                page.setClass(getApplicationContext(), Activity1_ChooseUtils.class);
                startActivity(page);
                finish();
                overridePendingTransition(0, 0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_base);
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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        networkChecker.stopChecking(); // 停止檢查
    }


    private void initialize(Bundle savedInstanceState) {
        // 螢幕長亮
        // getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        // 初始化 NetworkChecker
        networkChecker = new NetworkChecker(this);
        networkChecker.startChecking(); // 開始檢查網路狀態
        // 初始化 sp
        AppSettings = getSharedPreferences("AppSettings", Activity.MODE_PRIVATE);
        // Firebase 初始化 各項功能
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference State = database.getReference("state");
        State.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                State_Skin = dataSnapshot.child("1-skin").getValue(String.class);
                State_Lobby = dataSnapshot.child("2-lobby").getValue(String.class);
                State_Other = dataSnapshot.child("3-other").getValue(String.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        // 元件
        _toolbar = findViewById(R.id._toolbar);
        bottomNavigationView = findViewById(R.id.bottomnavigation);
        setSupportActionBar(_toolbar);
        // 將 Base 的 Frame 替換為 Home 的 Fragment (首頁)
        switchToFragment(Home, getResources().getString(R.string.Home), true);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        switchToFragment(Home, getResources().getString(R.string.Home), true);
                        return true;
                    case R.id.skin:
                        if (State_Skin.isEmpty()) {
                            showMessage(getResources().getString(R.string.Initializing));
                            return false;
                        } else if (State_Skin.equals("停用")) {
                            showMessage(getResources().getString(R.string.Disabled));
                            return false;
                        } else if (State_Skin.equals("更新")) {
                            showMessage(getResources().getString(R.string.Updating));
                            return false;
                        } else {
                            switchToFragment(Skin, getResources().getString(R.string.Skin), false);
                            return true;
                        }
                    case R.id.voice:
                        switchToFragment(Voice, getResources().getString(R.string.Voice), false);
                        return true;
                    case R.id.lobby:
                        if (State_Lobby.isEmpty()) {
                            showMessage(getResources().getString(R.string.Initializing));
                            return false;
                        } else if (State_Lobby.equals("停用")) {
                            showMessage(getResources().getString(R.string.Disabled));
                            return false;
                        } else if (State_Lobby.equals("更新")) {
                            showMessage(getResources().getString(R.string.Updating));
                            return false;
                        } else {
                            switchToFragment(Lobby, getResources().getString(R.string.Lobby), false);
                            return true;
                        }
                    case R.id.other:
                        if (State_Other.isEmpty()) {
                            showMessage(getResources().getString(R.string.Initializing));
                            return false;
                        } else if (State_Other.equals("停用")) {
                            showMessage(getResources().getString(R.string.Disabled));
                            return false;
                        } else if (State_Other.equals("更新")) {
                            showMessage(getResources().getString(R.string.Updating));
                            return false;
                        } else {
                            switchToFragment(Others, getResources().getString(R.string.Others), false);
                            return true;
                        }
                }
                return false;
            }
        });
    }

    private void initializeLogic() {
        AccessMethod = AppSettings.getString("AccessMethod", "");
    }

    // 切換畫面
    private void switchToFragment(Fragment fragment, String title, boolean showMenu) {
        ShowMenu = showMenu;
        setActionBarTitle(title);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    // 設標題
    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}