package com.aoveditor.phantomsneak;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.security.SecureRandom;
import java.util.Objects;

import rikka.shizuku.Shizuku;

import com.aoveditor.phantomsneak.AlertDialog.CustomAlertDialog2;
import com.aoveditor.phantomsneak.BackgroundTask.BackgroundTask;
import com.aoveditor.phantomsneak.Fragment5_TabFragmentAdapter.Fragment5_TabFragmentAdapter;
import com.aoveditor.phantomsneak.Utils.ShizukuUtil;



public class Activity2_Fragment5_Other extends Fragment {

    /**Components*/
    private SharedPreferences AppSettings; // 儲存 主題色, SAF授權目錄, AccessMethod, ...

    /**Variable*/
    private String AccessMethod = "";
    private boolean isBusying = false;
    // 產生亂數，應用不可動的目錄
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();


    public Activity2_Fragment5_Other() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity2_fragment5_other, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 初始化 View
        TabLayout tabLayout = view.findViewById(R.id.TabLayout);
        ViewPager2 viewPager2 = view.findViewById(R.id.viewPager2);
        viewPager2.setSaveEnabled(false);
        // 設置適配器
        Fragment5_TabFragmentAdapter adapter = new Fragment5_TabFragmentAdapter(this);
        viewPager2.setAdapter(adapter);
        // 綁定 TabLayout 與 ViewPager2
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            if (position == 0) {
                tab.setText("其他修改");
            } else if (position == 1) {
                tab.setText("出場動作修改");
            } else {
                tab.setText("個性按鈕修改");
            }
        }).attach();
    }
    @Override
    public void onResume() {
        super.onResume();
        // 初始化 sp
        AppSettings = requireActivity().getSharedPreferences("AppSettings", Activity.MODE_PRIVATE);
        // 重新取得 sp 資料
        AccessMethod = AppSettings.getString("AccessMethod", "");
        // 在這裡重新載入資料或刷新畫面
        if (!isBusying) {
            updateUI();
        }
    }

    // 更新 UI 資料
    private void updateUI() {
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
                            String lsld_SoundDLC = Objects.requireNonNull(ShizukuUtil.executeShellCommandWithShizuku("ls -ld /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/Sound_DLC/Android")).trim();
                            if (lsld_SoundDLC.contains(GameUID)) {
                                // 將 Sound_DLC 授權
                                // 同時產生亂數，加在無法授權的目錄後方，以避免後續授權卡讀取
                                String randomString = generateRandomString();
                                CustomAlertDialog2.showDialog(requireContext(), false, getString(R.string.Hint), "檢測到您 傳說對決 語音目錄「完全無法」被白魔法存取，點擊「OK」進行自動授權。\n過程非常漫長(7~10分鐘)，請耐心等候...\n期間「請勿」跳離畫面，以免授權失敗或不完全。", getString(R.string.DialogCancel), getString(R.string.DialogOK), 0, new CustomAlertDialog2.DialogCallback() {
                                    @SuppressLint("StaticFieldLeak")
                                    @Override
                                    public void onResult(boolean isConfirmed) {
                                        if (isConfirmed) {
                                            isBusying = true;
                                            // 背景執行，避免組塞主執行緒
                                            String Access_command =
                                                    "mkdir /storage/emulated/0/" + randomString + " &&" +
                                                            "cp -r /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/Sound_DLC" + " " +
                                                            "/storage/emulated/0/" + randomString + "/ && " +
                                                            "mv /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/Sound_DLC" + " " +
                                                            "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/Sound_DLC" + "_" + randomString + " && " +
                                                            "mv /storage/emulated/0/" + randomString + "/Sound_DLC /storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2022.V3/" + " && " +
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
                                            Intent page = new Intent(getActivity(), Activity2_Base.class);
                                            startActivity(page);
                                            requireActivity().overridePendingTransition(0, 0);
                                        }
                                    }
                                });
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
    private static String generateRandomString() {
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
