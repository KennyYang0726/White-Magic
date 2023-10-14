package com.aoveditor.phantomsneak;

import android.Manifest;
import android.animation.*;
import android.annotation.SuppressLint;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.*;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnAdapterChangeListener;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import com.bumptech.glide.Glide;
import com.example.RootTools.*;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.ktx.*;
import com.stericson.RootShell.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;

import org.jetbrains.annotations.NotNull;
import org.json.*;
import java.io.*;
import java.util.zip.*;
import android.provider.DocumentsContract;
import android.provider.DocumentsContract.Document;
import android.provider.Settings;
import android.database.*;
import androidx.documentfile.provider.DocumentFile;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import java.net.URL;
import java.net.HttpURLConnection;
import android.content.res.AssetFileDescriptor;

import com.stericson.RootTools.*;
import android.os.Looper;
import java.lang.Process;


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
    private Uri uriC;
    private String game_ver = "";
    private String language = "";
    private String res_access = "";
    private String extra_access = "";
    private String tip_open = "";
    private String tip = "";
    private String last_ver2 = "";
    private String ISPDiff_access = "";
    private int re_scan_cnt = 0;
    private boolean test_ver = false;
    private static final int PERMISSION_REQUEST_CODE = 200;
    private boolean first_install = false;
    private boolean haveSU = false;

    private ArrayList<HashMap<String, Object>> map1 = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> map_view = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> map2 = new ArrayList<>();

    private ScrollView vscroll1;
    private LinearLayout linear7;
    private LinearLayout linear1;
    private LinearLayout linear2;
    private ViewPager viewpager1;
    private TextView textview3;
    private TextView textview4;
    private ImageView imageview3;
    private TextView textview5;
    private TextView textview6;
    private ImageView imageview4;
    private TextView textview9;
    private ImageView imageview5;
    private LinearLayout linear4;
    private Button button1;
    private TextView textview1;
    private TextView textview2;
    private Button button2;
    private ImageView imageview6;
    private Button button3;
    private AdView banner0;
    private ImageView imageview7;
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
    private TimerTask t_rename_test;
    private AlertDialog.Builder hint;
    private DatabaseReference hints = _firebase.getReference("hint");
    private ChildEventListener _hints_child_listener;
    private TimerTask firsttime_getmyver;
    private TimerTask t_internet;
    private TimerTask t_permission_delay;
    private AlertDialog.Builder res;

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

    private void initialize(Bundle _savedInstanceState) {
        _app_bar = findViewById(R.id._app_bar);
        _coordinator = findViewById(R.id._coordinator);
        _toolbar = findViewById(R.id._toolbar);
        setSupportActionBar(_toolbar);
        vscroll1 = findViewById(R.id.vscroll1);
        linear7 = findViewById(R.id.linear7);
        linear1 = findViewById(R.id.linear1);
        linear2 = findViewById(R.id.linear2);
        viewpager1 = findViewById(R.id.viewpager1);
        textview3 = findViewById(R.id.textview3);
        textview4 = findViewById(R.id.textview4);
        imageview3 = findViewById(R.id.imageview3);
        textview5 = findViewById(R.id.textview5);
        textview6 = findViewById(R.id.textview6);
        imageview4 = findViewById(R.id.imageview4);
        textview9 = findViewById(R.id.textview9);
        imageview5 = findViewById(R.id.imageview5);
        linear4 = findViewById(R.id.linear4);
        button1 = findViewById(R.id.button1);
        textview1 = findViewById(R.id.textview1);
        textview2 = findViewById(R.id.textview2);
        button2 = findViewById(R.id.button2);
        imageview6 = findViewById(R.id.imageview6);
        button3 = findViewById(R.id.button3);
        banner0 = findViewById(R.id.banner0);
        imageview7 = findViewById(R.id.imageview7);
        imageview8 = findViewById(R.id.imageview8);
        imageview9 = findViewById(R.id.imageview9);
        imageview10 = findViewById(R.id.imageview10);
        imageview11 = findViewById(R.id.imageview11);
        sp = getSharedPreferences("sp", Activity.MODE_PRIVATE);
        net = new RequestNetwork(this);
        dialog = new AlertDialog.Builder(this);
        delete = new AlertDialog.Builder(this);
        hint = new AlertDialog.Builder(this);
        res = new AlertDialog.Builder(this);

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
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources");
                            try {
                                try{
                                    DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);

                                } catch (FileNotFoundException e) {

                                }
                            } catch (Exception e) {

                            }
                            uriB = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC");
                            try {
                                try{
                                    DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriB);

                                } catch (FileNotFoundException e) {

                                }
                            } catch (Exception e) {

                            }
                            uriC = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FISPDiff%2FLobbyMovie");
                            try {
                                try{
                                    DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriC);

                                } catch (FileNotFoundException e) {

                                }
                            } catch (Exception e) {

                            }
                            showMessage("還原完畢！");
                        } else {
                            FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources");
                            FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC");
                            FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/ISPDiff/LobbyMovie");
                            showMessage("還原完畢！");
                        }
                    }
                });
                delete.setNeutralButton("取消", null);
                delete.create().show();
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
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            _get_permission();
                        }
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
                            hint.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface _dialog, int _which) {

                                }
                            });
                            hint.create().show();
                        } else {

                        }
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) { //讓root的安卓11以上可用root模式啟用(もっと速く)
                            new Thread() {
                                public void run() {
                                    if (_RootAccess()) {
                                        haveSU = true;
                                    }
                                    else {
                                        haveSU = false;
                                    }
                                }
                            }.start();
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
        FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/1-skin"));
        FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/2-voice"));
        FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/3-lobby"));
        FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/4-other"));
        quit = false;
        _Internet();
        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("key", "https://cdn.discordapp.com/attachments/872037042337484840/934379368665448468/50112.png");
            map_view.add(_item);
        }

        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("key", "https://cdn.discordapp.com/attachments/872037042337484840/927256305368981594/Genos1.png");
            map_view.add(_item);
        }

        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("key", "https://cdn.discordapp.com/attachments/872037042337484840/927467247339974666/NAK.png");
            map_view.add(_item);
        }

        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("key", "https://cdn.discordapp.com/attachments/872037042337484840/927411590641356870/diaochan.png");
            map_view.add(_item);
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
            try {
                muri = Uri.parse(sp.getString("DIRECT_FOLDER_URI", ""));
                mfile = DocumentFile.fromTreeUri(this, muri);

                if (!sp.getString("FOLDER_URI", "").equals("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw")) {
                    showMessage("請直接按使用這個資料夾");
                    first_install = false;
                    _askPermission(linear1);
                }
                if (!mfile.canRead() || !mfile.canWrite()) {
                    first_install = true;
                    _askPermission(linear1);
                } else {
                    parenturi = Uri.parse(sp.getString("FOLDER_URI", ""));
                    first_install = false;
                }
            } catch (Exception e) {
                first_install = true;
                _askPermission(linear1);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_DENIED) {
                    showMessage("無通知權限");
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, PERMISSION_REQUEST_CODE);
                }
            }
        } else{
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
                        showMessage("請直接按使用這個資料夾");
                        _askPermission(linear1);
                    }
                    parenturi = Uri.parse(sp.getString("FOLDER_URI", ""));
                }
            } else {
            }
        } else {
            showMessage("你拒絕了授權！");
            finishAffinity();
        }
        switch (_requestCode) {
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
        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        i.setAction(Intent.ACTION_OPEN_DOCUMENT_TREE);
        muri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw");
        i.putExtra(DocumentsContract.EXTRA_INITIAL_URI, muri);
        startActivityForResult(i, NEW_FOLDER_REQUEST_CODE);
    }

    /*使選項內Icon與文字並存*/
    private CharSequence menuIconWithText(Drawable r, String title) {
        r.setBounds(0, 0, r.getIntrinsicWidth(), r.getIntrinsicHeight());
        SpannableString sb = new SpannableString("    " + title);
        ImageSpan imageSpan = new ImageSpan(r, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }
    /*程式中新增MenuItem選項*/
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        /**itemId為稍後判斷點擊事件要用的*/
        menu.add(0,0,0,menuIconWithText(getDrawable(R.drawable.youtube),"YouTube"));
        menu.add(0,1,1,menuIconWithText(getDrawable(R.drawable.discord),"Discord"));
        menu.add(0,2,2,menuIconWithText(getDrawable(R.drawable.github),"Github開源"));
        menu.add(0,3,3,menuIconWithText(getDrawable(R.drawable.rate),"評分"));
        menu.add(0,4,4,menuIconWithText(getDrawable(R.drawable.more_apps),"更多apps"));
        return super.onCreateOptionsMenu(menu);
    }
    /*此處為設置點擊事件*/
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

            uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2F"+uridelete);
            if (uridelete.contains("Sound_DLC")){
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
    //檢測Uri文件大小
    private boolean URI(Uri uri) {
        try{
            AssetFileDescriptor fileDescriptor = getApplicationContext().getContentResolver().openAssetFileDescriptor(uri , "r");
            long fileSize = fileDescriptor.getLength();
            return true;
        } catch (Exception e) {
            showMessage("跑到這0");
            return false;
        }
    }

    //Unzip assets with replace
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

    // url = file path or whatever suitable URL you want.
    public static String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }
    public boolean copyFilePath2Uri(Context context, File inputfile, Uri targetUri){
        InputStream fis = null;
        OutputStream fos = null;

        try {

            ContentResolver content = context.getContentResolver();
            fis = new FileInputStream(inputfile);
            fos = content.openOutputStream(targetUri);

            byte[] buff = new byte[1024];
            int length = 0;

            while ((length = fis.read(buff)) > 0) {
                fos.write(buff, 0, length);
            }
        } catch (IOException e) {
            return false;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    return false;
                }
            }
            if (fos != null) {
                try {
                    fos.close();

                } catch (IOException e) {
                    return false;
                }
            }
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
                            uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FLanguages%2FCHT_Garena_TW%2FlanguageMap.txt")));
                            try {
                                try{
                                    DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
                                } catch (FileNotFoundException e) {
                                }
                                uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FLanguages%2FCHT_Garena_TW%2F")));
                                _copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/languageMap.txt");
                                FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/languageMap.txt");
                                showMessage("修復成功");
                            } catch (Exception e) {
                                uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FLanguages%2FCHT_Garena_TW%2F")));
                                _copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/languageMap.txt");
                                FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/languageMap.txt");
                                showMessage("修復成功");
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
                                                            if (ISPDiff_access.equals("❌") || (res_access.equals("❌") || extra_access.equals("❌"))) {
                                                                if (res_access.equals("❌")) {
                                                                    showMessage("請確認是否下載遊戲登入畫面前置資源");
                                                                    showMessage("或嘗試再次點擊以自動授權");
                                                                }
                                                                final ImageView image = new ImageView(HomeActivity.this);
                                                                image.setImageResource(R.drawable.click_me);
                                                                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                                                                builder.setTitle("獲取權限資訊狀態")
                                                                        .setMessage("Resources："+res_access+"\n\n①Sound_DLC："+extra_access+"\n②LobbyMovie："+ISPDiff_access+"\n(①②特殊型可自動授權)\n")
                                                                        .setView(image)
                                                                        .setIcon(R.drawable.downloadlogo)
                                                                        .setCancelable(false)
                                                                        .setPositiveButton("重新檢測", new DialogInterface.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(DialogInterface dialog, int id) {
                                                                                if (haveSU && res_access.equals("❌") && ISPDiff_access.equals("⭕") && extra_access.equals("⭕")){
                                                                                    showMessage("以Root模式繼續\n忽略Res授權");
                                                                                    showMessage("請勿在離開此app前\n移除超級使用者權限");
                                                                                } else {
                                                                                    try{
                                                                                        _get_permission();
                                                                                    }catch(Exception e){
                                                                                        showMessage(e.getMessage());
                                                                                    }
                                                                                    re_scan_cnt++;
                                                                                }
                                                                            }
                                                                        });
                                                                image.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View _view) {
                                                                        Intent browserintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/kw7AxRIRg3s"));
                                                                        startActivity(browserintent);
                                                                    }
                                                                });
                                                                if (re_scan_cnt >= 2) {
																	    /*
    if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/cache/res")) {
    builder.setNegativeButton("授權Res", new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int id) {
			//自動授權Res
            _auto_access_res();
		}
	});
    }*/
                                                                    builder.setNeutralButton("授權①②", new DialogInterface.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(DialogInterface dialog, int id) {
                                                                            page.setClass(getApplicationContext(), WarningActivity.class);
                                                                            startActivity(page);
                                                                            overridePendingTransition(0, 0);
                                                                        }
                                                                    });
                                                                }
                                                                AlertDialog alert1 = builder.create();
                                                                alert1.show();

																/*
AlertDialog.Builder builder2 = new AlertDialog.Builder(HomeActivity.this);
builder.setTitle("請稍等...")
.setCancelable(false);
AlertDialog alert2 = builder2.create();
alert2.show();*/

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


    public void _copyFilePath2Uri(final String _OriginalFilePath) {
        File mfile6 = new File(_OriginalFilePath);
        mfile1 = DocumentFile.fromTreeUri(this, uri2);

        mfile1 = mfile1.createFile(getMimeType(_OriginalFilePath), Uri.parse(_OriginalFilePath).getLastPathSegment());
        uri2 = mfile1.getUri();
        if (copyFilePath2Uri(HomeActivity.this, mfile6, uri2)) {
            try {

            } catch (Exception e) {

            }
        } else {
            try {
                showMessage("失敗");
            } catch (Exception e) {

            }
        }

    }


    public void _Shape(final double _t1, final double _t2, final double _b1, final double _b2, final String _Background, final double _Stroke, final String _stroke, final double _Elevation, final View _view) {
        android.graphics.drawable.GradientDrawable gs = new android.graphics.drawable.GradientDrawable();

        gs.setColor(Color.parseColor(_Background));

        gs.setStroke((int)_Stroke, Color.parseColor(_stroke));

        gs.setCornerRadii(new float[]{(int)_t1,(int)_t1,(int)_t2,(int)_t2,(int)_b1,(int)_b1,(int)_b2,(int)_b2});

        _view.setBackground(gs);
        _view.setElevation((int)_Elevation);
    }


    public void _auto_access_res() {
        ProgressBar_Show("處理中...");
        new BackgroundTaskClass(HomeActivity.this){
            @Override
            public void doInBackground() {
                uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F" + game_ver + "%2FAges%2FPrefab_Characters%2FPrefab_Hero%2FActor_150_Actions.pkg.bytes");
                try{
                    AssetFileDescriptor fileDescriptor = getApplicationContext().getContentResolver().openAssetFileDescriptor(uriA , "r");
                    renameTo("/files/Resources/" + game_ver + "/Ages/Prefab_Characters/Prefab_Hero", "Actor_150_Actions.pkg.bytes", "Actor_150_Actions.pkg.bytesss");
                    renameTo("/files/Resources/" + game_ver + "/Ages/Prefab_Characters/Prefab_Hero", "Actor_150_Actions.pkg.bytesss", "Actor_150_Actions.pkg.bytes");
                } catch (Exception e) { // 1/3
                    renameTo("/files/Resources", game_ver, game_ver + "_trash1");
                    Res_Status = 1;
                }
            }
            @Override
            public void onPostExecute(){
                //完成(以Res_Status判斷情形)
                ProgressBar_Dismiss();
                if (Res_Status == 1){ // 1/3
                    res.setIcon(R.drawable.downloadlogo);
                    res.setTitle("第一步驟成功");
                    res.setMessage("您處於特殊型 1/3 步驟完成狀態，請進入遊戲重新下載資源後，重新回白魔法進行檢測。");
                    res.setCancelable(false);
                    res.setPositiveButton("進入遊戲", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface _dialog, int _which) {
                            Intent mIntent = getPackageManager().getLaunchIntentForPackage("com.garena.game.kgtw");
                            startActivity(mIntent);
                            finishAffinity();
                        }
                    });
                    res.create().show();
                } else if (Res_Status == 2) { // 2/3
                    res.setIcon(R.drawable.downloadlogo);
                    res.setTitle("第二步驟成功");
                    res.setMessage("您處於特殊型 2/3 步驟完成狀態，請進入遊戲重新下載資源後，重新回白魔法進行檢測。");
                    res.setCancelable(false);
                    res.setPositiveButton("進入遊戲", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface _dialog, int _which) {
                            Intent mIntent = getPackageManager().getLaunchIntentForPackage("com.garena.game.kgtw");
                            startActivity(mIntent);
                            finishAffinity();
                        }
                    });
                    res.create().show();
                }
            }
        }.execute();
        //結束
    }


    public boolean _RootAccess() {
        if (RootTools.isRootAvailable()){
            //該手機已root
            try { //會跳出視窗
                java.util.Scanner s = new java.util.Scanner(Runtime.getRuntime().exec(new String[]{"/system/bin/su","-c","cd / && ls"}).getInputStream()).useDelimiter("\\A");
                //true為有root且允許，false為有root但不允許
                return !(s.hasNext() ? s.next() : "").equals("");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }else {
            //該手機無root
            return false;
        }
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
