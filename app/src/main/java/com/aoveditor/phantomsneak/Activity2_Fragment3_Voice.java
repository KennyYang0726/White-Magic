package com.aoveditor.phantomsneak;

import android.annotation.SuppressLint;
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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aoveditor.phantomsneak.Services.AdmobService;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.SecureRandom;
import java.util.ArrayList;

import rikka.shizuku.Shizuku;

import com.aoveditor.phantomsneak.AlertDialog.CustomAlertDialog2;
import com.aoveditor.phantomsneak.BackgroundTask.BackgroundTask;
import com.aoveditor.phantomsneak.DownloadManager.DownloadManager;
import com.aoveditor.phantomsneak.Utils.FileUtil;
import com.aoveditor.phantomsneak.Utils.RootUtil;
import com.aoveditor.phantomsneak.Utils.ShizukuUtil;
import com.aoveditor.phantomsneak.ZipManager.ZipManager;



public class Activity2_Fragment3_Voice extends Fragment {

    /**Element*/
    private ImageView JP_Voice;
    private TextView Text_JP_Version;
    private TextView Text_JP_Last_ver;
    private ImageView EN_Voice;
    private TextView Text_EN_Version;
    private TextView Text_EN_Last_ver;
    private Button button_del_jp_plugin;
    private Button button_del_en_plugin;
    private Button button_recovery_voice_mod1;
    private Button button_recovery_voice_mod2;

    /**Components*/
    private SharedPreferences AppSettings; // 儲存 主題色, SAF授權目錄, AccessMethod, ...
    private DatabaseReference voice_plugins;
    private ValueEventListener voicePluginListener;
    private AdmobService admobService;

    /**Variable*/
    private String EN_URL = "";
    private String EN_Plugin_Ver = "";
    private String JP_URL = "";
    private String JP_Plugin_Ver = "";
    private String AccessMethod = "";
    private boolean AutoMod = false;
    private final ArrayList<String> files_in_en_voice = new ArrayList<>();
    private final ArrayList<String> files_in_jp_voice = new ArrayList<>();
    private boolean canMod = true;
    // 產生亂數，應用不可動的目錄
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();
    // 標誌位，避免授權中手癢按出去
    private boolean isBusying = false;


    public Activity2_Fragment3_Voice() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity2_fragment3_voice, container, false);
        // 初始化 sp
        AppSettings = requireActivity().getSharedPreferences("AppSettings", Activity.MODE_PRIVATE);
        // 初始化廣告
        admobService = new AdmobService(requireContext());
        // 加載插頁廣告
        admobService.loadInterstitialAd(getString(R.string.Interstitial));
        // 設置螢幕常亮
        requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        // 元件
        JP_Voice = v.findViewById(R.id.Voice_JP);
        Text_JP_Version = v.findViewById(R.id.Text_JPVoicePlugVer);
        Text_JP_Last_ver = v.findViewById(R.id.Text_LatestJPVoicePlugVer);
        EN_Voice = v.findViewById(R.id.Voice_EN);
        Text_EN_Version = v.findViewById(R.id.Text_ENVoicePlugVer);
        Text_EN_Last_ver = v.findViewById(R.id.Text_LatestENVoicePlugVer);
        button_del_jp_plugin = v.findViewById(R.id.button_del_jp_plugin);
        button_del_en_plugin = v.findViewById(R.id.button_del_en_plugin);
        button_recovery_voice_mod1 = v.findViewById(R.id.button_recovery_voice_mod1);
        button_recovery_voice_mod2 = v.findViewById(R.id.button_recovery_voice_mod2);
        // 事件
        JP_Voice.setOnClickListener(v16 -> {
            // 若有插件，啟用
            if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-Voice/JP/".concat(JP_Plugin_Ver))) {
                StartModVoice("JP", JP_Plugin_Ver);
            } else {
                if (!AutoMod) {
                    showMessage(getString(R.string.DownloadFinishedClickAgainToast));
                }
                FileUtil.deleteFile(FileUtil.getPackageDataDir(requireActivity())+"/3-Voice/JP");
                FileUtil.makeDir(FileUtil.getPackageDataDir(requireActivity())+"/3-Voice/JP");
                // 開始下載插件
                if (AutoMod) {
                    DownloadManager.downloadPlugins(requireContext(), "https://" + JP_URL, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-Voice/JP/", "下載日語插件中...", JP_Plugin_Ver, new DownloadManager.UIDownloadCallback() {
                        @Override
                        public void onDownloadComplete(String filePath) {
                            // 把該改變的狀態全部修正
                            requireActivity().runOnUiThread(() -> {
                                Text_JP_Version.setText(JP_Plugin_Ver);
                            });
                            StartModVoice("JP", JP_Plugin_Ver);
                        }
                        @Override
                        public void onDownloadFailed(String error) {
                        }
                    });
                } else {
                    admobService.showInterstitialAd(getString(R.string.Interstitial));
                    showMessage(getString(R.string.WaitTillDownloadCompletelyToast));
                    showMessage(getString(R.string.DownloadManagerProgressSeeStatusBarToast));
                    DownloadManager.downloadManager(requireContext(), "https://" + JP_URL, "Android/data/com.aoveditor.phantomsneak/files/3-Voice/JP/", JP_Plugin_Ver);
                    updateUI();
                }
            }
        });
        EN_Voice.setOnClickListener(v15 -> {
            // 若有插件，啟用
            if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-Voice/EN/".concat(EN_Plugin_Ver))) {
                StartModVoice("EN", EN_Plugin_Ver);
            } else {
                if (!AutoMod) {
                    showMessage(getString(R.string.DownloadFinishedClickAgainToast));
                }
                FileUtil.deleteFile(FileUtil.getPackageDataDir(requireActivity())+"/3-Voice/EN");
                FileUtil.makeDir(FileUtil.getPackageDataDir(requireActivity())+"/3-Voice/EN");
                // 開始下載插件
                if (AutoMod) {
                    DownloadManager.downloadPlugins(requireContext(), "https://" + EN_URL, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-Voice/EN/", "下載英語插件中...", EN_Plugin_Ver, new DownloadManager.UIDownloadCallback() {
                        @Override
                        public void onDownloadComplete(String filePath) {
                            // 把該改變的狀態全部修正
                            requireActivity().runOnUiThread(() -> {
                                Text_EN_Version.setText(EN_Plugin_Ver);
                            });
                            StartModVoice("EN", EN_Plugin_Ver);
                        }
                        @Override
                        public void onDownloadFailed(String error) {
                        }
                    });
                } else {
                    admobService.showInterstitialAd(getString(R.string.Interstitial));
                    showMessage(getString(R.string.WaitTillDownloadCompletelyToast));
                    showMessage(getString(R.string.DownloadManagerProgressSeeStatusBarToast));
                    DownloadManager.downloadManager(requireContext(), "https://" + EN_URL, "Android/data/com.aoveditor.phantomsneak/files/3-Voice/EN/", EN_Plugin_Ver);
                    updateUI();
                }
            }
        });
        button_del_jp_plugin.setOnClickListener(v14 -> {
            FileUtil.deleteFile(FileUtil.getPackageDataDir(requireActivity())+"/3-Voice/JP");
            FileUtil.makeDir(FileUtil.getPackageDataDir(requireActivity())+"/3-Voice/JP");
            updateUI();
        });
        button_del_en_plugin.setOnClickListener(v13 -> {
            FileUtil.deleteFile(FileUtil.getPackageDataDir(requireActivity())+"/3-Voice/EN");
            FileUtil.makeDir(FileUtil.getPackageDataDir(requireActivity())+"/3-Voice/EN");
            updateUI();
        });
        button_recovery_voice_mod1.setOnClickListener(v12 -> ClearAllVoiceMod());
        button_recovery_voice_mod2.setOnClickListener(v1 -> ClearAllVoiceMod());
        // 加載和顯示橫幅廣告
        AdView banner = v.findViewById(R.id.banner);
        admobService.loadBannerAd(banner);
        return v;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 當 Fragment 不可見時移除 Firebase 監聽器
        if (voice_plugins != null && voicePluginListener != null) {
            voice_plugins.removeEventListener(voicePluginListener);
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
        voice_plugins = database.getReference("3-Voice");
        voicePluginListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                EN_URL = dataSnapshot.child("en").getValue(String.class);
                EN_Plugin_Ver = dataSnapshot.child("en_ver").getValue(String.class);
                JP_URL = dataSnapshot.child("jp").getValue(String.class);
                JP_Plugin_Ver = dataSnapshot.child("jp_ver").getValue(String.class);
                if (!isBusying) {
                    updateUI();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        voice_plugins.addValueEventListener(voicePluginListener);
    }

    // 更新 UI 資料
    private void updateUI() {
        // 清空 List 避免誤判
        files_in_en_voice.clear();
        files_in_jp_voice.clear();
        // 設置最新插件版本文字
        Text_EN_Last_ver.setText(EN_Plugin_Ver);
        Text_JP_Last_ver.setText(JP_Plugin_Ver);
        // 設置目前插件版本文字
        FileUtil.listDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-Voice/EN/", files_in_en_voice);
        FileUtil.listDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-Voice/JP/", files_in_jp_voice);
        if (files_in_en_voice.isEmpty()) {
            Text_EN_Version.setText(getString(R.string.PlugNone));
        } else {
            Text_EN_Version.setText(Uri.parse(files_in_en_voice.get((0))).getLastPathSegment());
        }
        if (files_in_jp_voice.isEmpty()) {
            Text_JP_Version.setText(getString(R.string.PlugNone));
        } else {
            Text_JP_Version.setText(Uri.parse(files_in_jp_voice.get((0))).getLastPathSegment());
        }
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
                            // 判斷裡面是否有 擁有者為 AOV 的檔案
                            String LSlR_Result = ShizukuUtil.executeShellCommandWithShizuku("ls -lR /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/");
                            // Log.d("ShizukuResult-d", LSlR_Result);
                            String GameUID = AppSettings.getString("GameUID", "");
                            assert LSlR_Result != null;
                            if (LSlR_Result.contains(GameUID)) {
                                // 同時產生亂數，加在無法授權的目錄後方，以避免後續授權卡讀取
                                String randomString = generateRandomString();
                                CustomAlertDialog2.showDialog(requireContext(), false, getString(R.string.Hint), "檢測到您 傳說對決 語音目錄無法「完全」被白魔法存取，點擊「OK」進行自動授權。\n過程非常漫長(7~10分鐘)，請耐心等候...\n期間「請勿」跳離畫面，以免授權失敗或不完全。", getString(R.string.DialogCancel), getString(R.string.DialogOK), 0, new CustomAlertDialog2.DialogCallback() {
                                    @SuppressLint("StaticFieldLeak")
                                    @Override
                                    public void onResult(boolean isConfirmed) {
                                        if (isConfirmed) {
                                            isBusying = true;
                                            // 背景執行，避免組塞主執行緒
                                            String Access_command =
                                                    "mkdir /storage/emulated/0/" + randomString + " &&" +
                                                    "cp -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC" + " " +
                                                    "/storage/emulated/0/" + randomString + "/ && " +
                                                    "mv /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC" + " " +
                                                    "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC" + "_" + randomString + " && " +
                                                    "mv /storage/emulated/0/" + randomString + "/Sound_DLC /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/" + " && " +
                                                    "rm -r /storage/emulated/0/" + randomString;

                                            String Message = "正在為語音目錄授權..."; // 自訂 prog 訊息

                                            // 創建並執行 BackgroundTask，傳入具體的背景任務
                                            new BackgroundTask(requireActivity(), () -> {
                                                return ShizukuUtil.executeShellCommandWithShizuku(Access_command);
                                            }, Message, result -> {
                                                // 在這裡處理結果，例如顯示結果到 Toast 或更新 UI
                                                if (result != null) {
                                                    showMessage(getString(R.string.Successfully));
                                                    isBusying = false;
                                                }
                                            }).execute();

                                        } else {
                                            canMod = false;
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            } else {
                showMessage(getResources().getString(R.string.ShizukuPingFailed));
                Intent page = new Intent(getActivity(), Activity1_ChooseUtils.class);
                startActivity(page);
            }
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

    // 還原語音修改
    private void ClearAllVoiceMod() {
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
                            if (AppSettings.contains("GameUID")) {
                                // ADB啟用，刪除時要檢查權限
                                ShizukuUtil.executeShellCommandWithShizuku("find /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC -exec rm -f {} + 2>/dev/null");
                            } else {
                                ShizukuUtil.executeShellCommandWithShizuku("rm -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC");
                            }
                        }
                    } else {
                        showMessage(getResources().getString(R.string.ShizukuPingFailed));
                        Intent page = new Intent(getActivity(), Activity1_ChooseUtils.class);
                        startActivity(page);
                    }
                } else if (AccessMethod.equals("Root")) {
                    // 使用 Root 權限執行刪除命令
                    RootUtil.executeRootCommand("rm -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC");
                }
            } else {
                // Android 11 以下使用 FileUtil 進行刪除
                FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC");
            }
            return ""; // 不需要返回值
        }, getString(R.string.RecoveryING), result -> {
            // 在這裡處理結果，例如顯示結果到 Toast 或更新 UI
            if (result != null) {
                showMessage(getString(R.string.Recovery_Successfully));
                CustomAlertDialog2.showDialog(requireContext(), false, getString(R.string.Hint), "已還原語音修改\n是否幫您自動開啟遊戲？", getString(R.string.DialogCancel), getString(R.string.DialogOK), 0, isConfirmed -> {
                    if (isConfirmed) {
                        Intent launchIntent = requireActivity().getPackageManager().getLaunchIntentForPackage("com.garena.game.kgtw");
                        startActivity(launchIntent);
                    }
                });
            }
        }).execute();
    }

    // 啟用語音修改
    // 注意！BackGroundTask 裡面不得包 Unzip，會導致重疊 AsyncTask
    private void StartModVoice(final String Language, final String Version) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // 判斷是否能修改
            if (canMod) {
                FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-Voice/tmp");
                ZipManager.UnzipWithoutPwd(requireContext(), true, "解壓中","/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-Voice/"+Language+"/"+Version, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-Voice/tmp/", () -> {
                    // 解壓完成再調用 BackGroundTask
                    new BackgroundTask(requireActivity(), () -> {
                        if (AccessMethod.equals("SAF")) {
                            // 在這裡處理 SAF 的邏輯
                        } else if (AccessMethod.equals("Shizuku")) {
                            if (Shizuku.pingBinder()) {
                                if (checkShizukuPermission()) {
                                    ShizukuUtil.executeShellCommandWithShizuku("cp -r /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-Voice/tmp/* /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/");
                                }
                            } else {
                                showMessage(getResources().getString(R.string.ShizukuPingFailed));
                                Intent page = new Intent(getActivity(), Activity1_ChooseUtils.class);
                                startActivity(page);
                            }
                        } else if (AccessMethod.equals("Root")) {
                            RootUtil.executeRootCommand("cp -r /storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-Voice/tmp/* /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/");
                        }
                        return ""; // 不需要返回值
                    }, getString(R.string.Modding), result -> {
                        // 在這裡處理結果，例如顯示結果到 Toast 或更新 UI
                        requireActivity().runOnUiThread(() -> showMessage(getString(R.string.ApplySuccessfullyToast)));
                        FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-Voice/tmp");
                    }).execute();
                });
            } else {
                updateUI();
                showMessage("請先進行授權");
            }

        } else {
            FileUtil.makeDir("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/");
            ZipManager.UnzipWithoutPwd(requireContext(), true, "啟用中","/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-Voice/"+Language+"/"+Version, "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/", () -> requireActivity().runOnUiThread(() -> showMessage(getString(R.string.ApplySuccessfullyToast))));
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
