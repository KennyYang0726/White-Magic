package com.aoveditor.phantomsneak;

import android.Manifest;
import android.animation.*;
import android.annotation.SuppressLint;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.*;
import android.graphics.drawable.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.Log;
import android.view.*;
import android.view.View;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.AppBarLayout;
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
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.zip.*;
import android.provider.DocumentsContract;
import android.provider.Settings;
import androidx.documentfile.provider.DocumentFile;
import java.net.URL;
import java.net.HttpURLConnection;
import android.os.Looper;

import com.aoveditor.phantomsneak.Utils.FileUtil;
import com.aoveditor.phantomsneak.Utils.SuperUserUtil;
import com.aoveditor.phantomsneak.Utils.ShizukuShellUtil;
import com.aoveditor.phantomsneak.Utils.SAFUtil;
import com.aoveditor.phantomsneak.network.RequestNetwork;
import com.aoveditor.phantomsneak.network.RequestNetworkController;
import com.google.firebase.messaging.FirebaseMessaging;

import rikka.shizuku.Shizuku;


public class HomeActivity extends AppCompatActivity {
    private Timer _timer = new Timer();
    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
    private Toolbar _toolbar;
    private AppBarLayout _app_bar;
    private CoordinatorLayout _coordinator;
    private Uri muri;
    private Uri parenturi;
    private Uri uri2;
    private DocumentFile mfile;
    private DocumentFile mfile1;
    private String apk_name = "";
    private String last_ver = "";
    private String apk = "";
    private String your_version = "";
    private int pos = 0;
    private Uri uriA;
    private Uri uriB;
    private static final int  NEW_FOLDER_REQUEST_CODE = 43;
    private String skin_string = "";
    private String lobby_string = "";
    private String other_string = "";
    private boolean quit = false;
    private String game_ver = "";
    private String language = "";
    private String res_access = "";
    public static String extra_access = "";
    private String tip_open = "";
    private String tip = "";
    private String last_ver2 = "";
    public static String ISPDiff_access = "";
    private int re_scan_cnt = 0;
    private boolean test_ver = false;
    private static final int PERMISSION_REQUEST_CODE = 200;
    private boolean first_install = true;


    private ArrayList<HashMap<String, Object>> map1 = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> map_view = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> map2 = new ArrayList<>();
    private LinearLayout linear1;
    private ViewPager viewpager1;
    private Button button1;
    private TextView textview1;
    private TextView textview2;
    private Button button2;
    private Button button3;
    private Button button4; // 強制修復資源無法下載
    private AdView banner0;
    private ImageView imageview8;
    private ImageView imageview9;
    private ImageView imageview10;
    private ImageView imageview11;
    private Intent i = new Intent();
    private SharedPreferences sp;
    private DatabaseReference Ver = _firebase.getReference("version");
    private ChildEventListener _Ver_child_listener;
    private TimerTask t_pager;
    private Intent page = new Intent();
    private DatabaseReference string_skin = _firebase.getReference("string_skin");
    private ChildEventListener _string_skin_child_listener;
    private RequestNetwork net;
    private RequestNetwork.RequestListener _net_request_listener;
    private AlertDialog.Builder dialog;
    private AlertDialog.Builder delete;
    private AlertDialog.Builder hint;
    private DatabaseReference hints = _firebase.getReference("hint");
    private ChildEventListener _hints_child_listener;
    private TimerTask firsttime_getmyver;
    private TimerTask t_internet;
    private TimerTask t_permission_delay;

    private ShizukuShellUtil mShizukuShell = null;
    private List<String> mResult = null;
    public static boolean CheckPermissionSoundSuShizuku = false;
    /**傳說UID*/
    public static String AOV_UID = "";

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.home);
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
            try {
                if (mShizukuShell != null)
                    mShizukuShell.destroy();
            } catch (Exception r) {
            }

        }
    }

    private void initialize(Bundle _savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        _app_bar = findViewById(R.id._app_bar);
        _coordinator = findViewById(R.id._coordinator);
        _toolbar = findViewById(R.id._toolbar);
        setSupportActionBar(_toolbar);
        linear1 = findViewById(R.id.linear1);
        viewpager1 = findViewById(R.id.viewpager1);
        button1 = findViewById(R.id.button1);
        textview1 = findViewById(R.id.textview1);
        textview2 = findViewById(R.id.textview2);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        banner0 = findViewById(R.id.banner0);
        imageview8 = findViewById(R.id.imageview8);
        imageview9 = findViewById(R.id.imageview9);
        imageview10 = findViewById(R.id.imageview10);
        imageview11 = findViewById(R.id.imageview11);
        sp = getSharedPreferences("sp", Activity.MODE_PRIVATE);
        net = new RequestNetwork(this);
        dialog = new AlertDialog.Builder(this);
        delete = new AlertDialog.Builder(this);
        hint = new AlertDialog.Builder(this);

        AdRequest adRequest = new AdRequest.Builder().build();
        banner0.loadAd(adRequest);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                repair("https://" + language);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                delete.setTitle("警告");
                delete.setIcon(R.drawable.downloadlogo);
                delete.setMessage("按下確認後，將刪除所有下載的插件\n(如有需要將要全部重新下載)");
                delete.setCancelable(false);
                delete.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {
                        FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files");
                        FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files");
                        FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/1-skin"));
                        FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/2-voice"));
                        FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/3-lobby"));
                        FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/4-other"));
                        showMessage("刪除完畢！");
                    }
                });
                delete.setNeutralButton("取消", null);
                delete.create().show();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                delete.setTitle("警告");
                delete.setIcon(R.drawable.downloadlogo);
                delete.setMessage("按下確認後，將還原所有的修改\n(幾乎等同於重新下載傳說，只不過不需要重新把紅點按完)");
                delete.setCancelable(false);
                delete.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {

                        String path_res = "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources";
                        String path_sound = "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC";
                        String path_lobbymovie = "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/ISPDiff/LobbyMovie";

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

                            if (ChooseUtilActivity.Method == "SAF") {
                                // Resources
                                SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources");
                                // Sound_DLC
                                SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC");
                                // LobbyMovie
                                SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FISPDiff%2FLobbyMovie");
                                showMessage("還原完畢！");
                                LaunchAOV();

                            } else if (ChooseUtilActivity.Method == "SU") {
                                if (SuperUserUtil.haveSU()) {
                                    SuperUserUtil.rmWithSU(path_res);
                                    SuperUserUtil.rmWithSU(path_sound);
                                    SuperUserUtil.rmWithSU(path_lobbymovie);
                                    showMessage("還原完畢！");
                                    LaunchAOV();
                                } else {
                                    showMessage("你已撤銷 root 權限");
                                    MainActivity.haveSU = false;
                                    Intent pageJump = new Intent();
                                    pageJump.setClass(HomeActivity.this, ChooseUtilActivity.class);
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
                                        pageJump.setClass(HomeActivity.this, ChooseUtilActivity.class);
                                        startActivity(pageJump);
                                        finish();
                                        overridePendingTransition(0, 0);
                                    } else { // 這裡才能執行 shell
                                        //Shizuku ALL
                                        StartInitializeShell("rm -r " + path_lobbymovie);
                                        waitForShizukuCompletion(() -> {
                                            StartInitializeShell("rm -r " + path_lobbymovie);
                                            waitForShizukuCompletion(() -> {
                                                StartInitializeShell("rm -r " + path_res);
                                                waitForShizukuCompletion(() -> {
                                                    showMessage("還原完畢！");
                                                    LaunchAOV();
                                                });
                                            });
                                        });

                                    }
                                } else {
                                    showMessage("由於您關閉Shizuku服務\n請重新選擇存取方式");
                                    Intent pageJump = new Intent();
                                    pageJump.setClass(HomeActivity.this, ChooseUtilActivity.class);
                                    startActivity(pageJump);
                                    finish();
                                    overridePendingTransition(0, 0);
                                }

                            }
                        } else {
                            FileUtil.deleteFile(path_res);
                            FileUtil.deleteFile(path_sound);
                            FileUtil.deleteFile(path_lobbymovie);
                            showMessage("還原完畢！");
                            LaunchAOV();
                        }
                    }
                });
                delete.setNeutralButton("取消", null);
                delete.create().show();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {  /**強制修復資源無法下載*/
            @Override
            public void onClick(View view) {
                AlertDialog.Builder force_rmRES = new AlertDialog.Builder(HomeActivity.this);
                final ImageView force_rmRES_IMG = new ImageView(HomeActivity.this);
                force_rmRES_IMG.setImageResource(R.drawable.force_rm_res);
                force_rmRES_IMG.setScaleType(ImageView.ScaleType.FIT_CENTER);

                force_rmRES.setTitle("⚠ 警告！ ⚠")
                        .setMessage("此按鈕是設計給未 root 使用者以 shizuku 啟用後，不幸按了遊戲內建「一鍵恢復」的使用者(通常發生於該狀況)，或是卡加載畫面約86%左右的使用者，點擊「確認」以修復遇到圖片中的問題\n若仍然卡讀取，請試著換一種存取模式試試")
                        .setView(force_rmRES_IMG)
                        .setCancelable(false)
                        .setNeutralButton("取消", null);

                if (ChooseUtilActivity.Method == "SU") {
                    if (SuperUserUtil.haveSU()) {
                        //以 su 刪除資源
                        force_rmRES.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SuperUserUtil.rmWithSU("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources");
                                LaunchAOV();
                            }
                        });
                        force_rmRES.create().show();

                    } else {
                        showMessage("你已撤銷 root 權限");
                        MainActivity.haveSU = false;
                        Intent pageJump = new Intent();
                        pageJump.setClass(HomeActivity.this, ChooseUtilActivity.class);
                        startActivity(pageJump);
                        finish();
                        overridePendingTransition(0, 0);
                    }

                } else if (ChooseUtilActivity.Method == "SAF") {
                    renameTo("/files/Resources", game_ver, game_ver+"_8964");
                    LaunchAOV();
                } else if (ChooseUtilActivity.Method == "Shizuku") {
                    //以 shell 刪除資源
                    force_rmRES.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            StartInitializeShell("rm -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + game_ver);
                            waitForShizukuCompletion(() ->
                                    Shizuku可能造成卡讀取之判斷());
                        }
                    });
                    force_rmRES.create().show();

                }
            }
        });


        imageview8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (your_version.equals(last_ver)) {
                    if (skin_string.equals("") || internet == 0) {
                        showMessage("初始化中..");
                    } else if (skin_string.contains("停用")) {
                        showMessage("無法使用");
                    } else if (skin_string.contains("更新")) {
                        if (test_ver) {
                            page.setClass(getApplicationContext(), SkinActivity.class);
                            startActivity(page);
                            overridePendingTransition(0, 0);
                        } else {
                            showMessage("更新中");
                        }
                    } else {
                        page.setClass(getApplicationContext(), SkinActivity.class);
                        startActivity(page);
                        overridePendingTransition(0, 0);
                    }

                } else {
                    if (test_ver) {
                        page.setClass(getApplicationContext(), SkinActivity.class);
                        startActivity(page);
                        overridePendingTransition(0, 0);

                    } else {
                        showMessage("請更新至最新版本再進行操作");
                    }
                }
            }
        });

        imageview9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (your_version.equals(last_ver)) {
                    page.setClass(getApplicationContext(), VoiceActivity.class);
                    startActivity(page);
                    overridePendingTransition(0, 0);
                } else {
                    if (test_ver) {
                        page.setClass(getApplicationContext(), VoiceActivity.class);
                        startActivity(page);
                        overridePendingTransition(0, 0);

                    } else {
                        showMessage("請更新至最新版本再進行操作");
                    }
                }
            }
        });

        imageview10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (your_version.equals(last_ver)) {
                    if (lobby_string.equals("") || internet == 0) {
                        showMessage("初始化中..");
                    } else if (lobby_string.contains("停用")) {
                        showMessage("無法使用");
                    } else if (lobby_string.contains("更新")) {
                        if (test_ver) {
                            page.setClass(getApplicationContext(), LobbyActivity.class);
                            startActivity(page);
                            overridePendingTransition(0, 0);
                        } else {
                            showMessage("更新中");
                        }
                    } else {
                        page.setClass(getApplicationContext(), LobbyActivity.class);
                        startActivity(page);
                        overridePendingTransition(0, 0);
                    }
                } else {
                    if (test_ver) {
                        page.setClass(getApplicationContext(), LobbyActivity.class);
                        startActivity(page);
                        overridePendingTransition(0, 0);

                    } else {
                        showMessage("請更新至最新版本再進行操作");
                    }
                }
            }
        });

        imageview11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (your_version.equals(last_ver)) {
                    if (other_string.equals("") || internet == 0) {
                        showMessage("初始化中..");
                    } else if (other_string.contains("停用")) {
                        showMessage("無法使用");
                    } else if (other_string.contains("更新")) {
                        if (test_ver) {
                            page.setClass(getApplicationContext(), OtherActivity.class);
                            startActivity(page);
                            overridePendingTransition(0, 0);
                        } else {
                            showMessage("更新中");
                        }
                    } else {
                        page.setClass(getApplicationContext(), OtherActivity.class);
                        startActivity(page);
                        overridePendingTransition(0, 0);
                    }
                } else {
                    if (test_ver) {
                        page.setClass(getApplicationContext(), OtherActivity.class);
                        startActivity(page);
                        overridePendingTransition(0, 0);

                    } else {
                        showMessage("請更新至最新版本再進行操作");
                    }
                }
            }
        });

        _Ver_child_listener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot _param1, String _param2) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);
                Ver.addListenerForSingleValueEvent(new ValueEventListener() {
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
                        last_ver = map1.get((int)0).get("v").toString();
                        last_ver2 = map1.get((int)0).get("v2").toString();
                        apk_name = map1.get((int)0).get("name").toString();
                        apk = map1.get((int)0).get("apk").toString();
                        textview2.setText("最新版本："+last_ver);
                        if (your_version.equals("")) {
                            //首次安裝未授權前取不到your_version，須做額外判斷，給3秒的時間
                            firsttime_getmyver = new TimerTask() {
                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                android.content.pm.PackageInfo pinfo = getPackageManager().getPackageInfo("com.aoveditor.phantomsneak", android.content.pm.PackageManager.GET_ACTIVITIES);
                                                your_version = pinfo.versionName;
                                                textview1.setText("目前版本："+your_version);
                                            }catch (Exception e){

                                            }
                                            if (your_version.equals(last_ver)) {
                                                showMessage("您正在使用最新版本");
                                                FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/".concat(apk_name));
                                            } else {
                                                if (your_version.equals(last_ver2)) {
                                                    showMessage("您正在使用內測版");
                                                    last_ver = last_ver2;
                                                    test_ver = true;
                                                } else {
                                                    test_ver = false;
                                                    showMessage("需要更新");
                                                    _install_new_packages();
                                                }
                                            }
                                        }
                                    });
                                }
                            };
                            _timer.schedule(firsttime_getmyver, (int)(3000));
                        } else {
                            if (your_version.equals(last_ver)) {
                                showMessage("您正在使用最新版本");
                                FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/".concat(apk_name));
                            } else {
                                if (your_version.equals(last_ver2)) {
                                    showMessage("您正在使用內測版");
                                    last_ver = last_ver2;
                                    test_ver = true;
                                } else {
                                    test_ver = false;
                                    showMessage("需要更新");
                                    _install_new_packages();
                                }
                            }
                        }
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
        Ver.addChildEventListener(_Ver_child_listener);

        _string_skin_child_listener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot _param1, String _param2) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);
                string_skin.addListenerForSingleValueEvent(new ValueEventListener() {
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
                        skin_string = map2.get((int)0).get("1-skin").toString();
                        lobby_string = map2.get((int)0).get("2-lobby").toString();
                        other_string = map2.get((int)0).get("3-other").toString();
                        game_ver = map2.get((int)0).get("ver").toString();
                        language = map2.get((int)0).get("language").toString();
                        tip_open = map2.get((int)0).get("hint_open").toString();
                        tip = map2.get((int)0).get("hint").toString();
                        if (lobby_string.contains("更新")) {
                            FileUtil.deleteFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/3-lobby"));
                            FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/3-lobby"));
                        }
                        if (other_string.contains("更新")) {
                            FileUtil.deleteFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/4-other"));
                            FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/4-other"));
                        }
                        if (tip_open.equals("1")) {
                            hint.setTitle("各項功能狀態欄");
                            hint.setIcon(R.drawable.downloadlogo);
                            hint.setMessage(tip.substring((int)(0), (int)(11)).concat("\n").concat(tip.substring((int)(11), (int)(22)).concat("\n")).concat(tip.substring((int)(22), (int)(33)).concat("\n").concat(tip.substring((int)(33), (int)(44)).concat("\n".concat(tip.substring(44))))));
                            hint.setCancelable(false);
                            hint.setPositiveButton("確認", null);
                            hint.create().show();
                        }

                        /**檢查權限*/
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            if (ChooseUtilActivity.Method == "Shizuku") {
                                //檢測是否有權限修改Resources目錄擁有者，有的話即SU啟用，直接不檢查
                                initializeShell("chown root /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources");
                                waitForShizukuCompletion(() ->
                                        CheckPermissionSoundSuShizuku());
                            }
                        }
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

        _hints_child_listener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot _param1, String _param2) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);
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
        hints.addChildEventListener(_hints_child_listener);
    }

    private void initializeLogic() {
        //取得FCM token (測試用)
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }
                        String token = task.getResult();
                        Log.d("FireBaseFCM_Token", token);
                    }
                });
        FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/1-skin"));
        FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/2-voice"));
        FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/3-lobby"));
        FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/4-other"));
        quit = false;
        _Internet();
        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("key", "https://raw.githubusercontent.com/JamesYang0826/WhiteMagic-Plugins/main/Photos/1-Home/50112.png");
            map_view.add(_item);
        }

        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("key", "https://raw.githubusercontent.com/JamesYang0826/WhiteMagic-Plugins/main/Photos/1-Home/Genos1.png");
            map_view.add(_item);
        }

        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("key", "https://raw.githubusercontent.com/JamesYang0826/WhiteMagic-Plugins/main/Photos/1-Home/NAK.png");
            map_view.add(_item);
        }

        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("key", "https://raw.githubusercontent.com/JamesYang0826/WhiteMagic-Plugins/main/Photos/1-Home/diaochan.png");
            map_view.add(_item);
        }

        //btn4的隱藏與否
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            button4.setVisibility(View.VISIBLE);
        } else {
            button4.setVisibility(View.GONE);
        }

        viewpager1.setAdapter(new Viewpager1Adapter(map_view));
        re_scan_cnt = 0;
        pos = 0;
        try {
            android.content.pm.PackageInfo pinfo = getPackageManager().getPackageInfo("com.aoveditor.phantomsneak", android.content.pm.PackageManager.GET_ACTIVITIES);
            your_version = pinfo.versionName;
            textview1.setText("目前版本："+your_version);
        }catch (Exception e){
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

            if (ChooseUtilActivity.Method == "SAF") {
                //如果選擇SAF，才要檢查授權
                try {
                    muri = Uri.parse(sp.getString("DIRECT_FOLDER_URI", ""));
                    mfile = DocumentFile.fromTreeUri(HomeActivity.this, muri);

                    if (!sp.getString("FOLDER_URI", "").equals("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw")) {
                        first_install = false;
                        _askPermission(linear1);
                    }
                    if (!mfile.canRead() || !mfile.canWrite()) {
                        first_install = true;
                        _askPermission(linear1);
                    } else {
                        parenturi = Uri.parse(sp.getString("FOLDER_URI", ""));
                        first_install = false;
                        _get_permission();
                    }
                } catch (Exception e) {
                    first_install = true;
                    _askPermission(linear1);
                }
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_DENIED) {
                    showMessage("無通知權限");
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, PERMISSION_REQUEST_CODE);
                }
            }
        }
        t_pager = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        viewpager1.setCurrentItem((int)pos);
                        pos++;
                        if (pos == map_view.size()) {
                            pos = 0;
                        }
                    }
                });
            }
        };
        _timer.scheduleAtFixedRate(t_pager, (int)(0), (int)(2000));
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
        super.onActivityResult(_requestCode, _resultCode, _data);
        if (_resultCode == Activity.RESULT_OK) {
            if (_data != null) {
                muri = _data.getData();
                if (Uri.decode(muri.toString()).endsWith(":")) {
                    showMessage("該目錄不可用");
                    _askPermission(linear1);
                } else {
                    final int takeFlags = i.getFlags()
                            & (Intent.FLAG_GRANT_READ_URI_PERMISSION
                            | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    // Check for the freshest data.
                    getContentResolver().takePersistableUriPermission(muri, takeFlags);
                    sp.edit().putString("FOLDER_URI", muri.toString()).commit();
                    mfile = DocumentFile.fromTreeUri(this, muri);

                    mfile1 = mfile.createFile("*/*", "test.file");
                    uri2 = mfile1.getUri();
                    sp.edit().putString("DIRECT_FOLDER_URI", uri2.toString().substring((int)(0), (int)(uri2.toString().length() - 9))).commit();
                    try{
                        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uri2);

                    } catch (FileNotFoundException e) {

                    }
                    if (!sp.getString("FOLDER_URI", "").equals("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw")) {
                        HelpSAFDialog();
                    } else {
                        if (first_install) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                                if (ChooseUtilActivity.Method == "SAF")
                                    _get_permission();
                            }
                        }
                    }
                    parenturi = Uri.parse(sp.getString("FOLDER_URI", ""));
                }
            }
        } else {
            HelpSAFDialog();
        } switch (_requestCode) {
            default:
                break;
        }
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

    public void _askPermission(final View _view) {
        AlertDialog.Builder askPermission = new AlertDialog.Builder(this);
        askPermission.setTitle("提示")
                        .setIcon(R.drawable.downloadlogo)
                        .setCancelable(false)
                        .setMessage("請直接按使用這個資料夾\n若沒跳轉至「com.garena.game.kgtw」\n請跳至其他隨意目錄按使用\n或於SAF畫面退回至底層\n將有步驟引導您做法")
                        .setPositiveButton("去授權", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int id) {
                                i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                i.setAction(Intent.ACTION_OPEN_DOCUMENT_TREE);
                                muri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw");
                                i.putExtra(DocumentsContract.EXTRA_INITIAL_URI, muri);
                                startActivityForResult(i, NEW_FOLDER_REQUEST_CODE);
                            }
                        });
        askPermission.create().show();
    }

    private void HelpSAFDialog() {
        //跳出AlerDialog
        final ImageView image_A = new ImageView(HomeActivity.this);
        image_A.setImageResource(R.drawable.ununstall_update_saf);
        AlertDialog.Builder builder_A = new AlertDialog.Builder(HomeActivity.this);
        builder_A.setTitle("需要協助？")
                .setView(image_A)
                .setMessage("無法正確跳轉至傳說的目錄授權？\n如果是的話，請按底下「跳轉」將 Files 應用程式解除更新後，點擊「重試」。")
                .setIcon(R.drawable.downloadlogo)
                .setCancelable(false)
                .setNegativeButton("解除更新", null);

        builder_A.setPositiveButton("重試", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                _askPermission(linear1);
            }
        });

        final AlertDialog dialog_A = builder_A.create();
        dialog_A.show();
        dialog_A.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_A = new Intent(Intent.ACTION_UNINSTALL_PACKAGE);
                intent_A.setData(Uri.parse("package:" + "com.google.android.documentsui"));
                startActivity(intent_A);
            }
        });
    }


    private boolean isDarkMode() {
        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES;
    }

    /**使選項內Icon與文字並存*/
    private CharSequence menuIconWithText(Drawable r, String title) {
        r.setBounds(0, 0, r.getIntrinsicWidth(), r.getIntrinsicHeight());
        SpannableString sb = new SpannableString("    " + title);
        ImageSpan imageSpan = new ImageSpan(r, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //if (isDarkMode())
            //sb.setSpan(new android.text.style.ForegroundColorSpan(Color.WHITE), 0, sb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //else
            //sb.setSpan(new android.text.style.ForegroundColorSpan(Color.BLACK), 0, sb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }
    /**程式中新增MenuItem選項*/
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        /**itemId為稍後判斷點擊事件要用的*/
        menu.add(0,0,0,menuIconWithText(getDrawable(R.drawable.youtube),"YouTube"));
        menu.add(0,1,1,menuIconWithText(getDrawable(R.drawable.discord),"Discord"));
        menu.add(0,2,2,menuIconWithText(getDrawable(R.drawable.github),"Github開源"));
        menu.add(0,3,3,menuIconWithText(getDrawable(R.drawable.rate),"評分"));
        menu.add(0,4,4,menuIconWithText(getDrawable(R.drawable.more_apps),"更多apps"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (ChooseUtilActivity.Method=="SAF")
                menu.add(0,5,5,menuIconWithText(getDrawable(R.drawable.saf),"切換存取模式"));
            if (ChooseUtilActivity.Method=="SU")
                menu.add(0,5,5,menuIconWithText(getDrawable(R.drawable.su),"切換存取模式"));
            if (ChooseUtilActivity.Method=="Shizuku")
                menu.add(0,5,5,menuIconWithText(getDrawable(R.drawable.shizuku),"切換存取模式"));
        }
        return super.onCreateOptionsMenu(menu);
    }
    /**此處為設置點擊事件*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*取得Item的ItemId，判斷點擊到哪個元件*/
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
                Intent pageJump = new Intent();
                pageJump.setClass(HomeActivity.this, ChooseUtilActivity.class);
                startActivity(pageJump);
                finish();
                overridePendingTransition(0, 0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private DocumentFile getDocumentFile1(DocumentFile documentFile,String dir){
        if (documentFile==null)return null;
        try {
            DocumentFile[] documentFiles = documentFile.listFiles();
            DocumentFile res = null;
            int i = 0;
            while (i < documentFile.length()) {
                if (documentFiles[i].getName().equals(dir) && documentFiles[i].isDirectory()) {
                    res = documentFiles[i];
                    return res;
                }
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean renameTo(String dir,String fileName,String targetName) {
        try {
            Uri uri1 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw");
            DocumentFile documentFile = DocumentFile.fromTreeUri(HomeActivity.this, uri1);
            String[] list = dir.split("/");
            int i = 0;
            while (i < list.length) {
                if (!list[i].equals("")) {
                    DocumentFile a = getDocumentFile1(documentFile, list[i]);
                    if (a == null) {
                        documentFile = documentFile.createDirectory(list[i]);
                    } else {
                        documentFile = a;
                    }
                }
                i++;
            }
            documentFile=documentFile.findFile(fileName);
            Res_Status = 0;
            return documentFile.renameTo(targetName);
        }catch (Exception e){
            e.printStackTrace();
            Res_Status = 2;
            return false;
        }
    }

    public void createdirectoryandfile(String dir, String uridelete) {
        try {
            Uri uri1 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw");
            DocumentFile documentFile = DocumentFile.fromTreeUri(HomeActivity.this, uri1);
            String[] list = dir.split("/");
            int i = 0;
            while (i < list.length) {
                if (!list[i].equals("")) {
                    DocumentFile a = getDocumentFile1(documentFile, list[i]);
                    if (a == null) {
                        documentFile = documentFile.createDirectory(list[i]);
                    } else {
                        documentFile = a;
                    }
                }
                i++;
            }

            documentFile = documentFile.createFile("text/plain", "test.txt");

            if (uridelete.contains("Sound_DLC")){
                uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2F"+uridelete);
                try {
                    try{
                        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
                        extra_access = "⭕";
                    } catch (FileNotFoundException e) {

                    }
                } catch (Exception e) {
                    extra_access = "❌";
                }
            } else if (uridelete.contains("LobbyMovie")){
                uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2F"+uridelete);
                try {
                    try{
                        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
                        ISPDiff_access = "⭕";
                    } catch (FileNotFoundException e) {

                    }
                } catch (Exception e) {
                    ISPDiff_access = "❌";
                }
            } else if (uridelete.contains("Config")) {
                uriB = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2F"+uridelete);
                try {
                    try{
                        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriB);
                        res_access = "⭕";
                    } catch (FileNotFoundException e) {
                        res_access = "❌";
                    }
                } catch (Exception e) {
                    res_access = "❌";
                }
            } else {
                try {
                    try{
                        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
                    } catch (FileNotFoundException e) {
                        showMessage("發生了預期外的錯誤");
                    }
                } catch (Exception e) {
                    showMessage("發生了預期外的錯誤");
                }
            }
        }catch (Exception e){
        }
    }


    //Unzip assets with replace
    @SuppressLint("NewApi")
    Boolean unzipAssets(String _filename, DocumentFile _myDestFolder) {

        DocumentFile myFolder = null;
        DocumentFile mySubFolder = null;
        DocumentFile mySubSubFolder = null;
        DocumentFile tempFile = null;

        try {
            try{
                InputStream is = this.getAssets().open(_filename);
                BufferedInputStream bis = new BufferedInputStream(is);
                ZipInputStream zis = new ZipInputStream(bis);
                ZipEntry zipEntry;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) //重要！！！
                    dalvik.system.ZipPathValidator.clearCallback();
                while ((zipEntry = zis.getNextEntry()) != null) {
                    String fileName = null;

                    try {
                        fileName = zipEntry.getName();
                        fileName = fileName.replace("\\",File.separator).replace("/",File.separator);
                        int p=fileName.lastIndexOf(File.separator);
                        DocumentFile destFolder = _myDestFolder;
                        //DocumentFile of the destination folder
                        String destName = fileName;

                        if (p>=0) {
                            String[] split = fileName.split(File.separator);
                            //If the .zip file contains multiple folder levels, this is where you
                            //have to check and then create them, e.g. for 3 levels:
                            if(split.length==1) {
                                if(myFolder==null) {
                                    myFolder = _myDestFolder;
                                }
                                destFolder = myFolder;
                                destName = fileName;
                            } else if(split.length==2) {
                                myFolder = _myDestFolder;
                                if(mySubFolder==null) {
                                    tempFile = null;
                                    tempFile = DocumentFile.fromSingleUri(this, Uri.parse(myFolder.getUri().toString().concat(Uri.encode("/").concat(split[0]))));
                                    if (tempFile.exists()) {
                                        mySubFolder = tempFile;
                                    } else {
                                        mySubFolder = myFolder.createDirectory(split[0]);
                                    }
                                }
                                destFolder = mySubFolder;
                                destName = split[1];
                            } else if(split.length==3) {
                                myFolder = _myDestFolder;
                                if(mySubFolder==null) {
                                    tempFile = null;
                                    tempFile = DocumentFile.fromSingleUri(this, Uri.parse(myFolder.getUri().toString().concat(Uri.encode("/").concat(split[0]))));
                                    if (tempFile.exists()) {
                                        mySubFolder = tempFile;
                                    } else {
                                        mySubFolder = myFolder.createDirectory(split[0]);
                                    }
                                }
                                if(mySubSubFolder==null) {
                                    tempFile = null;
                                    tempFile = DocumentFile.fromSingleUri(this, Uri.parse(mySubFolder.getUri().toString().concat(Uri.encode("/").concat(split[1]))));
                                    if (tempFile.exists()) {
                                        mySubSubFolder = tempFile;
                                    } else {
                                        mySubSubFolder = mySubFolder.createDirectory(split[1]);
                                    }
                                }
                                destFolder = mySubSubFolder;
                                destName = split[2];
                            }
                        }
                        if (!zipEntry.isDirectory()) {
                            DocumentFile df = null;
                            //Now you have to tell it what file extensions ("MIME" type) you want to use, e.g.:
                            tempFile = null;
                            tempFile = DocumentFile.fromSingleUri(this, Uri.parse(destFolder.getUri().toString().concat(Uri.encode("/").concat(destName))));
                            if (tempFile.exists()) {
                                df = tempFile;
                            } else {
                                df = destFolder.createFile("*/*",destName);
                            }
                            OutputStream out = getContentResolver().openOutputStream(df.getUri());
                            BufferedOutputStream bos = new BufferedOutputStream(out);
                            long zipfilesize = zipEntry.getSize();

                            byte[] buffer = new byte[10000];
                            int len = 0;
                            int totlen = 0;

                            while (((len = zis.read(buffer, 0, 10000)) > 0) ) {
                                bos.write(buffer, 0, len);
                                totlen += len;
                            }


                            bos.close();
                        }
                    } catch (IOException e1) {

                        showMessage(e1.getMessage());

                        return false;
                    }
                }

                is.close();
                bis.close();
                zis.close();
            } catch (IOException e2) {
                showMessage(e2.getMessage());
                return false;
            }

        } catch(Exception e){
            showMessage(e.getMessage());
            return false;
        }
        return true;
    }


    public void repair(String Url){
        final ProgressDialog prog = new ProgressDialog(this);
        prog.setIcon(R.drawable.app_icon_r);
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
                        String Path;
                        filename = URLUtil.guessFileName(Url, null, null);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            Path = "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/";
                        }else{
                            Path = "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + game_ver + "/Languages/CHT_Garena_TW/";
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
                                    prog.setTitle("修復中");
                                    prog.setMessage("修復中...請稍後... ");
                                    prog.show();
                                }
                            });
                            bout.write(data, 0, x);
                        }
                        bout.close();
                        in.close();
                        prog.dismiss();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

                            if (ChooseUtilActivity.Method == "SAF") {

                                uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FLanguages%2FCHT_Garena_TW%2FlanguageMap.txt")));
                                try {
                                    try{
                                        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
                                    } catch (FileNotFoundException e) {
                                    }
                                    uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FLanguages%2FCHT_Garena_TW%2F")));
                                    SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/languageMap.txt", uri2);
                                } catch (Exception e) {
                                    uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FLanguages%2FCHT_Garena_TW%2F")));
                                    SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/languageMap.txt", uri2);
                                }
                                //刪除 app 目錄下的 languageMap
                                FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/languageMap.txt");
                                showMessage("修復成功");

                            } else if (ChooseUtilActivity.Method == "SU") {
                                if (SuperUserUtil.haveSU()) {
                                    SuperUserUtil.cpWithSU(FileUtil.getPackageDataDir(getApplicationContext()).concat("/languageMap.txt"), "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + game_ver + "/Languages/CHT_Garena_TW/");
                                    //刪除 app 目錄下的 languageMap
                                    FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/languageMap.txt");
                                    showMessage("修復成功");
                                } else {
                                    showMessage("你已撤銷 root 權限");
                                    MainActivity.haveSU = false;
                                    Intent pageJump = new Intent();
                                    pageJump.setClass(HomeActivity.this, ChooseUtilActivity.class);
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
                                        pageJump.setClass(HomeActivity.this, ChooseUtilActivity.class);
                                        startActivity(pageJump);
                                        finish();
                                        overridePendingTransition(0, 0);
                                    } else { // 這裡才能執行 shell
                                        StartInitializeShell("cp -r /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/languageMap.txt /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + game_ver + "/Languages/CHT_Garena_TW/");
                                        showMessage("修復成功");
                                        waitForShizukuCompletion(() ->
                                                //刪除 app 目錄下的 languageMap
                                                FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/languageMap.txt"));
                                    }
                                } else {
                                    showMessage("由於您關閉Shizuku服務\n請重新選擇存取方式");
                                    Intent pageJump = new Intent();
                                    pageJump.setClass(HomeActivity.this, ChooseUtilActivity.class);
                                    startActivity(pageJump);
                                    finish();
                                    overridePendingTransition(0, 0);
                                }

                            }
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


    public void DownloadAndInstall(String Url, String Path){
        final ProgressDialog prog = new ProgressDialog(this);
        prog.setIcon(R.drawable.app_icon_r);
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
                                    prog.setTitle("正在下載更新");
                                    prog.setMessage("正在為您下載新版本\n下載進度：" + (currentProgress/1000) + "%");
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

    public void showMessage(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
    private int internet;
    private int Res_Status;
    //背景運作Prog
    private ProgressDialog prog2;
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
        prog2 = new ProgressDialog(HomeActivity.this);
        prog2.setMax(100);
        prog2.setMessage(title);
        prog2.setIndeterminate(true);
        prog2.setCancelable(false);
        prog2.setCanceledOnTouchOutside(false);
        prog2.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        prog2.show();
    }

    private void ProgressBar_Dismiss(){
        if (prog2 != null){
            prog2.dismiss();
        }
    }

    public void _Internet() {
        net.startRequestNetwork(RequestNetworkController.GET, "https://1.1.1.1", "", _net_request_listener);
    }


    public void _install_package() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Uri uri = androidx.core.content.FileProvider.getUriForFile(getApplicationContext(), HomeActivity.this.getPackageName() + ".provider", new java.io.File("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/"+apk_name));
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(uri, "application/vnd.android.package-archive");
                startActivity(intent);
            } else {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile( new java.io.File("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/"+apk_name)), "application/vnd.android.package-archive");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        } catch (Exception rr) {
            showMessage (rr.toString());
        }
    }

    public void _get_permission() {
        if (!first_install) {
            //稍等的dialog
            AlertDialog.Builder builder2 = new AlertDialog.Builder(HomeActivity.this);
            builder2.setTitle("")
                    .setMessage("\n請稍等...\n")
                    .setCancelable(false);
            AlertDialog alert2 = builder2.create();
            alert2.show();

            createdirectoryandfile("/files/Extra/2019.V2/ISPDiff/LobbyMovie", "Extra%2F2019.V2%2FISPDiff%2FLobbyMovie%2Ftest.txt");
            t_permission_delay = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files")) {
                                //showMessage("一般型&微特殊型？");
                                //有載傳說的，一般型，基本上在這裡
                                createdirectoryandfile("/files/Extra/2019.V2/Sound_DLC/Android", "Extra%2F2019.V2%2FSound_DLC%2FAndroid%2Ftest.txt");
                            } else {
                                //showMessage("特殊型？");
                                //有載傳說的，特殊型，基本上在這裡
                                createdirectoryandfile("/files/Extra/2019.V2/Sound_DLC/Android/Chinese(Taiwan)", "Extra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2Ftest.txt");
                            }
                            t_permission_delay = new TimerTask() {
                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            //RES檢測亦採用 新建/刪除 test.txt 成功與否判斷，而非解壓splash.txt
                                            createdirectoryandfile("/files/Resources/" + game_ver + "/Config", "Resources%2F" + game_ver + "%2FConfig%2Ftest.txt");
                                            t_permission_delay = new TimerTask() {
                                                @Override
                                                public void run() {
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            alert2.dismiss();

                                                            if (ISPDiff_access.equals("❌") || (res_access.equals("❌") || extra_access.equals("❌") || ISPDiff_access.equals("") || extra_access.equals(""))) {
                                                                //要彈窗顯示

                                                                if (ISPDiff_access.equals("")) //有這種狀況，通常是ISPDiff被傳說建立，但LobbyMovie尚未建立
                                                                    ISPDiff_access = "❌";
                                                                if (extra_access.equals(""))
                                                                    extra_access = "❌";

                                                                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                                                                if (ChooseUtilActivity.Method == "SAF") {
                                                                    if (res_access.equals("❌")) {
                                                                        showMessage("請確認是否下載遊戲登入畫面前置資源");
                                                                        showMessage("或按照教學完成手動授權");
                                                                    }
                                                                    final ImageView image = new ImageView(HomeActivity.this);
                                                                    image.setImageResource(R.drawable.click_me);
                                                                    builder.setTitle("獲取權限資訊狀態")
                                                                            .setMessage("Resources："+res_access+"\n\n①Sound_DLC："+extra_access+"\n②LobbyMovie："+ISPDiff_access+"\n(①②特殊型可自動授權)\n")
                                                                            .setView(image)
                                                                            .setIcon(R.drawable.downloadlogo)
                                                                            .setCancelable(false)
                                                                            .setPositiveButton("重新檢測", new DialogInterface.OnClickListener() {
                                                                                @Override
                                                                                public void onClick(DialogInterface dialog, int id) {
                                                                                    try{
                                                                                        _get_permission();
                                                                                    }catch(Exception e){
                                                                                        showMessage(e.getMessage());
                                                                                    }
                                                                                    re_scan_cnt++;
                                                                                }
                                                                            })
                                                                            .setNegativeButton("切換存取模式", new DialogInterface.OnClickListener() {
                                                                                @Override
                                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                                    Intent pageJump = new Intent();
                                                                                    pageJump.setClass(HomeActivity.this, ChooseUtilActivity.class);
                                                                                    startActivity(pageJump);
                                                                                    finish();
                                                                                    overridePendingTransition(0, 0);
                                                                                }
                                                                            });
                                                                    image.setOnClickListener(new View.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(View _view) {
                                                                            String videoId = "zJilQ4vuzVY"; // 替換為實際的YouTube影片ID
                                                                            String startTime = "t=576s"; // 替換為想要的開始時間，例如30秒
                                                                            String url = "https://www.youtube.com/watch?v=" + videoId + "&" + startTime;
                                                                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                                                            startActivity(browserIntent);
                                                                        }
                                                                    });

                                                                    if (re_scan_cnt >= 1) {
                                                                        builder.setNeutralButton("授權①②", new DialogInterface.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(DialogInterface dialog, int id) {
                                                                                page.setClass(getApplicationContext(), WarningActivity.class);
                                                                                startActivity(page);
                                                                                overridePendingTransition(0, 0);
                                                                            }
                                                                        });
                                                                    }
                                                                    //builder.create().show();

                                                                }

                                                                if (!isFinishing()) //不加可能出錯
                                                                    builder.create().show();

                                                            }
                                                        }
                                                    });
                                                }
                                            };
                                            _timer.schedule(t_permission_delay, (int)(1000));
                                        }
                                    });
                                }
                            };
                            _timer.schedule(t_permission_delay, (int)(1000));
                        }
                    });
                }
            };
            _timer.schedule(t_permission_delay, (int)(1000));
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
            builder.setTitle("獲取權限資訊狀態")
                    .setMessage("Resources："+"❓"+"\n\n①Sound_DLC："+"❓"+"\n②LobbyMovie："+"❓"+"\n(①②特殊型可自動授權)\n")
                    .setIcon(R.drawable.downloadlogo)
                    .setCancelable(false)
                    .setPositiveButton("重新檢測", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            first_install = false;
                            try{
                                _get_permission();
                            }catch(Exception e){
                                showMessage(e.getMessage());
                            }
                        }
                    });
            AlertDialog alert1 = builder.create();
            alert1.show();
        }
    }


    public void _install_new_packages() {
        //Designby DOY

        final AlertDialog db = new AlertDialog.Builder(HomeActivity.this).create();
        LayoutInflater inflater = getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.dia, null);
        db.setView(convertView);
        db.requestWindowFeature(Window.FEATURE_NO_TITLE);
        db.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
        LinearLayout over_ly01 = (LinearLayout)
                convertView.findViewById(R.id.over_ly01);
        LinearLayout extra02 = (LinearLayout)
                convertView.findViewById(R.id.extra02);
        LinearLayout main_bgd1 = (LinearLayout)
                convertView.findViewById(R.id.main_bgd1);
        LinearLayout img_bgd1 = (LinearLayout)
                convertView.findViewById(R.id.img_bgd1);
        TextView ttext01 = (TextView)
                convertView.findViewById(R.id.ttext01);
        TextView ttext03 = (TextView)
                convertView.findViewById(R.id.ttext03);
        ttext03.setText("版本："+apk_name);
        ImageView timage02 = (ImageView)
                convertView.findViewById(R.id.timage02);
        Button bt01 = (Button)
                convertView.findViewById(R.id.bt01);
        Button bt02 = (Button)
                convertView.findViewById(R.id.bt02);
        if(FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/".concat(apk_name))){
            bt02.setText("安裝更新");
        } else {
            bt02.setText("下載更新");
        }
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
                finishAffinity();
            }
        });
        bt02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                //下載更新或安裝
                if(FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/".concat(apk_name))){
                    //安裝更新
                    //_install_package();
                } else {
                    //下載更新
                    Intent playstore = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.aoveditor.phantomsneak"));
                    startActivity(playstore);
                    //DownloadAndInstall("https://" + apk, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/");
                }
            }
        });
        _Shape(0, 0, 30, 30, "#ffffff", 0, "#ffffff", 0, main_bgd1);
        _Shape(20, 20, 20, 20, "#ffffff", 6, "#fbc02d", 6, bt01);
        _Shape(20, 20, 20, 20, "#fbc02d", 0, "#ffff00", 6, bt02);
        db.show();

    }


    public void _Shape(final double _t1, final double _t2, final double _b1, final double _b2, final String _Background, final double _Stroke, final String _stroke, final double _Elevation, final View _view) {
        android.graphics.drawable.GradientDrawable gs = new android.graphics.drawable.GradientDrawable();

        gs.setColor(Color.parseColor(_Background));

        gs.setStroke((int)_Stroke, Color.parseColor(_stroke));

        gs.setCornerRadii(new float[]{(int)_t1,(int)_t1,(int)_t2,(int)_t2,(int)_b1,(int)_b1,(int)_b2,(int)_b2});

        _view.setBackground(gs);
        _view.setElevation((int)_Elevation);
    }


    private String result_tmp;
    private void CheckPermissionSoundSuShizuku() {
        //弄一點延遲
        new Handler().postDelayed(new Runnable() {
            public void run() {
                result_tmp = mResult.get(mResult.size()-3);
                //判斷情況
                if (result_tmp.contains("Permission denied") || result_tmp.contains("Operation not permitted") || result_tmp.contains("or directory")) {
                    //沒權限，即非su啟用
                    CheckPermissionSoundSuShizuku = false;
                    FirstDetectPermission_Shizuku();
                } else {
                    CheckPermissionSoundSuShizuku = true;
                }
            }
        }, 500);
    }


    //Shizuku初始檢測權限狀態
    private void FirstDetectPermission_Shizuku() {
        /**重要，如果關閉服務，需要先判斷ping才能檢測是否granted，否則異常*/
        if (Shizuku.pingBinder()) { //關閉 shizuku 服務就 ping 不到了
            if (!(Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED)) {
                showMessage("由於您拒絕Shizuku權限\n請重新選擇存取方式");
                Intent pageJump = new Intent();
                pageJump.setClass(HomeActivity.this, ChooseUtilActivity.class);
                startActivity(pageJump);
                finish();
                overridePendingTransition(0, 0);
            } else { // 這裡才能執行 shell
                StartInitializeShell("ls /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + game_ver + "/Config/");
                waitForShizukuCompletion(() ->
                        Second_DetectPermission_Shizuku());
            }
        } else {
            showMessage("由於您關閉Shizuku服務\n請重新選擇存取方式");
            Intent pageJump = new Intent();
            pageJump.setClass(HomeActivity.this, ChooseUtilActivity.class);
            startActivity(pageJump);
            finish();
            overridePendingTransition(0, 0);
        }
    }
    private void Shizuku可能造成卡讀取之判斷() {
        String result_tmp = mResult.get(mResult.size()-3);
        //判斷情況
        if (result_tmp.contains("Permission denied")) {
            letter1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            letter2 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            letter3 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            cc1 = getRandom((int)(0), (int)(61));
            cc2 = getRandom((int)(0), (int)(61));
            cc3 = getRandom((int)(0), (int)(61));
            str_tmp1 = (letter1.substring(cc1, cc1+1)+letter2.substring(cc2, cc2+1)+letter3.substring(cc3, cc3+1));
            StartInitializeShell("mv /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/"+game_ver + " " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/"+game_ver+"_"+str_tmp1);
            waitForShizukuCompletion(() ->
                    LaunchAOV());

        } else {
            LaunchAOV();
        }
    }
    private void LaunchAOV() {
        try {
            if (mShizukuShell != null)
                mShizukuShell.destroy();
        } catch (Exception r) {
        }
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.garena.game.kgtw");
        startActivity(launchIntent);
        if (ChooseUtilActivity.Method == "Shizuku")
            StartInitializeShell("am force-stop com.aoveditor.phantomsneak");
        else
            finishAffinity();
    }
    private void Second_DetectPermission_Shizuku() {
        String result_tmp = mResult.get(mResult.size()-3);
        //判斷情況
        if (result_tmp.contains("No such file or directory")) {
            //未下載資源
            AlertDialog.Builder shizuku_permission = new AlertDialog.Builder(this);
            shizuku_permission.setIcon(R.drawable.downloadlogo);
            shizuku_permission.setTitle("提示")
                    .setCancelable(false)
                    .setMessage("檢測到您尚未下載 傳說對決 資源，點擊「確認」跳轉遊戲下載資源。\n若您認為這是個錯誤(資源已下載)，點擊「直接授權」進行步驟 1/2 授權。")
                    .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            LaunchAOV();
                        }
                    })
                    .setNegativeButton("直接授權", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            StartInitializeShell("mv /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/"+game_ver + " " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/"+game_ver+"_trash0");
                            waitForShizukuCompletion(() ->
                                    LaunchAOV());
                        }
                    });
            shizuku_permission.create().show();

        } else if (result_tmp.contains("Permission denied")) {
            //第一次
            AlertDialog.Builder shizuku_permission = new AlertDialog.Builder(this);
            shizuku_permission.setIcon(R.drawable.downloadlogo);
            shizuku_permission.setTitle("自動授權存取步驟 1/2")
                    .setCancelable(false)
                    .setMessage("檢測到您 傳說對決 資源無法被白魔法存取，點擊「確認」進行步驟 1/2 自動授權。完成後將自動開啟傳說，下載資源完成後再回白魔法進行步驟 2/2 之授權")
                    .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            StartInitializeShell("mv /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/"+game_ver + " " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/"+game_ver+"_trash0");
                            waitForShizukuCompletion(() ->
                                    LaunchAOV());
                        }
                    })
                    .setNegativeButton("切換存取模式", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent pageJump = new Intent();
                            pageJump.setClass(HomeActivity.this, ChooseUtilActivity.class);
                            startActivity(pageJump);
                            finish();
                            overridePendingTransition(0, 0);
                        }
                    });
            shizuku_permission.create().show();

        } else {
            FileUtil.writeFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/cache/Shizuku", "");
            //可以 ls，再來判斷能不能 cp
            StartInitializeShell("cp /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/cache/Shizuku /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/"+game_ver+"/Config/");
            waitForShizukuCompletion(() ->
                    Third_DetectPermission_Shizuku());
        }

    }
    private int getRandom(int _min, int _max) {
        Random random = new Random();
        return random.nextInt(_max - _min + 1) + _min;
    }
    private String letter1,letter2,letter3,str_tmp1;
    private int cc1,cc2,cc3;
    private void Third_DetectPermission_Shizuku() {
        FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/cache/Shizuku");
        String result_tmp = mResult.get(mResult.size()-3);
        //判斷情況
        if (result_tmp.contains("Permission denied")) {
            //自動授權存取步驟 2/2
            letter1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            letter2 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            letter3 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            cc1 = getRandom((int)(0), (int)(61));
            cc2 = getRandom((int)(0), (int)(61));
            cc3 = getRandom((int)(0), (int)(61));
            str_tmp1 = (letter1.substring(cc1, cc1+1)+letter2.substring(cc2, cc2+1)+letter3.substring(cc3, cc3+1));
            StartInitializeShell("mkdir -p /storage/emulated/0/Android/.tmp/zzz.zz.z_"+str_tmp1);
            AlertDialog.Builder shizuku_permission2 = new AlertDialog.Builder(this);
            shizuku_permission2.setIcon(R.drawable.downloadlogo);
            shizuku_permission2.setTitle("自動授權存取步驟 2/2")
                    .setCancelable(false)
                    .setMessage("恭喜您快要完成授權了~~\n檢測到您 傳說對決 資源無法被白魔法存取，點擊「確認」進行步驟 2/2 自動授權。完成後即可使用接下來白魔法所有功能啦~")
                    .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            StartInitializeShell("cp -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/"+game_ver + " " + "/storage/emulated/0/Android/.tmp/zzz.zz.z_"+str_tmp1+"/");
                            waitForShizukuCompletion(() ->
                                    Forth_DetectPermission_Shizuku());
                        }
                    });
            shizuku_permission2.create().show();
        } else {
            //沒問題拉~~(吧)
            StartInitializeShell("rm -r /storage/emulated/0/Android/.tmp");
            waitForShizukuCompletion(() -> {
                //將傳說 UID 儲存，方便檢測權限(擁有者)
                StartInitializeShell("dumpsys package com.garena.game.kgtw | grep uid | head -n 1 | awk \'{print $1}\'");
                waitForShizukuCompletion(() -> {
                    String result_tmp2 = mResult.get(mResult.size()-3);
                    AOV_UID = "u0_a" + result_tmp2.substring(result_tmp2.length() - 3);
                    /*************************/
                });
            });
        }

    }
    private void Forth_DetectPermission_Shizuku() {
        //複製到本地後的部分，要來重命名原本了
        StartInitializeShell("mv /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/"+game_ver + " " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/"+game_ver+"_trash1");
        waitForShizukuCompletion(() -> {
            //複製回去
            StartInitializeShell("cp -r /storage/emulated/0/Android/.tmp/zzz.zz.z_"+str_tmp1+"/"+game_ver + " " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/");
            waitForShizukuCompletion(() -> {
                //刪暫存
                StartInitializeShell("rm -r /storage/emulated/0/Android/.tmp");
                waitForShizukuCompletion(() -> {
                    //將傳說 UID 儲存，方便檢測權限(擁有者)
                    StartInitializeShell("dumpsys package com.garena.game.kgtw | grep uid | head -n 1 | awk \'{print $1}\'");
                    waitForShizukuCompletion(() -> {
                        String result_tmp = mResult.get(mResult.size()-3);
                        AOV_UID = "u0_a" + result_tmp.substring(result_tmp.length() - 3);
                        showMessage("大功告成~~");
                    });

                });
            });
        });
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
            //Looper.prepare();
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
                    prog.dismiss();
                    isShizukuING = false;
                });
            }

        });
    }



    public class Viewpager1Adapter extends PagerAdapter {
        Context _context;
        ArrayList<HashMap<String, Object>> _data;

        public Viewpager1Adapter(Context _ctx, ArrayList<HashMap<String, Object>> _arr) {
            _context = _ctx;
            _data = _arr;
        }

        public Viewpager1Adapter(ArrayList<HashMap<String, Object>> _arr) {
            _context = getApplicationContext();
            _data = _arr;
        }

        @Override
        public int getCount() {
            return _data.size();
        }

        @Override
        public boolean isViewFromObject(View _view, Object _object) {
            return _view == _object;
        }

        @Override
        public void destroyItem(ViewGroup _container, int _position, Object _object) {
            _container.removeView((View) _object);
        }

        @Override
        public int getItemPosition(Object _object) {
            return super.getItemPosition(_object);
        }

        @Override
        public CharSequence getPageTitle(int pos) {
            // Use the Activity Event (onTabLayoutNewTabAdded) in order to use this method
            return "page " + String.valueOf(pos);
        }

        @Override
        public Object instantiateItem(ViewGroup _container,  final int _position) {
            View _view = LayoutInflater.from(_context).inflate(R.layout.page, _container, false);
            final ImageView imageview1 = _view.findViewById(R.id.imageview1);
            Glide.with(getApplicationContext()).load(Uri.parse(map_view.get((int)_position).get("key").toString())).into(imageview1);
            _container.addView(_view);
            return _view;
        }
    }
}
