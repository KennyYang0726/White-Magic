package com.aoveditor.phantomsneak;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.aoveditor.phantomsneak.AlertDialog.CustomAlertDialog2;
import com.aoveditor.phantomsneak.BackgroundTask.BackgroundTask;
import com.aoveditor.phantomsneak.DownloadManager.DownloadManager;
import com.aoveditor.phantomsneak.Services.AdmobService;
import com.aoveditor.phantomsneak.Utils.FileUtil;
import com.aoveditor.phantomsneak.Utils.RootUtil;
import com.aoveditor.phantomsneak.Utils.ShizukuUtil;
import com.aoveditor.phantomsneak.ZipManager.ZipManager;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import rikka.shizuku.Shizuku;

public class Activity2_Fragment5_Other_Tab3 extends Fragment {

    /**Element*/
    private Button btn_recovery_btn_modified;
    private ImageView Mod_10620;
    private ImageView Mod_11115;
    private ImageView Mod_11614;
    private ImageView Mod_11616;
    private ImageView Mod_11812;
    private ImageView Mod_13015;
    private ImageView Mod_13116;
    private ImageView Mod_13613;
    private ImageView Mod_14111;
    private ImageView Mod_15012;
    private ImageView Mod_15013;
    private ImageView Mod_15015;
    private ImageView Mod_15212;
    private ImageView Mod_15412;
    private ImageView Mod_15711;
    private ImageView Mod_19015;
    private ImageView Mod_19508;
    private ImageView Mod_19906;
    private ImageView Mod_51015;
    private ImageView Mod_52011;
    private ImageView Mod_54307;
    private ImageView Mod_59702;

    /**Components*/
    private SharedPreferences AppSettings; // 儲存 主題色, SAF授權目錄, AccessMethod, ...
    private AdmobService admobService;

    /**Variable*/
    private String Game_Ver = "";
    private String AccessMethod = "";
    private final String url_10620 = "https://github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_10620.PS";
    private final String url_11115 = "https://github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_11115.PS";
    private final String url_11614 = "https://github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_11614.PS";
    private final String url_11616 = "https://github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_11616.PS";
    private final String url_11812 = "https://github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_11812.PS";
    private final String url_13015 = "https://github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_13015.PS";
    private final String url_13116 = "https://github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_13116.PS";
    private final String url_13613 = "https://github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_13613.PS";
    private final String url_14111 = "https://github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_14111.PS";
    private final String url_15012 = "https://github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_15012.PS";
    private final String url_15013 = "https://github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_15013.PS";
    private final String url_15015 = "https://github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_15015.PS";
    private final String url_15212 = "https://github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_15212.PS";
    private final String url_15412 = "https://github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_15412.PS";
    private final String url_15711 = "https://github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_15711.PS";
    private final String url_19015 = "https://github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_19015.PS";
    private final String url_19508 = "https://github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_19508.PS";
    private final String url_19906 = "https://github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_19906.PS";
    private final String url_51015 = "https://github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_51015.PS";
    private final String url_52011 = "https://github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_52011.PS";
    private final String url_54307 = "https://github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_54307.PS";
    private final String url_59702 = "https://github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_59702.PS";
    private boolean AutoMod = false;


    public Activity2_Fragment5_Other_Tab3() {
        // Required empty public constructor
    }

    private static void onTaskCompleted(String result) {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity2_fragment5_other_tab3, container, false);
        // 初始化 sp
        AppSettings = requireActivity().getSharedPreferences("AppSettings", Activity.MODE_PRIVATE);
        // 初始化廣告
        admobService = new AdmobService(requireContext());
        // 加載插頁廣告
        admobService.loadInterstitialAd(getString(R.string.Interstitial));
        // 元件
        btn_recovery_btn_modified = v.findViewById(R.id.button_recovery_btn_modified);
        // 事件

        // 加載和顯示橫幅廣告
        AdView banner = v.findViewById(R.id.banner);
        admobService.loadBannerAd(banner);
        return v;
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
    }


    // 圖片點擊事件，先檢查是否存在插件
    private void ImageClickEvent(String PluginName, String PluginURL, String TargetRootDir) {
        if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/" + PluginName)) {
            StartModPersonalBtn(PluginName, TargetRootDir);
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
            // 若不存在還原檔，則下載還原檔
            if (!FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/PersonalBtn_0.PS")) {
                DownloadManager.downloadInBackground(requireContext(), "https://github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_0.PS", "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/", "PersonalBtn_0.PS", new DownloadManager.DownloadCallback() {
                    @Override
                    public void onDownloadInBackgroundComplete(String filePath) {
                    }
                    @Override
                    public void onDownloadFailed(String error) {
                        requireActivity().runOnUiThread(() -> showMessage(getString(R.string.DownloadFailedToast)));
                    }
                });
            }
            DownloadManager.downloadPlugins(requireContext(), "https://" + PluginURL, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/", "下載插件中...", PluginName, new DownloadManager.UIDownloadCallback() {
                @Override
                public void onDownloadComplete(String filePath) {
                    if (AutoMod) {
                        StartModPersonalBtn(PluginName, TargetRootDir);
                    }
                }
                @Override
                public void onDownloadFailed(String error) {
                    requireActivity().runOnUiThread(() -> showMessage(getString(R.string.DownloadFailedToast)));
                }
            });
        }
    }

    private void StartModPersonalBtn(String PluginName, String TargetRootDir) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/tmp");
            ZipManager.UnzipWithoutPwd(requireContext(), true, "解壓中","/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/" + PluginName, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/tmp/", () -> {
                // 解壓完成再調用 BackGroundTask
                new BackgroundTask(requireActivity(), () -> {
                    if (AccessMethod.equals("SAF")) {
                        // 在這裡處理 SAF 的邏輯
                    } else if (AccessMethod.equals("Shizuku")) {
                        if (Shizuku.pingBinder()) {
                            if (checkShizukuPermission()) {
                                ShizukuUtil.executeShellCommandWithShizuku("cp -r /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/tmp/*" + " " + TargetRootDir);
                            }
                        } else {
                            showMessage(getResources().getString(R.string.ShizukuPingFailed));
                            Intent page = new Intent(getActivity(), Activity1_ChooseUtils.class);
                            startActivity(page);
                        }
                    } else if (AccessMethod.equals("Root")) {
                        RootUtil.executeRootCommand("cp -r /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/tmp/*" + " " + TargetRootDir);
                    }
                    return ""; // 不需要返回值
                }, getString(R.string.Modding), result -> {
                    // 在這裡處理結果，例如顯示結果到 Toast 或更新 UI
                    requireActivity().runOnUiThread(() -> showMessage(getString(R.string.ApplySuccessfullyToast)));
                    FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/tmp");
                }).execute();
            });
        } else {
            FileUtil.makeDir(TargetRootDir);
            ZipManager.UnzipWithoutPwd(requireContext(), true, "啟用中","/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/" + PluginName, TargetRootDir, () -> requireActivity().runOnUiThread(() -> showMessage(getString(R.string.ApplySuccessfullyToast))));
        }
    }

    private void ClearMod() {
        CustomAlertDialog2.showDialog(
                requireContext(),
                false,
                "   警告！",
                "點擊 OK 還原「個性按鈕修改」\n是否繼續？",
                getString(R.string.DialogCancel),
                getString(R.string.DialogOK),
                0,
                isConfirmed -> {
                    if (isConfirmed) {
                        /*
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            new BackgroundTask(requireActivity(), () -> {
                                if (AccessMethod.equals("SAF")) {
                                    // 在這裡處理 SAF 的邏輯
                                } else if (AccessMethod.equals("Shizuku")) {
                                    if (Shizuku.pingBinder()) {
                                        if (checkShizukuPermission()) {
                                            ShizukuUtil.executeShellCommandWithShizuku("rm -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/assetbundle/show/" + str);
                                        }
                                    } else {
                                        showMessage(getResources().getString(R.string.ShizukuPingFailed));
                                        Intent page = new Intent(getActivity(), Activity1_ChooseUtils.class);
                                        startActivity(page);
                                    }
                                } else if (AccessMethod.equals("Root")) {
                                    RootUtil.executeRootCommand("rm -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/assetbundle/show/" + str);
                                }
                                return ""; // 不需要返回值
                            }, getString(R.string.RecoveryING), Activity2_Fragment5_Other_Tab3::onTaskCompleted).execute();
                        } else {
                            FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/assetbundle/show/" + str);
                        }*/
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

    private void showMessage(String message) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();
    }

}