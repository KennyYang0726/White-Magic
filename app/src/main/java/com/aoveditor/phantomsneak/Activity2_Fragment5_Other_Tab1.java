package com.aoveditor.phantomsneak;

import static android.content.Context.DISPLAY_SERVICE;

import static com.google.common.primitives.Bytes.indexOf;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import rikka.shizuku.Shizuku;

import com.aoveditor.phantomsneak.ModdingVeryHighFrame.ModdingVeryHighFrame;
import com.aoveditor.phantomsneak.Services.AdmobService;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Objects;

import com.aoveditor.phantomsneak.AlertDialog.CustomAlertDialog2;
import com.aoveditor.phantomsneak.BackgroundTask.BackgroundTask;
import com.aoveditor.phantomsneak.DownloadManager.DownloadManager;
import com.aoveditor.phantomsneak.Utils.FileUtil;
import com.aoveditor.phantomsneak.Utils.RootUtil;
import com.aoveditor.phantomsneak.Utils.ShizukuUtil;
import com.aoveditor.phantomsneak.ZipManager.ZipManager;



public class Activity2_Fragment5_Other_Tab1 extends Fragment {

    /**Element*/
    private Button Mod_Wiro;
    private Button button_recovery_wiro_modified;
    private Button Mod_Wisp;
    private Button button_recovery_wisp_modified;
    private Button Mod_Tower;
    private Button button_recovery_tower_modified;
    private Button Mod_Soldier;
    private Button button_recovery_soldier_modified;
    private Button Mod_Monster;
    private Button button_recovery_monster_modified;
    private ImageView Mod_FPS;

    /**Components*/
    private SharedPreferences AppSettings; // 儲存 主題色, SAF授權目錄, AccessMethod, ...
    private DatabaseReference other_plugins;
    private ValueEventListener otherPluginListener;
    private AdmobService admobService;

    /**Variable*/
    private String Game_Ver = "";
    private String AccessMethod = "";
    private String Wiro = "";
    private String Wiro_UpdateDate = "";
    private String Wiro_CrcXor = "";
    private final String Wisp = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/Hero_Wisp_SFX.bnk";
    private String Prefab_ = "";
    private String Prefab_UpdateDate = "";
    private boolean AutoMod = false;
    // 產生亂數，應用不可動的目錄
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();


    public Activity2_Fragment5_Other_Tab1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity2_fragment5_other_tab1, container, false);
        // 初始化 sp
        AppSettings = requireActivity().getSharedPreferences("AppSettings", Activity.MODE_PRIVATE);
        // 初始化廣告
        admobService = new AdmobService(requireContext());
        // 加載插頁廣告
        admobService.loadInterstitialAd(getString(R.string.Interstitial));
        // 加載獎勵廣告
        admobService.loadRewardedAd(getString(R.string.Reward));
        // 元件
        Mod_Wiro = v.findViewById(R.id.Wiro);
        button_recovery_wiro_modified = v.findViewById(R.id.button_recovery_wiro_mod);
        Mod_Wisp = v.findViewById(R.id.Wisp);
        button_recovery_wisp_modified = v.findViewById(R.id.button_recovery_wisp_mod);
        Mod_Tower = v.findViewById(R.id.Tower);
        button_recovery_tower_modified = v.findViewById(R.id.button_recovery_tower_mod);
        Mod_Soldier = v.findViewById(R.id.Soldier);
        button_recovery_soldier_modified = v.findViewById(R.id.button_recovery_soldier_mod);
        Mod_Monster = v.findViewById(R.id.Monster);
        button_recovery_monster_modified = v.findViewById(R.id.button_recovery_monster_mod);
        Mod_FPS = v.findViewById(R.id.FPS);
        // 事件
        Mod_Wiro.setOnClickListener(v1 -> {
            String name = Uri.parse(Wiro).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Wiro_UpdateDate + ".PS";
            // SoundList 僅用於 有語音修改的
            ArrayList<String> SoundList = new ArrayList<>();
            SoundList.add("Hero_Wiro_SFX.bnk");
            SoundList.add("Chinese\\(Taiwan\\)/Hero_Wiro_Show.bnk");
            SoundList.add("Chinese\\(Taiwan\\)/Hero_Wiro_VO.bnk");
            OtherImageClickEvent(PluginName, Wiro, false, "*", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/", SoundList);
        });
        button_recovery_wiro_modified.setOnClickListener(v13 -> {
            ArrayList<String> ClearList = new ArrayList<>();
            ClearList.add("CDNImage/HeroHeadIcon/E08074BD2C22D7294436E68F0AEA0E90");
            ClearList.add("Extra/2022.V3/Sound_DLC/Android/Hero_Wiro_SFX.bnk");
            ClearList.add("Extra/2022.V3/Sound_DLC/Android/Chinese(Taiwan)/Hero_Wiro_Show.bnk");
            ClearList.add("Extra/2022.V3/Sound_DLC/Android/Chinese(Taiwan)/Hero_Wiro_VO.bnk");
            ClearList.add("Resources/" + Game_Ver);
            ClearMod("8964", ClearList, "點擊 OK 還原\n「維羅國動」\n完成後強制為您開啟遊戲，是否繼續？", true);
        });
        Mod_Wisp.setOnClickListener(v12 -> {
            // SoundList 僅用於 有語音修改的
            ArrayList<String> SoundList = new ArrayList<>();
            SoundList.add("Hero_Wisp_SFX.bnk");
            OtherImageClickEvent("Hero_Wisp_SFX.bnk", Wisp, true, "*", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/Sound_DLC/Android/", SoundList);
        });
        button_recovery_wisp_modified.setOnClickListener(v14 -> {
            ArrayList<String> ClearList = new ArrayList<>();
            ClearList.add("CDNImage/HeroHeadIcon/E08074BD2C22D7294436E68F0AEA0E90");
            ClearList.add("Extra/2022.V3/Sound_DLC/Android/Hero_Wiro_SFX.bnk");
            ClearList.add("Extra/2022.V3/Sound_DLC/Android/Chinese(Taiwan)/Hero_Wiro_Show.bnk");
            ClearList.add("Extra/2022.V3/Sound_DLC/Android/Chinese(Taiwan)/Hero_Wiro_VO.bnk");
            ClearList.add("Resources/" + Game_Ver);
            ClearMod("8964", ClearList, "點擊 OK 還原\n「靈靈專業配音」\n是否繼續？", false);
        });
        Mod_Tower.setOnClickListener(v15 -> {
            String name = Uri.parse(Prefab_).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Prefab_UpdateDate + ".PS";
            ArrayList<String> EmptyList = new ArrayList<>();
            OtherImageClickEvent(PluginName, Prefab_, false, "1-New/Prefab_Organ.pkg.bytes", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/Prefab_Characters/", EmptyList);
        });
        button_recovery_tower_modified.setOnClickListener(v16 -> {
            ArrayList<String> ClearList = new ArrayList<>();
            ClearList.add("Prefab_Organ.pkg.bytes");
            ClearMod("Prefab", ClearList, "點擊 OK 還原\n「次元塔修改」\n是否繼續？", false);
        });
        Mod_Soldier.setOnClickListener(v17 -> {
            String name = Uri.parse(Prefab_).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Prefab_UpdateDate + ".PS";
            ArrayList<String> EmptyList = new ArrayList<>();
            OtherImageClickEvent(PluginName, Prefab_, false, "1-New/Prefab_Soldier.pkg.bytes", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/Prefab_Characters/", EmptyList);
        });
        button_recovery_soldier_modified.setOnClickListener(v18 -> {
            ArrayList<String> ClearList = new ArrayList<>();
            ClearList.add("Prefab_Soldier.pkg.bytes");
            ClearMod("Prefab", ClearList, "點擊 OK 還原\n「次元兵線修改」\n是否繼續？", false);
        });

        Mod_Monster.setOnClickListener(v19 -> {
            String name = Uri.parse(Prefab_).getLastPathSegment();
            String PluginName = Objects.requireNonNull(name).substring(0, name.length()-3) + Prefab_UpdateDate + ".PS";
            ArrayList<String> EmptyList = new ArrayList<>();
            OtherImageClickEvent(PluginName, Prefab_, false, "1-New/Prefab_Monster.pkg.bytes", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/Prefab_Characters/", EmptyList);
        });
        button_recovery_monster_modified.setOnClickListener(v110 -> {
            ArrayList<String> ClearList = new ArrayList<>();
            ClearList.add("Prefab_Monster.pkg.bytes");
            ClearMod("Prefab", ClearList, "點擊 OK 還原\n「次元Buff修改」\n是否繼續？", false);
        });
        Mod_FPS.setOnClickListener(v111 -> {
            showFPSDialog();
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
        if (other_plugins != null && otherPluginListener != null) {
            other_plugins.removeEventListener(otherPluginListener);
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
        Game_Ver = AppSettings.getString("Game_Ver", "");
        // 在這裡重新載入資料或刷新畫面
        InitFirebaseDatabase();
    }

    // 初始化 Firebase Database
    private void InitFirebaseDatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        other_plugins = database.getReference("5-Other");
        otherPluginListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Wiro = dataSnapshot.child("wiro/wiro").getValue(String.class);
                String wiro_updateDate = Objects.requireNonNull(dataSnapshot.child("wiro/更新日期").getValue(String.class));
                Wiro_UpdateDate = "_" + wiro_updateDate.split("/")[1] + "_" + wiro_updateDate.split("/")[2];
                Wiro_CrcXor = Objects.requireNonNull(dataSnapshot.child("wiro/crcxor").getValue(String.class));
                Prefab_ = dataSnapshot.child("prefab/Prefab_").getValue(String.class);
                String prefab_updateDate = Objects.requireNonNull(dataSnapshot.child("prefab/更新日期").getValue(String.class));
                Prefab_UpdateDate = "_" + prefab_updateDate.split("/")[1] + "_" + prefab_updateDate.split("/")[2];
                updateUI();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        other_plugins.addValueEventListener(otherPluginListener);
    }

    // 更新 UI 資料 ( 改變按鈕文字 )
    private void updateUI() {
        // 維羅
        if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/" + "wiro" + Wiro_UpdateDate + ".PS")) {
            Mod_Wiro.setText(getString(R.string.Btn_ApplyOtherPlugin));
        } else {
            Mod_Wiro.setText(getString(R.string.Btn_DownloadOtherPlugin));
        }
        // wisp
        if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/Hero_Wisp_SFX.bnk")) {
            Mod_Wisp.setText(getString(R.string.Btn_ApplyOtherPlugin));
        } else {
            Mod_Wisp.setText(getString(R.string.Btn_DownloadOtherPlugin));
        }
        // Prefab
        if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/" + "Prefab" + Prefab_UpdateDate + ".PS")) {
            Mod_Tower.setText(getString(R.string.Btn_ApplyOtherPlugin));
            Mod_Soldier.setText(getString(R.string.Btn_ApplyOtherPlugin));
            Mod_Monster.setText(getString(R.string.Btn_ApplyOtherPlugin));
        } else {
            Mod_Tower.setText(getString(R.string.Btn_DownloadOtherPlugin));
            Mod_Soldier.setText(getString(R.string.Btn_DownloadOtherPlugin));
            Mod_Monster.setText(getString(R.string.Btn_DownloadOtherPlugin));
        }
    }


    // 圖片點擊事件，先檢查是否存在插件
    private void OtherImageClickEvent(String PluginName, String PluginURL, boolean SingleFile, String Source, String TargetRootDir, ArrayList<String> SoundList) {
        if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/" + PluginName)) {
            StartModOther(PluginName, SingleFile, Source, TargetRootDir, SoundList);
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
            DownloadManager.downloadPlugins(requireContext(), "https://" + PluginURL, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/", "下載插件中...", PluginName, new DownloadManager.UIDownloadCallback() {
                @Override
                public void onDownloadComplete(String filePath) {
                    updateUI();
                    if (AutoMod) {
                        StartModOther(PluginName, SingleFile, Source, TargetRootDir, SoundList);
                    }
                }
                @Override
                public void onDownloadFailed(String error) {
                    requireActivity().runOnUiThread(() -> showMessage(getString(R.string.DownloadFailedToast)));
                }
            });
        }
    }

    // 啟用其他修改
    private void StartModOther(String PluginName, boolean SingleFile, String Source, String TargetRootDir, ArrayList<String> SoundList) {
        // 若是國動修改，要改效驗包
        if (PluginName.contains("wiro")) {
            ModVertifyAssetBundle_Wiro();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            final String[] ShizukuResult = {""};
            if (!SingleFile) {
                // zip 插件
                FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/tmp");
                ZipManager.UnzipWithoutPwd(requireContext(), true, "解壓中","/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/" + PluginName, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/tmp/", () -> {
                    // 解壓完成再調用 BackGroundTask
                    new BackgroundTask(requireActivity(), () -> {
                        if (AccessMethod.equals("SAF")) {
                            // 在這裡處理 SAF 的邏輯
                        } else if (AccessMethod.equals("Shizuku")) {
                            if (Shizuku.pingBinder()) {
                                if (checkShizukuPermission()) {
                                    ShizukuResult[0] = ShizukuUtil.executeShellCommandWithShizuku("cp -r /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/tmp/" + Source + " " + TargetRootDir);
                                }
                            } else {
                                showMessage(getResources().getString(R.string.ShizukuPingFailed));
                                Intent page = new Intent(getActivity(), Activity1_ChooseUtils.class);
                                startActivity(page);
                            }
                        } else if (AccessMethod.equals("Root")) {
                            RootUtil.executeRootCommand("cp -r /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/tmp/" + Source + " " + TargetRootDir);
                        }
                        return ""; // 不需要返回值
                    }, getString(R.string.Modding), result -> {
                        // 在這裡處理結果，例如顯示結果到 Toast 或更新 UI
                        requireActivity().runOnUiThread(() -> showMessage(getString(R.string.ApplySuccessfullyToast)));
                        FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/tmp");
                        // 檢查 Shizuku 啟用結果，是否包含 Permission Denined
                        if (AccessMethod.equals("Shizuku")) {
                            if (ShizukuResult[0].contains("failed")) {
                                // Sound_DLC 目錄有檔案 因權限問題沒複製成功
                                showAccessDialog(SoundList);
                            }
                        }
                    }).execute();
                });
            } else {
                // hero_wisp_sfx.bnk
                // 沒有解壓程序
                new BackgroundTask(requireActivity(), () -> {
                    if (AccessMethod.equals("SAF")) {
                        // 在這裡處理 SAF 的邏輯
                    } else if (AccessMethod.equals("Shizuku")) {
                        if (Shizuku.pingBinder()) {
                            if (checkShizukuPermission()) {
                                ShizukuResult[0] = ShizukuUtil.executeShellCommandWithShizuku("cp -r /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/" + PluginName + " " + TargetRootDir);
                            }
                        } else {
                            showMessage(getResources().getString(R.string.ShizukuPingFailed));
                            Intent page = new Intent(getActivity(), Activity1_ChooseUtils.class);
                            startActivity(page);
                        }
                    } else if (AccessMethod.equals("Root")) {
                        RootUtil.executeRootCommand("cp -r /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/" + PluginName + " " + TargetRootDir);
                    }
                    return ""; // 不需要返回值
                }, getString(R.string.Modding), result -> {
                    // 在這裡處理結果，例如顯示結果到 Toast 或更新 UI
                    requireActivity().runOnUiThread(() -> showMessage(getString(R.string.ApplySuccessfullyToast)));
                    // 檢查 Shizuku 啟用結果，是否包含 Permission Denined
                    if (AccessMethod.equals("Shizuku")) {
                        if (ShizukuResult[0].contains("failed")) {
                            // Sound_DLC 目錄有檔案 因權限問題沒複製成功
                            showAccessDialog(SoundList);
                        }
                    }
                }).execute();
            }

        } else {
            if (!SingleFile) {
                // zip 插件
                FileUtil.makeDir(TargetRootDir);
                ZipManager.UnzipWithoutPwd(requireContext(), true, "啟用中","/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/" + PluginName, TargetRootDir, () -> requireActivity().runOnUiThread(() -> showMessage(getString(R.string.ApplySuccessfullyToast))));
            } else {
                // hero_wisp_sfx.bnk
                // 沒有解壓程序
                FileUtil.copyFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/" + PluginName, TargetRootDir + PluginName);
                showMessage(getString(R.string.ApplySuccessfullyToast));
            }
        }
    }

    // 還原修改
    private void ClearMod(String Type, ArrayList<String> ClearList, String DialogMessage, boolean ForceOpenGame) {
        CustomAlertDialog2.showDialog(
                requireContext(),
                false,
                "   警告！",
                DialogMessage,
                getString(R.string.DialogCancel),
                getString(R.string.DialogOK),
                0,
                isConfirmed -> {
                    if (isConfirmed) {
                        if (!Type.equals("Prefab")) {
                            // 前 2 個
                            for (String str : ClearList) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                                    new BackgroundTask(requireActivity(), () -> {
                                        if (AccessMethod.equals("SAF")) {
                                            // 在這裡處理 SAF 的邏輯
                                        } else if (AccessMethod.equals("Shizuku")) {
                                            if (Shizuku.pingBinder()) {
                                                if (checkShizukuPermission()) {
                                                    String Str = str.replace("(", "\\(").replace(")", "\\)");
                                                    ShizukuUtil.executeShellCommandWithShizuku("rm -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/" + Str);
                                                }
                                            } else {
                                                showMessage(getResources().getString(R.string.ShizukuPingFailed));
                                                Intent page = new Intent(getActivity(), Activity1_ChooseUtils.class);
                                                startActivity(page);
                                            }
                                        } else if (AccessMethod.equals("Root")) {
                                            String Str = str.replace("(", "\\(").replace(")", "\\)");
                                            RootUtil.executeRootCommand("rm -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/" + Str);
                                        }
                                        return ""; // 不需要返回值
                                    }, getString(R.string.RecoveryING), result -> {

                                    }).execute();
                                } else {
                                    FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/" + str);
                                }
                            }
                        } else {
                            // Prefab 系列
                            String Name = ClearList.get(0);
                            String prefab_name = Uri.parse(Prefab_).getLastPathSegment();
                            String PluginName = Objects.requireNonNull(prefab_name).substring(0, prefab_name.length()-3) + Prefab_UpdateDate + ".PS";
                            FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/tmp");
                            ZipManager.UnzipWithoutPwd(requireContext(), false, "解壓中","/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/" + PluginName, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/tmp/", () -> {
                                // 解壓完成
                                new BackgroundTask(requireActivity(), () -> {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                                        if (AccessMethod.equals("SAF")) {
                                            // 在這裡處理 SAF 的邏輯
                                        } else if (AccessMethod.equals("Shizuku")) {
                                            if (Shizuku.pingBinder()) {
                                                if (checkShizukuPermission()) {
                                                    ShizukuUtil.executeShellCommandWithShizuku("cp -r /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/tmp/0-Original/" + Name + " " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/Prefab_Characters/");
                                                }
                                            } else {
                                                showMessage(getResources().getString(R.string.ShizukuPingFailed));
                                                Intent page = new Intent(getActivity(), Activity1_ChooseUtils.class);
                                                startActivity(page);
                                            }
                                        } else if (AccessMethod.equals("Root")) {
                                            RootUtil.executeRootCommand("cp -r /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/tmp/0-Original/" + Name + " " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/Prefab_Characters/");
                                        }
                                    } else {
                                        FileUtil.copyFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/tmp/0-Original/" + Name, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/Prefab_Characters/" + Name);
                                    }
                                    return ""; // 不需要返回值
                                }, getString(R.string.RecoveryING), result -> {
                                    FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/tmp");
                                }).execute();
                            });
                        }
                        showMessage(getString(R.string.Recovery_Successfully));
                        if (ForceOpenGame) {
                            CustomAlertDialog2.showDialog(
                                    requireContext(),
                                    false,
                                    getString(R.string.Hint),
                                    "已還原遊戲資源，是否幫您自動開啟遊戲？",
                                    getString(R.string.DialogCancel),
                                    getString(R.string.DialogOK),
                                    0,
                                    isConfirmed2 -> {
                                        if (isConfirmed2) {
                                            Intent launchIntent = requireActivity().getPackageManager().getLaunchIntentForPackage("com.garena.game.kgtw");
                                            startActivity(launchIntent);
                                        } else {
                                            requireActivity().finishAffinity();
                                        }
                                    });

                        }
                    }
                });

    }

    // 跳 啟用不全 Dialog
    private void showAccessDialog(ArrayList<String> SoundList) {
        // 同時產生亂數，加在無法授權的 檔案 後方，以避免後續授權卡讀取
        String randomString = generateRandomString();
        CustomAlertDialog2.showDialog(
                requireContext(),
                true,
                getString(R.string.Hint),
                "語音目錄部分檔案未啟用成功，雖不影響對局，不過啟用的效果不完全，您可以選擇不授權，則無需理會。\n若希望有更完整的體驗，點擊「OK」進行自動授權。\n授權過程約耗時 2 秒。",
                getString(R.string.DialogCancel),
                getString(R.string.DialogOK),
                0,
                new CustomAlertDialog2.DialogCallback() {
                    @SuppressLint("StaticFieldLeak")
                    @Override
                    public void onResult(boolean isConfirmed) {
                        if (isConfirmed) {
                            // 背景執行，避免組塞主執行緒
                            // 直接重命名檔案，不理會結果
                            for (String str : SoundList) {
                                new Thread(() -> ShizukuUtil.executeShellCommandWithShizuku("mv /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/Sound_DLC/Android/" + str + " " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/Sound_DLC/Android/" + str + "_" + randomString)).start();
                            }
                            String Message = "正在為語音目錄授權..."; // 自訂 prog 訊息
                            // 創建並執行 BackgroundTask，傳入具體的背景任務
                            new BackgroundTask(requireActivity(), () -> {
                                return "";
                            }, Message, result -> {
                                // 在這裡處理結果，例如顯示結果到 Toast 或更新 UI
                                if (result != null) {
                                    showMessage(getString(R.string.Other_Mod_Access_Success_Toast));
                                }
                            }).execute();

                        }
                    }
                });
    }

    // 跳 FPS 警告 Dialog
    private void showFPSDialog() {
        if (open120()) {
            FileUtil.makeDir(FileUtil.getPackageDataDir(requireActivity())+"/tmp");
            saveAssets("高幀率.png", FileUtil.getPackageDataDir(requireActivity())+"/tmp/", "VeryHighFrameModeBlackList.bytes");
            String Device_ID = ModdingVeryHighFrame.getDeviceName();
            CustomAlertDialog2.showDialog(
                    requireContext(),
                    false,
                    "注意！",
                    "◑開啟超高幀率模式(120FPS)將擁有更加流暢的遊戲體驗，但可能加快手機耗電、發熱。\n◑啟用該功能並不會直接幫你啟用至120FPS，而是使120FPS選項出現，須手動去戰場設置中開啟\n◑若已開啟超高幀率，但遊戲顯示幀率仍為60，代表您的手機可能有鎖幀的問題，須查閱相關資料\n◑不能保證100%修改成功",
                    getString(R.string.DialogCancel),
                    getString(R.string.DialogOK),
                    0,
                    new CustomAlertDialog2.DialogCallback() {
                        @SuppressLint("StaticFieldLeak")
                        @Override
                        public void onResult(boolean isConfirmed) {
                            if (isConfirmed) {
                                // 顯示獎勵廣告
                                admobService.showRewardedAd(completed -> {
                                    if (completed) {
                                        ModdingVeryHighFrame.Decrypt(requireContext());
                                        // 創建並執行 BackgroundTask，傳入具體的背景任務
                                        new BackgroundTask(requireActivity(), () -> {
                                            ModdingVeryHighFrame.ModDevice(requireContext(), Device_ID);
                                            if (Device_ID.length()-("xiaomi M2102J20SG".length()) == 0) {
                                                java.io.File inp = new java.io.File(FileUtil.getPackageDataDir(requireContext()).concat("/tmp/VeryHighFrameModeBlackList0.bytes"));
                                                java.io.File opt = new java.io.File(FileUtil.getPackageDataDir(requireContext()).concat("/tmp/VeryHighFrameModeBlackList.bytes"));
                                                inp.renameTo(opt);
                                            } else {
                                                ModdingVeryHighFrame.ModOffest(requireContext(), Device_ID.length()-("xiaomi M2102J20SG".length()));
                                            }
                                            ModdingVeryHighFrame.Encrypt(requireContext());
                                            return "";
                                        }, "請稍後", result -> {
                                            // 在這裡處理結果，例如顯示結果到 Toast 或更新 UI
                                            if (result != null) {
                                                // 依照存取模式複製進去遊戲
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                                                    if (AccessMethod.equals("SAF")) {
                                                        // 在這裡處理 SAF 的邏輯
                                                    } else if (AccessMethod.equals("Shizuku")) {
                                                        if (Shizuku.pingBinder()) {
                                                            if (checkShizukuPermission()) {
                                                                ShizukuUtil.executeShellCommandWithShizuku("cp /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/tmp/VeryHighFrameModeBlackList.bytes" + " " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/Databin/Client/Text/");
                                                            }
                                                        } else {
                                                            showMessage(getResources().getString(R.string.ShizukuPingFailed));
                                                            Intent page = new Intent(getActivity(), Activity1_ChooseUtils.class);
                                                            startActivity(page);
                                                        }
                                                    } else if (AccessMethod.equals("Root")) {
                                                        RootUtil.executeRootCommand("cp /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/tmp/VeryHighFrameModeBlackList.bytes" + " " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/Databin/Client/Text/");
                                                    }
                                                } else {
                                                    FileUtil.copyFile(FileUtil.getPackageDataDir(requireActivity())+"/tmp/VeryHighFrameModeBlackList.bytes", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/Databin/Client/Text/VeryHighFrameModeBlackList.bytes");
                                                }
                                                showMessage("完成");
                                                // FileUtil.deleteFile(FileUtil.getPackageDataDir(requireActivity())+"/tmp");
                                            }
                                        }).execute();
                                    }
                                });

                            } else {
                                FileUtil.deleteFile(FileUtil.getPackageDataDir(requireActivity())+"/tmp");
                            }
                        }
                    });
        } else {
            showMessage("請先開啟設備的高幀率模式");
        }

    }


    private boolean open120(){
        DisplayManager displayManager = (DisplayManager) requireContext().getSystemService(DISPLAY_SERVICE);
        if (displayManager != null) {
            Display display = displayManager.getDisplay(Display.DEFAULT_DISPLAY);
            if (display != null) {
                float refreshRate = display.getRefreshRate();
                return refreshRate >= 87.0f;
            }
        }
        return false;
    }

    // 解壓縮 Asset (高幀率)
    private void saveAssets(final String file, final String path, final String file2) {
        try{
            int count;
            java.io.InputStream input= requireContext().getAssets().open(file);
            java.io.OutputStream output = new java.io.FileOutputStream(path + file2);
            byte[] data = new byte[1024];
            while ((count = input.read(data))>0) {
                output.write(data, 0, count);
            }
            output.flush();
            output.close();
            input.close();
        }catch(Exception e){
            Log.e("ERR", e.toString());
        }
    }

    /** 維羅國動 修改 效驗包 */
    private void ModVertifyAssetBundle_Wiro() {
        // 創建並執行 BackgroundTask，傳入具體的背景任務
        new BackgroundTask(requireActivity(), () -> {
            // 若安卓11以上，用 Shizuku/Root 複製，否則用 FileUtil
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (AccessMethod.equals("SAF")) {
                    // 在這裡處理 SAF 的邏輯
                    return null;
                } else if (AccessMethod.equals("Shizuku")) {
                    if (Shizuku.pingBinder()) {
                        if (checkShizukuPermission()) {
                            ShizukuUtil.executeShellCommandWithShizuku("cp /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/assetbundle/resourceverificationinfosetall.assetbundle /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/");
                        }
                    } else {
                        showMessage(getResources().getString(R.string.ShizukuPingFailed));
                        Intent page = new Intent(getActivity(), Activity1_ChooseUtils.class);
                        startActivity(page);
                        return null;
                    }
                } else if (AccessMethod.equals("Root")) {
                    RootUtil.executeRootCommand("cp /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/assetbundle/resourceverificationinfosetall.assetbundle /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/");
                }
            } else {
                FileUtil.copyFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/assetbundle/resourceverificationinfosetall.assetbundle", "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/resourceverificationinfosetall.assetbundle");
                return "hmmm";
            }
            return "aboveR";
        }, "", result -> {
            // 在這裡處理結果，開始讀取並修改
            if (result != null) {
                // root 環境下，要先 chmod 660 才能改，否則改失敗 (非root已經授權 2/2 即 660)
                if (!AppSettings.contains("GameUID")) {
                    if (AccessMethod.equals("SAF")) {
                        // 在這裡處理 SAF 的邏輯
                    } else if (AccessMethod.equals("Shizuku")) {
                        ShizukuUtil.executeShellCommandWithShizuku("chmod 660 /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/resourceverificationinfosetall.assetbundle");
                    } else if (AccessMethod.equals("Root")) {
                        RootUtil.executeRootCommand("chmod 660 /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/resourceverificationinfosetall.assetbundle");
                    }
                }
                byte[] data = FileUtil.readBinaryFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/resourceverificationinfosetall.assetbundle");
                patchBytesWithHex(data, "Actor_194_Actions.pkg.bytes", Wiro_CrcXor);
                FileUtil.writeBinaryFile(data, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/resourceverificationinfosetall.assetbundle");
                // 若安卓11以上，用 Shizuku 複製，否則用 FileUtil
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    if (AccessMethod.equals("SAF")) {
                        // 在這裡處理 SAF 的邏輯
                    } else if (AccessMethod.equals("Shizuku")) {
                        ShizukuUtil.executeShellCommandWithShizuku("mv /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/resourceverificationinfosetall.assetbundle /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/assetbundle/");
                    } else if (AccessMethod.equals("Root")) {
                        RootUtil.executeRootCommand("mv /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/resourceverificationinfosetall.assetbundle /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/assetbundle/");
                    }
                } else {
                    FileUtil.moveFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/resourceverificationinfosetall.assetbundle", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/assetbundle/resourceverificationinfosetall.assetbundle");
                }
            }
        }).execute();
    }

    // 轉換 hexstring 成 byte array
    private static byte[] hexStringToBytes(String hex) {
        byte[] bytes = new byte[8];
        for (int i = 0; i < 8; i++) {
            int index = i * 2;
            bytes[i] = (byte) Integer.parseInt(hex.substring(index, index + 2), 16);
        }
        return bytes;
    }

    // 尋找 byte array 在 data 中的 index，並替換 (crcxor)
    public static void patchBytesWithHex(byte[] data, String marker, String hexString) {
        try {
            byte[] markerBytes = (marker + "\0\0").getBytes(StandardCharsets.UTF_8);
            int index = indexOf(data, markerBytes);
            if (index == -1) {
                System.out.println("Marker not found.");
                return;
            }

            int offset = index + markerBytes.length;
            byte[] patchBytes = hexStringToBytes(hexString);

            if (offset + patchBytes.length > data.length) {
                System.out.println("Data too short to patch.");
                return;
            }

            System.arraycopy(patchBytes, 0, data, offset, patchBytes.length);
            System.out.println("Patch complete.");
        } catch (Exception e) {
            e.printStackTrace();
        }
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