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

public class Activity2_Fragment5_Other_Tab2  extends Fragment {

    /**Element*/
    private Button btn_recovery_come_modified;
    private ImageView Mod_136;
    private ImageView Mod_175;
    private ImageView Mod_506;
    private ImageView Mod_518;
    private ImageView Mod_521;
    private ImageView Mod_535;

    /**Components*/
    private SharedPreferences AppSettings; // 儲存 主題色, SAF授權目錄, AccessMethod, ...
    private AdmobService admobService;

    /**Variable*/
    private String Game_Ver = "";
    private String AccessMethod = "";
    private final String url_136 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/Come/136_Anim.PS";
    private final String url_175 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/Come/175_Anim.PS";
    private final String url_506 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/Come/506_Anim.PS";
    private final String url_518 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/Come/518_Anim.PS";
    private final String url_521 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/Come/521_Anim.PS";
    private final String url_535 = "github.com/JamesYang0826/WhiteMagic-Plugins/raw/refs/heads/main/Plugins/5-Others/Come/535_Anim.PS";
    private boolean AutoMod = false;


    public Activity2_Fragment5_Other_Tab2() {
        // Required empty public constructor
    }

    private static void onTaskCompleted(String result) {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity2_fragment5_other_tab2, container, false);
        // 初始化 sp
        AppSettings = requireActivity().getSharedPreferences("AppSettings", Activity.MODE_PRIVATE);
        // 初始化廣告
        admobService = new AdmobService(requireContext());
        // 加載插頁廣告
        admobService.loadInterstitialAd(getString(R.string.Interstitial));
        // 元件
        btn_recovery_come_modified = v.findViewById(R.id.button_recovery_come_modified);
        Mod_136 = v.findViewById(R.id.come_01);
        Mod_175 = v.findViewById(R.id.come_02);
        Mod_506 = v.findViewById(R.id.come_03);
        Mod_518 = v.findViewById(R.id.come_04);
        Mod_521 = v.findViewById(R.id.come_05);
        Mod_535 = v.findViewById(R.id.come_06);
        // 事件
        btn_recovery_come_modified.setOnClickListener(v17 -> {
            ArrayList<String> ClearList = new ArrayList<>();
            ClearList.add("hero/136_wuzetian_show_base_low_raw_h.assetbundle");
            ClearList.add("hero/136_wuzetian_show_base_raw_h.assetbundle");
            ClearList.add("hero/175_zhongkui_show_base_low_raw_h.assetbundle");
            ClearList.add("hero/175_zhongkui_show_base_raw_h.assetbundle");
            ClearList.add("hero/506_darkknight_show_base_low_raw_h.assetbundle");
            ClearList.add("hero/506_darkknight_show_base_raw_h.assetbundle");
            ClearList.add("hero/518_quillen_show_base_low_raw_h.assetbundle");
            ClearList.add("hero/518_quillen_show_base_raw_h.assetbundle");
            ClearList.add("hero/521_florentino_show_base_low_raw_h.assetbundle");
            ClearList.add("hero/521_florentino_show_base_raw_h.assetbundle");
            ClearList.add("hero/535_wish_show_base_low_raw_h.assetbundle");
            ClearList.add("hero/535_wish_show_base_raw_h.assetbundle");
            ClearMod(ClearList);
        });
        Mod_136.setOnClickListener(v1 -> {
            ImageClickEvent("136_Anim.PS", url_136, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/assetbundle/show/");
        });
        Mod_175.setOnClickListener(v12 -> {
            ImageClickEvent("175_Anim.PS", url_175, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/assetbundle/show/");
        });
        Mod_506.setOnClickListener(v13 -> {
            ImageClickEvent("506_Anim.PS", url_506, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/assetbundle/show/");
        });
        Mod_518.setOnClickListener(v14 -> {
            ImageClickEvent("518_Anim.PS", url_518, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/assetbundle/show/");
        });
        Mod_521.setOnClickListener(v15 -> {
            ImageClickEvent("521_Anim.PS", url_521, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/assetbundle/show/");
        });
        Mod_535.setOnClickListener(v16 -> {
            ImageClickEvent("535_Anim.PS", url_535, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/assetbundle/show/");
        });
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
            StartModAnim(PluginName, TargetRootDir);
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
                    if (AutoMod) {
                        StartModAnim(PluginName, TargetRootDir);
                    }
                }
                @Override
                public void onDownloadFailed(String error) {
                    requireActivity().runOnUiThread(() -> showMessage(getString(R.string.DownloadFailedToast)));
                }
            });
        }
    }

    private void StartModAnim(String PluginName, String TargetRootDir) {
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

    private void ClearMod(ArrayList<String> ClearList) {
        CustomAlertDialog2.showDialog(
                requireContext(),
                false,
                "   警告！",
                "點擊 OK 還原所有\n「出場動作修改」\n是否繼續？",
                getString(R.string.DialogCancel),
                getString(R.string.DialogOK),
                0,
                isConfirmed -> {
                    if (isConfirmed) {
                        for (String str : ClearList) {
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
                                }, getString(R.string.RecoveryING), Activity2_Fragment5_Other_Tab2::onTaskCompleted).execute();
                            } else {
                                FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "/assetbundle/show/" + str);
                            }
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

    private void showMessage(String message) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();
    }

}