package com.aoveditor.phantomsneak;

import android.Manifest;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.*;
import android.net.Uri;
import android.os.*;
import android.provider.OpenableColumns;
import android.view.*;
import android.view.View;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import android.provider.DocumentsContract;
import androidx.documentfile.provider.DocumentFile;
import java.net.URL;
import java.net.HttpURLConnection;

import android.app.Activity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import com.stericson.RootTools.*;
import android.os.Looper;
import java.lang.Process;

import android.os.Build;
//aes
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.util.concurrent.Executors;
//120fps
import android.hardware.display.DisplayManager;


public class OtherActivity extends AppCompatActivity {

    private Timer _timer = new Timer();
    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();

    private boolean quit = false;
    private Uri muri;
    private Uri uri2;
    private DocumentFile mfile;
    private DocumentFile mfile1;
    private String wiro = "";
    private String Volkath = "";
    private String game_ver = "";
    private Uri uriA;
    private Uri uriB;
    private Uri uriC;
    private Uri uriD;
    private Uri uriE;
    private Uri uriF;
    private boolean assetbundle_access;
    private String tower = "";
    private String monster = "";
    private String soldier = "";
    private String skin_string = "";
    private String lobby_string = "";
    private boolean haveSU = false;
    private String soldier0 = "";
    private String monster0 = "";
    private String tower0 = "";
    private String GOD = "";
    private String GOD_URL = "";
    private String Device_ID = "";

    private ArrayList<HashMap<String, Object>> map1 = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> map2 = new ArrayList<>();

    //SAF Main Var
    private final String mAndroidDataDocId = "primary:Android/data/com.garena.game.kgtw";
    private final Uri mAndroidUri = DocumentsContract.buildTreeDocumentUri(
            "com.android.externalstorage.documents", "primary:Android"
    );
    private int currentLevel = -1;
    private int fileCount = 0;
    private ContentResolver mContentResolver;

    //Layout Object var
    private LinearLayout linear16;
    private ScrollView vscroll1;
    private LinearLayout linear7;
    private ImageView imageview15;
    private TextView textview1;
    private Button button13;
    private LinearLayout linear8;
    private ImageView imageview12;
    private LinearLayout linear9;
    private ImageView imageview17;
    private ImageView imageview18;
    private LinearLayout linear10;
    private ImageView imageview25;
    private ImageView imageview23;
    private LinearLayout linear11;
    private ImageView imageview20;
    private ImageView imageview21;
    private LinearLayout linear12;
    private ImageView imageview32;
    private ImageView imageview27;
    private LinearLayout linear13;
    private ImageView imageview36;
    private ImageView imageview33;
    private LinearLayout linear18;
    private ImageView imageview39;
    private ImageView imageview37;
    private LinearLayout linear19;
    private ImageView imageview38;
    private LinearLayout bannerAd;
    private Button button1;
    private ImageView imageview13;
    private Button button2;
    private Button button3;
    private ImageView imageview19;
    private Button button4;
    private Button button5;
    private ImageView imageview22;
    private Button button6;
    private Button button7;
    private ImageView imageview26;
    private Button button8;
    private Button button9;
    private ImageView imageview29;
    private Button button10;
    private Button button16;
    private ImageView imageview35;
    private Button button17;
    private Button button18;
    private Button button19;
    private Button button20;
    private AdView banner4;
    private ImageView imageview7;
    private ImageView imageview8;
    private ImageView imageview9;
    private ImageView imageview10;
    private ImageView imageview11;

    private RequestNetwork net;
    private RequestNetwork.RequestListener _net_request_listener;
    private AlertDialog.Builder dialog;
    private Intent page = new Intent();
    private DatabaseReference string_skin = _firebase.getReference("string_skin");
    private ChildEventListener _string_skin_child_listener;
    private TimerTask t_delay;
    private DatabaseReference Other = _firebase.getReference("Other");
    private ChildEventListener _Other_child_listener;
    private AlertDialog.Builder delete;
    private TimerTask t_internet;
    private AlertDialog.Builder dialog_120fps;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.other);
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
        linear16 = findViewById(R.id.linear16);
        vscroll1 = findViewById(R.id.vscroll1);
        linear7 = findViewById(R.id.linear7);
        imageview15 = findViewById(R.id.imageview15);
        textview1 = findViewById(R.id.textview1);
        button13 = findViewById(R.id.button13);
        linear8 = findViewById(R.id.linear8);
        imageview12 = findViewById(R.id.imageview12);
        linear9 = findViewById(R.id.linear9);
        imageview17 = findViewById(R.id.imageview17);
        imageview18 = findViewById(R.id.imageview18);
        linear10 = findViewById(R.id.linear10);
        imageview25 = findViewById(R.id.imageview25);
        imageview23 = findViewById(R.id.imageview23);
        linear11 = findViewById(R.id.linear11);
        imageview20 = findViewById(R.id.imageview20);
        imageview21 = findViewById(R.id.imageview21);
        linear12 = findViewById(R.id.linear12);
        imageview32 = findViewById(R.id.imageview32);
        imageview27 = findViewById(R.id.imageview27);
        linear13 = findViewById(R.id.linear13);
        imageview36 = findViewById(R.id.imageview36);
        imageview33 = findViewById(R.id.imageview33);
        linear18 = findViewById(R.id.linear18);
        imageview39 = findViewById(R.id.imageview39);
        imageview37 = findViewById(R.id.imageview37);
        linear19 = findViewById(R.id.linear19);
        button1 = findViewById(R.id.button1);
        imageview13 = findViewById(R.id.imageview13);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        imageview19 = findViewById(R.id.imageview19);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        imageview22 = findViewById(R.id.imageview22);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        imageview26 = findViewById(R.id.imageview26);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        imageview29 = findViewById(R.id.imageview29);
        button10 = findViewById(R.id.button10);
        button16 = findViewById(R.id.button16);
        imageview35 = findViewById(R.id.imageview35);
        button17 = findViewById(R.id.button17);
        button18 = findViewById(R.id.button18);
        imageview38 = findViewById(R.id.imageview38);
        button19 = findViewById(R.id.button19);
        button20 = findViewById(R.id.button20);
        banner4 = findViewById(R.id.banner4);
        imageview7 = findViewById(R.id.imageview7);
        imageview8 = findViewById(R.id.imageview8);
        imageview9 = findViewById(R.id.imageview9);
        imageview10 = findViewById(R.id.imageview10);
        imageview11 = findViewById(R.id.imageview11);
        net = new RequestNetwork(this);
        dialog = new AlertDialog.Builder(this);
        delete = new AlertDialog.Builder(this);
        dialog_120fps = new AlertDialog.Builder(this);

        AdRequest adRequest = new AdRequest.Builder().build();
        banner4.loadAd(adRequest);

        mContentResolver = getContentResolver();

        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                FileUtil.deleteFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/4-other"));
                FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/4-other"));
                showMessage("刪除完成");
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/wiro.PS")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        _unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/wiro.PS", "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/");
                        t_delay = new TimerTask() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (haveSU) {
                                            CopyWithhaveSU("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/Extra", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra", true);
                                            CopyWithhaveSU("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/CDNImage", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/CDNImage", true);
                                            CopyWithhaveSU("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/Resources", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources", true);
                                            FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/CDNImage");
                                            FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/Extra");
                                            FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/Resources");
                                            showMessage("完成");
                                        }
                                        else {
                                            uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FAges%2FPrefab_Characters%2FPrefab_Hero%2FActor_194_Actions.pkg.bytes")));
                                            try {
                                                try{
                                                    DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);

                                                } catch (FileNotFoundException e) {

                                                }
                                                uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FAges%2FPrefab_Characters%2FPrefab_Hero%2F")));
                                                _copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/Resources/".concat(game_ver.concat("/Ages/Prefab_Characters/Prefab_Hero/Actor_194_Actions.pkg.bytes")));
                                            } catch (Exception e) {

                                            }
                                            uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2Fassetbundle%2Ficon%2Fhero%2F194_sulie_icon.assetbundle")));
                                            try {
                                                try{
                                                    DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);

                                                } catch (FileNotFoundException e) {

                                                }
                                                uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2Fassetbundle%2Ficon%2Fhero%2F")));
                                                _copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/Resources/".concat(game_ver.concat("/assetbundle/icon/hero/194_sulie_icon.assetbundle")));
                                            } catch (Exception e) {

                                            }
                                            uriB = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2Fassetbundle%2Fshow%2Fhero%2F194_sulie_show_base_low_raw_h.assetbundle");
                                            try {
                                                try{
                                                    DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriB);

                                                } catch (FileNotFoundException e) {

                                                }
                                                uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2Fassetbundle%2Fshow%2Fhero%2F");
                                                _copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/Extra/2019.V2/assetbundle/show/hero/194_sulie_show_base_low_raw_h.assetbundle");
                                            } catch (Exception e) {

                                            }
                                            uriB = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2Fassetbundle%2Fshow%2Fhero%2F194_sulie_show_base_raw_h.assetbundle");
                                            try {
                                                try{
                                                    DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriB);

                                                } catch (FileNotFoundException e) {

                                                }
                                                uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2Fassetbundle%2Fshow%2Fhero%2F");
                                                _copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/Extra/2019.V2/assetbundle/show/hero/194_sulie_show_base_raw_h.assetbundle");
                                            } catch (Exception e) {

                                            }
                                            uriB = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FLanguages%2FCHT_Garena_TW%2FlanguageMap_Newbie.txt")));
                                            try {
                                                try{
                                                    DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriB);

                                                } catch (FileNotFoundException e) {

                                                }
                                                uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FLanguages%2FCHT_Garena_TW%2F")));
                                                _copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/Resources/".concat(game_ver.concat("/Languages/CHT_Garena_TW/languageMap_Newbie.txt")));
                                            } catch (Exception e) {

                                            }
                                            uriC = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FCDNImage%2FHeroHeadIcon%2FE08074BD2C22D7294436E68F0AEA0E90");
                                            try {
                                                try{
                                                    DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriC);

                                                } catch (FileNotFoundException e) {

                                                }
                                                uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FCDNImage%2FHeroHeadIcon%2F");
                                                _copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/CDNImage/HeroHeadIcon/E08074BD2C22D7294436E68F0AEA0E90");
                                            } catch (Exception e) {

                                            }
                                            uriD = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FHero_Wiro_SFX.bnk");
                                            try {
                                                try{
                                                    DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriD);

                                                } catch (FileNotFoundException e) {

                                                }
                                                uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2F");
                                                _copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/Extra/2019.V2/Sound_DLC/Android/Hero_Wiro_SFX.bnk");
                                            } catch (Exception e) {

                                            }
                                            uriE = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2FHero_Wiro_Show.bnk");
                                            try {
                                                try{
                                                    DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriE);

                                                } catch (FileNotFoundException e) {

                                                }
                                                uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2F");
                                                _copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/Extra/2019.V2/Sound_DLC/Android/Chinese(Taiwan)/Hero_Wiro_Show.bnk");
                                            } catch (Exception e) {

                                            }
                                            uriF = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2FHero_Wiro_VO.bnk");
                                            try {
                                                try{
                                                    DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriF);

                                                } catch (FileNotFoundException e) {

                                                }
                                                uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2F");
                                                _copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/Extra/2019.V2/Sound_DLC/Android/Chinese(Taiwan)/Hero_Wiro_VO.bnk");
                                                showMessage("啟用成功");
                                                FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/CDNImage");
                                                FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/Extra");
                                                FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/Resources");
                                            } catch (Exception e) {
                                                showMessage("啟用失敗");
                                            }
                                        }
                                    }
                                });
                            }
                        };
                        _timer.schedule(t_delay, (int)(100));
                    } else {
                        _unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/wiro.PS", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/");
                        showMessage("完成");
                    }
                }
                else {
                    DownloadHttpUrlConnection("https://" + wiro, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/", "wiro.PS");
                    showMessage("下載完成再次點擊以啟用");
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                delete.setTitle("提示");
                delete.setIcon(R.drawable.downloadlogo);
                delete.setMessage("此按鈕用於還原國動維羅修改\n按下確認將還原，並且須下載一小部分大廳資源");
                delete.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            if (haveSU) {
                                String comando;
                                try{
                                    comando = "rm -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources";
                                    Process suProcess = Runtime.getRuntime().exec("su");
                                    DataOutputStream os = new DataOutputStream(suProcess.getOutputStream());
                                    os.writeBytes(comando + "\n");
                                    os.flush();
                                    os.writeBytes("exit\n");
                                    os.flush();
                                    try {
                                        suProcess.waitFor();
                                        if (suProcess.exitValue() != 255) {
                                            // TODO Code to run on success
                                            //showMessage("YES");
                                        }else {
                                            // TODO Code to run on unsuccessful
                                            //showMessage("No1");
                                        }
                                    } catch (InterruptedException e) {
                                        // TODO Code to run in interrupted exception
                                        //showMessage("No2");
                                    }
                                } catch (IOException e) {
                                    // TODO Code to run in input/output exception
                                    //showMessage("No3");
                                }
                                uriB = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2Fassetbundle%2Fshow%2Fhero%2F194_sulie_show_base_low_raw_h.assetbundle");
                                try {
                                    try{
                                        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriB);

                                    } catch (FileNotFoundException e) {

                                    }
                                } catch (Exception e) {

                                }
                                uriB = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2Fassetbundle%2Fshow%2Fhero%2F194_sulie_show_base_raw_h.assetbundle");
                                try {
                                    try{
                                        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriB);

                                    } catch (FileNotFoundException e) {

                                    }
                                } catch (Exception e) {

                                }
                                uriC = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FCDNImage%2FHeroHeadIcon%2FE08074BD2C22D7294436E68F0AEA0E90");
                                try {
                                    try{
                                        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriC);

                                    } catch (FileNotFoundException e) {

                                    }
                                } catch (Exception e) {

                                }
                                uriD = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FHero_Wiro_SFX.bnk");
                                try {
                                    try{
                                        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriD);

                                    } catch (FileNotFoundException e) {

                                    }
                                } catch (Exception e) {

                                }
                                uriE = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2FHero_Wiro_Show.bnk");
                                try {
                                    try{
                                        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriE);

                                    } catch (FileNotFoundException e) {

                                    }
                                } catch (Exception e) {

                                }
                                uriF = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2FHero_Wiro_VO.bnk");
                                try {
                                    try{
                                        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriF);

                                    } catch (FileNotFoundException e) {

                                    }
                                } catch (Exception e) {

                                }
                            } else {
                                uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources");
                                try {
                                    try{
                                        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);

                                    } catch (FileNotFoundException e) {

                                    }
                                } catch (Exception e) {
                                    showMessage("還原失敗");
                                    showMessage("請按遊戲登入畫面左上的一鍵恢復");
                                }
                                uriB = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2Fassetbundle%2Fshow%2Fhero%2F194_sulie_show_base_low_raw_h.assetbundle");
                                try {
                                    try{
                                        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriB);

                                    } catch (FileNotFoundException e) {

                                    }
                                } catch (Exception e) {

                                }
                                uriB = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2Fassetbundle%2Fshow%2Fhero%2F194_sulie_show_base_raw_h.assetbundle");
                                try {
                                    try{
                                        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriB);

                                    } catch (FileNotFoundException e) {

                                    }
                                } catch (Exception e) {

                                }
                                uriC = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FCDNImage%2FHeroHeadIcon%2FE08074BD2C22D7294436E68F0AEA0E90");
                                try {
                                    try{
                                        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriC);

                                    } catch (FileNotFoundException e) {

                                    }
                                } catch (Exception e) {

                                }
                                uriD = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FHero_Wiro_SFX.bnk");
                                try {
                                    try{
                                        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriD);

                                    } catch (FileNotFoundException e) {

                                    }
                                } catch (Exception e) {

                                }
                                uriE = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2FHero_Wiro_Show.bnk");
                                try {
                                    try{
                                        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriE);

                                    } catch (FileNotFoundException e) {

                                    }
                                } catch (Exception e) {

                                }
                                uriF = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2FHero_Wiro_VO.bnk");
                                try {
                                    try{
                                        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriF);

                                    } catch (FileNotFoundException e) {

                                    }
                                } catch (Exception e) {

                                }
                            }
                        } else {
                            FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources");
                            FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/CDNImage/HeroHeadIcon/E08074BD2C22D7294436E68F0AEA0E90");
                            FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/Hero_Wiro_SFX.bnk");
                            FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/Chinese(Taiwan)/Hero_Wiro_Show.bnk");
                            FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/Chinese(Taiwan)/Hero_Wiro_VO.bnk");
                            FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/assetbundle/show/hero/194_sulie_show_base_low_raw_h.assetbundle");
                            FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/assetbundle/show/hero/194_sulie_show_base_raw_h.assetbundle");
                        }
                        showMessage("還原成功");
                        Intent mIntent = getPackageManager().getLaunchIntentForPackage("com.garena.game.kgtw");
                        startActivity(mIntent);
                        finishAffinity();
                    }
                });
                delete.setNeutralButton("取消", null);
                delete.create().show();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/volkath.PS")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        _unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/volkath.PS", "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/");
                        t_delay = new TimerTask() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (haveSU) {
                                            CopyWithhaveSU("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/" + game_ver, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + game_ver, true);
                                            FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/".concat(game_ver));
                                            showMessage("完成");
                                        }
                                        else {
                                            uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FAges%2FPrefab_Characters%2FPrefab_Hero%2FActor_529_Actions.pkg.bytes")));
                                            try {
                                                try{
                                                    DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);

                                                } catch (FileNotFoundException e) {

                                                }
                                                uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FAges%2FPrefab_Characters%2FPrefab_Hero%2F")));
                                                _copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/".concat(game_ver.concat("/Ages/Prefab_Characters/Prefab_Hero/Actor_529_Actions.pkg.bytes")));
                                            } catch (Exception e) {

                                            }
                                            uriB = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FPrefab_Characters%2FActor_529_Infos.pkg.bytes")));
                                            try {
                                                try{
                                                    DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriB);

                                                } catch (FileNotFoundException e) {

                                                }
                                                uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FPrefab_Characters%2F")));
                                                _copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/".concat(game_ver.concat("/Prefab_Characters/Actor_529_Infos.pkg.bytes")));
                                                showMessage("啟用成功");
                                                FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/".concat(game_ver));
                                            } catch (Exception e) {
                                                showMessage("啟用失敗");
                                            }
                                        }
                                    }
                                });
                            }
                        };
                        _timer.schedule(t_delay, (int)(100));
                    } else {
                        _unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/volkath.PS", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/");
                        showMessage("完成");
                    }
                }
                else {
                    DownloadHttpUrlConnection("https://" + Volkath, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/", "volkath.PS");
                    showMessage("下載完成再次點擊以啟用");
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                delete.setTitle("提示");
                delete.setIcon(R.drawable.downloadlogo);
                delete.setMessage("此按鈕用於還原安格列變身修改\n按下確認將還原，無須於大廳下載資源");
                delete.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            if (haveSU) {
                                String comando;
                                try{
                                    comando = "rm -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources";
                                    Process suProcess = Runtime.getRuntime().exec("su");
                                    DataOutputStream os = new DataOutputStream(suProcess.getOutputStream());
                                    os.writeBytes(comando + "\n");
                                    os.flush();
                                    os.writeBytes("exit\n");
                                    os.flush();
                                    try {
                                        suProcess.waitFor();
                                        if (suProcess.exitValue() != 255) {
                                            // TODO Code to run on success
                                            //showMessage("YES");
                                        } else {
                                            // TODO Code to run on unsuccessful
                                            //showMessage("No1");
                                        }
                                    } catch (InterruptedException e) {
                                        // TODO Code to run in interrupted exception
                                        //showMessage("No2");
                                    }
                                } catch (IOException e) {
                                    // TODO Code to run in input/output exception
                                    //showMessage("No3");
                                }
                            }
                            else {
                                uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources");
                                try {
                                    try{
                                        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);

                                    } catch (FileNotFoundException e) {

                                    }
                                } catch (Exception e) {
                                    showMessage("還原失敗");
                                    showMessage("請按遊戲登入畫面左上的一鍵恢復");
                                }
                            }
                        } else {
                            FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources");
                        }
                        showMessage("還原成功");
                    }
                });
                delete.setNeutralButton("取消", null);
                delete.create().show();

            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                Other("https://" + tower, "Prefab_Organ.pkg.bytes", "Res");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                delete.setTitle("提示");
                delete.setIcon(R.drawable.downloadlogo);
                delete.setMessage("此按鈕用於還原次元防禦塔修改\n按下確認將還原，無須下載任何大廳資源");
                delete.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {
                        Other("https://" + tower0, "Prefab_Organ.pkg.bytes", "Res");
                    }
                });
                delete.setNeutralButton("取消", null);
                delete.create().show();
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                Other("https://" + soldier, "Prefab_Soldier.pkg.bytes", "Res");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                delete.setTitle("提示");
                delete.setIcon(R.drawable.downloadlogo);
                delete.setMessage("此按鈕用於還原次元兵線修改\n按下確認將還原，無須下載任何大廳資源");
                delete.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {
                        Other("https://" + soldier0, "Prefab_Soldier.pkg.bytes", "Res");
                    }
                });
                delete.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {

                    }
                });
                delete.create().show();
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                Other("https://" + monster, "Prefab_Monster.pkg.bytes", "Res");
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                delete.setTitle("提示");
                delete.setIcon(R.drawable.downloadlogo);
                delete.setMessage("此按鈕用於還原次元紅藍buff修改\n按下確認將還原，無須下載任何大廳資源");
                delete.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {
                        Other("https://" + monster0, "Prefab_Monster.pkg.bytes", "Res");
                    }
                });
                delete.setNeutralButton("取消", null);
                delete.create().show();
            }
        });

        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                Other("https://cdn.discordapp.com/attachments/842221289464004608/1022879773078327306/Hero_Wisp_SFX.bnk", "Hero_Wisp_SFX.bnk", "Extra");
            }
        });

        button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    if (haveSU){
                        String comando;
                        try{
                            comando = "rm -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/Hero_Wisp_SFX.bnk";
                            Process suProcess = Runtime.getRuntime().exec("su");
                            DataOutputStream os = new DataOutputStream(suProcess.getOutputStream());
                            os.writeBytes(comando + "\n");
                            os.flush();
                            os.writeBytes("exit\n");
                            os.flush();
                            try {
                                suProcess.waitFor();
                                if (suProcess.exitValue() != 255) {
                                    // TODO Code to run on success
                                }else {
                                    // TODO Code to run on unsuccessful
                                }
                            } catch (InterruptedException e) {
                                // TODO Code to run in interrupted exception
                            }
                        } catch (IOException e) {
                            // TODO Code to run in input/output exception
                        }
                    } else {
                        uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FHero_Wisp_SFX.bnk");
                        try {
                            try{
                                DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
                            } catch (FileNotFoundException e) {
                            }
                        } catch (Exception e) {
                        }
                    }
                }else{
                    FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/Hero_Wisp_SFX.bnk");
                }
                showMessage("還原成功");
            }
        });

        button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                Device_ID = getDeviceName();
                //showMessage(Device_ID);
                //showMessage(getResources().getString(Device_ID.length()-("xiaomi M2102J20SG".length())));
                FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tmp"));
                _save("窩不知道", FileUtil.getPackageDataDir(getApplicationContext()).concat("/tmp/"), "VeryHighFrameModeBlackList.bytes");
                dialog_120fps.setTitle("注意");
                dialog_120fps.setMessage("◑開啟超高幀率模式(120FPS)將擁有更加流暢的遊戲體驗，但可能加快手機耗電、發熱。\n◑啟用該功能並不會直接幫你啟用至120FPS，而是使120FPS選項出現，須手動去戰場設置中開啟\n◑若已開啟超高幀率，但遊戲顯示幀率仍為60，代表傳說對決不認可您的手機適合使用超高幀率，或是有鎖幀的問題\n◑不能保證100%修改成功");
                dialog_120fps.setCancelable(false);
                dialog_120fps.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {
                        Decrypt();
                        ProgressBar_Show("修改中...請稍後...");
                        new BackgroundTaskClass(OtherActivity.this){
                            @Override
                            public void doInBackground() {
                                Looper.prepare();
                                //todo
                                ModDevice(Device_ID);
                                if (Device_ID.length()-("xiaomi M2102J20SG".length()) == 0) {
                                    {
                                        java.io.File dYx4Y = new java.io.File(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tmp/VeryHighFrameModeBlackList0.bytes"));
                                        java.io.File e5Cyk = new java.io.File(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tmp/VeryHighFrameModeBlackList.bytes"));
                                        dYx4Y.renameTo(e5Cyk);
                                    }
                                }
                                else {
                                    ModOffest(Device_ID.length()-("xiaomi M2102J20SG".length()));
                                }
                                Encrypt();
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                                    if (haveSU) {
                                        CopyWithhaveSU(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tmp/VeryHighFrameModeBlackList.bytes"), "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + game_ver + "/Databin/Client/Text/VeryHighFrameModeBlackList.bytes", true);
                                    } else {
                                        uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FDatabin%2FClient%2FText%2FVeryHighFrameModeBlackList.bytes")));
                                        try {
                                            try{
                                                DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);

                                            } catch (FileNotFoundException e) {

                                            }
                                            uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FDatabin%2FClient%2FText%2F")));
                                            _copyFilePath2Uri(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tmp/VeryHighFrameModeBlackList.bytes"));
                                        } catch (Exception e) {
                                            showMessage("啟用失敗");
                                        }
                                    }
                                } else {
                                    FileUtil.copyFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tmp/VeryHighFrameModeBlackList.bytes"), "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + game_ver + "/Databin/Client/Text/VeryHighFrameModeBlackList.bytes");
                                }
                                FileUtil.deleteFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tmp"));
                            }
                            @Override
                            public void onPostExecute(){
                                //完成
                                showMessage("完成");
                                ProgressBar_Dismiss();
                            }
                        }.execute();
                        //結束
                    }
                });
                dialog_120fps.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {
                        FileUtil.deleteFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tmp"));
                    }
                });
                dialog_120fps.create().show();
            }
        });

        button19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                //開啟神仙？
                if (GOD.equals("0")) {
                    showMessage("暫未開放");
                } else if (GOD.equals("1")){
                    if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/wiro.PS")) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) { //安卓11以上處理程序

                        } else { //安卓11以下處理程序

                        }
                    } else { //下載插件

                    }
                } else {
                    showMessage("初始化中...");
                }
            }
        });

        button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
               //還沒寫48763
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
                }
                else if (skin_string.contains("停用")) {
                    showMessage("無法使用");
                }
                else if (skin_string.contains("更新")) {
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

        imageview10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (lobby_string.equals("") || internet == 0) {
                    showMessage("初始化中..");
                }
                else if (lobby_string.contains("停用")) {
                    showMessage("無法使用");
                }
                else if (lobby_string.contains("更新")) {
                    showMessage("更新中");
                } else {
                    page.setClass(getApplicationContext(), LobbyActivity.class);
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
                        lobby_string = map1.get((int)0).get("2-lobby").toString();
                        game_ver = map1.get((int)0).get("ver").toString();
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

        _Other_child_listener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot _param1, String _param2) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);
                Other.addListenerForSingleValueEvent(new ValueEventListener() {
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
                        wiro = map2.get((int)0).get("wiro").toString();
                        Volkath = map2.get((int)0).get("volkath").toString();
                        tower = map2.get((int)0).get("tower").toString();
                        monster = map2.get((int)0).get("monster").toString();
                        soldier = map2.get((int)0).get("solider").toString();
                        tower0 = map2.get((int)0).get("tower0").toString();
                        monster0 = map2.get((int)0).get("monster0").toString();
                        soldier0 = map2.get((int)0).get("solider0").toString();
                        GOD = map2.get((int)0).get("神仙亂鬥").toString();
                        GOD_URL = map2.get((int)0).get("神仙亂鬥_URL").toString();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) { //讓root的安卓11以上可用root模式啟用(もっと速く)
                            new Thread() {
                                public void run() {
                                    Looper.prepare();
                                    if (_RootAccess()) {
                                        haveSU = true;
                                    } else {
                                        haveSU = false;
                                    }
                                    //檢測 assetbundle 目錄權限
                                    if (!haveSU) //無root才要檢測
                                        get_permission();
                                    Looper.loop();
                                    //Walk through with SAF and get total size fun start
                                    //displaySafTree(mAndroidUri, mAndroidDataDocId);

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
        Other.addChildEventListener(_Other_child_listener);
    }

    private void initializeLogic() {
        if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/wiro.png")) {
            imageview12.setImageBitmap(FileUtil.decodeSampleBitmapFromPath("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/wiro.png", 1024, 1024));
        } else {
            DLC("https://cdn.discordapp.com/attachments/842221289464004608/1074304245035180092/wiro.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/", imageview12);
        }
        if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/volkath.png")) {
            imageview18.setImageBitmap(FileUtil.decodeSampleBitmapFromPath("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/volkath.png", 1024, 1024));
        } else {
            DLC("https://cdn.discordapp.com/attachments/842221289464004608/1074304244775129169/volkath.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/", imageview18);
        }
        if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/tower.png")) {
            imageview23.setImageBitmap(FileUtil.decodeSampleBitmapFromPath("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/tower.png", 1024, 1024));
        } else {
            DLC("https://cdn.discordapp.com/attachments/842221289464004608/1074304244506701924/tower.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/", imageview23);
        }
        if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/soldier.png")) {
            imageview21.setImageBitmap(FileUtil.decodeSampleBitmapFromPath("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/soldier.png", 1024, 1024));
        } else {
            DLC("https://cdn.discordapp.com/attachments/842221289464004608/1074304244242456656/soldier.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/", imageview21);
        }
        if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/buff.png")) {
            imageview27.setImageBitmap(FileUtil.decodeSampleBitmapFromPath("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/buff.png", 1024, 1024));
        } else {
            DLC("https://cdn.discordapp.com/attachments/842221289464004608/1074304243965628467/buff.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/", imageview27);
        }
        if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/zhadanren.png")) {
            imageview33.setImageBitmap(FileUtil.decodeSampleBitmapFromPath("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/zhadanren.png", 1024, 1024));
        } else {
            DLC("https://cdn.discordapp.com/attachments/842221289464004608/1074304245303619635/zhadanren.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/", imageview33);
        }
        if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/120FPS.png")) {
            imageview37.setImageBitmap(FileUtil.decodeSampleBitmapFromPath("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/120FPS.png", 1024, 1024));
        } else {
            DLC("https://cdn.discordapp.com/attachments/842221289464004608/1137475199411957901/120FPS.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/", imageview37);
        }
        /*
        if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/GOD.png")) {
            imageview38.setImageBitmap(FileUtil.decodeSampleBitmapFromPath("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/GOD.png", 1024, 1024));
        } else {
            DLC("https://cdn.discordapp.com/attachments/1069989755628032142/1163908324518002740/GOD.jpg", "/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/", imageview38);
        }*/
        if (!FileUtil.isExistFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/4-other"))) {
            FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/4-other"));
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
        try{
            java.io.File outdir = new java.io.File(_destDir);
            java.util.zip.ZipInputStream zin = new java.util.zip.ZipInputStream(new java.io.FileInputStream(_fileZip));
            java.util.zip.ZipEntry entry;
            String name, dir;
            while ((entry = zin.getNextEntry()) != null){

                name = entry.getName();
                if(entry.isDirectory())
                {
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
        catch (java.io.IOException e)
        {
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


    public void _extra() {
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

    public void CopyWithhaveSU(String original, String target, boolean exist_in_target_dest) {
        String comando;
        try {
            if (exist_in_target_dest){ //複製覆蓋
                String a = "cp -r " + original + " " + target;
                int len = a.lastIndexOf("/");
                comando = a.substring(0, len+1);
            } else {
                comando = "cp -r " + original + " " + target;
            }
            Process suProcess = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(suProcess.getOutputStream());
            os.writeBytes(comando + "\n");
            os.flush();
            os.writeBytes("exit\n");
            os.flush();
            try {
                suProcess.waitFor();
                if (suProcess.exitValue() != 255) {
                    // TODO Code to run on success
                    //showMessage("YES");
                }else {
                    // TODO Code to run on unsuccessful
                    //showMessage("No1");
                }
            } catch (InterruptedException e) {
                // TODO Code to run in interrupted exception
                //showMessage("No2");
            }
        } catch (IOException e) {
            // TODO Code to run in input/output exception
            //showMessage("No3");
        }
    }
    public void Other(String Url, String Name, String Located){
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
                        String filename = "";
                        String Path = "";
                        filename = Name;
                        //filename = URLUtil.guessFileName(Url, null, null);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            Path = "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/";
                        }else{
                            if (Located.contains("Res")){
                                Path = "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + game_ver + "/Prefab_Characters/";
                            } else if (Located.contains("Extra")){
                                Path = "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/";
                            }
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
                                    prog.setMessage("下載中...請稍後... ");
                                    prog.show();
                                }
                            });
                            bout.write(data, 0, x);
                        }
                        bout.close();
                        in.close();
                        prog.dismiss();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            if (haveSU){
                                if (Located.contains("Res")){
                                    CopyWithhaveSU(Path + filename, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + game_ver + "/Prefab_Characters/" + filename, true);
                                } else if (Located.contains("Extra")){
                                    CopyWithhaveSU(Path + filename, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/" + filename, true);
                                }
                                showMessage("完成");
                                FileUtil.deleteFile(Path + filename);
                            } else {
                                if (Located.contains("Res")){
                                    uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F" + game_ver + "%2FPrefab_Characters%2F" + filename);
                                    try {
                                        try{
                                            DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
                                        } catch (FileNotFoundException e) {
                                        }
                                        uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F" + game_ver + "%2FPrefab_Characters%2F");
                                        _copyFilePath2Uri(Path + filename);
                                        FileUtil.deleteFile(Path + filename);
                                        showMessage("成功");
                                    } catch (Exception e) {
                                        uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F" + game_ver + "%2FPrefab_Characters%2F");
                                        _copyFilePath2Uri(Path + filename);
                                        FileUtil.deleteFile(Path + filename);
                                        showMessage("成功");
                                    }
                                } else if (Located.contains("Extra")){
                                    uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2F" + filename);
                                    try {
                                        try{
                                            DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
                                        } catch (FileNotFoundException e) {
                                        }
                                        uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2F");
                                        _copyFilePath2Uri(Path + filename);
                                        FileUtil.deleteFile(Path + filename);
                                        showMessage("成功");
                                    } catch (Exception e) {
                                        uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2F");
                                        _copyFilePath2Uri(Path + filename);
                                        FileUtil.deleteFile(Path + filename);
                                        showMessage("成功");
                                    }
                                }
                            }
                        } else {
                            showMessage("成功");
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

    //檢測是否已開啟120fps選項
    private boolean open120(){
        DisplayManager displayManager = (DisplayManager) getSystemService(DISPLAY_SERVICE);
        if (displayManager != null) {
            Display display = displayManager.getDisplay(Display.DEFAULT_DISPLAY);
            if (display != null) {
                float refreshRate = display.getRefreshRate();
                if (refreshRate >= 110.0f) {
                    return true;
                } else {
                    showMessage("請先至手機 設定→螢幕 開啟\n 120Hz 螢幕更新率後再試一次");
                    return false;
                }
            }
        }
        return false;
    }

    //獲取手機型號
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {//from w ww .  jav a  2 s  . c  o m
            return capitalize(manufacturer) + " " + model;
        }
    }
    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        return s.toLowerCase();
    }

    //修改檔案機型
    public void ModDevice(String Device) {
        String FilePath0 = FileUtil.getPackageDataDir(getApplicationContext())+("/tmp/VeryHighFrameModeBlackList.bytes");
        String FilePath1 = FileUtil.getPackageDataDir(getApplicationContext())+("/tmp/VeryHighFrameModeBlackList0.bytes");

        byte[] oldDevice = ("xiaomi M2102J20SG").getBytes();
        byte[] newDevice = (Device).getBytes();

        try {
            FileInputStream fis = new FileInputStream(FilePath0);
            FileOutputStream fos = new FileOutputStream(FilePath1);

            byte[] buffer = new byte[1024];
            int bytesRead;
            boolean wordFound = false;

            while ((bytesRead = fis.read(buffer)) != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    if (buffer[i] == oldDevice[0]) {
                        wordFound = true;
                        int matchLength = 1;
                        for (int j = 1; j < oldDevice.length; j++) {
                            if (i + j >= bytesRead || buffer[i + j] != oldDevice[j]) {
                                wordFound = false;
                                break;
                            }
                            matchLength++;
                        }

                        if (wordFound) {
                            // Calculate the size difference between oldWord and newWord
                            int sizeDifference = newDevice.length - matchLength;

                            // Extend the buffer if necessary to accommodate the newWord
                            if (sizeDifference > 0) {
                                byte[] tempBuffer = new byte[bytesRead + sizeDifference];
                                System.arraycopy(buffer, 0, tempBuffer, 0, i);
                                System.arraycopy(newDevice, 0, tempBuffer, i, newDevice.length);
                                System.arraycopy(buffer, i + matchLength, tempBuffer, i + newDevice.length, bytesRead - i - matchLength);
                                buffer = tempBuffer;
                            } else if (sizeDifference < 0) {
                                // Shrink the buffer if necessary to accommodate the newWord
                                System.arraycopy(buffer, i + matchLength, buffer, i + newDevice.length, bytesRead - i - matchLength);
                                for (int j = 0; j < newDevice.length; j++) {
                                    buffer[i + j] = newDevice[j];
                                }
                            } else {
                                // If the lengths are the same, just replace the oldWord with the newWord
                                for (int j = 0; j < newDevice.length; j++) {
                                    buffer[i + j] = newDevice[j];
                                }
                            }
                            // Update the bytesRead variable accordingly
                            bytesRead += sizeDifference;
                        }
                    }
                }
                fos.write(buffer, 0, bytesRead);
            }

            fis.close();
            fos.close();
            FileUtil.deleteFile(FileUtil.getPackageDataDir(getApplicationContext())+("/tmp/VeryHighFrameModeBlackList.bytes"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ModOffest(int len_different) {
        String FilePath0 = FileUtil.getPackageDataDir(getApplicationContext())+("/tmp/VeryHighFrameModeBlackList0.bytes");
        String FilePath1 = FileUtil.getPackageDataDir(getApplicationContext())+("/tmp/VeryHighFrameModeBlackList.bytes");

        byte[] oldOffest = {0x1b, 0x00, 0x00, 0x00, 0x04, 0x00, 0x00, 0x00, 0x12};
        byte[] newOffest = {0x1b, 0x00, 0x00, 0x00, 0x04, 0x00, 0x00, 0x00, 0x12};
        newOffest[0] = (byte) (oldOffest[0] + (byte) len_different);
        newOffest[8] = (byte) (oldOffest[8] + (byte) len_different);

        try {
            FileInputStream fis = new FileInputStream(FilePath0);
            FileOutputStream fos = new FileOutputStream(FilePath1);
            byte[] buffer = new byte[1024];
            int bytesRead;
            boolean wordFound = false;

            while ((bytesRead = fis.read(buffer)) != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    // Check if the oldWord starts at the current position
                    if (buffer[i] == oldOffest[0]) {
                        wordFound = true;
                        for (int j = 1; j < oldOffest.length; j++) {
                            // Check if the rest of the oldWord matches
                            if (i + j >= bytesRead || buffer[i + j] != oldOffest[j]) {
                                wordFound = false;
                                break;
                            }
                        }

                        if (wordFound) {
                            // Replace oldWord with newWord in the buffer
                            for (int j = 0; j < newOffest.length; j++) {
                                buffer[i + j] = newOffest[j];
                            }
                        }
                    }
                }
                fos.write(buffer, 0, bytesRead);
            }

            fis.close();
            fos.close();
            FileUtil.deleteFile(FileUtil.getPackageDataDir(getApplicationContext())+("/tmp/VeryHighFrameModeBlackList0.bytes"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //處理ab包目錄授權
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

    public void createdirectoryandfile(String dir, String uridelete) {
        try {
            Uri uri1 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw");
            DocumentFile documentFile = DocumentFile.fromTreeUri(OtherActivity.this, uri1);
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
            try {
                try{
                    DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
                    assetbundle_access = true;
                } catch (FileNotFoundException e) {

                }
            } catch (Exception e) {
                assetbundle_access = false;
            }
        }catch (Exception e){

        }
    }

    public boolean renameTo(String dir,String fileName,String targetName) {
        try {
            Uri uri1 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw");
            DocumentFile documentFile = DocumentFile.fromTreeUri(OtherActivity.this, uri1);
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
            return documentFile.renameTo(targetName);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public void get_permission() {
        //稍等的dialog
        AlertDialog.Builder builder1 = new AlertDialog.Builder(OtherActivity.this);
        builder1.setTitle("")
                .setMessage("\n請稍等...\n")
                .setCancelable(false);
        AlertDialog alert1 = builder1.create();
        alert1.show();
        createdirectoryandfile("/files/Extra/2019.V2/assetbundle/show/hero", "Extra%2F2019.V2%2Fassetbundle%2Fshow%2Fhero%2Ftest.txt");

        t_delay = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //after counting down
                        if (!assetbundle_access){
                            alert1.dismiss();
                            builder1.setTitle("獲取權限資訊狀態")
                                    .setMessage("assetbundle 目錄尚未取得權限，若點擊「授權」，局內大量資源皆須重新下載 (最大可能高達 4 Gb)，目前僅 維羅國動 需要使用該目錄，若忽略，則國動模型的修改便無效，聲音仍然會修改成功")
                                    .setCancelable(true)
                                    .setNeutralButton("忽略",null)
                                    .setPositiveButton("授權", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int id) {
                                            renameTo("/files/Extra/2019.V2", "assetbundle", "assetbundle_trash");
                                            createdirectoryandfile("/files/Extra/2019.V2/assetbundle/show/hero", "Extra%2F2019.V2%2Fassetbundle%2Fshow%2Fhero%2Ftest.txt");
                                            createdirectoryandfile("/files/Extra/2019.V2/assetbundle/show/skin", "Extra%2F2019.V2%2Fassetbundle%2Fshow%2Fskin%2Ftest.txt");
                                            createdirectoryandfile("/files/Extra/2019.V2/assetbundle/battle/hero", "Extra%2F2019.V2%2Fassetbundle%2Fbattle%2Fhero%2Ftest.txt");
                                            createdirectoryandfile("/files/Extra/2019.V2/assetbundle/battle/skin", "Extra%2F2019.V2%2Fassetbundle%2Fbattle%2Fskin%2Ftest.txt");
                                            showMessage("完成");
                                            showMessage("請先回遊戲，將模型下載回來");
                                            Intent mIntent = getPackageManager().getLaunchIntentForPackage("com.garena.game.kgtw");
                                            startActivity(mIntent);
                                        }
                                    });
                            AlertDialog alert1 = builder1.create();
                            alert1.show();
                        } else {
                            alert1.dismiss();
                        }
                        //end
                    }
                });
            }
        };
        _timer.schedule(t_delay, (int)(500));
    }

    /*

    //SAF walk through fun
    private void displaySafTree(Uri treeUri, String docId) {
        currentLevel = -1;
        fileCount = 0;
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                SafUtils.walkSafTree(mContentResolver, treeUri, docId,
                        new SafUtils.Callback<Integer>() {
                            @Override
                            public boolean call(Integer levelChange) {
                                if (levelChange < 0) {
                                    currentLevel--;
                                } else {
                                    currentLevel++;
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            textview1.setText(String.valueOf(fileCount));
                                        }
                                    });
                                }
                                return false;
                            }
                        },
                        new SafUtils.Callback<Cursor>() {
                            @Override
                            public boolean call(Cursor cursor) {
                                fileCount++;
                                int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                                int mimeIndex = cursor.getColumnIndex(SafUtils.COLUMNS_MIME_TYPE);
                                String displayName = cursor.getString(nameIndex);
                                String mimeType = cursor.getString(mimeIndex);
                                return false;
                            }
                        });

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textview1.setText(String.valueOf(SafUtils.TotalSize));
                    }
                });
            }
        });
    }

    private void showToastOnMainThread(String message) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }*/


    //aes加解密
    private byte[] key = new byte[] { (byte)0xfc, (byte)0x88, (byte)0x8a, (byte) 0x32, (byte)0x0e, (byte)0xef, (byte)0xb6, (byte)0xfd, (byte)0xd2, (byte)0x91, (byte)0x8d, (byte)0x25, (byte)0x31, (byte)0x7c, (byte)0xb0, (byte)0xf1 };
    private byte[] iv = new byte[] { (byte)0x00, (byte)0x00, (byte)0x00, (byte) 0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00 };

    private void Decrypt(){
        byte[] input = FileUtil.readBinaryFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tmp/VeryHighFrameModeBlackList.bytes"));
        byte[] input_content = Arrays.copyOfRange(input, 8, input.length);
        FileUtil.deleteFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tmp/VeryHighFrameModeBlackList.bytes"));
        try {
            SecretKeySpec KEY = new SecretKeySpec(key, 0, key.length, "AES");
            IvParameterSpec IV = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(Cipher.DECRYPT_MODE, KEY, IV);
            byte[] output = cipher.doFinal(input_content);
            FileUtil.writeBinaryFile(output, FileUtil.getPackageDataDir(getApplicationContext()).concat("/tmp/VeryHighFrameModeBlackList.bytes"));
        }catch (Exception e){
        }
    }

    private void Encrypt(){
        byte[] input = FileUtil.readBinaryFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tmp/VeryHighFrameModeBlackList.bytes"));
        byte[] head = new byte[] {(byte)0x22, (byte)0x4a, (byte)0x67, (byte) 0x00};
        byte[] size = reverse(ByteBuffer.allocate(4).putInt(input.length).array());
        byte[] header = concatenateByteArrays(head, size);

        FileUtil.deleteFile(FileUtil.getPackageDataDir(getApplicationContext())+("/tmp/VeryHighFrameModeBlackList.bytes"));

        try {
            SecretKeySpec KEY = new SecretKeySpec(key, 0, key.length, "AES");
            IvParameterSpec IV = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(Cipher.ENCRYPT_MODE, KEY, IV);
            byte[] output = cipher.doFinal(input);
            FileUtil.writeBinaryFile(concatenateByteArrays(header, output), FileUtil.getPackageDataDir(getApplicationContext())+("/tmp/VeryHighFrameModeBlackList.bytes"));
        }catch (Exception e){
        }
    }

    byte[] concatenateByteArrays(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    byte[] reverse(byte[] array) {
        int i = 0;
        int j = array.length - 1;
        byte tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
        return array;
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
        prog2 = new ProgressDialog(OtherActivity.this);
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

    public void _copyFilePath2Uri(final String _OriginalFilePath) {
        File mfile6 = new File(_OriginalFilePath);
        mfile1 = DocumentFile.fromTreeUri(this, uri2);

        mfile1 = mfile1.createFile(getMimeType(_OriginalFilePath), Uri.parse(_OriginalFilePath).getLastPathSegment());
        uri2 = mfile1.getUri();
        if (copyFilePath2Uri(OtherActivity.this, mfile6, uri2)) {
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
        } else {
            //該手機無root
            return false;
        }
    }


    public void _save(final String _file, final String _path, final String _file2) {
        try{
            int count;
            java.io.InputStream input= this.getAssets().open(_file);
            java.io.OutputStream output = new  java.io.FileOutputStream(_path+_file2);
            byte data[] = new byte[1024];
            while ((count = input.read(data))>0) {
                output.write(data, 0, count);
            }
            output.flush();
            output.close();
            input.close();
        }catch(Exception e){
        }
    }

}
