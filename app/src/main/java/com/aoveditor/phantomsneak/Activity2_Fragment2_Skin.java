package com.aoveditor.phantomsneak;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.aoveditor.phantomsneak.Services.AdmobService;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import rikka.shizuku.Shizuku;

import com.aoveditor.phantomsneak.AlertDialog.CustomAlertDialog2;
import com.aoveditor.phantomsneak.BackgroundTask.BackgroundTask;
import com.aoveditor.phantomsneak.DownloadManager.DownloadManager;
import com.aoveditor.phantomsneak.Utils.FileUtil;
import com.aoveditor.phantomsneak.Utils.RootUtil;
import com.aoveditor.phantomsneak.Utils.ShizukuUtil;
import com.aoveditor.phantomsneak.ZipManager.ZipManager;



public class Activity2_Fragment2_Skin extends Fragment {

    /**Element*/
    private VideoView videoView;
    private Button Btn_DownloadSkin;
    private Button Btn_ClearMod;
    private TextView Text_SkinPlugVer;
    private TextView Text_LatestSkinPlugVer;

    /**Components*/
    private SharedPreferences AppSettings; // 儲存 主題色, SAF授權目錄, AccessMethod, ...
    private DatabaseReference skin_plugins;
    private ValueEventListener skinPluginListener;
    private AdmobService admobService;

    /**Variable*/
    private String Latest_Skin_Version = "";
    private String Skin_Url = "";
    private String Video_Url = "";
    private String languageMap = "";

    private String Game_Ver = "";
    private String AccessMethod = "";
    private boolean AutoMod = false;
    private final ArrayList<String> files_in_skin = new ArrayList<>();
    private boolean isDownload = true; // True 即 下載插件，False 即 啟用



    public Activity2_Fragment2_Skin() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity2_fragment2_skin, container, false);
        // 初始化 sp
        AppSettings = requireActivity().getSharedPreferences("AppSettings", Activity.MODE_PRIVATE);
        // 初始化廣告
        admobService = new AdmobService(requireContext());
        // 初始化獎勵廣告
        admobService.loadRewardedAd(getString(R.string.Reward));
        // 元件
        videoView = v.findViewById(R.id.video);
        Btn_DownloadSkin = v.findViewById(R.id.button_mod_skin);
        Btn_ClearMod = v.findViewById(R.id.button_recovery_all_modified);
        Text_SkinPlugVer = v.findViewById(R.id.Text_SkinPlugVer);
        Text_LatestSkinPlugVer = v.findViewById(R.id.Text_LatestSkinPlugVer);
        // 事件
        Btn_DownloadSkin.setOnClickListener(v1 -> {
            if (isDownload) {
                // 下載插件
                if (!AutoMod) {
                    showMessage(getString(R.string.DownloadFinishedClickAgainToast));
                }
                // 清空 2-Skin 目錄
                FileUtil.deleteFile(FileUtil.getPackageDataDir(requireActivity()).concat("/2-Skin"));
                FileUtil.makeDir(FileUtil.getPackageDataDir(requireActivity()).concat("/2-Skin"));
                // 顯示獎勵廣告
                admobService.showRewardedAd(completed -> {
                   if (completed) {
                       // 先背景下載語言包
                       DownloadManager.downloadInBackground(requireContext(), "https://" + languageMap, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/", "languageMap.txt", new DownloadManager.DownloadCallback() {
                           @Override
                           public void onDownloadInBackgroundComplete(String filePath) {
                               // 下載完成語言包，按照存取模式放進去
                               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                                   if (AccessMethod.equals("SAF")) {
                                       // 在這裡處理 SAF 的邏輯
                                   } else if (AccessMethod.equals("Shizuku")) {
                                       if (Shizuku.pingBinder()) {
                                           if (checkShizukuPermission()) {
                                               ShizukuUtil.executeShellCommandWithShizuku("cp " + filePath + " /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/Languages/CHT_Garena_TW/");
                                           }
                                       } else {
                                           showMessage(getResources().getString(R.string.ShizukuPingFailed));
                                           Intent page = new Intent(getActivity(), Activity1_ChooseUtils.class);
                                           startActivity(page);
                                       }
                                   } else if (AccessMethod.equals("Root")) {
                                       RootUtil.executeRootCommand("cp " + filePath + " /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/Languages/CHT_Garena_TW/");
                                   }
                               } else {
                                   FileUtil.copyFile(filePath, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/Languages/CHT_Garena_TW/languageMap.txt");
                               }
                               FileUtil.deleteFile(filePath);
                           }
                           @Override
                           public void onDownloadFailed(String error) {
                           }
                       });
                       // 同時再下載插件
                       DownloadManager.downloadPlugins(requireContext(), "https://" + Skin_Url, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-Skin/", "下載造型插件中...", Latest_Skin_Version, new DownloadManager.UIDownloadCallback() {
                           @Override
                           public void onDownloadComplete(String filePath) {
                               // 把該改變的狀態全部修正
                               isDownload = false;
                               requireActivity().runOnUiThread(() -> {
                                   Text_SkinPlugVer.setText(Latest_Skin_Version);
                                   Btn_DownloadSkin.setText(getString(R.string.Btn_ModSkin));
                               });
                               // 判斷是否自動啟用插件
                               if (AutoMod) {
                                   StartModSkin();
                               }
                           }
                           @Override
                           public void onDownloadFailed(String error) {
                               requireActivity().runOnUiThread(() -> showMessage(getString(R.string.DownloadFailedToast)));
                           }
                       });
                   }
                });
            } else {
                // 啟用插件
                StartModSkin();
            }
        });
        Btn_ClearMod.setOnClickListener(v12 -> {
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
                                ShizukuUtil.executeShellCommandWithShizuku("rm -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver);
                            }
                        } else {
                            showMessage(getResources().getString(R.string.ShizukuPingFailed));
                            Intent page = new Intent(getActivity(), Activity1_ChooseUtils.class);
                            startActivity(page);
                        }
                    } else if (AccessMethod.equals("Root")) {
                        // 使用 Root 權限執行刪除命令
                        RootUtil.executeRootCommand("rm -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources");
                    }
                } else {
                    // Android 11 以下使用 FileUtil 進行刪除
                    FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources");
                }
                return ""; // 不需要返回值
            }, getString(R.string.RecoveryING), result -> {
                // 在這裡處理結果，例如顯示結果到 Toast 或更新 UI
                if (result != null) {
                    showMessage(getString(R.string.Recovery_Successfully));
                    // 詢問開啟遊戲
                    CustomAlertDialog2.showDialog(requireContext(), false, getString(R.string.Hint), "已還原造型修改\n是否幫您自動開啟遊戲？", getString(R.string.DialogCancel), getString(R.string.DialogOK), 0, isConfirmed -> {
                        if (isConfirmed) {
                            Intent launchIntent = requireActivity().getPackageManager().getLaunchIntentForPackage("com.garena.game.kgtw");
                            startActivity(launchIntent);
                        } else {
                            requireActivity().finishAffinity();
                        }
                    });
                }
            }).execute();

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
        if (skin_plugins != null && skinPluginListener != null) {
            skin_plugins.removeEventListener(skinPluginListener);
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
        skin_plugins = database.getReference("2-Skin");
        skinPluginListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Latest_Skin_Version = dataSnapshot.child("plugin_ver").getValue(String.class);
                Skin_Url = dataSnapshot.child("plugin_url").getValue(String.class);
                Video_Url = dataSnapshot.child("video_url").getValue(String.class);
                languageMap = dataSnapshot.child("languageMap").getValue(String.class);
                updateUI();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        skin_plugins.addValueEventListener(skinPluginListener);
    }

    // 更新 UI 資料
    private void updateUI() {
        // 清空 List 避免誤判
        files_in_skin.clear();
        // 設置 Video 連結，開始播放，並設置按鈕文字
        videoView.setVideoURI(Uri.parse(Video_Url));
        videoView.start();
        if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-Skin/".concat(Latest_Skin_Version))) {
            Btn_DownloadSkin.setText(getString(R.string.Btn_ModSkin));
            isDownload = false;
        } else {
            Btn_DownloadSkin.setText(getString(R.string.Btn_DownloadSkin));
            isDownload = true;
        }
        // 設置最新插件版本文字
        Text_LatestSkinPlugVer.setText(Latest_Skin_Version);
        // 判定是否下載插件，設置目前插件版本文字
        FileUtil.listDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-Skin/", files_in_skin);
        if (files_in_skin.isEmpty()) {
            Text_SkinPlugVer.setText(getString(R.string.PlugNone));
        } else {
            Text_SkinPlugVer.setText(Uri.parse(files_in_skin.get((0))).getLastPathSegment());
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

    // 啟用造型
    // 注意！BackGroundTask 裡面不得包 Unzip，會導致重疊 AsyncTask
    private void StartModSkin() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            ZipManager.UnzipWithoutPwd(requireContext(), true, "解壓中","/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-Skin/"+Latest_Skin_Version, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-Skin/tmp/", () -> {
                // 解壓完成再調用 BackGroundTask
                new BackgroundTask(requireActivity(), () -> {
                    if (AccessMethod.equals("SAF")) {
                        // 在這裡處理 SAF 的邏輯
                    } else if (AccessMethod.equals("Shizuku")) {
                        if (Shizuku.pingBinder()) {
                            if (checkShizukuPermission()) {
                                ShizukuUtil.executeShellCommandWithShizuku("cp -r /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-Skin/tmp/* /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/");
                            }
                        } else {
                            showMessage(getResources().getString(R.string.ShizukuPingFailed));
                            Intent page = new Intent(getActivity(), Activity1_ChooseUtils.class);
                            startActivity(page);
                        }
                    } else if (AccessMethod.equals("Root")) {
                        RootUtil.executeRootCommand("cp -r /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-Skin/tmp/* /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/");
                    }
                    return ""; // 不需要返回值
                }, getString(R.string.Modding), result -> {
                    // 在這裡處理結果，例如顯示結果到 Toast 或更新 UI
                    requireActivity().runOnUiThread(() -> showMessage(getString(R.string.ApplySuccessfullyToast)));
                    FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-Skin/tmp");
                }).execute();
            });
        } else {
            ZipManager.UnzipWithoutPwd(requireContext(), true, "啟用中","/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-Skin/"+Latest_Skin_Version, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/", () -> requireActivity().runOnUiThread(() -> showMessage(getString(R.string.ApplySuccessfullyToast))));
        }
    }

    private void showMessage(String message) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();
    }

}
