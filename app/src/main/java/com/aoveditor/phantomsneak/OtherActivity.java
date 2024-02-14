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
import android.view.*;
import android.view.View;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.ImageView;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
//120fps
import android.hardware.display.DisplayManager;

import com.aoveditor.phantomsneak.Utils.FileUtil;
import com.aoveditor.phantomsneak.Utils.SuperUserUtil;
import com.aoveditor.phantomsneak.Utils.ShizukuShellUtil;
import com.aoveditor.phantomsneak.Utils.SAFUtil;
import com.aoveditor.phantomsneak.network.RequestNetwork;
import com.aoveditor.phantomsneak.network.RequestNetworkController;

import rikka.shizuku.Shizuku;


public class OtherActivity extends AppCompatActivity {

    private Timer _timer = new Timer();
    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();

    private boolean quit = false;
    private Uri uri2;
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
    private Button button13;
    private ImageView imageview12;
    private ImageView imageview18;
    private ImageView imageview23;
    private ImageView imageview21;
    private ImageView imageview27;
    private ImageView imageview33;
    private ImageView imageview37;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button10;
    private Button button16;
    private Button button17;
    private Button button18;
    private Button button19;
    private Button button20;
    private AdView banner4;
    private ImageView imageview7;
    private ImageView imageview8;
    private ImageView imageview9;
    private ImageView imageview10;

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

    private ShizukuShellUtil mShizukuShell = null;
    private List<String> mResult = null;


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
        button13 = findViewById(R.id.button13);
        imageview12 = findViewById(R.id.imageview12);
        imageview18 = findViewById(R.id.imageview18);
        imageview23 = findViewById(R.id.imageview23);
        imageview21 = findViewById(R.id.imageview21);
        imageview27 = findViewById(R.id.imageview27);
        imageview33 = findViewById(R.id.imageview33);
        imageview37 = findViewById(R.id.imageview37);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button10 = findViewById(R.id.button10);
        button16 = findViewById(R.id.button16);
        button17 = findViewById(R.id.button17);
        button18 = findViewById(R.id.button18);
        button19 = findViewById(R.id.button19);
        button20 = findViewById(R.id.button20);
        banner4 = findViewById(R.id.banner4);
        imageview7 = findViewById(R.id.imageview7);
        imageview8 = findViewById(R.id.imageview8);
        imageview9 = findViewById(R.id.imageview9);
        imageview10 = findViewById(R.id.imageview10);
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
                        FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmp");
                        ProgressBar_Show("請稍後...");
                        new BackgroundTaskClass(OtherActivity.this){
                            @Override
                            public void doInBackground() {
                                Looper.prepare();
                                _unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/wiro.PS", "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmp/");
                            }
                            @Override
                            public void onPostExecute() {
                                //完成解壓
                                ProgressBar_Dismiss();
                                if (ChooseUtilActivity.Method == "SU") {
                                    if (SuperUserUtil.haveSU()) {
                                        SuperUserUtil.cpWithSU("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmp/*", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/");
                                        FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmp");
                                        showMessage("完成");
                                    } else {
                                        showMessage("你已撤銷 root 權限");
                                        MainActivity.haveSU = false;
                                        FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmp");
                                        Intent pageJump = new Intent();
                                        pageJump.setClass(OtherActivity.this, ChooseUtilActivity.class);
                                        startActivity(pageJump);
                                        finish();
                                        overridePendingTransition(0, 0);
                                    }

                                } else if (ChooseUtilActivity.Method == "SAF") {
                                    //rm
                                    SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FAges%2FPrefab_Characters%2FPrefab_Hero%2FActor_194_Actions.pkg.bytes")));
                                    SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2Fassetbundle%2Ficon%2Fhero%2F194_sulie_icon.assetbundle")));
                                    SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FLanguages%2FCHT_Garena_TW%2FlanguageMap_Newbie.txt")));
                                    SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2Fassetbundle%2Fshow%2Fhero%2F194_sulie_show_base_low_raw_h.assetbundle");
                                    SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2Fassetbundle%2Fshow%2Fhero%2F194_sulie_show_base_raw_h.assetbundle");
                                    SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FHero_Wiro_SFX.bnk");
                                    SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2FHero_Wiro_VO.bnk");
                                    SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2FHero_Wiro_Show.bnk");
                                    SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FCDNImage%2FHeroHeadIcon%2FE08074BD2C22D7294436E68F0AEA0E90");
                                    //cp
                                    Uri uri1 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FAges%2FPrefab_Characters%2FPrefab_Hero%2F")));
                                    SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmp/Resources/".concat(game_ver.concat("/Ages/Prefab_Characters/Prefab_Hero/Actor_194_Actions.pkg.bytes")), uri1);
                                    Uri uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2Fassetbundle%2Ficon%2Fhero%2F")));
                                    SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmp/Resources/".concat(game_ver.concat("/assetbundle/icon/hero/194_sulie_icon.assetbundle")), uri2);
                                    Uri uri3 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FLanguages%2FCHT_Garena_TW%2F")));
                                    SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmp/Resources/".concat(game_ver.concat("/Languages/CHT_Garena_TW/languageMap_Newbie.txt")), uri3);
                                    Uri uri4 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2Fassetbundle%2Fshow%2Fhero%2F");
                                    SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmp/Extra/2019.V2/assetbundle/show/hero/194_sulie_show_base_low_raw_h.assetbundle", uri4);
                                    SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmp/Extra/2019.V2/assetbundle/show/hero/194_sulie_show_base_raw_h.assetbundle", uri4);
                                    Uri uri5 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2F");
                                    SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmp/Extra/2019.V2/Sound_DLC/Android/Hero_Wiro_SFX.bnk", uri5);
                                    Uri uri6 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2F");
                                    SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmp/Extra/2019.V2/Sound_DLC/Android/Chinese(Taiwan)/Hero_Wiro_VO.bnk", uri6);
                                    SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmp/Extra/2019.V2/Sound_DLC/Android/Chinese(Taiwan)/Hero_Wiro_Show.bnk", uri6);
                                    Uri uri7 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FCDNImage%2FHeroHeadIcon%2F");
                                    SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmp/CDNImage/HeroHeadIcon/E08074BD2C22D7294436E68F0AEA0E90", uri7);
                                    FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmp");
                                    showMessage("完成");

                                } else if (ChooseUtilActivity.Method == "Shizuku") {
                                    /**重要，如果關閉服務，需要先判斷ping才能檢測是否granted，否則異常*/
                                    if (Shizuku.pingBinder()) { //關閉 shizuku 服務就 ping 不到了
                                        if (!(Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED)) {
                                            showMessage("由於您拒絕Shizuku權限\n請重新選擇存取方式");
                                            FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmp");
                                            Intent pageJump = new Intent();
                                            pageJump.setClass(OtherActivity.this, ChooseUtilActivity.class);
                                            startActivity(pageJump);
                                            finish();
                                            overridePendingTransition(0, 0);
                                        } else { // 這裡才能執行 shell
                                            if (HomeActivity.CheckPermissionSoundSuShizuku) {
                                                StartInitializeShell("cp -r " + "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmp/*" +  " " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/");
                                                waitForShizukuCompletion(() -> FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmp"));
                                                showMessage("完成");
                                            } else {
                                                //SAF rm
                                                SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2Fassetbundle%2Fshow%2Fhero%2F194_sulie_show_base_low_raw_h.assetbundle");
                                                SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2Fassetbundle%2Fshow%2Fhero%2F194_sulie_show_base_raw_h.assetbundle");
                                                SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FHero_Wiro_SFX.bnk");
                                                SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2FHero_Wiro_VO.bnk");
                                                SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2FHero_Wiro_Show.bnk");
                                                SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FCDNImage%2FHeroHeadIcon%2FE08074BD2C22D7294436E68F0AEA0E90");
                                                //SAF cp
                                                Uri uri4 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2Fassetbundle%2Fshow%2Fhero%2F");
                                                SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmp/Extra/2019.V2/assetbundle/show/hero/194_sulie_show_base_low_raw_h.assetbundle", uri4);
                                                SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmp/Extra/2019.V2/assetbundle/show/hero/194_sulie_show_base_raw_h.assetbundle", uri4);
                                                Uri uri5 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2F");
                                                SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmp/Extra/2019.V2/Sound_DLC/Android/Hero_Wiro_SFX.bnk", uri5);
                                                Uri uri6 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2F");
                                                SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmp/Extra/2019.V2/Sound_DLC/Android/Chinese(Taiwan)/Hero_Wiro_VO.bnk", uri6);
                                                SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmp/Extra/2019.V2/Sound_DLC/Android/Chinese(Taiwan)/Hero_Wiro_Show.bnk", uri6);
                                                Uri uri7 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FCDNImage%2FHeroHeadIcon%2F");
                                                SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmp/CDNImage/HeroHeadIcon/E08074BD2C22D7294436E68F0AEA0E90", uri7);
                                                //Shizuku cp
                                                StartInitializeShell("cp -r " + "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmp/Resources" +  " " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/");
                                                waitForShizukuCompletion(() -> FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmp"));
                                                showMessage("完成");
                                            }

                                        }
                                    } else {
                                        showMessage("由於您關閉Shizuku服務\n請重新選擇存取方式");
                                        FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmp");
                                        Intent pageJump = new Intent();
                                        pageJump.setClass(OtherActivity.this, ChooseUtilActivity.class);
                                        startActivity(pageJump);
                                        finish();
                                        overridePendingTransition(0, 0);
                                    }
                                }
                            }
                        }.execute();
                        //結束
                    } else {
                        _unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/wiro.PS", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/");
                        showMessage("完成");
                    }
                } else {
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
                delete.setMessage("此按鈕用於還原國動維羅修改\n按下確認將還原，並且須下載一小部分大廳 DLC 資源");
                delete.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            if (ChooseUtilActivity.Method == "SU") {
                                if (SuperUserUtil.haveSU()) {
                                    SuperUserUtil.rmWithSU("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources");
                                    SuperUserUtil.rmWithSU("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/assetbundle/show/hero/194_sulie_show_base_low_raw_h.assetbundle");
                                    SuperUserUtil.rmWithSU("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/assetbundle/show/hero/194_sulie_show_base_raw_h.assetbundle");
                                    SuperUserUtil.rmWithSU("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/Hero_Wiro_SFX.bnk");
                                    SuperUserUtil.rmWithSU("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/Chinese(Taiwan)/Hero_Wiro_VO.bnk");
                                    SuperUserUtil.rmWithSU("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/Chinese(Taiwan)/Hero_Wiro_Show.bnk");
                                    SuperUserUtil.rmWithSU("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/CDNImage/HeroHeadIcon/E08074BD2C22D7294436E68F0AEA0E90");
                                    showMessage("還原完成\n正在為您開啟遊戲");
                                    LaunchAOV();
                                } else {
                                    showMessage("你已撤銷 root 權限");
                                    MainActivity.haveSU = false;
                                    Intent pageJump = new Intent();
                                    pageJump.setClass(OtherActivity.this, ChooseUtilActivity.class);
                                    startActivity(pageJump);
                                    finish();
                                    overridePendingTransition(0, 0);
                                }

                            } else if (ChooseUtilActivity.Method == "SAF") {
                                SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources");
                                SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2Fassetbundle%2Fshow%2Fhero%2F194_sulie_show_base_low_raw_h.assetbundle");
                                SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2Fassetbundle%2Fshow%2Fhero%2F194_sulie_show_base_raw_h.assetbundle");
                                SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FHero_Wiro_SFX.bnk");
                                SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2FHero_Wiro_VO.bnk");
                                SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2FHero_Wiro_Show.bnk");
                                SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FCDNImage%2FHeroHeadIcon%2FE08074BD2C22D7294436E68F0AEA0E90");
                                showMessage("還原完成\n正在為您開啟遊戲");
                                LaunchAOV();

                            } else if (ChooseUtilActivity.Method == "Shizuku") {
                                /**重要，如果關閉服務，需要先判斷ping才能檢測是否granted，否則異常*/
                                if (Shizuku.pingBinder()) { //關閉 shizuku 服務就 ping 不到了
                                    if (!(Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED)) {
                                        showMessage("由於您拒絕Shizuku權限\n請重新選擇存取方式");
                                        Intent pageJump = new Intent();
                                        pageJump.setClass(OtherActivity.this, ChooseUtilActivity.class);
                                        startActivity(pageJump);
                                        finish();
                                        overridePendingTransition(0, 0);
                                    } else { // 這裡才能執行 shell

                                        if (HomeActivity.CheckPermissionSoundSuShizuku) {

                                            StartInitializeShell("rm " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources");
                                            waitForShizukuCompletion(() -> {
                                                StartInitializeShell("rm " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/CDNImage/HeroHeadIcon/E08074BD2C22D7294436E68F0AEA0E90");
                                                waitForShizukuCompletion(() -> {
                                                    StartInitializeShell("rm " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/assetbundle/show/hero/194_sulie_show_base_low_raw_h.assetbundle");
                                                    waitForShizukuCompletion(() -> {
                                                        StartInitializeShell("rm " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/assetbundle/show/hero/194_sulie_show_base_raw_h.assetbundle");
                                                        waitForShizukuCompletion(() -> {
                                                            StartInitializeShell("rm " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/Hero_Wiro_SFX.bnk");
                                                            waitForShizukuCompletion(() -> {
                                                                StartInitializeShell("rm " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/Chinese(Taiwan)/Hero_Wiro_VO.bnk");
                                                                waitForShizukuCompletion(() -> {
                                                                    StartInitializeShell("rm " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/Chinese(Taiwan)/Hero_Wiro_Show.bnk");
                                                                    waitForShizukuCompletion(() -> {
                                                                        showMessage("還原完成\n正在為您開啟遊戲");
                                                                        LaunchAOV();
                                                                    });
                                                                });
                                                            });
                                                        });
                                                    });
                                                });
                                            });

                                        } else {
                                            SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2Fassetbundle%2Fshow%2Fhero%2F194_sulie_show_base_low_raw_h.assetbundle");
                                            SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2Fassetbundle%2Fshow%2Fhero%2F194_sulie_show_base_raw_h.assetbundle");
                                            SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FHero_Wiro_SFX.bnk");
                                            SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2FHero_Wiro_VO.bnk");
                                            SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2FHero_Wiro_Show.bnk");
                                            SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FCDNImage%2FHeroHeadIcon%2FE08074BD2C22D7294436E68F0AEA0E90");
                                            StartInitializeShell("rm -r " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources");
                                            waitForShizukuCompletion(() -> LaunchAOV());
                                            showMessage("還原完成\n正在為您開啟遊戲");
                                        }

                                    }
                                } else {
                                    showMessage("由於您關閉Shizuku服務\n請重新選擇存取方式");
                                    Intent pageJump = new Intent();
                                    pageJump.setClass(OtherActivity.this, ChooseUtilActivity.class);
                                    startActivity(pageJump);
                                    finish();
                                    overridePendingTransition(0, 0);
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
                            showMessage("還原成功");
                            LaunchAOV();
                        }
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
                        FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmpp");
                        ProgressBar_Show("請稍後...");
                        new BackgroundTaskClass(OtherActivity.this){
                            @Override
                            public void doInBackground() {
                                Looper.prepare();
                                _unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/volkath.PS", "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmpp/");
                            }
                            @Override
                            public void onPostExecute(){
                                //完成
                                ProgressBar_Dismiss();
                                if (ChooseUtilActivity.Method == "SU") {
                                    if (SuperUserUtil.haveSU()) {
                                        SuperUserUtil.cpWithSU("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmpp/*", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/");
                                        FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmpp");
                                        showMessage("完成");
                                    } else {
                                        showMessage("你已撤銷 root 權限");
                                        MainActivity.haveSU = false;
                                        Intent pageJump = new Intent();
                                        pageJump.setClass(OtherActivity.this, ChooseUtilActivity.class);
                                        startActivity(pageJump);
                                        finish();
                                        overridePendingTransition(0, 0);
                                    }

                                } else if (ChooseUtilActivity.Method == "SAF") {
                                    //rm
                                    SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FAges%2FPrefab_Characters%2FPrefab_Hero%2FActor_529_Actions.pkg.bytes")));
                                    SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FPrefab_Characters%2FActor_529_Infos.pkg.bytes")));
                                    //cp
                                    Uri uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FAges%2FPrefab_Characters%2FPrefab_Hero%2F")));
                                    SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmpp/".concat(game_ver.concat("/Ages/Prefab_Characters/Prefab_Hero/Actor_529_Actions.pkg.bytes")), uriA);
                                    Uri uriB = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FPrefab_Characters%2F")));
                                    SAFUtil.copyFilePath2Uri(getApplicationContext(), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmpp/".concat(game_ver.concat("/Prefab_Characters/Actor_529_Infos.pkg.bytes")), uriB);
                                    showMessage("啟用成功");
                                    FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmpp");
                                } else if (ChooseUtilActivity.Method == "Shizuku") {
                                    /**重要，如果關閉服務，需要先判斷ping才能檢測是否granted，否則異常*/
                                    if (Shizuku.pingBinder()) { //關閉 shizuku 服務就 ping 不到了
                                        if (!(Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED)) {
                                            showMessage("由於您拒絕Shizuku權限\n請重新選擇存取方式");
                                            Intent pageJump = new Intent();
                                            pageJump.setClass(OtherActivity.this, ChooseUtilActivity.class);
                                            startActivity(pageJump);
                                            finish();
                                            overridePendingTransition(0, 0);
                                        } else { // 這裡才能執行 shell
                                            StartInitializeShell("cp -r " + "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmpp/*" +  " " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/");
                                            waitForShizukuCompletion(() -> FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmpp"));
                                            showMessage("完成");
                                        }
                                    } else {
                                        showMessage("由於您關閉Shizuku服務\n請重新選擇存取方式");
                                        Intent pageJump = new Intent();
                                        pageJump.setClass(OtherActivity.this, ChooseUtilActivity.class);
                                        startActivity(pageJump);
                                        finish();
                                        overridePendingTransition(0, 0);
                                    }

                                }
                            }
                        }.execute();
                        //結束
                    } else {
                        _unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/volkath.PS", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/");
                        showMessage("完成");
                    }
                } else {
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
                delete.setMessage("此按鈕用於還原安格列變身修改\n按下確認將還原，無須於大廳下載 DLC 資源");
                delete.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            if (ChooseUtilActivity.Method == "SU") {
                                if (SuperUserUtil.haveSU()) {
                                    SuperUserUtil.rmWithSU("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources");
                                    showMessage("還原完成\n正在為您開啟遊戲");
                                    LaunchAOV();
                                } else {
                                    showMessage("你已撤銷 root 權限");
                                    MainActivity.haveSU = false;
                                    Intent pageJump = new Intent();
                                    pageJump.setClass(OtherActivity.this, ChooseUtilActivity.class);
                                    startActivity(pageJump);
                                    finish();
                                    overridePendingTransition(0, 0);
                                }

                            } else if (ChooseUtilActivity.Method == "SAF") {
                                SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources");
                                showMessage("還原完成\n正在為您開啟遊戲");
                                LaunchAOV();
                            } else if (ChooseUtilActivity.Method == "Shizuku") {
                                /**重要，如果關閉服務，需要先判斷ping才能檢測是否granted，否則異常*/
                                if (Shizuku.pingBinder()) { //關閉 shizuku 服務就 ping 不到了
                                    if (!(Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED)) {
                                        showMessage("由於您拒絕Shizuku權限\n請重新選擇存取方式");
                                        Intent pageJump = new Intent();
                                        pageJump.setClass(OtherActivity.this, ChooseUtilActivity.class);
                                        startActivity(pageJump);
                                        finish();
                                        overridePendingTransition(0, 0);
                                    } else { // 這裡才能執行 shell
                                        StartInitializeShell("rm -r " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources");
                                        waitForShizukuCompletion(() -> LaunchAOV());
                                        showMessage("還原完成\n正在為您開啟遊戲");
                                    }
                                } else {
                                    showMessage("由於您關閉Shizuku服務\n請重新選擇存取方式");
                                    Intent pageJump = new Intent();
                                    pageJump.setClass(OtherActivity.this, ChooseUtilActivity.class);
                                    startActivity(pageJump);
                                    finish();
                                    overridePendingTransition(0, 0);
                                }
                            }
                        } else {
                            FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources");
                            showMessage("還原完成\n正在為您開啟遊戲");
                            LaunchAOV();
                        }
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
                delete.setMessage("此按鈕用於還原次元防禦塔修改\n按下確認將還原，無須下載任何大廳 DLC 資源");
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
                delete.setMessage("此按鈕用於還原次元兵線修改\n按下確認將還原，無須下載任何大廳 DLC 資源");
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
                delete.setMessage("此按鈕用於還原次元紅藍buff修改\n按下確認將還原，無須下載任何大廳 DLC 資源");
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
                    if (ChooseUtilActivity.Method == "SU") {
                        if (SuperUserUtil.haveSU()) {
                            SuperUserUtil.rmWithSU("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/Hero_Wisp_SFX.bnk");
                            showMessage("還原成功");
                        } else {
                            showMessage("你已撤銷 root 權限");
                            MainActivity.haveSU = false;
                            Intent pageJump = new Intent();
                            pageJump.setClass(OtherActivity.this, ChooseUtilActivity.class);
                            startActivity(pageJump);
                            finish();
                            overridePendingTransition(0, 0);
                        }

                    } else if (ChooseUtilActivity.Method == "SAF") {
                        SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FHero_Wisp_SFX.bnk");
                        showMessage("還原成功");
                    } else if (ChooseUtilActivity.Method == "Shizuku") {
                        /**重要，如果關閉服務，需要先判斷ping才能檢測是否granted，否則異常*/
                        if (Shizuku.pingBinder()) { //關閉 shizuku 服務就 ping 不到了
                            if (!(Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED)) {
                                showMessage("由於您拒絕Shizuku權限\n請重新選擇存取方式");
                                Intent pageJump = new Intent();
                                pageJump.setClass(OtherActivity.this, ChooseUtilActivity.class);
                                startActivity(pageJump);
                                finish();
                                overridePendingTransition(0, 0);
                            } else { // 這裡才能執行 shell
                                StartInitializeShell("rm -r " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/Hero_Wisp_SFX.bnk");
                                showMessage("還原成功");
                            }
                        } else {
                            showMessage("由於您關閉Shizuku服務\n請重新選擇存取方式");
                            Intent pageJump = new Intent();
                            pageJump.setClass(OtherActivity.this, ChooseUtilActivity.class);
                            startActivity(pageJump);
                            finish();
                            overridePendingTransition(0, 0);
                        }
                    }
                }else{
                    FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/Hero_Wisp_SFX.bnk");
                    showMessage("還原成功");
                }
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
                                } else {
                                    ModOffest(Device_ID.length()-("xiaomi M2102J20SG".length()));
                                }
                                Encrypt();
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                                    if (ChooseUtilActivity.Method == "SU") {
                                        if (SuperUserUtil.haveSU()) {
                                            SuperUserUtil.cpWithSU(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tmp/VeryHighFrameModeBlackList.bytes"), "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + game_ver + "/Databin/Client/Text/");
                                            FileUtil.deleteFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tmp"));
                                        } else {
                                            showMessage("你已撤銷 root 權限");
                                            MainActivity.haveSU = false;
                                            Intent pageJump = new Intent();
                                            pageJump.setClass(OtherActivity.this, ChooseUtilActivity.class);
                                            startActivity(pageJump);
                                            finish();
                                            overridePendingTransition(0, 0);
                                        }

                                    } else if (ChooseUtilActivity.Method == "SAF") {
                                        //rm
                                        SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FDatabin%2FClient%2FText%2FVeryHighFrameModeBlackList.bytes")));
                                        //cp
                                        Uri uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FDatabin%2FClient%2FText%2F")));
                                        SAFUtil.copyFilePath2Uri(getApplicationContext(), FileUtil.getPackageDataDir(getApplicationContext()).concat("/tmp/VeryHighFrameModeBlackList.bytes"), uriA);
                                        FileUtil.deleteFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tmp"));
                                    } else if (ChooseUtilActivity.Method == "Shizuku") {
                                        /**重要，如果關閉服務，需要先判斷ping才能檢測是否granted，否則異常*/
                                        if (Shizuku.pingBinder()) { //關閉 shizuku 服務就 ping 不到了
                                            if (!(Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED)) {
                                                showMessage("由於您拒絕Shizuku權限\n請重新選擇存取方式");
                                                Intent pageJump = new Intent();
                                                pageJump.setClass(OtherActivity.this, ChooseUtilActivity.class);
                                                startActivity(pageJump);
                                                finish();
                                                overridePendingTransition(0, 0);
                                            } else { // 這裡才能執行 shell
                                                StartInitializeShell("cp " + "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/tmp/VeryHighFrameModeBlackList.bytes" +  " " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + game_ver + "/Databin/Client/Text/");
                                                waitForShizukuCompletion(() -> FileUtil.deleteFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tmp")));
                                            }
                                        } else {
                                            showMessage("由於您關閉Shizuku服務\n請重新選擇存取方式");
                                            Intent pageJump = new Intent();
                                            pageJump.setClass(OtherActivity.this, ChooseUtilActivity.class);
                                            startActivity(pageJump);
                                            finish();
                                            overridePendingTransition(0, 0);
                                        }
                                    }
                                } else {
                                    FileUtil.copyFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tmp/VeryHighFrameModeBlackList.bytes"), "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + game_ver + "/Databin/Client/Text/VeryHighFrameModeBlackList.bytes");
                                    FileUtil.deleteFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tmp"));
                                }
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

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            if (ChooseUtilActivity.Method == "SAF" || (ChooseUtilActivity.Method == "Shizuku" && !HomeActivity.CheckPermissionSoundSuShizuku))
                                get_permission();
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
        Other.addChildEventListener(_Other_child_listener);
    }

    private void initializeLogic() {
        if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/wiro.png")) {
            try {
                if (!Objects.equals(FileUtil.getFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/wiro.png", "MD5"), "251F3675E8E16FD55C90FF9440687A35")) {
                    //校驗失敗，重新下載
                    DLC("https://cdn.discordapp.com/attachments/842221289464004608/1074304245035180092/wiro.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/", imageview12);
                } else {
                    imageview12.setImageBitmap(FileUtil.decodeSampleBitmapFromPath("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/wiro.png", 1024, 1024));
                }
            } catch (Exception e) {
            }
        } else {
            DLC("https://cdn.discordapp.com/attachments/842221289464004608/1074304245035180092/wiro.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/", imageview12);
        }
        if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/volkath.png")) {
            try {
                if (!Objects.equals(FileUtil.getFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/volkath.png", "MD5"), "DCD63BC4D8DEBC65C3C9A269786DB671")) {
                    //校驗失敗，重新下載
                    DLC("https://cdn.discordapp.com/attachments/842221289464004608/1074304244775129169/volkath.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/", imageview18);
                } else {
                    imageview18.setImageBitmap(FileUtil.decodeSampleBitmapFromPath("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/volkath.png", 1024, 1024));
                }
            } catch (Exception e) {
            }
        } else {
            DLC("https://cdn.discordapp.com/attachments/842221289464004608/1074304244775129169/volkath.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/", imageview18);
        }
        if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/tower.png")) {
            try {
                if (!Objects.equals(FileUtil.getFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/tower.png", "MD5"), "52E71F84EDA74766C35882D83DAA6E27")) {
                    //校驗失敗，重新下載
                    DLC("https://cdn.discordapp.com/attachments/842221289464004608/1074304244506701924/tower.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/", imageview23);
                } else {
                    imageview23.setImageBitmap(FileUtil.decodeSampleBitmapFromPath("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/tower.png", 1024, 1024));
                }
            } catch (Exception e) {
            }
        } else {
            DLC("https://cdn.discordapp.com/attachments/842221289464004608/1074304244506701924/tower.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/", imageview23);
        }
        if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/soldier.png")) {
            try {
                if (!Objects.equals(FileUtil.getFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/soldier.png", "MD5"), "C4D53399CB34AD631D1FE4F570A37665")) {
                    //校驗失敗，重新下載
                    DLC("https://cdn.discordapp.com/attachments/842221289464004608/1074304244242456656/soldier.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/", imageview21);
                } else {
                    imageview21.setImageBitmap(FileUtil.decodeSampleBitmapFromPath("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/soldier.png", 1024, 1024));
                }
            } catch (Exception e) {
            }
        } else {
            DLC("https://cdn.discordapp.com/attachments/842221289464004608/1074304244242456656/soldier.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/", imageview21);
        }
        if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/buff.png")) {
            try {
                if (!Objects.equals(FileUtil.getFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/buff.png", "MD5"), "F9DC628215FEBE3E3E8DA535796443CF")) {
                    //校驗失敗，重新下載
                    DLC("https://cdn.discordapp.com/attachments/842221289464004608/1074304243965628467/buff.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/", imageview27);
                } else {
                    imageview27.setImageBitmap(FileUtil.decodeSampleBitmapFromPath("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/buff.png", 1024, 1024));
                }
            } catch (Exception e) {
            }
        } else {
            DLC("https://cdn.discordapp.com/attachments/842221289464004608/1074304243965628467/buff.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/", imageview27);
        }
        if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/zhadanren.png")) {
            try {
                if (!Objects.equals(FileUtil.getFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/zhadanren.png", "MD5"), "81D433796E339F73EA7B772218D4F2A1")) {
                    //校驗失敗，重新下載
                    DLC("https://cdn.discordapp.com/attachments/842221289464004608/1074304245303619635/zhadanren.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/", imageview33);
                } else {
                    imageview33.setImageBitmap(FileUtil.decodeSampleBitmapFromPath("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/zhadanren.png", 1024, 1024));
                }
            } catch (Exception e) {
            }
        } else {
            DLC("https://cdn.discordapp.com/attachments/842221289464004608/1074304245303619635/zhadanren.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/", imageview33);
        }
        if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/120FPS.png")) {
            try {
                if (!Objects.equals(FileUtil.getFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/120FPS.png", "MD5"), "C6D31639B84565116DB060CF6A851FF3")) {
                    //校驗失敗，重新下載
                    DLC("https://cdn.discordapp.com/attachments/842221289464004608/1137475199411957901/120FPS.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/", imageview37);
                } else {
                    imageview37.setImageBitmap(FileUtil.decodeSampleBitmapFromPath("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/120FPS.png", 1024, 1024));
                }
            } catch (Exception e) {
            }
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) //重要！！！
                dalvik.system.ZipPathValidator.clearCallback();
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
                            if (ChooseUtilActivity.Method == "SU") {
                                if (SuperUserUtil.haveSU()) {
                                    if (Located.contains("Res")){
                                        SuperUserUtil.cpWithSU(Path + filename, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + game_ver + "/Prefab_Characters/");
                                    } else if (Located.contains("Extra")){
                                        SuperUserUtil.cpWithSU(Path + filename, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/");
                                    }
                                    showMessage("完成");
                                    FileUtil.deleteFile(Path + filename);
                                } else {
                                    showMessage("你已撤銷 root 權限");
                                    MainActivity.haveSU = false;
                                    FileUtil.deleteFile(Path + filename);
                                    Intent pageJump = new Intent();
                                    pageJump.setClass(OtherActivity.this, ChooseUtilActivity.class);
                                    startActivity(pageJump);
                                    finish();
                                    overridePendingTransition(0, 0);
                                }

                            } else if (ChooseUtilActivity.Method == "SAF") {
                                if (Located.contains("Res")){
                                    SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F" + game_ver + "%2FPrefab_Characters%2F" + filename);
                                    Uri uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F" + game_ver + "%2FPrefab_Characters%2F");
                                    SAFUtil.copyFilePath2Uri(getApplicationContext(), Path + filename, uriA);
                                    showMessage("成功");
                                } else if (Located.contains("Extra")){
                                    SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2F" + filename);
                                    Uri uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2F");
                                    SAFUtil.copyFilePath2Uri(getApplicationContext(), Path + filename, uriA);
                                    showMessage("成功");
                                }
                                FileUtil.deleteFile(Path + filename);

                            } else if (ChooseUtilActivity.Method == "Shizuku") {
                                /**重要，如果關閉服務，需要先判斷ping才能檢測是否granted，否則異常*/
                                if (Shizuku.pingBinder()) { //關閉 shizuku 服務就 ping 不到了
                                    if (!(Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED)) {
                                        showMessage("由於您拒絕Shizuku權限\n請重新選擇存取方式");
                                        FileUtil.deleteFile(Path + filename);
                                        Intent pageJump = new Intent();
                                        pageJump.setClass(OtherActivity.this, ChooseUtilActivity.class);
                                        startActivity(pageJump);
                                        finish();
                                        overridePendingTransition(0, 0);
                                    } else { // 這裡才能執行 shell
                                        if (Located.contains("Res")){
                                            StartInitializeShell("cp -r " + Path + filename +  " " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + game_ver + "/Prefab_Characters/");
                                            String ppath = Path;
                                            String ffilename = filename;
                                            waitForShizukuCompletion(() -> FileUtil.deleteFile(ppath + ffilename));
                                            showMessage("成功");
                                        } else if (Located.contains("Extra")){
                                            if (HomeActivity.CheckPermissionSoundSuShizuku) {
                                                StartInitializeShell("cp -r " + Path + filename +  " " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/");
                                                String ppath = Path;
                                                String ffilename = filename;
                                                waitForShizukuCompletion(() -> FileUtil.deleteFile(ppath + ffilename));
                                                showMessage("成功");
                                            } else {
                                                SAFUtil.rmUriPath(getApplicationContext(), "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2F" + filename);
                                                Uri uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2F");
                                                SAFUtil.copyFilePath2Uri(getApplicationContext(), Path + filename, uriA);
                                                FileUtil.deleteFile(Path + filename);
                                                showMessage("成功");
                                            }
                                        }
                                    }
                                } else {
                                    showMessage("由於您關閉Shizuku服務\n請重新選擇存取方式");
                                    FileUtil.deleteFile(Path + filename);
                                    Intent pageJump = new Intent();
                                    pageJump.setClass(OtherActivity.this, ChooseUtilActivity.class);
                                    startActivity(pageJump);
                                    finish();
                                    overridePendingTransition(0, 0);
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
                if (refreshRate >= 87.0f) {
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

        final String[] result_tmp = new String[1];
        if (ChooseUtilActivity.Method == "Shizuku") {
            StartInitializeShell("du -sh " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/assetbundle");
            waitForShizukuCompletion(() -> {
                result_tmp[0] = mResult.get(mResult.size()-3).split("/")[0];
            });
        }

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
                            builder1.setTitle("獲取權限資訊狀態");
                            if (ChooseUtilActivity.Method == "Shizuku")
                                builder1.setMessage("assetbundle 目錄尚未取得權限，您已下載 " + result_tmp[0] + "的資源，點擊「授權」後，會占用 " + result_tmp[0] + "空間的垃圾，並且局內大量資源皆須重新下載，目前僅 維羅國動 需要使用該目錄，若忽略，則國動模型的修改便無效，聲音仍然會修改成功");
                            else
                                builder1.setMessage("assetbundle 目錄尚未取得權限，若點擊「授權」，局內大量資源皆須重新下載 (最大可能高達 5 Gb)，目前僅 維羅國動 需要使用該目錄，若忽略，則國動模型的修改便無效，聲音仍然會修改成功");
                            builder1.setCancelable(true)
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


    private void _save(final String _file, final String _path, final String _file2) {
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
