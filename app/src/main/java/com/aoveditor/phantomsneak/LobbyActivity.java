package com.aoveditor.phantomsneak;

import android.Manifest;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.*;
import android.util.Log;
import android.view.View;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.interstitial.InterstitialAd;
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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import com.aoveditor.phantomsneak.Utils.FileUtil;
import com.aoveditor.phantomsneak.Utils.SuperUserUtil;
import com.aoveditor.phantomsneak.Utils.ShizukuShellUtil;
import com.aoveditor.phantomsneak.Utils.SAFUtil;
import com.aoveditor.phantomsneak.network.RequestNetwork;
import com.aoveditor.phantomsneak.network.RequestNetworkController;

import rikka.shizuku.Shizuku;


public class LobbyActivity extends AppCompatActivity {
    /**Sound 在 firebase後檢查，movie在啟用後檢查*/

    private Timer _timer = new Timer();
    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();

    private boolean quit = false;
    private String skin_string = "";
    private String other_string = "";
    private String movie = "";
    private String sound = "";
    private Uri uri2;
    private String openquiz = "";
    private String quiz_content = "";
    private String quiz_url = "";
    private String Main_Theme1 = "";
    private String Main_Theme2 = "";
    private String Main_Theme3 = "";
    private String Main_Theme4 = "";
    private String Main_Theme5 = "";
    private String Main_Theme6 = "";
    private String Main_Theme7 = "";
    private String Main_Theme8 = "";
    private String Main_Theme9 = "";
    private String Main_Theme10 = "";
    private String Main_Theme11 = "";
    private String Main_Theme12 = "";
    private String Other_Theme1 = "";
    private String Other_Theme1_URL = "";
    private String Other_Theme2 = "";
    private String Other_Theme2_URL = "";
    private String Other_Theme3 = "";
    private String Other_Theme3_URL = "";
    private String Other_Theme4 = "";
    private String Other_Theme4_URL = "";
    private String Other_Theme5 = "";
    private String Other_Theme5_URL = "";

    private ArrayList<HashMap<String, Object>> map1 = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> map2 = new ArrayList<>();
    private Button button1;
    private Button button2;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button10;
    private Button button11;
    private Button button12;
    private Button button13;
    private Button button14;
    private Button button15;
    private Button button16;
    private Button button17;
    private Button button18;
    private Button button19;
    private Button button20;
    private Button button21;
    private Button button22;
    private Button button23;
    private AdView banner3;
    public InterstitialAd mInterstitialAd;
    private ImageView imageview7;
    private ImageView imageview8;
    private ImageView imageview9;
    private ImageView imageview11;

    private Intent page = new Intent();
    private RequestNetwork net;
    private RequestNetwork.RequestListener _net_request_listener;
    private AlertDialog.Builder dialog;
    private DatabaseReference string_skin = _firebase.getReference("string_skin");
    private ChildEventListener _string_skin_child_listener;
    private AlertDialog.Builder delete;
    private DatabaseReference lobby = _firebase.getReference("Lobby");
    private ChildEventListener _lobby_child_listener;
    private TimerTask t_delay;
    private AlertDialog.Builder quiz;
    private TimerTask t_internet;

    private ShizukuShellUtil mShizukuShell = null;
    private List<String> mResult = null;


    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.lobby);
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
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button10 = findViewById(R.id.button10);
        button11 = findViewById(R.id.button11);
        button12 = findViewById(R.id.button12);
        button13 = findViewById(R.id.button13);
        button14 = findViewById(R.id.button14);
        button15 = findViewById(R.id.button15);
        button16 = findViewById(R.id.button16);
        button17 = findViewById(R.id.button17);
        button18 = findViewById(R.id.button18);
        button19 = findViewById(R.id.button19);
        button20 = findViewById(R.id.button20);
        button21 = findViewById(R.id.button21);
        button22 = findViewById(R.id.button22);
        button23 = findViewById(R.id.button23);
        banner3 = findViewById(R.id.banner3);
        imageview7 = findViewById(R.id.imageview7);
        imageview8 = findViewById(R.id.imageview8);
        imageview9 = findViewById(R.id.imageview9);
        imageview11 = findViewById(R.id.imageview11);
        net = new RequestNetwork(this);
        dialog = new AlertDialog.Builder(this);
        delete = new AlertDialog.Builder(this);
        quiz = new AlertDialog.Builder(this);

        AdRequest adRequest = new AdRequest.Builder().build();
        banner3.loadAd(adRequest);
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
                FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby");
                FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby");
                showMessage("刪除成功");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                delete.setTitle("提示");
                delete.setIcon(R.drawable.downloadlogo);
                delete.setMessage("此按鈕用於還原大廳修改\n按下確認將還原，並且須下載一小部分大廳資源");
                delete.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

                            if (ChooseUtilActivity.Method == "SU") {
                                if (SuperUserUtil.haveSU()) {
                                    SuperUserUtil.rmWithSU("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/ISPDiff/LobbyMovie/".concat(movie));
                                    SuperUserUtil.rmWithSU("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/".concat(sound));
                                    showMessage("還原成功");

                                } else {
                                    showMessage("你已撤銷 root 權限");
                                    MainActivity.haveSU = false;
                                    Intent pageJump = new Intent();
                                    pageJump.setClass(LobbyActivity.this, ChooseUtilActivity.class);
                                    startActivity(pageJump);
                                    finish();
                                    overridePendingTransition(0, 0);
                                }

                            } else if (ChooseUtilActivity.Method == "SAF") {
                                SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2F".concat(sound));
                                SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FISPDiff%2FLobbyMovie%2F".concat(movie));
                                showMessage("還原成功");

                            } if (ChooseUtilActivity.Method == "Shizuku") {
                                /**重要，如果關閉服務，需要先判斷ping才能檢測是否granted，否則異常*/
                                if (Shizuku.pingBinder()) { //關閉 shizuku 服務就 ping 不到了
                                    if (!(Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED)) {
                                        showMessage("由於您拒絕Shizuku權限\n請重新選擇存取方式");
                                        Intent pageJump = new Intent();
                                        pageJump.setClass(LobbyActivity.this, ChooseUtilActivity.class);
                                        startActivity(pageJump);
                                        finish();
                                        overridePendingTransition(0, 0);
                                    } else { // 這裡才能執行 shell

                                        StartInitializeShell("rm /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/".concat(sound));
                                        waitForShizukuCompletion(() ->
                                                StartInitializeShell("rm /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/ISPDiff/LobbyMovie/".concat(movie)));
                                        showMessage("還原成功");
                                    }
                                } else {
                                    showMessage("由於您關閉Shizuku服務\n請重新選擇存取方式");
                                    Intent pageJump = new Intent();
                                    pageJump.setClass(LobbyActivity.this, ChooseUtilActivity.class);
                                    startActivity(pageJump);
                                    finish();
                                    overridePendingTransition(0, 0);
                                }
                            }

                        } else {
                            FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/ISPDiff/LobbyMovie/".concat(movie));
                            FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/".concat(sound));
                            showMessage("還原成功");
                        }
                    }
                });
                delete.setNeutralButton("取消", null);
                delete.create().show();
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/DaoGen1.PS")) {
                    _LinkStart("DaoGen1.PS");
                } else {
                    DownloadHttpUrlConnection(Main_Theme1, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", "DaoGen1.PS");
                    showMessage("下載完成再次點擊以啟用");
                }
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/DaoGen2.PS")) {
                    _LinkStart("DaoGen2.PS");
                } else {
                    DownloadHttpUrlConnection(Main_Theme2, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", "DaoGen2.PS");
                    showMessage("下載完成再次點擊以啟用");
                }
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/Nakaroth.PS")) {
                    _LinkStart("Nakaroth.PS");
                } else {
                    DownloadHttpUrlConnection(Main_Theme3, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", "Nakaroth.PS");
                    showMessage("下載完成再次點擊以啟用");
                }
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/WAVE.PS")) {
                    _LinkStart("WAVE.PS");
                } else {
                    DownloadHttpUrlConnection(Main_Theme4, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", "WAVE.PS");
                    showMessage("下載完成再次點擊以啟用");
                }
            }
        });

        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/newyear2019.PS")) {
                    _LinkStart("newyear2019.PS");
                } else {
                    DownloadHttpUrlConnection(Main_Theme5, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", "newyear2019.PS");
                    showMessage("下載完成再次點擊以啟用");
                }
            }
        });

        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/newyear2021.PS")) {
                    _LinkStart("newyear2021.PS");
                } else {
                    DownloadHttpUrlConnection(Main_Theme6, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", "newyear2021.PS");
                    showMessage("下載完成再次點擊以啟用");
                }
            }
        });

        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/newyear2022.PS")) {
                    _LinkStart("newyear2022.PS");
                } else {
                    DownloadHttpUrlConnection(Main_Theme7, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", "newyear2022.PS");
                    showMessage("下載完成再次點擊以啟用");
                }
            }
        });

        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/AIC2020.PS")) {
                    _LinkStart("AIC2020.PS");
                } else {
                    DownloadHttpUrlConnection(Main_Theme8, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", "AIC2020.PS");
                    showMessage("下載完成再次點擊以啟用");
                }
            }
        });

        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/Ultraman.PS")) {
                    _LinkStart("Ultraman.PS");
                } else {
                    DownloadHttpUrlConnection(Main_Theme9, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", "Ultraman.PS");
                    showMessage("下載完成再次點擊以啟用");
                }
            }
        });

        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/TiLi.PS")) {
                    _LinkStart("TiLi.PS");
                } else {
                    DownloadHttpUrlConnection(Main_Theme10, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", "TiLi.PS");
                    showMessage("下載完成再次點擊以啟用");
                }
            }
        });

        button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/PoolParty.PS")) {
                    _LinkStart("PoolParty.PS");
                } else {
                    DownloadHttpUrlConnection(Main_Theme11, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", "PoolParty.PS");
                    showMessage("下載完成再次點擊以啟用");
                }
            }
        });

        button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/SuLi.PS")) {
                    _LinkStart("SuLi.PS");
                } else {
                    DownloadHttpUrlConnection(Main_Theme12, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", "SuLi.PS");
                    showMessage("下載完成再次點擊以啟用");
                }
            }
        });

        button19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (Other_Theme1.contains("敬請期待")) {
                    showMessage("敬請期待");
                } else {
                    if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/" + Other_Theme1 + ".PS")) {
                        _LinkStart(Other_Theme1 + ".PS");
                    } else {
                        DownloadHttpUrlConnection(Other_Theme1_URL, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", Other_Theme1 + ".PS");
                        showMessage("下載完成再次點擊以啟用");
                    }
                }
            }
        });

        button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (Other_Theme2.contains("敬請期待")) {
                    showMessage("敬請期待");
                } else {
                    if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/" + Other_Theme2 + ".PS")) {
                        _LinkStart(Other_Theme2 + ".PS");
                    } else {
                        DownloadHttpUrlConnection(Other_Theme2_URL, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", Other_Theme2 + ".PS");
                        showMessage("下載完成再次點擊以啟用");
                    }
                }
            }
        });

        button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (Other_Theme3.contains("敬請期待")) {
                    showMessage("敬請期待");
                } else {
                    if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/" + Other_Theme3 + ".PS")) {
                        _LinkStart(Other_Theme3 + ".PS");
                    } else {
                        DownloadHttpUrlConnection(Other_Theme3_URL, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", Other_Theme3 + ".PS");
                        showMessage("下載完成再次點擊以啟用");
                    }
                }
            }
        });

        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (Other_Theme4.contains("敬請期待")) {
                    showMessage("敬請期待");
                } else {
                    if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/" + Other_Theme4 + ".PS")) {
                        _LinkStart(Other_Theme4 + ".PS");
                    } else {
                        DownloadHttpUrlConnection(Other_Theme4_URL, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", Other_Theme4 + ".PS");
                        showMessage("下載完成再次點擊以啟用");
                    }
                }
            }
        });

        button23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (Other_Theme5.contains("敬請期待")) {
                    showMessage("敬請期待");
                } else {
                    if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/" + Other_Theme5 + ".PS")) {
                        _LinkStart(Other_Theme5 + ".PS");
                    } else {
                        DownloadHttpUrlConnection(Other_Theme5_URL, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", Other_Theme5 + ".PS");
                        showMessage("下載完成再次點擊以啟用");
                    }
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

        imageview9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                page.setClass(getApplicationContext(), VoiceActivity.class);
                startActivity(page);
                overridePendingTransition(0, 0);
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

        _net_request_listener = new RequestNetwork.RequestListener() {
            @Override
            public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
                final String _tag = _param1;
                final String _response = _param2;
                final HashMap<String, Object> _responseHeaders = _param3;
                internet = 1;
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
                final String _tag = _param1;
                final String _message = _param2;
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

        _string_skin_child_listener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot _param1, String _param2) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);
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
                        other_string = map1.get((int)0).get("3-other").toString();
                    }
                    @Override
                    public void onCancelled(DatabaseError _databaseError) {
                    }
                });
            }

            @Override
            public void onChildChanged(DataSnapshot _param1, String _param2) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);

            }

            @Override
            public void onChildMoved(DataSnapshot _param1, String _param2) {
            }

            @Override
            public void onChildRemoved(DataSnapshot _param1) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);
            }

            @Override
            public void onCancelled(DatabaseError _param1) {
                final int _errorCode = _param1.getCode();
                final String _errorMessage = _param1.getMessage();
            }
        };
        string_skin.addChildEventListener(_string_skin_child_listener);

        _lobby_child_listener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot _param1, String _param2) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);
                lobby.addListenerForSingleValueEvent(new ValueEventListener() {
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
                        quiz_url = map2.get((int)0).get("quiz_url").toString();
                        quiz_content = map2.get((int)0).get("quiz_content").toString();
                        openquiz = map2.get((int)0).get("openquiz").toString();
                        movie = map2.get((int)0).get("delete_movie").toString();
                        sound = map2.get((int)0).get("delete_sound").toString();
                        Main_Theme1 = map2.get((int)0).get("1").toString();
                        Main_Theme2 = map2.get((int)0).get("2").toString();
                        Main_Theme3 = map2.get((int)0).get("3").toString();
                        Main_Theme4 = map2.get((int)0).get("4").toString();
                        Main_Theme5 = map2.get((int)0).get("5").toString();
                        Main_Theme6 = map2.get((int)0).get("6").toString();
                        Main_Theme7 = map2.get((int)0).get("7").toString();
                        Main_Theme8 = map2.get((int)0).get("8").toString();
                        Main_Theme9 = map2.get((int)0).get("9").toString();
                        Main_Theme10 = map2.get((int)0).get("10").toString();
                        Main_Theme11 = map2.get((int)0).get("11").toString();
                        Main_Theme12 = map2.get((int)0).get("12").toString();
                        Other_Theme1 = map2.get((int)0).get("Other1").toString();
                        Other_Theme1_URL = map2.get((int)0).get("Other1_URL").toString();
                        Other_Theme2 = map2.get((int)0).get("Other2").toString();
                        Other_Theme2_URL = map2.get((int)0).get("Other2_URL").toString();
                        Other_Theme3 = map2.get((int)0).get("Other3").toString();
                        Other_Theme3_URL = map2.get((int)0).get("Other3_URL").toString();
                        Other_Theme4 = map2.get((int)0).get("Other4").toString();
                        Other_Theme4_URL = map2.get((int)0).get("Other4_URL").toString();
                        Other_Theme5 = map2.get((int)0).get("Other5").toString();
                        Other_Theme5_URL = map2.get((int)0).get("Other5_URL").toString();
                        button19.setText(Other_Theme1);
                        button20.setText(Other_Theme2);
                        button21.setText(Other_Theme3);
                        button22.setText(Other_Theme4);
                        button23.setText(Other_Theme5);
                        if (openquiz.equals("1")) {
                            quiz.setTitle("問卷");
                            quiz.setIcon(R.drawable.downloadlogo);
                            quiz.setMessage(quiz_content);
                            quiz.setPositiveButton("前往", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface _dialog, int _which) {
                                    Intent browserintent = new Intent(Intent.ACTION_VIEW, Uri.parse(quiz_url));
                                    startActivity(browserintent);
                                }
                            });
                            quiz.setNeutralButton("拒絕", null);
                            quiz.create().show();
                        }

                        /********************* 檢查Sound擁有者 */
                        if (ChooseUtilActivity.Method == "Shizuku") {
                            StartInitializeShell("ls -l /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC" + " | awk \'{print $3}\'");
                            waitForShizukuCompletion(() -> {
                                String result_tmp0 = mResult.get(mResult.size() - 3);
                                if (result_tmp0.contains("or directory")) {
                                    /** 無 Sound_DLC 目錄 */
                                    StartInitializeShell("mkdir -p /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android && touch /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/" + sound);
                                } else if (result_tmp0.contains(HomeActivity.AOV_UID)) {
                                    /** Sound_DLC/Android 目錄存在但目錄本身沒權限 */
                                    StartInitializeShell("mkdir /storage/emulated/0/AccessSoundTMP");
                                    waitForShizukuCompletion(() -> {
                                        //複製到本地
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
                                                    waitForShizukuCompletion(() -> LSSound());
                                                });
                                            });
                                        });
                                    });
                                } else {
                                    /** Sound_DLC/Android 目錄存在，開始檢查 sound 權限 */
                                    LSSound();
                                }
                            });
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
        lobby.addChildEventListener(_lobby_child_listener);
    }


    private int getRandom(int _min, int _max) {
        Random random = new Random();
        return random.nextInt(_max - _min + 1) + _min;
    }

    /**LS Sound*/
    private void LSSound() {
        StartInitializeShell("ls -l /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/" + sound + " | awk \'{print $3}\'");
        waitForShizukuCompletion(() -> {
            String result_tmp = mResult.get(mResult.size()-3);
            if (result_tmp.contains("or directory")) {
                /**沒sound*/
                StartInitializeShell("touch /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/" + sound);
            } else if (result_tmp.contains(HomeActivity.AOV_UID)) {
                /**權限不足 -> 重命名 Sound 本身   通常是 目錄已有權限，按還原後，讓遊戲下載進來，影片本身沒權限*/
                String letter1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
                String letter2 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
                String letter3 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
                int cc1 = getRandom((int)(0), (int)(61));
                int cc2 = getRandom((int)(0), (int)(61));
                int cc3 = getRandom((int)(0), (int)(61));
                String str_tmp1 = (letter1.substring(cc1, cc1+1)+letter2.substring(cc2, cc2+1)+letter3.substring(cc3, cc3+1));
                StartInitializeShell("mv /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/" + sound + " /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/" + sound + "_" + str_tmp1 + "&& touch /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/" + sound);
            }
        });
    }

    private String result_tmp;
    private void MovieDenied() {
        String letter1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String letter2 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String letter3 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int cc1 = getRandom((int)(0), (int)(61));
        int cc2 = getRandom((int)(0), (int)(61));
        int cc3 = getRandom((int)(0), (int)(61));
        String str_tmp1 = (letter1.substring(cc1, cc1+1)+letter2.substring(cc2, cc2+1)+letter3.substring(cc3, cc3+1));
        //為了確定影片檔存在與否
        StartInitializeShell("mkdir /storage/emulated/0/AccessMovieTMP && mv /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/ISPDiff/LobbyMovie/" + movie + " /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/ISPDiff/LobbyMovie/" + movie + "_" + str_tmp1);
        waitForShizukuCompletion(() -> {
            result_tmp = mResult.get(mResult.size()-3);
            // 前2 影片存在且無法重命名，即目錄也是傳說的，最後影片不存在且目錄為傳說的 (目錄要是shell早就不會跑來 MovieDenied)
            Log.d("asdfgh", result_tmp);
            if (result_tmp.contains("Permission denied") || result_tmp.contains("Operation not permitted") || result_tmp.contains("or directory")) {
                StartInitializeShell("cp -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/ISPDiff /storage/emulated/0/AccessMovieTMP/");
                waitForShizukuCompletion(() -> {
                    StartInitializeShell("mv /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/ISPDiff /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/ISPDiff_trash0");
                    waitForShizukuCompletion(() -> {
                        StartInitializeShell("cp -r /storage/emulated/0/AccessMovieTMP/* /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/");
                        waitForShizukuCompletion(() -> {
                            StartInitializeShell("rm -r " + "/storage/emulated/0/AccessMovieTMP");
                            waitForShizukuCompletion(() -> {
                                showMessage("完成\n請重新啟用修改功能");
                            });
                        });
                    });
                });
            } else {
                //刪暫存
                StartInitializeShell("rm -r " + "/storage/emulated/0/AccessMovieTMP");
                showMessage("完成\n請重新啟用修改功能");
            }
        });
    }


    private void initializeLogic() {
        if (!FileUtil.isExistFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/3-lobby"))) {
            FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/3-lobby"));
        }
        quit = false;
        _Internet();
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


    public void _unzip(final String _fileZip, final String _destDir) {
        try {
            java.io.File outdir = new java.io.File(_destDir);
            java.util.zip.ZipInputStream zin = new java.util.zip.ZipInputStream(new java.io.FileInputStream(_fileZip));
            java.util.zip.ZipEntry entry;
            String name, dir;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) //重要！！！
                dalvik.system.ZipPathValidator.clearCallback();
            while ((entry = zin.getNextEntry()) != null) {
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
    private static void extractFile(java.util.zip.ZipInputStream in, java.io.File outdir, String name) throws java.io.IOException {
        byte[] buffer = new byte[4096];
        java.io.BufferedOutputStream out = new java.io.BufferedOutputStream(new java.io.FileOutputStream(new java.io.File(outdir, name)));
        int count = -1;
        while ((count = in.read(buffer)) != -1)
            out.write(buffer, 0, count);
        out.close();
    }

    private static void mkdirs(java.io.File outdir, String path) {
        java.io.File d = new java.io.File(outdir, path);
        if(!d.exists())
            d.mkdirs();
    }

    private static String dirpart(String name) {
        int s = name.lastIndexOf(java.io.File.separatorChar);
        return s == -1 ? null : name.substring(0, s);
    }


    private int internet;
    public void DownloadHttpUrlConnection(String Url, String Path, String FileName){
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
                        if (FileName == ""){
                            filename = URLUtil.guessFileName(Url, null, null);
                        } else {
                            filename = FileName;
                        }
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
                                    prog.setTitle("下載中...");
                                    prog.setMessage("正在下載 " + filename + "\n下載進度 - " + (currentProgress/1000) + "%");
                                    prog.show();
                                }
                            });
                            bout.write(data, 0, x);
                        }
                        bout.close();
                        in.close();
                        prog.dismiss();
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


    private void loadAds() {
        AdRequest adRequest2 = new AdRequest.Builder().build();
        InterstitialAd.load(this, getResources().getString(R.string.ad1), adRequest2, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                mInterstitialAd = interstitialAd;
            }
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                mInterstitialAd = null;
            }
        });
    }


    public void _LinkStart(final String _fileName) {
        //增加一點廣告收入XDD
        if (!FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/lb_adload_cnt.ini")) { //不存在數數ini
            if (mInterstitialAd != null) {
                mInterstitialAd.show(LobbyActivity.this);
            }
            FileUtil.writeFile("/data/user/0/com.aoveditor.phantomsneak/lb_adload_cnt.ini", "1");
        } else {
            try {
                int cnt = Integer.parseInt(FileUtil.readFile("/data/user/0/com.aoveditor.phantomsneak/lb_adload_cnt.ini"));
                if ((int)(cnt%3) == 0) {
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(LobbyActivity.this);
                    }
                }
                cnt++;
                FileUtil.writeFile("/data/user/0/com.aoveditor.phantomsneak/lb_adload_cnt.ini", String.valueOf(cnt));
            } catch (Exception e) { //被篡改直接跳廣告
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(LobbyActivity.this);
                }
            }
        }
        loadAds();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/tmp");
            _unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/".concat(_fileName), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/tmp/");
            t_delay = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            ArrayList<String> Movie_Name = new ArrayList<>();
                            FileUtil.listDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/tmp/ISPDiff/LobbyMovie/", Movie_Name);
                            if (!Objects.equals(Uri.parse(Movie_Name.get((int) (0))).getLastPathSegment(), movie)) {
                                showMessage("啟用失敗");
                                showMessage("請刪除插件後重試");
                            } else {

                                if (ChooseUtilActivity.Method == "SU") {
                                    if (SuperUserUtil.haveSU()) {
                                        SuperUserUtil.cpWithSU("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/tmp/*", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/");
                                        showMessage("啟用成功");
                                        FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/tmp");

                                    } else {
                                        showMessage("你已撤銷 root 權限");
                                        MainActivity.haveSU = false;
                                        Intent pageJump = new Intent();
                                        pageJump.setClass(LobbyActivity.this, ChooseUtilActivity.class);
                                        startActivity(pageJump);
                                        finish();
                                        overridePendingTransition(0, 0);
                                    }

                                } else if (ChooseUtilActivity.Method == "SAF") {
                                    SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2F".concat(sound));
                                    uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2F");
                                    SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/tmp/Sound_DLC/Android/".concat(sound), uri2);
                                    SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FISPDiff%2FLobbyMovie%2F".concat(movie));
                                    uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FISPDiff%2FLobbyMovie%2F");
                                    SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/tmp/ISPDiff/LobbyMovie/".concat(movie), uri2);
                                    showMessage("啟用成功");
                                    FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/tmp");

                                } else if (ChooseUtilActivity.Method == "Shizuku") {
                                    /**重要，如果關閉服務，需要先判斷ping才能檢測是否granted，否則異常*/
                                    if (Shizuku.pingBinder()) { //關閉 shizuku 服務就 ping 不到了
                                        if (!(Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED)) {
                                            showMessage("由於您拒絕Shizuku權限\n請重新選擇存取方式");
                                            Intent pageJump = new Intent();
                                            pageJump.setClass(LobbyActivity.this, ChooseUtilActivity.class);
                                            startActivity(pageJump);
                                            finish();
                                            overridePendingTransition(0, 0);
                                        } else { // 這裡才能執行 shell

                                            if (HomeActivity.CheckPermissionSoundSuShizuku) {
                                                StartInitializeShell("cp -r /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/tmp/* /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/");
                                                waitForShizukuCompletion(() ->
                                                        FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/tmp"));
                                                showMessage("啟用成功");
                                            } else {


                                                StartInitializeShell("cp -r /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/tmp/* /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/");
                                                waitForShizukuCompletion(() -> {
                                                    FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/tmp");
                                                    result_tmp = mResult.get(mResult.size()-3);
                                                    Log.d("asdfgh", result_tmp);
                                                    if (result_tmp.contains("Permission denied")) {
                                                        AlertDialog.Builder alert0 = new AlertDialog.Builder(LobbyActivity.this);
                                                        alert0.setIcon(R.drawable.app_icon_r)
                                                                .setTitle("警告")
                                                                .setMessage("檢測到啟用不完整，是否進行自動授權？\n點擊「授權」進行之。")
                                                                .setCancelable(false)
                                                                .setNegativeButton("取消", null)
                                                                .setPositiveButton("授權", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        MovieDenied();
                                                                    }
                                                                });
                                                        alert0.create().show();
                                                    }/* else if (result_tmp.contains("or directory")) {
                                                        //根本沒有 ISPDiff 目錄
                                                    }*/ else {
                                                        showMessage("啟用成功");
                                                    }
                                                });
                                            }
                                        }
                                    } else {
                                        showMessage("由於您關閉Shizuku服務\n請重新選擇存取方式");
                                        Intent pageJump = new Intent();
                                        pageJump.setClass(LobbyActivity.this, ChooseUtilActivity.class);
                                        startActivity(pageJump);
                                        finish();
                                        overridePendingTransition(0, 0);
                                    }
                                }
                            }
                        }
                    });
                }
            };
            _timer.schedule(t_delay, (int)(1000));

        } else {
            _unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/".concat(_fileName), "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/");
            showMessage("完成");
        }
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
