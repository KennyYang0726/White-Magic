package com.aoveditor.phantomsneak;

import android.Manifest;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.*;
import android.net.Uri;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.view.View;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.AdView;
import android.os.Looper;

import com.aoveditor.phantomsneak.Utils.FileUtil;
import com.aoveditor.phantomsneak.Utils.SuperUserUtil;
import com.aoveditor.phantomsneak.Utils.ShizukuShellUtil;
import com.aoveditor.phantomsneak.Utils.SAFUtil;
import com.aoveditor.phantomsneak.network.RequestNetwork;
import com.aoveditor.phantomsneak.network.RequestNetworkController;

import rikka.shizuku.Shizuku;


public class VoiceActivity extends AppCompatActivity {

    private Timer _timer = new Timer();
    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();

    private boolean quit = false;
    private String skin_string = "";
    private String lobby_string = "";
    private String other_string = "";
    private String EU_DLC = "";
    private String JP_DLC = "";
    private String EU_Ver = "";
    private String JP_Ver = "";
    public InterstitialAd mInterstitialAd;
    private ProgressDialog prog;

    private ArrayList<HashMap<String, Object>> map1 = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> map2 = new ArrayList<>();
    private final ArrayList<String> JP = new ArrayList<>();
    private final ArrayList<String> EN = new ArrayList<>();
    private Button button1;
    private Button button2;
    private ImageView imageview16;
    private TextView textview9;
    private TextView textview12;
    private ImageView imageview18;
    private TextView textview10;
    private TextView textview13;
    private ImageView imageview23;
    private AdView banner2;
    private ImageView imageview7;
    private ImageView imageview8;
    private ImageView imageview10;
    private ImageView imageview11;

    private Intent page = new Intent();
    private DatabaseReference string_skin = _firebase.getReference("string_skin");
    private ChildEventListener _string_skin_child_listener;
    private RequestNetwork net;
    private RequestNetwork.RequestListener _net_request_listener;
    private AlertDialog.Builder dialog;
    private DatabaseReference DLC = _firebase.getReference("eudlc");
    private ChildEventListener _DLC_child_listener;
    private AlertDialog.Builder delete;
    private AlertDialog.Builder auto_or_not;
    private TimerTask t_internet;

    private ShizukuShellUtil mShizukuShell = null;
    private List<String> mResult = null;


    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.voice);
        initialize(_savedInstanceState);
        FirebaseApp.initializeApp(this);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        } else {
            initializeLogic();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            initializeLogic();
        }
    }

    @Override
    public void onDestroy() {  //非常重要！！！
        super.onDestroy();
        if (ChooseUtilActivity.Method == "Shizuku") {
            if (mResult == null)
                mResult = new ArrayList<>();
            mResult.add("<i></i>");
            mResult.add("aShell: Finish");
            try{
                if (mShizukuShell != null)
                    mShizukuShell.destroy();
            } catch (Exception r) {
            }

        }
    }


    private void initialize(Bundle _savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        imageview16 = findViewById(R.id.imageview16);
        textview9 = findViewById(R.id.textview9);
        textview12 = findViewById(R.id.textview12);
        imageview18 = findViewById(R.id.imageview18);
        textview10 = findViewById(R.id.textview10);
        textview13 = findViewById(R.id.textview13);
        imageview23 = findViewById(R.id.imageview23);
        banner2 = findViewById(R.id.banner2);
        imageview7 = findViewById(R.id.imageview7);
        imageview8 = findViewById(R.id.imageview8);
        imageview10 = findViewById(R.id.imageview10);
        imageview11 = findViewById(R.id.imageview11);
        net = new RequestNetwork(this);
        dialog = new AlertDialog.Builder(this);
        delete = new AlertDialog.Builder(this);
        auto_or_not = new AlertDialog.Builder(this);

        AdRequest adRequest = new AdRequest.Builder().build();
        banner2.loadAd(adRequest);

        InterstitialAd.load(this, getResources().getString(R.string.ad1), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                mInterstitialAd = interstitialAd;
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                mInterstitialAd = null;
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice");
                FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice");
                textview9.setText("當前版本：".concat("窩不知道"));
                textview10.setText("當前版本：".concat("窩不知道"));
                showMessage("刪除成功");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                delete.setTitle("提醒");
                delete.setIcon(R.drawable.downloadlogo);
                delete.setMessage("按下確認後，將還原語音修改\n(需要進入遊戲重新下載語音資源)");
                delete.setCancelable(false);
                delete.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

                            if (ChooseUtilActivity.Method == "SU") {
                                if (SuperUserUtil.haveSU()) {
                                    SuperUserUtil.rmWithSU("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC");
                                    showMessage("還原成功");
                                } else {
                                    showMessage("你已撤銷 root 權限");
                                    MainActivity.haveSU = false;
                                    Intent pageJump = new Intent();
                                    pageJump.setClass(VoiceActivity.this, ChooseUtilActivity.class);
                                    startActivity(pageJump);
                                    finish();
                                    overridePendingTransition(0, 0);
                                }

                            } else if (ChooseUtilActivity.Method == "SAF") {
                                try {
                                    SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC");
                                    showMessage("還原成功");
                                } catch (Exception e) {
                                    showMessage("自動模式還原失敗");
                                    showMessage("請使用MT手動刪除");
                                }

                            } else if (ChooseUtilActivity.Method == "Shizuku") {
                                /**重要，如果關閉服務，需要先判斷ping才能檢測是否granted，否則異常*/
                                if (Shizuku.pingBinder()) { //關閉 shizuku 服務就 ping 不到了
                                    if (!(Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED)) {
                                        showMessage("由於您拒絕Shizuku權限\n請重新選擇存取方式");
                                        Intent pageJump = new Intent();
                                        pageJump.setClass(VoiceActivity.this, ChooseUtilActivity.class);
                                        startActivity(pageJump);
                                        finish();
                                        overridePendingTransition(0, 0);
                                    } else { // 這裡才能執行 shell
                                        if (HomeActivity.CheckPermissionSoundSuShizuku) {
                                            StartInitializeShell("rm -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC");
                                            waitForShizukuCompletion(() ->
                                                    showMessage("還原成功"));
                                        } else {

                                            StartInitializeShell("rm -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC");
                                            waitForShizukuCompletion(() -> {
                                                String result_tmp = mResult.get(mResult.size()-3);
                                                //判斷情況
                                                if (result_tmp.contains("Permission denied") || result_tmp.contains("Operation not permitted")) {
                                                    showMessage("自動模式還原失敗");
                                                } else {
                                                    showMessage("還原成功");
                                                }
                                            });

                                        }

                                    }
                                } else {
                                    showMessage("由於您關閉Shizuku服務\n請重新選擇存取方式");
                                    Intent pageJump = new Intent();
                                    pageJump.setClass(VoiceActivity.this, ChooseUtilActivity.class);
                                    startActivity(pageJump);
                                    finish();
                                    overridePendingTransition(0, 0);
                                }

                            }
                        } else {
                            showMessage("正在還原，請耐心等待至完成");
                            FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC");
                            showMessage("完成");
                        }
                    }
                });
                delete.setNeutralButton("取消", null);
                delete.create().show();
            }
        });

        imageview16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/JP/".concat(JP_Ver))) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

                        LinkStart_R("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/JP/"+(JP_Ver));

                    } else {
                        _unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/JP/".concat(JP_Ver), "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/");
                        showMessage("完成");
                    }
                } else {
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(VoiceActivity.this);
                    }
                    FileUtil.deleteFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/2-voice/JP"));
                    FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/2-voice/JP"));
                    _Download_Plugins("https://".concat(JP_DLC), "Android/data/com.aoveditor.phantomsneak/files/2-voice/", "JP/");
                    showMessage("下載完成再次點擊以啟用");
                }
            }
        });

        imageview18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/EN/".concat(EU_Ver))) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

                        LinkStart_R("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/EN/"+(EU_Ver));

                    } else {
                        _unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/EN/".concat(EU_Ver), "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/");
                        showMessage("完成");
                    }
                } else {
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(VoiceActivity.this);
                    }
                    FileUtil.deleteFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/2-voice/EN"));
                    FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/2-voice/EN"));
                    _Download_Plugins("https://".concat(EU_DLC), "Android/data/com.aoveditor.phantomsneak/files/2-voice/", "EN/");
                    showMessage("下載完成再次點擊以啟用");
                }
            }
        });

        imageview23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/KR_DLC.∠( ᐛ 」∠)＿")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

                        LinkStart_R("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/KR_DLC.∠( ᐛ 」∠)＿");

                    } else {
                        _unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/KR_DLC.∠( ᐛ 」∠)＿", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/");
                        showMessage("完成");
                    }
                } else {
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(VoiceActivity.this);
                    }
                    FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/2-voice"));
                    _Download_Plugins("https://www.dropbox.com/s/lgaf0isyyxrddnn/KR_DLC.%E2%88%A0%28%20%E1%90%9B%20%E3%80%8D%E2%88%A0%29%EF%BC%BF?dl=1", "Android/data/com.aoveditor.phantomsneak/files/2-voice", "/");
                    showMessage("下載完成再次點擊以啟用");
                }
            }
        });

        imageview7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                page.setClass(getApplicationContext(), HomeActivity.class);
                startActivity(page);
                overridePendingTransition(0, 0);
            }
        });

        imageview8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (skin_string.equals("") || internet == 0) {
                    showMessage("初始化中..");
                } else if (skin_string.contains("停用")) {
                    showMessage("無法使用");
                } else if (skin_string.contains("更新")) {
                    showMessage("更新中");
                } else {
                    page.setClass(getApplicationContext(), SkinActivity.class);
                    startActivity(page);
                    overridePendingTransition(0, 0);
                }
            }
        });

        imageview10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (lobby_string.equals("") || internet == 0) {
                    showMessage("初始化中..");
                } else if (lobby_string.contains("停用")) {
                    showMessage("無法使用");
                } else if (lobby_string.contains("更新")) {
                    showMessage("更新中");
                } else {
                    page.setClass(getApplicationContext(), LobbyActivity.class);
                    startActivity(page);
                    overridePendingTransition(0, 0);
                }
            }
        });

        imageview11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (other_string.equals("") || internet == 0) {
                    showMessage("初始化中..");
                } else if (other_string.contains("停用")) {
                    showMessage("無法使用");
                } else if (other_string.contains("更新")) {
                    showMessage("更新中");
                } else {
                    page.setClass(getApplicationContext(), OtherActivity.class);
                    startActivity(page);
                    overridePendingTransition(0, 0);
                }
            }
        });

        _string_skin_child_listener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot _param1, String _param2) {
                string_skin.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot _dataSnapshot) {
                        map1 = new ArrayList<>();
                        try {
                            GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                            for (DataSnapshot _data : _dataSnapshot.getChildren()) {
                                HashMap<String, Object> _map = _data.getValue(_ind);
                                map1.add(_map);
                            }
                        }
                        catch (Exception _e) {
                            _e.printStackTrace();
                        }
                        skin_string = map1.get((int)0).get("1-skin").toString();
                        lobby_string = map1.get((int)0).get("2-lobby").toString();
                        other_string = map1.get((int)0).get("3-other").toString();
                    }
                    @Override
                    public void onCancelled(DatabaseError _databaseError) {
                    }
                });
            }
            @Override
            public void onChildChanged(DataSnapshot _param1, String _param2) {
            }
            @Override
            public void onChildMoved(DataSnapshot _param1, String _param2) {
            }
            @Override
            public void onChildRemoved(DataSnapshot _param1) {
            }
            @Override
            public void onCancelled(DatabaseError _param1) {
            }
        };
        string_skin.addChildEventListener(_string_skin_child_listener);


        _net_request_listener = new RequestNetwork.RequestListener() {
            @Override
            public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
                final String _tag = _param1;
                final String _response = _param2;
                final HashMap<String, Object> _responseHeaders = _param3;
                internet =1;
                t_internet = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                _Internet();
                            }
                        });
                    }
                };
                _timer.schedule(t_internet, (int)(7000));
            }

            @Override
            public void onErrorResponse(String _param1, String _param2) {
                internet = 0;
                dialog.setTitle("錯誤");
                dialog.setMessage("目前網路不可用，請檢查是否連接了可用的Wifi或行動網路");
                dialog.setCancelable(false);
                dialog.setPositiveButton("重試", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {
                        _Internet();
                    }
                });
                dialog.create().show();
            }
        };

        _DLC_child_listener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot _param1, String _param2) {
                DLC.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot _dataSnapshot) {
                        map2 = new ArrayList<>();
                        try {
                            GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                            for (DataSnapshot _data : _dataSnapshot.getChildren()) {
                                HashMap<String, Object> _map = _data.getValue(_ind);
                                map2.add(_map);
                            }
                        }
                        catch (Exception _e) {
                            _e.printStackTrace();
                        }
                        EU_DLC = map2.get((int)0).get("eu").toString();
                        JP_DLC = map2.get((int)0).get("jp").toString();
                        EU_Ver = map2.get((int)0).get("eu_ver").toString();
                        JP_Ver = map2.get((int)0).get("jp_ver").toString();
                        textview12.setText("最新版本：".concat(JP_Ver));
                        textview13.setText("最新版本：".concat(EU_Ver));
                        FileUtil.listDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/JP/", JP);
                        FileUtil.listDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/EN/", EN);
                        if (JP.size() == 0) {
                            textview9.setText("當前版本：".concat("窩不知道"));
                        } else {
                            textview9.setText("當前版本：".concat(Uri.parse(JP.get((int)(0))).getLastPathSegment()));
                        }
                        if (EN.size() == 0) {
                            textview10.setText("當前版本：".concat("窩不知道"));
                        } else {
                            textview10.setText("當前版本：".concat(Uri.parse(EN.get((int)(0))).getLastPathSegment()));
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError _databaseError) {
                    }
                });
            }
            @Override
            public void onChildChanged(DataSnapshot _param1, String _param2) {
            }
            @Override
            public void onChildMoved(DataSnapshot _param1, String _param2) {
            }
            @Override
            public void onChildRemoved(DataSnapshot _param1) {
            }
            @Override
            public void onCancelled(DatabaseError _param1) {
            }
        };
        DLC.addChildEventListener(_DLC_child_listener);
    }

    private void initializeLogic() {

        /** 檢查目錄擁有者，以免不停機 DLC 權限覆蓋 */
        if (ChooseUtilActivity.Method == "Shizuku") {
            StartInitializeShell("ls -lR /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC | awk \'{print $3}\'");
            waitForShizukuCompletion(() -> {
                if (mResult.contains(HomeActivity.AOV_UID)) {
                    /** 如果有檔案的擁有者是傳說 */
                    StartInitializeShell("mkdir /storage/emulated/0/AccessSoundTMP");
                    AlertDialog.Builder 警告 = new AlertDialog.Builder(VoiceActivity.this);
                    警告.setIcon(R.drawable.app_icon_r)
                            .setCancelable(false)
                            .setTitle("警告")
                            .setMessage("Sound_DLC 目錄權限不完全，導致修改時語音部分無法修改。點擊「授權」即可授權目錄。\n這可能需要3分鐘(或更久)的時間。")
                            .setPositiveButton("授權", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    StartInitializeShell("cp -r " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC" + " " + "/storage/emulated/0/AccessSoundTMP/");
                                    waitForShizukuCompletion(() -> {
                                        //複製到本地後的部分，要來重命名原本了
                                        StartInitializeShell("mv " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC" + " " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC_trash0");
                                        waitForShizukuCompletion(() -> {
                                            //複製回去
                                            StartInitializeShell("cp -r " + "/storage/emulated/0/AccessSoundTMP/* /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/");
                                            waitForShizukuCompletion(() -> {
                                                //刪暫存
                                                StartInitializeShell("rm -r " + "/storage/emulated/0/AccessSoundTMP");
                                                waitForShizukuCompletion(() -> {
                                                    showMessage("授權完成~~");
                                                });
                                            });
                                        });
                                    });
                                }
                            })
                            .setNegativeButton("忽略", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    StartInitializeShell("mkdir /storage/emulated/0/AccessSoundTMP");
                                }
                            });
                    警告.create().show();
                }
            });
        }


        if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/3-Voice/jpv.png")) {
            try {
                if (!Objects.equals(FileUtil.getFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/3-Voice/jpv.png", "MD5"), "04F21E26961AE937932EDF6B1D307F0A")) {
                    //校驗失敗，重新下載
                    DLC("https://raw.githubusercontent.com/JamesYang0826/WhiteMagic-Plugins/main/Photos/3-Voice/jpv.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/3-Voice/", imageview16);
                } else {
                    imageview16.setImageBitmap(FileUtil.decodeSampleBitmapFromPath("/data/user/0/com.aoveditor.phantomsneak/files/texture/3-Voice/jpv.png", 1024, 1024));
                }
            } catch (Exception e) {
            }
        } else {
            DLC("https://raw.githubusercontent.com/JamesYang0826/WhiteMagic-Plugins/main/Photos/3-Voice/jpv.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/3-Voice/", imageview16);
        }
        if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/3-Voice/env.png")) {
            try {
                if (!Objects.equals(FileUtil.getFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/3-Voice/env.png", "MD5"), "8287C9CA40EACF277FF4BC35CD119CA5")) {
                    //校驗失敗，重新下載
                    DLC("https://raw.githubusercontent.com/JamesYang0826/WhiteMagic-Plugins/main/Photos/3-Voice/env.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/3-Voice/", imageview18);
                } else {
                    imageview18.setImageBitmap(FileUtil.decodeSampleBitmapFromPath("/data/user/0/com.aoveditor.phantomsneak/files/texture/3-Voice/env.png", 1024, 1024));
                }
            } catch (Exception e) {
            }
        } else {
            DLC("https://raw.githubusercontent.com/JamesYang0826/WhiteMagic-Plugins/main/Photos/3-Voice/env.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/3-Voice/", imageview18);
        }
        if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/3-Voice/krv.png")) {
            try {
                if (!Objects.equals(FileUtil.getFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/3-Voice/krv.png", "MD5"), "A6D227BE3B42F6650C187993FFD21CE7")) {
                    //校驗失敗，重新下載
                    DLC("https://raw.githubusercontent.com/JamesYang0826/WhiteMagic-Plugins/main/Photos/3-Voice/krv.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/3-Voice/", imageview23);
                } else {
                    imageview23.setImageBitmap(FileUtil.decodeSampleBitmapFromPath("/data/user/0/com.aoveditor.phantomsneak/files/texture/3-Voice/krv.png", 1024, 1024));
                }
            } catch (Exception e) {
            }
        } else {
            DLC("https://raw.githubusercontent.com/JamesYang0826/WhiteMagic-Plugins/main/Photos/3-Voice/krv.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/3-Voice/", imageview23);
        }
        FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/2-voice/JP"));
        FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/2-voice/EN"));
        quit = false;
        _Internet();
        /**********
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            AlertDialog.Builder abc = new AlertDialog.Builder(this);
            abc.setTitle("公告")
                    .setMessage("安卓14以上 此部分尚未完成，此為過渡版本，僅開放造型功能使用")
                    .setCancelable(false)
                    .setIcon(R.drawable.app_icon_r)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            page.setClass(getApplicationContext(), HomeActivity.class);
                            startActivity(page);
                            overridePendingTransition(0, 0);
                        }
                    });
            abc.create().show();
        }*/
    }

    @Override
    public void onBackPressed() {
        if (quit == false) {//詢問退出程序
            showMessage("再按一次退出應用");
            new Timer(true).schedule(new TimerTask() {//啟動定時任務
                @Override
                public void run() {
                    quit = false;//重置退出標示
                }
            }, 2000);//2秒後執行run()方法
            quit = true;
        } else {//確認退出應用
            super.onBackPressed();
            finishAffinity();
        }
    }


    public void _Internet() {
        net.startRequestNetwork(RequestNetworkController.GET, "https://1.1.1.1", "", _net_request_listener);
    }


    /*授權次數越多，垃圾越多(Sound_DLC無法存取所以整個會跑進_trash裡面，每次都進去一層)*/
    private void ShizukuAccessSound() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        StartInitializeShell("mkdir /storage/emulated/0/AccessSoundTMP");
        alert.setTitle("啟用不全警告")
                .setIcon(R.drawable.app_icon_r)
                .setCancelable(false)
                .setMessage("Sound_DLC 目錄權限不完全，導致修改時語音部分無法修改。點擊「授權」即可授權目錄。\n這可能需要3分鐘(或更久)的時間。\n※授權完成後須重新啟用剛才的功能。")
                .setPositiveButton("授權", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StartInitializeShell("cp -r " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC" + " " + "/storage/emulated/0/AccessSoundTMP/");
                        waitForShizukuCompletion(() -> {
                            StartInitializeShell("mv " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC" + " " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC_trash0");
                            waitForShizukuCompletion(() -> {
                                StartInitializeShell("cp -r " + "/storage/emulated/0/AccessSoundTMP/* /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/");
                                waitForShizukuCompletion(() -> {
                                    StartInitializeShell("rm -r " + "/storage/emulated/0/AccessSoundTMP");
                                    waitForShizukuCompletion(() -> {
                                        showMessage("授權完畢！");
                                        showMessage("請重新啟用剛才的功能");
                                    });
                                });
                            });
                        });
                    }
                })
                .setNegativeButton("忽略", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StartInitializeShell("rm -r /storage/emulated/0/AccessSoundTMP");
                    }
                });
        alert.create().show();
    }


    public void _unzip(final String _fileZip, final String _destDir) {
        try{
            java.io.File outdir = new java.io.File(_destDir);
            java.util.zip.ZipInputStream zin = new java.util.zip.ZipInputStream(new java.io.FileInputStream(_fileZip));
            java.util.zip.ZipEntry entry;
            String name, dir;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) //重要！！！
                dalvik.system.ZipPathValidator.clearCallback();
            while ((entry = zin.getNextEntry()) != null){

                name = entry.getName();
                if(entry.isDirectory()) {
                    mkdirs(outdir, name);
                    continue;
                }

                /* this part is necessary because file entry can come before
                 * directory entry where is file located
                 * i.e.:
                 * /foo/foo.txt
                 * /foo/
                 */

                dir = dirpart(name);
                if(dir != null)
                    mkdirs(outdir, dir);

                extractFile(zin, outdir, name);
            }
            zin.close();
        }
        catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }
    private static void extractFile(java.util.zip.ZipInputStream in, java.io.File outdir, String name) throws java.io.IOException{
        byte[] buffer = new byte[4096];
        java.io.BufferedOutputStream out = new java.io.BufferedOutputStream(new java.io.FileOutputStream(new java.io.File(outdir, name)));
        int count = -1;
        while ((count = in.read(buffer)) != -1)
            out.write(buffer, 0, count);
        out.close();
    }

    private static void mkdirs(java.io.File outdir, String path){
        java.io.File d = new java.io.File(outdir, path);
        if(!d.exists())
            d.mkdirs();
    }

    private static String dirpart(String name){
        int s = name.lastIndexOf(java.io.File.separatorChar);
        return s == -1 ? null : name.substring(0, s);

    }


    public void _openapp() {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("bin.mt.plus");
        startActivity(launchIntent);
    }


    public void _install_package() {
        try {
            Uri uri = androidx.core.content.FileProvider.getUriForFile(getApplicationContext(), VoiceActivity.this.getPackageName() + ".provider", new java.io.File("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/MT_2.13.0.apk"));
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            startActivity(intent);
        } catch (Exception rr) {
            showMessage (rr.toString());
        }
    }


    private int internet;


    public void DLC(String Url, String Path, ImageView view){
        Runnable updatethread = new Runnable() {
            public void run() {
                try {
                    android.net.ConnectivityManager connMgr = (android.net.ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                    android.net.NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                    Looper.prepare();
                    if (networkInfo != null && networkInfo.isConnected()) {
                        URL url = new URL(Url);
                        HttpURLConnection httpConnection = (HttpURLConnection) (url.openConnection());
                        long completeFileSize = httpConnection.getContentLength();
                        String filename;
                        filename = URLUtil.guessFileName(Url, null, null);
                        java.io.BufferedInputStream in = new java.io.BufferedInputStream(httpConnection.getInputStream());
                        java.io.FileOutputStream fos = new java.io.FileOutputStream(Path + filename);
                        java.io.BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
                        byte[] data = new byte[1024];
                        long downloadedFileSize = 0;
                        int x = 0;
                        while ((x = in.read(data, 0, 1024)) >= 0) {
                            downloadedFileSize += x;
                            // calculate progress
                            final int currentProgress = (int) ((((double)downloadedFileSize) / ((double)completeFileSize)) * 100000d);
                            // update progress bar
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //prog
                                }
                            });
                            bout.write(data, 0, x);
                        }
                        bout.close();
                        in.close();
                        try{
                            File IMG = new File(Path + filename);
                            Bitmap bitmap = BitmapFactory.decodeFile(IMG.getAbsolutePath());
                            view.setImageBitmap(bitmap);
                        } catch (Exception e) {
                        }
                    } else {
                        showMessage("無網際網路連線");
                    }
                    Looper.loop();
                } catch (FileNotFoundException e) {
                    showMessage(e.getMessage());
                } catch (IOException e) {
                    showMessage(e.getMessage());
                }
            }
        };
        new Thread(updatethread).start();
    }


    public void DownloadAndInstallMT(String Url, String Path){
        final ProgressDialog prog = new ProgressDialog(this);
        prog.setIcon(R.drawable.downloadlogo);
        prog.setMax(100);
        prog.setIndeterminate(true);
        prog.setCancelable(false);
        prog.setCanceledOnTouchOutside(false);
        prog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        prog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
        Runnable updatethread = new Runnable() {
            public void run() {
                try {
                    android.net.ConnectivityManager connMgr = (android.net.ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                    android.net.NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                    Looper.prepare();
                    if (networkInfo != null && networkInfo.isConnected()) {
                        URL url = new URL(Url);
                        HttpURLConnection httpConnection = (HttpURLConnection) (url.openConnection());
                        long completeFileSize = httpConnection.getContentLength();
                        String filename;
                        filename = URLUtil.guessFileName(Url, null, null);
                        java.io.BufferedInputStream in = new java.io.BufferedInputStream(httpConnection.getInputStream());
                        java.io.FileOutputStream fos = new java.io.FileOutputStream(Path + filename);
                        java.io.BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
                        byte[] data = new byte[1024];
                        long downloadedFileSize = 0;
                        int x = 0;
                        while ((x = in.read(data, 0, 1024)) >= 0) {
                            downloadedFileSize += x;
                            // calculate progress
                            final int currentProgress = (int) ((((double)downloadedFileSize) / ((double)completeFileSize)) * 100000d);
                            // update progress bar
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    prog.setTitle("正在下載MT管理器");
                                    prog.setMessage("正在為您下載 MT管理器\n下載進度：" + (currentProgress/1000) + "%");
                                    prog.show();
                                }
                            });
                            bout.write(data, 0, x);
                        }
                        bout.close();
                        in.close();
                        prog.dismiss();
                        _install_package();
                    } else {
                        showMessage("無網際網路連線");
                    }
                    Looper.loop();
                } catch (FileNotFoundException e) {
                    showMessage(e.getMessage());
                } catch (IOException e) {
                    showMessage(e.getMessage());
                }
            }
        };
        new Thread(updatethread).start();
    }


    public void showMessage(String _s) {
        Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
    }



    public static abstract class BackgroundTaskClass {
        private Activity activity;
        public BackgroundTaskClass(Context activity) {
            this.activity = (Activity) activity;
        }
        private void startBackground() {
            new Thread(new Runnable() {
                public void run() {
                    doInBackground();
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            onPostExecute();
                        }
                    });
                }
            }).start();
        }
        public void execute(){
            startBackground();
        }
        public abstract void doInBackground();
        public abstract void onPostExecute();
    }
    private void ProgressBar_Show(String title){
        prog = new ProgressDialog(VoiceActivity.this);
        prog.setMax(100);
        prog.setMessage(title);
        prog.setIndeterminate(true);
        prog.setCancelable(false);
        prog.setCanceledOnTouchOutside(false);
        prog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        prog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
        prog.show();
    }
    private void ProgressBar_Dismiss() {
        if (prog != null) {
            prog.dismiss();
        }
    }



    public void _Download_Plugins(final String _url, final String _path, final String _located) {
        try {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(_url));
            request.setMimeType(this.getContentResolver().getType(Uri.parse(_url)));
            String cookies = CookieManager.getInstance().getCookie(_url);
            request.addRequestHeader("cookie", cookies);
            //request.addRequestHeader("User-Agent", tab.getSettings().getUserAgentString());
            request.setDescription("正在下載插件");
            request.setTitle(URLUtil.guessFileName(_url, "", ""));
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                Uri destinationUri = Uri.fromFile(new File("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/" + _located + URLUtil.guessFileName(_url, "", "")));
                request.setDestinationUri(destinationUri);
            } else {
                request.setDestinationInExternalPublicDir(_path.equals("") ? Environment.DIRECTORY_DOWNLOADS : _path + _located, URLUtil.guessFileName(_url, "", ""));
            }
            DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            dm.enqueue(request);
            showMessage("正在下載插件...");
        } catch (Exception e) {
            showMessage(e.toString());
        }
    }



    private void LinkStart_R(String zip_path) {
        auto_or_not.setTitle("提示");
        auto_or_not.setIcon(R.drawable.downloadlogo);
        auto_or_not.setMessage("使用自動模式或手動模式？\n自動模式速度較慢，但無須使用MT管理器手動啟用\n手動模式即使用MT管理器按照教學影片進行啟用");

        if (ChooseUtilActivity.Method == "SU") {
            if (SuperUserUtil.haveSU()) {
                ProgressBar_Show("請稍等...");
                new BackgroundTaskClass(VoiceActivity.this){
                    @Override
                    public void doInBackground() {
                        Looper.prepare();
                        FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/Android");
                        _unzip(zip_path, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/Android/");
                    }
                    @Override
                    public void onPostExecute(){
                        ProgressBar_Dismiss();
                        ProgressBar_Show("啟用中...");
                        new BackgroundTaskClass(VoiceActivity.this){
                            @Override
                            public void doInBackground() {
                                Looper.prepare();
                                //root複製
                                SuperUserUtil.cpWithSU("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/Android", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/");
                            }
                            @Override
                            public void onPostExecute(){
                                ProgressBar_Dismiss();
                                showMessage("完成");
                                FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/Android");
                            }
                        }.execute();
                        //結束
                    }
                }.execute();
                //結束

            } else {
                showMessage("你已撤銷 root 權限");
                MainActivity.haveSU = false;
                Intent pageJump = new Intent();
                pageJump.setClass(VoiceActivity.this, ChooseUtilActivity.class);
                startActivity(pageJump);
                finish();
                overridePendingTransition(0, 0);
            }

        } else if (ChooseUtilActivity.Method == "Shizuku") {
            /**重要，如果關閉服務，需要先判斷ping才能檢測是否granted，否則異常*/
            if (Shizuku.pingBinder()) { //關閉 shizuku 服務就 ping 不到了
                if (!(Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED)) {
                    showMessage("由於您拒絕Shizuku權限\n請重新選擇存取方式");
                    Intent pageJump = new Intent();
                    pageJump.setClass(VoiceActivity.this, ChooseUtilActivity.class);
                    startActivity(pageJump);
                    finish();
                    overridePendingTransition(0, 0);
                } else { // 這裡才能執行 shell

                    ProgressBar_Show("請稍等...");
                    new BackgroundTaskClass(VoiceActivity.this){
                        @Override
                        public void doInBackground() {
                            Looper.prepare();
                            FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/Android");
                            _unzip(zip_path, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/Android/");
                        }
                        @Override
                        public void onPostExecute(){
                            ProgressBar_Dismiss();
                            if (HomeActivity.CheckPermissionSoundSuShizuku) {

                                StartInitializeShell("cp -r /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/Android /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/");
                                waitForShizukuCompletion(() -> {
                                    FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/Android");
                                    showMessage("完成");
                                });

                            } else {

                                StartInitializeShell("cp -r /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/Android/* /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/");
                                waitForShizukuCompletion(() -> {
                                    String result_tmp = mResult.get(mResult.size()-3);
                                    //判斷情況
                                    if (result_tmp.contains("Permission denied") || result_tmp.contains("Operation not permitted")) {
                                        ShizukuAccessSound();
                                    } else {
                                        showMessage("完成");
                                    }
                                    FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/Android");
                                });

                            }
                        }
                    }.execute();
                    //結束

                }
            } else {
                showMessage("由於您關閉Shizuku服務\n請重新選擇存取方式");
                Intent pageJump = new Intent();
                pageJump.setClass(VoiceActivity.this, ChooseUtilActivity.class);
                startActivity(pageJump);
                finish();
                overridePendingTransition(0, 0);
            }

        } else if (ChooseUtilActivity.Method == "SAF") {
            SAF_LINKSTART(zip_path);
        }

    }


    private void SAF_LINKSTART(String zip_path) {

        auto_or_not.setPositiveButton("手動模式", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface _dialog, int _which) {
                try {
                    _openapp();
                    showMessage("請參考YT影片-白魔法1.4.0 影片\n手動進行啟用");
                    FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/MT_2.13.0.apk");
                } catch (Exception e) {
                    showMessage("play商店政策因素\n暫時移除自行安裝功能");
                    showMessage("請自行下載MT管理器後安裝");
                    Intent playstore = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mt2.cn/download/"));
                    startActivity(playstore);
                }
            }
        });
        auto_or_not.setNeutralButton("自動模式", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface _dialog, int _which) {
                ArrayList<String> sound_list0 = new ArrayList<>();
                ArrayList<String> sound_list1 = new ArrayList<>();
                showMessage("啟用過程請勿跳離畫面");
                ProgressBar_Show("請稍等...");
                new BackgroundTaskClass(VoiceActivity.this){
                    @Override
                    public void doInBackground() {
                        Looper.prepare();
                        FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/DLC");
                        _unzip(zip_path, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/DLC/");
                    }
                    @Override
                    public void onPostExecute(){
                        ProgressBar_Dismiss();
                        showMessage("1 / 3 步驟啟用完成");
                        //內層
                        FileUtil.listDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/DLC/Chinese(Taiwan)/", sound_list1);
                        final int[] count1 = {sound_list1.size()};
                        final ProgressDialog prog2 = new ProgressDialog(VoiceActivity.this);
                        prog2.setIcon(R.drawable.downloadlogo);
                        prog2.setMax(100);
                        prog2.setIndeterminate(true);
                        prog2.setCancelable(false);
                        prog2.setCanceledOnTouchOutside(false);
                        prog2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        prog2.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                for(int ii = 0; ii < (int)(sound_list1.size()); ii++) {
                                    SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2F"+Uri.parse(sound_list1.get((count1[0] - 1))).getLastPathSegment());
                                    Uri uri1 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2F");
                                    SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/DLC/Chinese(Taiwan)/"+Uri.parse(sound_list1.get(count1[0] - 1)).getLastPathSegment(), uri1);
                                    count1[0]--;
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            prog2.setTitle("2 / 3 步驟啟用中...");
                                            prog2.setMessage("\n剩餘檔案數量："+count1[0]);
                                            prog2.show();
                                            if (count1[0] == 0) {
                                                prog2.dismiss();
                                                sound_list1.clear();
                                            }
                                        }
                                    });
                                }
                            }
                        }).start();
                        //外層
                        FileUtil.listDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/DLC/", sound_list0);
                        final int[] count0 = {sound_list0.size()};
                        final ProgressDialog prog = new ProgressDialog(VoiceActivity.this);
                        prog.setIcon(R.drawable.downloadlogo);
                        prog.setMax(100);
                        prog.setIndeterminate(true);
                        prog.setCancelable(false);
                        prog.setCanceledOnTouchOutside(false);
                        prog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        prog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                for(int ii = 0; ii < (int)(sound_list0.size()); ii++) {
                                    if (!Uri.parse(sound_list0.get((count0[0] - 1))).getLastPathSegment().equals("Chinese(Taiwan)")){
                                        SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2F"+Uri.parse(sound_list0.get((count0[0] - 1))).getLastPathSegment());
                                        Uri uri0 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2F");
                                        SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/DLC/"+Uri.parse(sound_list0.get(count0[0] - 1)).getLastPathSegment(), uri0);
                                    }
                                    count0[0]--;
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            prog.setTitle("3 / 3 步驟啟用中...");
                                            prog.setMessage("\n剩餘檔案數量："+count0[0]);
                                            prog.show();
                                            if (count0[0] == 0) {
                                                prog.dismiss();
                                                sound_list0.clear();
                                                FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/DLC");
                                                showMessage("完成");
                                            }
                                        }
                                    });
                                }
                            }
                        }).start();

                    }
                }.execute();
                //解壓
            }
        });
        auto_or_not.create().show();

    }


    // Shizuku 服務
    private boolean isShizukuING = false;

    private void waitForShizukuCompletion(Runnable callback) {
        new Handler().postDelayed(() -> {
            if (!isShizukuING) {
                callback.run();
            } else {
                waitForShizukuCompletion(callback);
            }
        }, 100); // 每100毫秒检查一次isShizukuING是否为false
    }


    private void StartInitializeShell(String Command) {
        isShizukuING = true;
        if (mShizukuShell != null && mShizukuShell.isBusy()) {
            mShizukuShell.destroy();
        } else {
            initializeShell(Command);
        }
    }

    private void initializeShell(String mCommand) {
        if (mCommand == null || mCommand.trim().isEmpty()) {
            return;
        }
        runShellCommand(mCommand);
    }

    private void runShellCommand(String command) {

        String finalCommand;

        if (command.startsWith("adb shell ")) {
            finalCommand = command.replace("adb shell ", "");
        } else if (command.startsWith("adb -d shell ")) {
            finalCommand = command.replace("adb -d shell ", "");
        } else {
            finalCommand = command;
        }

        if (finalCommand.equals("clear")) {
            if (mResult != null) {
                mResult.clear();
            }
            return;
        }

        if (finalCommand.startsWith("su")) {
            showMessage("去你的，請勿使用 su 指令");
            return;
        }

        if (mResult == null) {
            mResult = new ArrayList<>();
        }
        mResult.add(finalCommand);

        ExecutorService mExecutors = Executors.newSingleThreadExecutor();

        final ProgressDialog prog = new ProgressDialog(this);
        prog.setIcon(R.drawable.downloadlogo);
        prog.setMax(100);
        prog.setIndeterminate(true);
        prog.setCancelable(false);
        prog.setMessage("請稍後...");
        prog.setCanceledOnTouchOutside(false);
        prog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        prog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);

        prog.show();

        mExecutors.execute(() -> {
            if (Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED) {
                mShizukuShell = new ShizukuShellUtil(mResult, finalCommand);
                mShizukuShell.exec();
                try {
                    TimeUnit.MILLISECONDS.sleep(250);
                } catch (InterruptedException ignored) {}

            } else {
                new MaterialAlertDialogBuilder(this)
                        .setCancelable(false)
                        .setTitle(getString(R.string.app_name))
                        .setMessage("權限請求失敗")
                        .setNegativeButton("結束", (dialogInterface, i) -> finishAffinity())
                        .setPositiveButton("請求", (dialogInterface, i) -> Shizuku.requestPermission(0)
                        ).show();
            }

            new Handler(Looper.getMainLooper()).post(() -> {
                if (!(Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED)) {
                    new MaterialAlertDialogBuilder(this)
                            .setCancelable(false)
                            .setTitle(getString(R.string.app_name))
                            .setMessage("權限請求失敗")
                            .setNegativeButton("結束", (dialogInterface, i) -> finishAffinity())
                            .setPositiveButton("請求", (dialogInterface, i) -> Shizuku.requestPermission(0)
                            ).show();
                }
                if (mResult != null && mResult.size() > 0) {
                    mResult.add("<i></i>");
                    mResult.add("aShell: Finish");
                }
            });

            if (!mExecutors.isShutdown()) {
                mExecutors.shutdown();
                runOnUiThread(() -> {
                    //showMessage("完成");
                    prog.dismiss();
                    isShizukuING = false;
                });
            }

        });
    }

}
