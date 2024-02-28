package com.aoveditor.phantomsneak;

import android.Manifest;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.*;
import android.net.Uri;
import android.os.*;
import android.view.View;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import java.util.Timer;
import java.util.TimerTask;
import android.provider.DocumentsContract;
import androidx.documentfile.provider.DocumentFile;
import java.security.Key;
import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URL;
import java.net.HttpURLConnection;
import android.app.Activity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import android.widget.LinearLayout.LayoutParams;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdSize;

import com.stericson.RootTools.*;
import android.os.Looper;
import java.lang.Process;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.aoveditor.phantomsneak.Utils.FileUtil;
import com.aoveditor.phantomsneak.Utils.SuperUserUtil;
import com.aoveditor.phantomsneak.Utils.ShizukuShellUtil;
import com.aoveditor.phantomsneak.Utils.SAFUtil;
import com.aoveditor.phantomsneak.network.RequestNetwork;
import com.aoveditor.phantomsneak.network.RequestNetworkController;

import rikka.shizuku.Shizuku;


public class SkinActivity extends AppCompatActivity {
    private Timer _timer = new Timer();
    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
    private boolean quit = false;
    private String lobby_string = "";
    private String other_string = "";
    private String skin_name = "";
    private String plugin = "";
    private Uri uriA;
    private String game_ver = "";
    private String video = "";
    private String language = "";
    private DocumentFile mfile;
    private DocumentFile mfile1;
    private Uri uri2;
    private Uri muri;
    private Uri uriB;
    private Uri uriC;
    private Uri uriD;
    public static final String IV = "1234567890148763";
    private static Key KEY;
    private String desurl = "";
    public InterstitialAd mInterstitialAd;
    private boolean haveSU = false;

    private ArrayList<HashMap<String, Object>> map1 = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> map2 = new ArrayList<>();
    private ArrayList<String> files_in_skin = new ArrayList<>();
    private ArrayList<String> file_list = new ArrayList<>();
    private Button button3;
    private Button button1;
    private Button button2;
    private VideoView videoview1;
    private TextView textview5;
    private TextView textview6;
    private AdView banner1;
    private ImageView imageview7;
    private ImageView imageview9;
    private ImageView imageview10;
    private ImageView imageview11;

    private Intent page = new Intent();
    private RequestNetwork net;
    private RequestNetwork.RequestListener _net_request_listener;
    private AlertDialog.Builder dialog;
    private DatabaseReference string_skin = _firebase.getReference("string_skin");
    private ChildEventListener _string_skin_child_listener;
    private DatabaseReference skin_plugin = _firebase.getReference("skin_pluging");
    private ChildEventListener _skin_plugin_child_listener;
    private AlertDialog.Builder delete;
    private TimerTask t;
    private TimerTask t_internet;

    private ShizukuShellUtil mShizukuShell = null;
    private List<String> mResult = null;



    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.skin);
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
        button3 = findViewById(R.id.button3);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        videoview1 = findViewById(R.id.videoview1);
        MediaController videoview1_controller = new MediaController(this);
        videoview1.setMediaController(videoview1_controller);
        textview5 = findViewById(R.id.textview5);
        textview6 = findViewById(R.id.textview6);
        banner1 = findViewById(R.id.banner1);
        imageview7 = findViewById(R.id.imageview7);
        imageview9 = findViewById(R.id.imageview9);
        imageview10 = findViewById(R.id.imageview10);
        imageview11 = findViewById(R.id.imageview11);
        net = new RequestNetwork(this);
        dialog = new AlertDialog.Builder(this);
        delete = new AlertDialog.Builder(this);

        AdRequest adRequest = new AdRequest.Builder().build();
        banner1.loadAd(adRequest);

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

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/".concat(skin_name))) {
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(SkinActivity.this);
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        if (ChooseUtilActivity.Method == "SU") {
                            if (SuperUserUtil.haveSU()) {
                                ProgressBar_Show("請稍等...");
                                new BackgroundTaskClass(SkinActivity.this){
                                    @Override
                                    public void doInBackground() {
                                        Looper.prepare();
                                        FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/Resources");
                                        _unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/"+(skin_name), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/Resources/");
                                    }
                                    @Override
                                    public void onPostExecute(){
                                        ProgressBar_Dismiss();
                                        ProgressBar_Show("啟用中...");
                                        new BackgroundTaskClass(SkinActivity.this){
                                            @Override
                                            public void doInBackground() {
                                                Looper.prepare();
                                                //root複製
                                                SuperUserUtil.cpWithSU("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/Resources", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/");
                                            }
                                            @Override
                                            public void onPostExecute(){
                                                ProgressBar_Dismiss();
                                                showMessage("完成");
                                                FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/Resources");
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
                                pageJump.setClass(SkinActivity.this, ChooseUtilActivity.class);
                                startActivity(pageJump);
                                finish();
                                overridePendingTransition(0, 0);
                            }

                        } else if (ChooseUtilActivity.Method == "SAF") {
                            try {
                                _startSkinSAF();
                            } catch (Exception e) {
                                showMessage(e.getMessage());
                            }

                        } else if (ChooseUtilActivity.Method == "Shizuku") {

                            /**重要，如果關閉服務，需要先判斷ping才能檢測是否granted，否則異常*/
                            if (Shizuku.pingBinder()) { //關閉 shizuku 服務就 ping 不到了
                                if (!(Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED)) {
                                    showMessage("由於您拒絕Shizuku權限\n請重新選擇存取方式");
                                    Intent pageJump = new Intent();
                                    pageJump.setClass(SkinActivity.this, ChooseUtilActivity.class);
                                    startActivity(pageJump);
                                    finish();
                                    overridePendingTransition(0, 0);
                                } else { // 這裡才能執行 shell

                                    ProgressBar_Show("請稍等...");
                                    new BackgroundTaskClass(SkinActivity.this){
                                        @Override
                                        public void doInBackground() {
                                            Looper.prepare();
                                            FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/Resources");
                                            _unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/"+(skin_name), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/Resources/");
                                        }
                                        @Override
                                        public void onPostExecute(){
                                            ProgressBar_Dismiss();
                                            StartInitializeShell("cp -r " + "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/Resources" + " " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/");
                                            waitForShizukuCompletion(() -> FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/Resources"));
                                            showMessage("完成");
                                        }
                                    }.execute();
                                    //結束
                                }
                            } else {
                                showMessage("由於您關閉Shizuku服務\n請重新選擇存取方式");
                                Intent pageJump = new Intent();
                                pageJump.setClass(SkinActivity.this, ChooseUtilActivity.class);
                                startActivity(pageJump);
                                finish();
                                overridePendingTransition(0, 0);
                            }

                        }
                    } else {
                        _unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/".concat(skin_name), "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/");
                        showMessage("完成");
                    }
                } else {
                    FileUtil.deleteFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/1-skin"));
                    FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/1-skin"));
                    Download(plugin, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/");
                    showMessage("下載完成再次點擊以啟用");
                    repair("https://" + language);
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin");
                FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin");
                showMessage("刪除成功");
                textview5.setText("無造型插件");
                button3.setText("下載插件");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                delete.setTitle("提醒");
                delete.setIcon(R.drawable.downloadlogo);
                delete.setMessage("按下確認後，還原遊戲造型資源\n需進入遊戲重新下載前置資源");
                delete.setCancelable(false);
                delete.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            if (ChooseUtilActivity.Method == "SU") {
                                if (SuperUserUtil.haveSU()) {
                                    SuperUserUtil.rmWithSU("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources");
                                    showMessage("還原成功");
                                    Intent mIntent = getPackageManager().getLaunchIntentForPackage("com.garena.game.kgtw");
                                    startActivity(mIntent);
                                    finishAffinity();
                                } else {
                                    showMessage("你已撤銷 root 權限");
                                    MainActivity.haveSU = false;
                                    Intent pageJump = new Intent();
                                    pageJump.setClass(SkinActivity.this, ChooseUtilActivity.class);
                                    startActivity(pageJump);
                                    finish();
                                    overridePendingTransition(0, 0);
                                }

                            } else if (ChooseUtilActivity.Method == "SAF") {
                                try {
                                    SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources");
                                    showMessage("還原成功");
                                    Intent mIntent = getPackageManager().getLaunchIntentForPackage("com.garena.game.kgtw");
                                    startActivity(mIntent);
                                    finishAffinity();
                                } catch (Exception e) {
                                    showMessage("還原失敗");
                                    showMessage("請按遊戲設置的「一鍵恢復」");
                                }

                            } else if (ChooseUtilActivity.Method == "Shizuku") {

                                /**重要，如果關閉服務，需要先判斷ping才能檢測是否granted，否則異常*/
                                if (Shizuku.pingBinder()) { //關閉 shizuku 服務就 ping 不到了
                                    if (!(Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED)) {
                                        showMessage("由於您拒絕Shizuku權限\n請重新選擇存取方式");
                                        Intent pageJump = new Intent();
                                        pageJump.setClass(SkinActivity.this, ChooseUtilActivity.class);
                                        startActivity(pageJump);
                                        finish();
                                        overridePendingTransition(0, 0);
                                    } else { // 這裡才能執行 shell
                                        StartInitializeShell("rm -r " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources");
                                        waitForShizukuCompletion(() -> EndShizukuShellAndJumpToAOV());
                                    }
                                } else {
                                    showMessage("由於您關閉Shizuku服務\n請重新選擇存取方式");
                                    Intent pageJump = new Intent();
                                    pageJump.setClass(SkinActivity.this, ChooseUtilActivity.class);
                                    startActivity(pageJump);
                                    finish();
                                    overridePendingTransition(0, 0);
                                }

                            }
                        } else {
                            FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources");
                            showMessage("還原完成");
                            Intent mIntent = getPackageManager().getLaunchIntentForPackage("com.garena.game.kgtw");
                            startActivity(mIntent);
                            finishAffinity();
                        }
                    }
                });
                delete.setNeutralButton("取消", null);
                delete.create().show();
            }
        });

        videoview1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer _mediaPlayer) {

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

        imageview9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                page.setClass(getApplicationContext(), VoiceActivity.class);
                startActivity(page);
                overridePendingTransition(0, 0);
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
                        } catch (Exception _e) {
                            _e.printStackTrace();
                        }
                        lobby_string = map1.get((int)0).get("2-lobby").toString();
                        other_string = map1.get((int)0).get("3-other").toString();
                        game_ver = map1.get((int)0).get("ver").toString();
                        language = map1.get((int)0).get("language").toString();
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

        _skin_plugin_child_listener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot _param1, String _param2) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);
                skin_plugin.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot _dataSnapshot) {
                        map2 = new ArrayList<>();
                        try {
                            GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                            for (DataSnapshot _data : _dataSnapshot.getChildren()) {
                                HashMap<String, Object> _map = _data.getValue(_ind);
                                map2.add(_map);
                            }
                        } catch (Exception _e) {
                            _e.printStackTrace();
                        }
                        //skin_name = map2.get((int)0).get("plugin").toString();
                        //plugin = map2.get((int)0).get("zurl").toString();
                        skin_name = map2.get((int)0).get("plugin_ver").toString();
                        plugin = map2.get((int)0).get("z_url").toString();
                        video = map2.get((int)0).get("video_url").toString();
                        textview6.setText(skin_name);
                        videoview1.setMediaController(null);
                        videoview1.setVideoURI(Uri.parse(video));
                        videoview1.start();
                        if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/".concat(skin_name))) {
                            button3.setText("啟用");
                        } else {
                            button3.setText("下載插件");
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
        skin_plugin.addChildEventListener(_skin_plugin_child_listener);
    }

    private void initializeLogic() {
        quit = false;
        FileUtil.listDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/", files_in_skin);
        if (files_in_skin.size() == 0) {
            textview5.setText("無造型插件");
        } else {
            textview5.setText(Uri.parse(files_in_skin.get((int)(0))).getLastPathSegment());
        }
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


    // url = file path or whatever suitable URL you want.
    public static String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }
    private int count;
    private int internet;
    public void Decryption(){
        try{
            String stringKey = "F0UKlJdM5s8bY6uksC8763==";
            byte[] encodedKey = Base64.decode(stringKey, Base64.DEFAULT);
            KEY = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, KEY, iv);
            byte[] bytes = cipher.doFinal(Base64.decode(plugin, Base64.DEFAULT));
            desurl = new String(bytes);
            //showMessage (desurl);
        }catch (Exception e){}
    }


    public void Download(String Url, String Path) {
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
                            final int currentProgress = (int) ((((double)downloadedFileSize) / ((double)completeFileSize)) * 100000d);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    prog.setTitle("下載造型插件中...");
                                    prog.setMessage("正在下載 " + filename + "\n下載進度：" + (currentProgress/1000) + "%");
                                    prog.show();
                                    textview5.setText(skin_name);
                                    button3.setText("啟用");
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

    public void repair(String Url){
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
                                }
                            });
                            bout.write(data, 0, x);
                        }
                        bout.close();
                        in.close();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            if (ChooseUtilActivity.Method == "SU") {
                                SuperUserUtil.cpWithSU("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/languageMap.txt", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + game_ver + "/Languages/CHT_Garena_TW/");
                                FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/languageMap.txt");
                            
                            } else if (ChooseUtilActivity.Method == "SAF") {
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

                            } else if (ChooseUtilActivity.Method == "Shizuku") {

                                /**重要，如果關閉服務，需要先判斷ping才能檢測是否granted，否則異常*/
                                if (Shizuku.pingBinder()) { //關閉 shizuku 服務就 ping 不到了
                                    if (!(Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED)) {
                                        showMessage("由於您拒絕Shizuku權限\n請重新選擇存取方式");
                                        Intent pageJump = new Intent();
                                        pageJump.setClass(SkinActivity.this, ChooseUtilActivity.class);
                                        startActivity(pageJump);
                                        finish();
                                        overridePendingTransition(0, 0);
                                    } else { // 這裡才能執行 shell
                                        StartInitializeShell("cp -r /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/languageMap.txt /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + game_ver + "/Languages/CHT_Garena_TW/");
                                        waitForShizukuCompletion(() ->
                                                //刪除 app 目錄下的 languageMap
                                                FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/languageMap.txt"));
                                    }
                                } else {
                                    showMessage("由於您關閉Shizuku服務\n請重新選擇存取方式");
                                    Intent pageJump = new Intent();
                                    pageJump.setClass(SkinActivity.this, ChooseUtilActivity.class);
                                    startActivity(pageJump);
                                    finish();
                                    overridePendingTransition(0, 0);
                                }

                            }
                        }
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
        prog2 = new ProgressDialog(SkinActivity.this);
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


    public void _startSkinSAF() {
        FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/1-skin/tmp"));
        _unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/".concat(skin_name), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/");
        uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FActor%2F"+"heroSkin.bytes");
        try {
            try{
                DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
            } catch (FileNotFoundException e) {
            }
            uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FActor%2F");
            SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/Databin/Client/Actor/heroSkin.bytes", uri2);
        } catch (Exception e) {
        }
        uriB = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FCharacter%2F"+"ResCharacterComponent.bytes");
        try {
            try{
                DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriB);
            } catch (FileNotFoundException e) {
            }
            uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FCharacter%2F");
            SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/Databin/Client/Character/ResCharacterComponent.bytes", uri2);
        } catch (Exception e) {
        }
        uriB = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FCharacter%2F"+"ResComponentShow.bytes");
        try {
            try{
                DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriB);
            } catch (FileNotFoundException e) {
            }
            uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FCharacter%2F");
            SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/Databin/Client/Character/ResComponentShow.bytes", uri2);
        } catch (Exception e) {
        }
        uriC = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FSkill%2F"+"liteBulletCfg.bytes");
        try {
            try{
                DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriC);
            } catch (FileNotFoundException e) {
            }
            uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FSkill%2F");
            SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/Databin/Client/Skill/liteBulletCfg.bytes", uri2);
        } catch (Exception e) {
        }
        uriC = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FSkill%2F"+"skillmark.bytes");
        try {
            try{
                DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriC);
            } catch (FileNotFoundException e) {
            }
            uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FSkill%2F");
            SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/Databin/Client/Skill/skillmark.bytes", uri2);
        } catch (Exception e) {
        }
        uriD = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FSound%2F"+"BattleBank.bytes");
        try {
            try{
                DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriD);
            } catch (FileNotFoundException e) {
            }
            uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FSound%2F");
            SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/Databin/Client/Sound/BattleBank.bytes", uri2);
        } catch (Exception e) {
        }
        uriD = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FSound%2F"+"ChatSound.bytes");
        try {
            try{
                DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriD);
            } catch (FileNotFoundException e) {
            }
            uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FSound%2F");
            SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/Databin/Client/Sound/ChatSound.bytes", uri2);
        } catch (Exception e) {
        }
        uriD = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FSound%2F"+"HeroSound.bytes");
        try {
            try{
                DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriD);
            } catch (FileNotFoundException e) {
            }
            uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FSound%2F");
            SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/Databin/Client/Sound/HeroSound.bytes", uri2);
        } catch (Exception e) {
        }
        uriD = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FSound%2F"+"LobbyBank.bytes");
        try {
            try{
                DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriD);
            } catch (FileNotFoundException e) {
            }
            uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FSound%2F");
            SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/Databin/Client/Sound/LobbyBank.bytes", uri2);
        } catch (Exception e) {
        }
        uriD = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FSound%2F"+"LobbySound.bytes");
        try {
            try{
                DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriD);
            } catch (FileNotFoundException e) {
            }
            uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FSound%2F");
            SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/Databin/Client/Sound/LobbySound.bytes", uri2);
        } catch (Exception e) {
        }

        FileUtil.listDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/Ages/Prefab_Characters/Prefab_Hero/", file_list);
        count=file_list.size();
        final ProgressDialog prog = new ProgressDialog(SkinActivity.this);
        prog.setIcon(R.drawable.downloadlogo);
        prog.setMax(100);
        prog.setIndeterminate(true);
        prog.setCancelable(false);
        prog.setCanceledOnTouchOutside(false);
        prog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        prog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int j = 0; j < (int)(file_list.size()); j++) {
                    SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FAges%2FPrefab_Characters%2FPrefab_Hero%2F"+Uri.parse(file_list.get((count - 1))).getLastPathSegment());
                    uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FAges%2FPrefab_Characters%2FPrefab_Hero%2F");
                    SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/Ages/Prefab_Characters/Prefab_Hero/" + Uri.parse(file_list.get(count - 1)).getLastPathSegment(), uri2);
                    count--;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            prog.setTitle("2 / 4 步驟啟用中...");
                            prog.setMessage("\n剩餘檔案數量："+count);
                            prog.show();
                            if (count == 0) {
                                prog.dismiss();
                                file_list.clear();

                                FileUtil.listDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/AssetRefs/Hero/", file_list);
                                count=file_list.size();
                                final ProgressDialog prog = new ProgressDialog(SkinActivity.this);
                                prog.setIcon(R.drawable.downloadlogo);
                                prog.setMax(100);
                                prog.setIndeterminate(true);
                                prog.setCancelable(false);
                                prog.setCanceledOnTouchOutside(false);
                                prog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                prog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        for(int k = 0; k < (int)(file_list.size()); k++) {
                                            SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FAssetRefs%2FHero%2F"+Uri.parse(file_list.get((count - 1))).getLastPathSegment());
                                            uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FAssetRefs%2FHero%2F");
                                            SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/AssetRefs/Hero/" + Uri.parse(file_list.get(count - 1)).getLastPathSegment(), uri2);
                                            count--;
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    prog.setTitle("3 / 4 步驟啟用中...");
                                                    prog.setMessage("\n剩餘檔案數量："+count);
                                                    prog.show();
                                                    if (count == 0) {
                                                        prog.dismiss();
                                                        file_list.clear();

                                                        FileUtil.listDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/Prefab_Characters/", file_list);
                                                        count=file_list.size();
                                                        final ProgressDialog prog = new ProgressDialog(SkinActivity.this);
                                                        prog.setIcon(R.drawable.downloadlogo);
                                                        prog.setMax(100);
                                                        prog.setIndeterminate(true);
                                                        prog.setCancelable(false);
                                                        prog.setCanceledOnTouchOutside(false);
                                                        prog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                                        prog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
                                                        new Thread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                for(int l = 0; l < (int)(file_list.size()); l++) {
                                                                    SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FPrefab_Characters%2F"+Uri.parse(file_list.get((count - 1))).getLastPathSegment());
                                                                    uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FPrefab_Characters%2F");
                                                                    SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/Prefab_Characters/" + Uri.parse(file_list.get(count - 1)).getLastPathSegment(), uri2);
                                                                    count--;
                                                                    runOnUiThread(new Runnable() {
                                                                        @Override
                                                                        public void run() {
                                                                            prog.setTitle("4 / 4 步驟啟用中...");
                                                                            prog.setMessage("\n剩餘檔案數量："+count);
                                                                            prog.show();
                                                                            if (count == 0) {
                                                                                prog.dismiss();
                                                                                file_list.clear();
                                                                                FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp");
                                                                                showMessage("完成");
                                                                            }
                                                                        }
                                                                    });
                                                                }
                                                            }
                                                        }).start();
                                                    }
                                                }
                                            });
                                        }
                                    }
                                }).start();
                            }
                        }
                    });
                }
            }
        }).start();
    }
    

    private void EndShizukuShellAndJumpToAOV() {
        try {
            if (mShizukuShell != null)
                mShizukuShell.destroy();
        } catch (Exception r) {
        }
        showMessage("還原成功");
        Intent mIntent = getPackageManager().getLaunchIntentForPackage("com.garena.game.kgtw");
        startActivity(mIntent);
        finishAffinity();
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
