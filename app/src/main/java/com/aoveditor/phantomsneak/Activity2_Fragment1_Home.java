package com.aoveditor.phantomsneak;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.aoveditor.phantomsneak.Services.AdmobService;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.security.SecureRandom;
import java.util.Objects;

import rikka.shizuku.Shizuku;

import com.aoveditor.phantomsneak.AlertDialog.CustomAlertDialog1;
import com.aoveditor.phantomsneak.AlertDialog.CustomAlertDialog2;
import com.aoveditor.phantomsneak.BackgroundTask.BackgroundTask;
import com.aoveditor.phantomsneak.DownloadManager.DownloadManager;
import com.aoveditor.phantomsneak.ImagePagerAdapter.ImagePagerAdapter;
import com.aoveditor.phantomsneak.Utils.FileUtil;
import com.aoveditor.phantomsneak.Utils.RootUtil;
import com.aoveditor.phantomsneak.Utils.ShizukuUtil;



/** 重要！ 以 sp 是否存在 GameUID 做判斷，若沒有，表示 Root 啟用 Shizuku */
public class Activity2_Fragment1_Home extends Fragment {

    /**Element*/
    private TextView appVer;
    private TextView lastVer;
    private ViewPager viewPager;
    private DotsIndicator dotsIndicator;
    private Button button_del_all_components;
    private Button button_recovery_all_modified;
    private Button button_fix_languages;

    /**Components*/
    private SharedPreferences AppSettings; // 儲存 主題色, SAF授權目錄, AccessMethod, ...
    private DatabaseReference state;
    private ValueEventListener stateListener;
    // ViewPager 用
    private Handler handler;
    private Runnable runnable;
    private AdmobService admobService;

    /**Variable*/
    private String AccessMethod = "";
    private String Game_Ver = "";
    // 產生亂數，應用不可動的目錄
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();
    // 標誌位，避免授權中手癢按出去
    private boolean isBusying = false;

    // ViewPager 用
    private final int[] viewpager_images = {
            R.drawable.viewpager_telannas,
            R.drawable.viewpager_nakroth,
            R.drawable.viewpager_errol,
            R.drawable.viewpager_diaochan
    };


    public Activity2_Fragment1_Home() {
        // Required empty public constructor
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity2_fragment1_home, container, false);
        // 初始化 sp
        AppSettings = requireActivity().getSharedPreferences("AppSettings", Activity.MODE_PRIVATE);
        String app_Ver = AppSettings.getString("App_Ver", "");
        String Last_Ver = AppSettings.getString("Latest_Ver", "");
        // 初始化廣告
        admobService = new AdmobService(requireContext());
        // 元件
        appVer = v.findViewById(R.id.text_appver);
        lastVer = v.findViewById(R.id.text_latestver);
        viewPager = v.findViewById(R.id.viewpager1);
        dotsIndicator = v.findViewById(R.id.dots_indicator);
        button_del_all_components = v.findViewById(R.id.button_del_all_components);
        button_recovery_all_modified = v.findViewById(R.id.button_recovery_all_modified);
        button_fix_languages = v.findViewById(R.id.button_fix_languages);
        // 初始化 ViewPager
        InitViewPager();
        // 初始化設置
        appVer.setText(getString(R.string.Version_App) + app_Ver);
        lastVer.setText(getString(R.string.Version_Latest) + Last_Ver);
        // 事件
        button_del_all_components.setOnClickListener(v1 -> {
            FileUtil.deleteFile(FileUtil.getPackageDataDir(requireActivity()));
            FileUtil.makeDir(FileUtil.getPackageDataDir(requireActivity()));
            FileUtil.makeDir(FileUtil.getPackageDataDir(requireActivity())+"/2-Skin");
            FileUtil.makeDir(FileUtil.getPackageDataDir(requireActivity())+"/3-Voice");
            FileUtil.makeDir(FileUtil.getPackageDataDir(requireActivity())+"/3-Voice/JP");
            FileUtil.makeDir(FileUtil.getPackageDataDir(requireActivity())+"/3-Voice/EN");
            FileUtil.makeDir(FileUtil.getPackageDataDir(requireActivity())+"/4-Lobby");
            FileUtil.makeDir(FileUtil.getPackageDataDir(requireActivity())+"/5-Other");
            showMessage(getString(R.string.Delete_Successfully));
        });
        button_recovery_all_modified.setOnClickListener(v12 -> CustomAlertDialog2.showDialog(requireContext(), false, getString(R.string.Dialog_AlertTitle), getString(R.string.Dialog_RecoveryAllText), getString(R.string.DialogCancel), getString(R.string.DialogOK), 0, isConfirmed -> {
            if (isConfirmed) {
                // 背景執行，避免組塞主執行緒
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
                                        ShizukuUtil.executeShellCommandWithShizuku("rm -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver);
                                        ShizukuUtil.executeShellCommandWithShizuku("rm -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/CDNImage/HeroHeadIcon/E08074BD2C22D7294436E68F0AEA0E90");
                                        ShizukuUtil.executeShellCommandWithShizuku("find /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/ISPDiff/LobbyMovie -exec rm -f {} + 2>/dev/null");
                                        ShizukuUtil.executeShellCommandWithShizuku("find /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC -exec rm -f {} + 2>/dev/null");
                                    } else {
                                        ShizukuUtil.executeShellCommandWithShizuku("rm -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources");
                                        ShizukuUtil.executeShellCommandWithShizuku("rm -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/CDNImage/HeroHeadIcon/E08074BD2C22D7294436E68F0AEA0E90");
                                        ShizukuUtil.executeShellCommandWithShizuku("rm -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/ISPDiff/LobbyMovie");
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
                            RootUtil.executeRootCommand("rm -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources");
                            RootUtil.executeRootCommand("rm -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/CDNImage/HeroHeadIcon/E08074BD2C22D7294436E68F0AEA0E90");
                            RootUtil.executeRootCommand("rm -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/ISPDiff/LobbyMovie");
                            RootUtil.executeRootCommand("rm -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC");
                        }
                    } else {
                        // Android 11 以下使用 FileUtil 進行刪除
                        FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources");
                        FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/CDNImage/HeroHeadIcon/E08074BD2C22D7294436E68F0AEA0E90");
                        FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/ISPDiff/LobbyMovie");
                        FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC");
                    }
                    return ""; // 不需要返回值
                }, getString(R.string.RecoveryING), result -> {
                    // 在這裡處理結果，例如顯示結果到 Toast 或更新 UI
                    if (result != null) {
                        showMessage(getString(R.string.Recovery_Successfully));
                    }
                }).execute();
            }
        }));
        button_fix_languages.setOnClickListener(v13 -> CustomAlertDialog2.showDialog(requireContext(), false, getString(R.string.Dialog_AlertTitle), getString(R.string.Dialog_RecoveryLanguageMap), getString(R.string.DialogCancel), getString(R.string.DialogOK), 0, isConfirmed -> {
            if (isConfirmed) {
                DownloadManager.downloadInBackground(getContext(), "https://github.com/JamesYang0826/WhiteMagic-Plugins/raw/main/Plugins/languageMap.txt", FileUtil.getPackageDataDir(requireActivity())+"/", "languageMap.txt", new DownloadManager.DownloadCallback() {
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
                        requireActivity().runOnUiThread(() -> showMessage(getString(R.string.Successfully)));
                    }
                    @Override
                    public void onDownloadFailed(String error) {
                    }
                });
            }
        }));
        // 加載和顯示橫幅廣告
        AdView banner = v.findViewById(R.id.banner);
        admobService.loadBannerAd(banner);
        return v;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable); // 停止滾動
        }
        if (state != null && stateListener != null) {
            state.removeEventListener(stateListener);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        // 初始化 sp
        AppSettings = requireActivity().getSharedPreferences("AppSettings", Activity.MODE_PRIVATE);
        // 重新取得 sp 資料
        AccessMethod = AppSettings.getString("AccessMethod", "");
        Game_Ver = AppSettings.getString("Game_Ver", "");
        // 在這裡重新載入資料或刷新畫面
        InitFirebaseDatabase();
    }

    // 初始化 Firebase Database
    private void InitFirebaseDatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        state = database.getReference("state");
        stateListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String all = "◐造型修改:";
                String skin = dataSnapshot.child("1-skin").getValue(String.class);
                assert skin != null;
                all += dialogContent(skin);
                all += "◐語音修改:✅已開放\n◐大廳主題:";
                String lobby = dataSnapshot.child("2-lobby").getValue(String.class);
                assert lobby != null;
                all += dialogContent(lobby);
                all += "◑其他修改:";
                String other = dataSnapshot.child("3-other").getValue(String.class);
                assert other != null;
                all += dialogContent(other);
                String hint = dataSnapshot.child("hint").getValue(String.class);
                assert hint != null;
                all += hint.replaceAll("`", "\n");
                if (!isBusying) {
                    CustomAlertDialog1.showDialog(requireContext(), getString(R.string.AllState), all, getString(R.string.DialogOK), 0);
                    updateUI();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        state.addValueEventListener(stateListener);
    }

    private String dialogContent(String content) {
        if (content.contains("停用")) {
            return "❌已停用\n";
        } else if (content.contains("更新")) {
            return "\uD83D\uDD04更新中\n";
        } else if (content.contains("開放")) {
            return "✅已開放\n";
        } else {
            return "⚠️停止維護\n";
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

    // 更新 UI 資料
    private void updateUI() {
        // 安卓 11 以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // 檢查修改權限是否充足
            if (AccessMethod.equals("SAF")) {

            } else if (AccessMethod.equals("Shizuku")) {
                if (Shizuku.pingBinder()) {
                    if (checkShizukuPermission()) {
                        // 確實檢測完成
                        // 檢測是否有權限修改 Resources 目錄擁有者
                        String ChownResult = ShizukuUtil.executeShellCommandWithShizuku("chown root /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources");
                        assert ChownResult != null;
                        if (!ChownResult.contains("failed")) {
                            // 成功執行 -> Root 啟用服務
                            // 刪除 sp 的 GameUID
                            AppSettings.edit().remove("GameUID").apply();
                        } else {
                            // 權限不足 -> ADB 啟用服務
                            new Thread(() -> {
                                String getUIDResult = Objects.requireNonNull(ShizukuUtil.executeShellCommandWithShizuku("dumpsys package com.garena.game.kgtw | grep uid | head -n 1 | awk '{print $1}' | sed 's/.*\\([0-9]\\{3\\}\\)$/u0_a\\1/'")).trim();
                                // 儲存 傳說對決 UID 到 sp
                                AppSettings.edit().putString("GameUID", getUIDResult).apply();
                            }).start();
                            String LSResult = ShizukuUtil.executeShellCommandWithShizuku("ls /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/"+Game_Ver+"/Languages/");
                            assert LSResult != null;
                            if (LSResult.contains("failed")) {
                                // 權限不足 -> 第一步驟，先重命名
                                String RenameResult = ShizukuUtil.executeShellCommandWithShizuku("mv /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/"+Game_Ver + " " + "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/"+Game_Ver+"_old");
                                CustomAlertDialog2.showDialog(requireContext(), false, getString(R.string.Hint), "授權步驟 1/2 已完成\n是否幫您自動開啟遊戲？", getString(R.string.DialogCancel), getString(R.string.DialogOK), 0, isConfirmed -> {
                                    if (isConfirmed) {
                                        Intent launchIntent = requireActivity().getPackageManager().getLaunchIntentForPackage("com.garena.game.kgtw");
                                        startActivity(launchIntent);
                                    } else {
                                        requireActivity().finishAffinity();
                                    }
                                });
                            } else {
                                // 有抓到目錄 -> 第二步驟，判斷裡面是否有 擁有者為 AOV 的檔案
                                String LSlR_Result = ShizukuUtil.executeShellCommandWithShizuku("ls -lR /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/"+Game_Ver + "/");
                                // Log.d("ShizukuResult-d", LSlR_Result);
                                String GameUID = AppSettings.getString("GameUID", "");
                                assert LSlR_Result != null;
                                if (LSlR_Result.contains(GameUID)) {
                                    // 同時產生亂數，加在無法授權的目錄後方，以避免後續授權卡讀取
                                    String randomString = generateRandomString();
                                    CustomAlertDialog2.showDialog(requireContext(), false, getString(R.string.Hint), "恭喜您快要完成授權了~~\n檢測到您 傳說對決 資源無法「完全」被白魔法存取，點擊「OK」進行步驟 2/2 自動授權。\n期間「請勿」跳離畫面，以免授權失敗或不完全。", getString(R.string.DialogCancel), getString(R.string.DialogOK), 0, new CustomAlertDialog2.DialogCallback() {
                                        @SuppressLint("StaticFieldLeak")
                                        @Override
                                        public void onResult(boolean isConfirmed) {
                                            if (isConfirmed) {
                                                isBusying = true;
                                                // 背景執行，避免組塞主執行緒
                                                String Access2_2_command =
                                                        "mkdir /storage/emulated/0/" + randomString + " &&" +
                                                        "cp -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + " " +
                                                        "/storage/emulated/0/" + randomString + "/ && " +
                                                        "mv /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + " " +
                                                        "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + Game_Ver + "_" + randomString + " && " +
                                                        "mv /storage/emulated/0/" + randomString + "/" + Game_Ver + " /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + " && " +
                                                        "rm -r /storage/emulated/0/" + randomString;

                                                String Message = "正在進行 2/2 授權..."; // 自訂 prog 訊息

                                                // 創建並執行 BackgroundTask，傳入具體的背景任務
                                                new BackgroundTask(requireActivity(), () -> {
                                                    return ShizukuUtil.executeShellCommandWithShizuku(Access2_2_command);
                                                }, Message, result -> {
                                                    // 在這裡處理結果，例如顯示結果到 Toast 或更新 UI
                                                    if (result != null) {
                                                        showMessage(getString(R.string.Successfully));
                                                        isBusying = false;
                                                    }
                                                }).execute();

                                            } else {
                                                requireActivity().finishAffinity();
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


    // ViewPager 初始化部分
    private void InitViewPager() {
        ImagePagerAdapter adapter = new ImagePagerAdapter(getActivity(), viewpager_images);
        viewPager.setAdapter(adapter);
        // 將 DotsIndicator 與 ViewPager 連接
        dotsIndicator.setViewPager(viewPager);
        handler = new Handler();
        // 開始滾動
        runnable = new Runnable() {
            @Override
            public void run() {
                int nextItem = viewPager.getCurrentItem() + 1;
                if (nextItem >= viewpager_images.length) {
                    nextItem = 0;
                }
                viewPager.setCurrentItem(nextItem);
                handler.postDelayed(this, 2000); // 每 2 秒自動切換
            }
        };
        handler.postDelayed(runnable, 2000);
    }

    private void showMessage(String message) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
