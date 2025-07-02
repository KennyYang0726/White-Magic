package com.aoveditor.phantomsneak;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.aoveditor.phantomsneak.Services.AdmobService;
import com.bumptech.glide.Glide;

import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

import rikka.shizuku.Shizuku;

import com.aoveditor.phantomsneak.AlertDialog.CustomAlertDialog2;
import com.aoveditor.phantomsneak.BackgroundTask.BackgroundTask;
import com.aoveditor.phantomsneak.DownloadManager.DownloadManager;
import com.aoveditor.phantomsneak.Utils.FileUtil;
import com.aoveditor.phantomsneak.Utils.RootUtil;
import com.aoveditor.phantomsneak.Utils.ShizukuUtil;
import com.aoveditor.phantomsneak.ZipManager.ZipManager;



public class Activity2_Fragment4_Lobby extends Fragment {

    /**Element*/
    // AOV 主題
    private Button button_delete_lobby_plugins;
    private Button button_recovery_lobby_modified;
    private ImageView lobby_01;
    private ImageView lobby_02;
    private ImageView lobby_03;
    private ImageView lobby_04;
    private ImageView lobby_05;
    private ImageView lobby_06;
    private ImageView lobby_07;
    private ImageView lobby_08;
    private ImageView lobby_09;
    private ImageView lobby_10;
    private ImageView lobby_11;
    private ImageView lobby_12;
    private ImageView lobby_13;
    private ImageView lobby_14;
    private ImageView lobby_15;
    private ImageView lobby_16;
    private ImageView lobby_17;
    private ImageView lobby_18;
    private ImageView lobby_19;
    private ImageView lobby_20;
    private ImageView lobby_21;
    // 額外主題
    private ImageView lobby_41;
    private ImageView lobby_42;
    private ImageView lobby_43;
    private ImageView lobby_44;
    private ImageView lobby_45;
    private ImageView lobby_46;

    /**Components*/
    private SharedPreferences AppSettings; // 儲存 主題色, SAF授權目錄, AccessMethod, ...
    private DatabaseReference lobby_plugins;
    private ValueEventListener lobbyPluginListener;
    private AdmobService admobService;

    /**Variable*/
    private String AccessMethod = "";
    private final String Lobby01 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/main/Plugins/4-Lobby/1-SAO1.PS";
    private final String Lobby02 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/main/Plugins/4-Lobby/2-SAO2.PS";
    private final String Lobby03 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/main/Plugins/4-Lobby/3-Nakaroth.PS";
    private final String Lobby04 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/main/Plugins/4-Lobby/4-WAVE.PS";
    private final String Lobby05 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/main/Plugins/4-Lobby/5-NewYear2019.PS";
    private final String Lobby06 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/main/Plugins/4-Lobby/6-NewYear2021.PS";
    private final String Lobby07 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/main/Plugins/4-Lobby/7-NewYear2022.PS";
    private final String Lobby08 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/main/Plugins/4-Lobby/8-AIC2020.PS";
    private final String Lobby09 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/main/Plugins/4-Lobby/9-Ultraman.PS";
    private final String Lobby10 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/main/Plugins/4-Lobby/10-DoRiA.PS";
    private final String Lobby11 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/main/Plugins/4-Lobby/11-PoolParty.PS";
    private final String Lobby12 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/main/Plugins/4-Lobby/12-SuLi.PS";
    private final String Lobby13 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/main/Plugins/4-Lobby/13-Nymph.PS";
    private final String Lobby14 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/main/Plugins/4-Lobby/14-DaSiKong.PS";
    private final String Lobby15 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/main/Plugins/4-Lobby/15-Goverra.PS";
    private final String Lobby16 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/main/Plugins/4-Lobby/16-時之奇旅.PS";
    private final String Lobby17 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/main/Plugins/4-Lobby/17-Yao.PS";
    private final String Lobby18 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/main/Plugins/4-Lobby/18-Bonnie.PS";
    private final String Lobby19 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/main/Plugins/4-Lobby/19-WuKongEVO.PS";
    private final String Lobby20 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/main/Plugins/4-Lobby/20-BLEACH.PS";
    private final String Lobby21 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/main/Plugins/4-Lobby/21-HarleyQuinn.PS";
    private String Lobby41 = "";
    private String Lobby41_IMG = "";
    private String Lobby42 = "";
    private String Lobby42_IMG = "";
    private String Lobby43 = "";
    private String Lobby43_IMG = "";
    private String Lobby44 = "";
    private String Lobby44_IMG = "";
    private String Lobby45 = "";
    private String Lobby45_IMG = "";
    private String Lobby46 = "";
    private String Lobby46_IMG = "";
    private String Delete_Sound = "";
    private String Delete_Movie = "";
    private String Update_Date = "";

    private boolean AutoMod = false;
    private boolean canMod = true;
    // 產生亂數，應用不可動的目錄
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();
    // 標誌位，避免授權中手癢按出去
    private boolean isBusying = false;


    public Activity2_Fragment4_Lobby() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity2_fragment4_lobby, container, false);
        // 初始化 sp
        AppSettings = requireActivity().getSharedPreferences("AppSettings", Activity.MODE_PRIVATE);
        // 初始化廣告
        admobService = new AdmobService(requireContext());
        // 加載插頁廣告
        admobService.loadInterstitialAd(getString(R.string.Interstitial));
        // 元件
        button_delete_lobby_plugins = v.findViewById(R.id.button_delete_lobby_plugins);
        button_recovery_lobby_modified = v.findViewById(R.id.button_recovery_lobby_modified);
        lobby_01 = v.findViewById(R.id.lobby_01);
        lobby_02 = v.findViewById(R.id.lobby_02);
        lobby_03 = v.findViewById(R.id.lobby_03);
        lobby_04 = v.findViewById(R.id.lobby_04);
        lobby_05 = v.findViewById(R.id.lobby_05);
        lobby_06 = v.findViewById(R.id.lobby_06);
        lobby_07 = v.findViewById(R.id.lobby_07);
        lobby_08 = v.findViewById(R.id.lobby_08);
        lobby_09 = v.findViewById(R.id.lobby_09);
        lobby_10 = v.findViewById(R.id.lobby_10);
        lobby_11 = v.findViewById(R.id.lobby_11);
        lobby_12 = v.findViewById(R.id.lobby_12);
        lobby_13 = v.findViewById(R.id.lobby_13);
        lobby_14 = v.findViewById(R.id.lobby_14);
        lobby_15 = v.findViewById(R.id.lobby_15);
        lobby_16 = v.findViewById(R.id.lobby_16);
        lobby_17 = v.findViewById(R.id.lobby_17);
        lobby_18 = v.findViewById(R.id.lobby_18);
        lobby_19 = v.findViewById(R.id.lobby_19);
        lobby_20 = v.findViewById(R.id.lobby_20);
        lobby_21 = v.findViewById(R.id.lobby_21);
        lobby_41 = v.findViewById(R.id.lobby_41);
        lobby_42 = v.findViewById(R.id.lobby_42);
        lobby_43 = v.findViewById(R.id.lobby_43);
        lobby_44 = v.findViewById(R.id.lobby_44);
        lobby_45 = v.findViewById(R.id.lobby_45);
        lobby_46 = v.findViewById(R.id.lobby_46);
        // 事件
        button_delete_lobby_plugins.setOnClickListener(v120 -> {
            FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-Lobby");
            FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-Lobby");
            showMessage(getString(R.string.Delete_Successfully));
        });
        button_recovery_lobby_modified.setOnClickListener(v1 -> ClearLobbyMod());
        lobby_01.setOnClickListener(v12 -> {
            String name = Uri.parse(Lobby01).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Update_Date + ".PS";
            LobbyImageClickEvent(PluginName, Lobby01);
        });
        lobby_02.setOnClickListener(v13 -> {
            String name = Uri.parse(Lobby02).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Update_Date + ".PS";
            LobbyImageClickEvent(PluginName, Lobby02);
        });
        lobby_03.setOnClickListener(v14 -> {
            String name = Uri.parse(Lobby03).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Update_Date + ".PS";
            LobbyImageClickEvent(PluginName, Lobby03);
        });
        lobby_04.setOnClickListener(v15 -> {
            String name = Uri.parse(Lobby04).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Update_Date + ".PS";
            LobbyImageClickEvent(PluginName, Lobby04);
        });
        lobby_05.setOnClickListener(v16 -> {
            String name = Uri.parse(Lobby05).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Update_Date + ".PS";
            LobbyImageClickEvent(PluginName, Lobby05);
        });
        lobby_06.setOnClickListener(v17 -> {
            String name = Uri.parse(Lobby06).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Update_Date + ".PS";
            LobbyImageClickEvent(PluginName, Lobby06);
        });
        lobby_07.setOnClickListener(v18 -> {
            String name = Uri.parse(Lobby07).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Update_Date + ".PS";
            LobbyImageClickEvent(PluginName, Lobby07);
        });
        lobby_08.setOnClickListener(v19 -> {
            String name = Uri.parse(Lobby08).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Update_Date + ".PS";
            LobbyImageClickEvent(PluginName, Lobby08);
        });
        lobby_09.setOnClickListener(v110 -> {
            String name = Uri.parse(Lobby09).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Update_Date + ".PS";
            LobbyImageClickEvent(PluginName, Lobby09);
        });
        lobby_10.setOnClickListener(v111 -> {
            String name = Uri.parse(Lobby10).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Update_Date + ".PS";
            LobbyImageClickEvent(PluginName, Lobby10);
        });
        lobby_11.setOnClickListener(v112 -> {
            String name = Uri.parse(Lobby11).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Update_Date + ".PS";
            LobbyImageClickEvent(PluginName, Lobby11);
        });
        lobby_12.setOnClickListener(v113 -> {
            String name = Uri.parse(Lobby12).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Update_Date + ".PS";
            LobbyImageClickEvent(PluginName, Lobby12);
        });
        lobby_13.setOnClickListener(v114 -> {
            String name = Uri.parse(Lobby13).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Update_Date + ".PS";
            LobbyImageClickEvent(PluginName, Lobby13);
        });
        lobby_14.setOnClickListener(v115 -> {
            String name = Uri.parse(Lobby14).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Update_Date + ".PS";
            LobbyImageClickEvent(PluginName, Lobby14);
        });
        lobby_15.setOnClickListener(v116 -> {
            String name = Uri.parse(Lobby15).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Update_Date + ".PS";
            LobbyImageClickEvent(PluginName, Lobby15);
        });
        lobby_16.setOnClickListener(v117 -> {
            String name = Uri.parse(Lobby16).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Update_Date + ".PS";
            LobbyImageClickEvent(PluginName, Lobby16);
        });
        lobby_17.setOnClickListener(v118 -> {
            String name = Uri.parse(Lobby17).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Update_Date + ".PS";
            LobbyImageClickEvent(PluginName, Lobby17);
        });
        lobby_18.setOnClickListener(v119 -> {
            String name = Uri.parse(Lobby18).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Update_Date + ".PS";
            LobbyImageClickEvent(PluginName, Lobby18);
        });
        lobby_19.setOnClickListener(v120 -> {
            String name = Uri.parse(Lobby19).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Update_Date + ".PS";
            LobbyImageClickEvent(PluginName, Lobby19);
        });
        lobby_20.setOnClickListener(v121 -> {
            String name = Uri.parse(Lobby20).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Update_Date + ".PS";
            LobbyImageClickEvent(PluginName, Lobby20);
        });
        lobby_21.setOnClickListener(v122 -> {
            String name = Uri.parse(Lobby21).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Update_Date + ".PS";
            LobbyImageClickEvent(PluginName, Lobby21);
        });

        lobby_41.setOnClickListener(v144 -> {
            String name = Uri.parse(Lobby41).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Update_Date + ".PS";
            LobbyImageClickEvent(PluginName, Lobby41);
        });
        lobby_42.setOnClickListener(v145 -> {
            String name = Uri.parse(Lobby42).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Update_Date + ".PS";
            LobbyImageClickEvent(PluginName, Lobby42);
        });
        lobby_43.setOnClickListener(v146 -> {
            String name = Uri.parse(Lobby43).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Update_Date + ".PS";
            LobbyImageClickEvent(PluginName, Lobby43);
        });
        lobby_44.setOnClickListener(v147 -> {
            String name = Uri.parse(Lobby44).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Update_Date + ".PS";
            LobbyImageClickEvent(PluginName, Lobby44);
        });
        lobby_45.setOnClickListener(v148 -> {
            String name = Uri.parse(Lobby45).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Update_Date + ".PS";
            LobbyImageClickEvent(PluginName, Lobby45);
        });
        lobby_46.setOnClickListener(v149 -> {
            String name = Uri.parse(Lobby46).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Update_Date + ".PS";
            LobbyImageClickEvent(PluginName, Lobby46);
        });
        // 加載和顯示橫幅廣告
        AdView banner = v.findViewById(R.id.banner);
        admobService.loadBannerAd(banner);
        return v;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 當 Fragment 不可見時移除 Firebase 監聽器
        if (lobby_plugins != null && lobbyPluginListener != null) {
            lobby_plugins.removeEventListener(lobbyPluginListener);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        // 初始化 sp
        AppSettings = requireActivity().getSharedPreferences("AppSettings", Activity.MODE_PRIVATE);
        // 重新取得 sp 資料
        AccessMethod = AppSettings.getString("AccessMethod", "");
        AutoMod = AppSettings.getBoolean("AutoMod", false);
        // 在這裡重新載入資料或刷新畫面
        InitFirebaseDatabase();
    }

    // 初始化 Firebase Database
    private void InitFirebaseDatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        lobby_plugins = database.getReference("4-Lobby");
        lobbyPluginListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Lobby41 = dataSnapshot.child("Other1_URL").getValue(String.class);
                Lobby41_IMG = dataSnapshot.child("Other1").getValue(String.class);
                Lobby42 = dataSnapshot.child("Other2_URL").getValue(String.class);
                Lobby42_IMG = dataSnapshot.child("Other2").getValue(String.class);
                Lobby43 = dataSnapshot.child("Other3_URL").getValue(String.class);
                Lobby43_IMG = dataSnapshot.child("Other3").getValue(String.class);
                Lobby44 = dataSnapshot.child("Other4_URL").getValue(String.class);
                Lobby44_IMG = dataSnapshot.child("Other4").getValue(String.class);
                Lobby45 = dataSnapshot.child("Other5_URL").getValue(String.class);
                Lobby45_IMG = dataSnapshot.child("Other5").getValue(String.class);
                Lobby46 = dataSnapshot.child("Other6_URL").getValue(String.class);
                Lobby46_IMG = dataSnapshot.child("Other6").getValue(String.class);
                Delete_Sound = dataSnapshot.child("delete_sound").getValue(String.class);
                Delete_Movie = dataSnapshot.child("delete_movie").getValue(String.class);
                String update_date = Objects.requireNonNull(dataSnapshot.child("更新日期").getValue(String.class));
                Update_Date = "_" + update_date.split("/")[1] + "_" + update_date.split("/")[2];
                if (!isBusying) {
                    updateUI();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        lobby_plugins.addValueEventListener(lobbyPluginListener);
    }

    // 更新 UI 資料
    private void updateUI() {
        // 若額外主題不存在圖片 -> 下載圖片
        LoadExtraThemePtoto(Lobby41_IMG, lobby_41);
        LoadExtraThemePtoto(Lobby42_IMG, lobby_42);
        LoadExtraThemePtoto(Lobby43_IMG, lobby_43);
        LoadExtraThemePtoto(Lobby44_IMG, lobby_44);
        LoadExtraThemePtoto(Lobby45_IMG, lobby_45);
        LoadExtraThemePtoto(Lobby46_IMG, lobby_46);
        // 安卓 11 以上 檢查目錄權限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // 檢查修改權限是否充足
            if (AccessMethod.equals("SAF")) {

            } else if (AccessMethod.equals("Shizuku")) {
                if (Shizuku.pingBinder()) {
                    if (checkShizukuPermission()) {
                        // 確實檢測完成
                        if (AppSettings.contains("GameUID")) {
                            // 若存在 GameUID -> ADB 啟用
                            String GameUID = AppSettings.getString("GameUID", "");
                            // 新增計數器，讓兩個檢測都結束後，可以顯示 Dialog 用
                            CountDownLatch latch = new CountDownLatch(2); // 設置計數器為 2
                            // 新增 2 標誌位，分別為 Sound 和 Movie 的 bool
                            boolean[] SoundAccess = {false};
                            boolean[] MovieAccess = {false};
                            // 檢測語音
                            new Thread(() -> {
                                String lsld_SoundDLC = Objects.requireNonNull(ShizukuUtil.executeShellCommandWithShizuku("ls -ld /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/Sound_DLC/Android")).trim();
                                if (lsld_SoundDLC.contains(GameUID)) {
                                    // 將 Sound_DLC 授權
                                    canMod = false;
                                    SoundAccess[0] = false;
                                } else {
                                    canMod = true;
                                    SoundAccess[0] = true;
                                }
                                latch.countDown(); // 減少計數
                            }).start();
                            // 檢測影片
                            new Thread(() -> {
                                String lsld_LobbyMovie = Objects.requireNonNull(ShizukuUtil.executeShellCommandWithShizuku("ls -ld /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/ISPDiff/LobbyMovie")).trim();
                                if (lsld_LobbyMovie.contains(GameUID)) {
                                    // 將 ISPDiff 授權
                                    canMod = false;
                                    MovieAccess[0] = false;
                                } else {
                                    canMod = true;
                                    MovieAccess[0] = true;
                                }
                                latch.countDown(); // 減少計數
                            }).start();
                            // 等待兩個執行緒完成後處理
                            new Thread(() -> {
                                try {
                                    latch.await(); // 等待計數器歸零
                                    HandleAccessState(SoundAccess[0], MovieAccess[0]);
                                } catch (InterruptedException e) {
                                    Log.e("Error", e.toString());
                                }
                            }).start();
                        }
                    }
                } else {
                    showMessage(getResources().getString(R.string.ShizukuPingFailed));
                    Intent page = new Intent(getActivity(), Activity1_ChooseUtils.class);
                    startActivity(page);
                }
            }
        }
    }

    // 處理回傳的兩個目錄授權狀態
    private void HandleAccessState(boolean SoundAccess, boolean MovieAccess) {
        requireActivity().runOnUiThread(() -> {
            // 同時產生亂數，加在無法授權的目錄後方，以避免後續授權卡讀取
            String randomString = generateRandomString();
            if (!SoundAccess && !MovieAccess) {
                showAccessDialog(
                        "檢測到您的 Sound_DLC 目錄 及 LobbyMovie 目錄皆無法存取，需要授權才能使用。點擊「OK」進行自動授權。\n過程非常漫長(10~15分鐘)，請耐心等候...\n期間「請勿」跳離畫面，以免授權失敗或不完全。",
                        "mkdir /storage/emulated/0/" + randomString + " &&" +
                              "cp -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/Sound_DLC" + " " +
                              "/storage/emulated/0/" + randomString + "/ && " +
                              "mv /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/Sound_DLC" + " " +
                              "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/Sound_DLC" + "_" + randomString + " && " +
                              "mv /storage/emulated/0/" + randomString + "/Sound_DLC /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/" + " && " +
                              "rm -r /storage/emulated/0/" + randomString,
                        "mkdir /storage/emulated/0/tmp" + " &&" +
                              "cp -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/ISPDiff" + " " +
                              "/storage/emulated/0/tmp/" + " && " +
                              "mv /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/ISPDiff" + " " +
                              "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/ISPDiff" + "_" + randomString + " && " +
                              "mv /storage/emulated/0/tmp/ISPDiff /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/" + " && " +
                              "rm -r /storage/emulated/0/tmp");
            } else if (!SoundAccess) {
                showAccessDialog(
                        "檢測到您的 Sound_DLC 目錄無法存取，需要授權才能使用。點擊「OK」進行自動授權。\n過程非常漫長(7~10分鐘)，請耐心等候...\n期間「請勿」跳離畫面，以免授權失敗或不完全。",
                        "mkdir /storage/emulated/0/" + randomString + " &&" +
                              "cp -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/Sound_DLC" + " " +
                              "/storage/emulated/0/" + randomString + "/ && " +
                              "mv /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/Sound_DLC" + " " +
                              "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/Sound_DLC" + "_" + randomString + " && " +
                              "mv /storage/emulated/0/" + randomString + "/Sound_DLC /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/" + " && " +
                              "rm -r /storage/emulated/0/" + randomString,
                        "");
            } else if (!MovieAccess) {
                showAccessDialog(
                        "檢測到您的 LobbyMovie 目錄無法存取，需要授權才能使用。點擊「OK」進行自動授權。\n過程非常漫長(7~13分鐘)，請耐心等候...\n期間「請勿」跳離畫面，以免授權失敗或不完全。",
                        "mkdir /storage/emulated/0/" + randomString + " &&" +
                              "cp -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/ISPDiff" + " " +
                              "/storage/emulated/0/" + randomString + "/ && " +
                              "mv /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/ISPDiff" + " " +
                              "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/ISPDiff" + "_" + randomString + " && " +
                              "mv /storage/emulated/0/" + randomString + "/ISPDiff /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/" + " && " +
                              "rm -r /storage/emulated/0/" + randomString,
                        "");
            }
        });
    }

    // 處理授權 Dialog
    private void showAccessDialog(String message, String cmd1, String cmd2) {
        CustomAlertDialog2.showDialog(requireContext(), false, getString(R.string.Hint), message, getString(R.string.DialogCancel), getString(R.string.DialogOK), 0, new CustomAlertDialog2.DialogCallback() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onResult(boolean isConfirmed) {
                if (isConfirmed) {
                    isBusying = true;
                    // 這裡用背景回調
                    new BackgroundTask(requireActivity(), () -> {
                        if (cmd2.isEmpty()) {
                            // 授權單一目錄
                            ShizukuUtil.executeShellCommandWithShizuku(cmd1);
                        } else {
                            // 雙目錄授權，使用 thread 同步授權
                            // 新增計數器，讓兩個授權都結束後，可以知道各自結果
                            CountDownLatch latch = new CountDownLatch(2); // 設置計數器為 2
                            // 授權語音
                            new Thread(() -> {
                                ShizukuUtil.executeShellCommandWithShizuku(cmd1);
                                latch.countDown(); // 減少計數
                            }).start();
                            // 授權影片
                            new Thread(() -> {
                                ShizukuUtil.executeShellCommandWithShizuku(cmd2);
                                latch.countDown(); // 減少計數
                            }).start();
                            // 等待兩個執行緒完成後
                            latch.await();
                        }
                        return "Completed"; // 返回執行結果
                    }, "正在為您授權目錄...", result -> {
                        // 在這裡處理結果，例如顯示結果到 Toast 或更新 UI
                        if (result != null) {
                            showMessage(getString(R.string.Successfully));
                            isBusying = false;
                            canMod = true;
                        }
                    }).execute();
                } else {
                    canMod = false;
                }
            }
        });
    }


    // 檢查 Shizuku 權限
    private boolean checkShizukuPermission() {
        if (Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED) {
            // Granted
            return true;
        } else if (Shizuku.shouldShowRequestPermissionRationale()) {
            // Users choose "Deny and don't ask again"
            return false;
        } else {
            return false;
        }
    }

    // 還原大廳修改
    private void ClearLobbyMod() {
        // 按照存取模式還原，這裡用背景回調
        new BackgroundTask(requireActivity(), () -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                // 根據 AccessMethod 進行不同的操作
                if (AccessMethod.equals("SAF")) {
                    // 在這裡處理 SAF 的邏輯
                } else if (AccessMethod.equals("Shizuku")) {
                    // 使用 Shizuku 執行刪除命令
                    if (Shizuku.pingBinder()) {
                        if (checkShizukuPermission()) {
                            ShizukuUtil.executeShellCommandWithShizuku("rm /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/Sound_DLC/Android/" + Delete_Sound);
                            ShizukuUtil.executeShellCommandWithShizuku("rm /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/ISPDiff/LobbyMovie/" + Delete_Movie);
                        }
                    } else {
                        showMessage(getResources().getString(R.string.ShizukuPingFailed));
                        Intent page = new Intent(getActivity(), Activity1_ChooseUtils.class);
                        startActivity(page);
                    }
                } else if (AccessMethod.equals("Root")) {
                    // 使用 Root 權限執行刪除命令
                    RootUtil.executeRootCommand("rm /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/Sound_DLC/Android/" + Delete_Sound);
                    RootUtil.executeRootCommand("rm /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/ISPDiff/LobbyMovie/" + Delete_Movie);
                }
            } else {
                // Android 11 以下使用 FileUtil 進行刪除
                FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/Sound_DLC/Android/" + Delete_Sound);
                FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/ISPDiff/LobbyMovie/" + Delete_Movie);
            }
            return ""; // 不需要返回值
        }, getString(R.string.RecoveryING), result -> {
            // 在這裡處理結果，例如顯示結果到 Toast 或更新 UI
            if (result != null) {
                showMessage(getString(R.string.Recovery_Successfully));
                CustomAlertDialog2.showDialog(requireContext(), true, getString(R.string.Hint), "已還原大廳修改\n是否幫您自動開啟遊戲？", getString(R.string.DialogCancel), getString(R.string.DialogOK), 0, isConfirmed -> {
                    if (isConfirmed) {
                        Intent launchIntent = requireActivity().getPackageManager().getLaunchIntentForPackage("com.garena.game.kgtw");
                        startActivity(launchIntent);
                    }
                });
            }
        }).execute();
    }

    // 圖片點擊事件，先檢查是否存在插件
    private void LobbyImageClickEvent(String PluginName, String PluginURL) {
        if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-Lobby/" + PluginName)) {
            StartModLobby(PluginName);
        } else {
            // 不存在插件，進入下載程序
            // 載入廣告計數
            int showInterstitialAdCnt = AppSettings.getInt("showInterstitialAdCnt", 3);
            if (showInterstitialAdCnt%3 == 0) {
                // 3個一數，顯示廣告
                admobService.showInterstitialAd(getString(R.string.Interstitial));
            }
            showInterstitialAdCnt += 1;
            AppSettings.edit().putInt("showInterstitialAdCnt", showInterstitialAdCnt).apply();
            if (!AutoMod) {
                showMessage(getString(R.string.DownloadFinishedClickAgainToast));
            }
            DownloadManager.downloadPlugins(requireContext(), "https://" + PluginURL, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-Lobby/", "下載主題中...", PluginName, new DownloadManager.UIDownloadCallback() {
                @Override
                public void onDownloadComplete(String filePath) {
                   if (AutoMod) {
                       StartModLobby(PluginName);
                   }
                }
                @Override
                public void onDownloadFailed(String error) {
                    requireActivity().runOnUiThread(() -> showMessage(getString(R.string.DownloadFailedToast)));
                }
            });
        }
    }

    // 啟用大廳修改
    private void StartModLobby(String PluginName) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // 判斷是否能修改
            if (canMod) {
                FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-Lobby/tmp");
                ZipManager.UnzipWithoutPwd(requireContext(), true, "解壓中","/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-Lobby/"+PluginName, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-Lobby/tmp/", () -> {
                    // 解壓完成再調用 BackGroundTask
                    new BackgroundTask(requireActivity(), () -> {
                        if (AccessMethod.equals("SAF")) {
                            // 在這裡處理 SAF 的邏輯
                        } else if (AccessMethod.equals("Shizuku")) {
                            if (Shizuku.pingBinder()) {
                                if (checkShizukuPermission()) {
                                    if (AppSettings.contains("GameUID")) {
                                        // 同時產生亂數，加在無法授權的檔案後方，以避免複製失敗 (if Permission Denined)
                                        String randomString = generateRandomString();
                                        String GameUID = AppSettings.getString("GameUID", "");
                                        // 檢查 mp4 權限 及 bnk 權限
                                        // 不包含 GameUID 可能是沒檔案或已授權完畢，無論如何皆可成功複製
                                        String lsl_bnk = Objects.requireNonNull(ShizukuUtil.executeShellCommandWithShizuku("ls -l /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/Sound_DLC/Android/" + Delete_Sound)).trim();
                                        String lsl_mp4 = Objects.requireNonNull(ShizukuUtil.executeShellCommandWithShizuku("ls -l /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/ISPDiff/LobbyMovie/" + Delete_Movie)).trim();
                                        if (lsl_bnk.contains(GameUID)) {
                                            // 直接重命名
                                            ShizukuUtil.executeShellCommandWithShizuku("mv /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/Sound_DLC/Android/"+Delete_Sound + " " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/Sound_DLC/Android/"+Delete_Sound+"_"+randomString);
                                        }
                                        if (lsl_mp4.contains(GameUID)) {
                                            // 直接重命名
                                            ShizukuUtil.executeShellCommandWithShizuku("mv /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/ISPDiff/LobbyMovie/"+Delete_Movie + " " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/ISPDiff/LobbyMovie/"+Delete_Movie+"_"+randomString);
                                        }
                                    }
                                    ShizukuUtil.executeShellCommandWithShizuku("cp -r /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-Lobby/tmp/* /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/");
                                }
                            } else {
                                showMessage(getResources().getString(R.string.ShizukuPingFailed));
                                Intent page = new Intent(getActivity(), Activity1_ChooseUtils.class);
                                startActivity(page);
                            }
                        } else if (AccessMethod.equals("Root")) {
                            RootUtil.executeRootCommand("cp -r /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-Lobby/tmp/* /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/");
                        }
                        return ""; // 不需要返回值
                    }, getString(R.string.Modding), result -> {
                        // 在這裡處理結果，例如顯示結果到 Toast 或更新 UI
                        requireActivity().runOnUiThread(() -> showMessage(getString(R.string.ApplySuccessfullyToast)));
                        FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-Lobby/tmp");
                    }).execute();
                });
            } else {
                updateUI();
                showMessage("請先進行授權");
            }
        } else {
            FileUtil.makeDir("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/");
            ZipManager.UnzipWithoutPwd(requireContext(), true, "啟用中","/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-Lobby/"+PluginName, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/", () -> requireActivity().runOnUiThread(() -> showMessage(getString(R.string.ApplySuccessfullyToast))));
        }
    }

    private void LoadExtraThemePtoto(String ImageUrl, ImageView view) {
        String FileName = Uri.parse(ImageUrl).getLastPathSegment();
        String path = "/data/user/0/com.aoveditor.phantomsneak/files/texture/4-Lobby/";
        if (!FileUtil.isExistFile(path + FileName)) {
            DownloadManager.downloadInBackground(requireContext(), ImageUrl, path, FileName, new DownloadManager.DownloadCallback() {
                @Override
                public void onDownloadInBackgroundComplete(String filePath) {
                    // 使用 Glide 加載圖片
                    getActivity().runOnUiThread(() -> {
                        Glide.with(requireActivity())
                                .load(filePath)
                                .placeholder(R.drawable.none)
                                .error(R.drawable.none)
                                .into(view);
                    });
                }
                @Override
                public void onDownloadFailed(String error) {
                    FileUtil.deleteFile(path + FileName);
                }
            });
        } else {
            // 使用 Glide 加載圖片
            Glide.with(requireActivity())
                    .load(path+FileName)
                    .placeholder(R.drawable.none)
                    .error(R.drawable.none)
                    .into(view);
        }
    }

    // 產生長度為3的字串亂數
    private String generateRandomString() {
        StringBuilder result = new StringBuilder(3);
        for (int i = 0; i < 3; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            result.append(CHARACTERS.charAt(index));
        }
        return result.toString();
    }


    private void showMessage(String message) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();
    }

}
