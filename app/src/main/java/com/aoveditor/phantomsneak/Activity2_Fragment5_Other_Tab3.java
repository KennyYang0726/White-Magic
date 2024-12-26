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

import rikka.shizuku.Shizuku;

import com.aoveditor.phantomsneak.AlertDialog.CustomAlertDialog2;
import com.aoveditor.phantomsneak.BackgroundTask.BackgroundTask;
import com.aoveditor.phantomsneak.DownloadManager.DownloadManager;
import com.aoveditor.phantomsneak.Services.AdmobService;
import com.aoveditor.phantomsneak.Utils.FileUtil;
import com.aoveditor.phantomsneak.Utils.RootUtil;
import com.aoveditor.phantomsneak.Utils.ShizukuUtil;
import com.aoveditor.phantomsneak.ZipManager.ZipManager;
import com.google.android.gms.ads.AdView;



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
    private final String url_10620 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_10620.PS";
    private final String url_11115 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_11115.PS";
    private final String url_11614 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_11614.PS";
    private final String url_11616 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_11616.PS";
    private final String url_11812 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_11812.PS";
    private final String url_13015 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_13015.PS";
    private final String url_13116 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_13116.PS";
    private final String url_13613 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_13613.PS";
    private final String url_14111 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_14111.PS";
    private final String url_15012 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_15012.PS";
    private final String url_15013 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_15013.PS";
    private final String url_15015 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_15015.PS";
    private final String url_15212 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_15212.PS";
    private final String url_15412 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_15412.PS";
    private final String url_15711 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_15711.PS";
    private final String url_19015 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_19015.PS";
    private final String url_19508 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_19508.PS";
    private final String url_19906 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_19906.PS";
    private final String url_51015 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_51015.PS";
    private final String url_52011 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_52011.PS";
    private final String url_54307 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_54307.PS";
    private final String url_59702 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_59702.PS";
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
        Mod_10620 = v.findViewById(R.id.btn_01);
        Mod_11115 = v.findViewById(R.id.btn_02);
        Mod_11614 = v.findViewById(R.id.btn_03);
        Mod_11616 = v.findViewById(R.id.btn_04);
        Mod_11812 = v.findViewById(R.id.btn_05);
        Mod_13015 = v.findViewById(R.id.btn_06);
        Mod_13116 = v.findViewById(R.id.btn_07);
        Mod_13613 = v.findViewById(R.id.btn_08);
        Mod_14111 = v.findViewById(R.id.btn_09);
        Mod_15012 = v.findViewById(R.id.btn_10);
        Mod_15013 = v.findViewById(R.id.btn_11);
        Mod_15015 = v.findViewById(R.id.btn_12);
        Mod_15212 = v.findViewById(R.id.btn_13);
        Mod_15412 = v.findViewById(R.id.btn_14);
        Mod_15711 = v.findViewById(R.id.btn_15);
        Mod_19015 = v.findViewById(R.id.btn_16);
        Mod_19508 = v.findViewById(R.id.btn_17);
        Mod_19906 = v.findViewById(R.id.btn_18);
        Mod_51015 = v.findViewById(R.id.btn_19);
        Mod_52011 = v.findViewById(R.id.btn_20);
        Mod_54307 = v.findViewById(R.id.btn_21);
        Mod_59702 = v.findViewById(R.id.btn_22);
        // 事件
        btn_recovery_btn_modified.setOnClickListener(v1 -> ClearModClick());
        Mod_10620.setOnClickListener(v12 -> ImageClickEvent("PersonalBtn_10620.PS", url_10620, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/"));
        Mod_11115.setOnClickListener(v13 -> ImageClickEvent("PersonalBtn_11115.PS", url_11115, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/"));
        Mod_11614.setOnClickListener(v14 -> ImageClickEvent("PersonalBtn_11614.PS", url_11614, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/"));
        Mod_11616.setOnClickListener(v15 -> ImageClickEvent("PersonalBtn_11616.PS", url_11616, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/"));
        Mod_11812.setOnClickListener(v16 -> ImageClickEvent("PersonalBtn_11812.PS", url_11812, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/"));
        Mod_13015.setOnClickListener(v17 -> ImageClickEvent("PersonalBtn_13015.PS", url_13015, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/"));
        Mod_13116.setOnClickListener(v18 -> ImageClickEvent("PersonalBtn_13116.PS", url_13116, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/"));
        Mod_13613.setOnClickListener(v19 -> ImageClickEvent("PersonalBtn_13613.PS", url_13613, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/"));
        Mod_14111.setOnClickListener(v110 -> ImageClickEvent("PersonalBtn_14111.PS", url_14111, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/"));
        Mod_15012.setOnClickListener(v111 -> ImageClickEvent("PersonalBtn_15012.PS", url_15012, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/"));
        Mod_15013.setOnClickListener(v112 -> ImageClickEvent("PersonalBtn_15013.PS", url_15013, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/"));
        Mod_15015.setOnClickListener(v113 -> ImageClickEvent("PersonalBtn_15015.PS", url_15015, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/"));
        Mod_15212.setOnClickListener(v114 -> ImageClickEvent("PersonalBtn_15212.PS", url_15212, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/"));
        Mod_15412.setOnClickListener(v115 -> ImageClickEvent("PersonalBtn_15412.PS", url_15412, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/"));
        Mod_15711.setOnClickListener(v116 -> ImageClickEvent("PersonalBtn_15711.PS", url_15711, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/"));
        Mod_19015.setOnClickListener(v117 -> ImageClickEvent("PersonalBtn_19015.PS", url_19015, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/"));
        Mod_19508.setOnClickListener(v118 -> ImageClickEvent("PersonalBtn_19508.PS", url_19508, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/"));
        Mod_19906.setOnClickListener(v119 -> ImageClickEvent("PersonalBtn_19906.PS", url_19906, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/"));
        Mod_51015.setOnClickListener(v120 -> ImageClickEvent("PersonalBtn_51015.PS", url_51015, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/"));
        Mod_52011.setOnClickListener(v121 -> ImageClickEvent("PersonalBtn_52011.PS", url_52011, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/"));
        Mod_54307.setOnClickListener(v122 -> ImageClickEvent("PersonalBtn_54307.PS", url_54307, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/"));
        Mod_59702.setOnClickListener(v123 -> ImageClickEvent("PersonalBtn_59702.PS", url_59702, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/"));
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
            if (showInterstitialAdCnt%2 == 0) {
                // 2個一數，顯示廣告
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
    

    // 點擊還原按鈕 先檢查是否存在還原檔
    private void ClearModClick() {
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
                        if (!FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/PersonalBtn_0.PS")) {
                            DownloadManager.downloadPlugins(requireContext(), "https://github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/PersonalBtn/PersonalBtn_0.PS", "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/", "下載還原檔...", "PersonalBtn_0.PS", new DownloadManager.UIDownloadCallback() {
                                @Override
                                public void onDownloadComplete(String filePath) {
                                    requireActivity().runOnUiThread(() -> ClearMod());
                                }
                                @Override
                                public void onDownloadFailed(String error) {
                                    requireActivity().runOnUiThread(() -> showMessage(getString(R.string.DownloadFailedToast)));
                                }
                            });
                        } else {
                            ClearMod();
                        }
                    }
                });
    }
    
    // 還原邏輯
    private void ClearMod() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/tmp");
            ZipManager.UnzipWithoutPwd(requireContext(), false, "解壓中","/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/PersonalBtn_0.PS", "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/tmp/", () -> {
                // 解壓完成再調用 BackGroundTask
                new BackgroundTask(requireActivity(), () -> {
                    if (AccessMethod.equals("SAF")) {
                        // 在這裡處理 SAF 的邏輯
                    } else if (AccessMethod.equals("Shizuku")) {
                        if (Shizuku.pingBinder()) {
                            if (checkShizukuPermission()) {
                                ShizukuUtil.executeShellCommandWithShizuku("cp -r /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/tmp/*" + " " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/");
                            }
                        } else {
                            showMessage(getResources().getString(R.string.ShizukuPingFailed));
                            Intent page = new Intent(getActivity(), Activity1_ChooseUtils.class);
                            startActivity(page);
                        }
                    } else if (AccessMethod.equals("Root")) {
                        RootUtil.executeRootCommand("cp -r /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/tmp/*" + " " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/");
                    }
                    return ""; // 不需要返回值
                }, getString(R.string.RecoveryING), result -> {
                    // 在這裡處理結果，例如顯示結果到 Toast 或更新 UI
                    requireActivity().runOnUiThread(() -> showMessage("還原成功"));
                    FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/tmp");
                }).execute();
            });
        } else {
            ZipManager.UnzipWithoutPwd(requireContext(), true, getString(R.string.RecoveryING), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/5-Other/PersonalBtn_0.PS", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/", () -> requireActivity().runOnUiThread(() -> showMessage("還原成功")));
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

    private void showMessage(String message) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();
    }

}