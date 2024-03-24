package com.aoveditor.phantomsneak;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import rikka.shizuku.Shizuku;

import com.aoveditor.phantomsneak.Utils.FileUtil;
import com.aoveditor.phantomsneak.Utils.SAFUtil;
import com.aoveditor.phantomsneak.Utils.SuperUserUtil;


public class ChooseUtilActivity extends AppCompatActivity {

    public static String Method = ""; //判斷方法的依據，檔案只是決定是否跳轉此畫面

    private void onRequestPermissionsResult(int requestCode, int grantResult) { //允許 Shizuku
        boolean granted = grantResult == PackageManager.PERMISSION_GRANTED;
        if (granted) {
            Method = "Shizuku";
            FileUtil.deleteFile("/data/user/0/com.aoveditor.phantomsneak/SAF");
            FileUtil.deleteFile("/data/user/0/com.aoveditor.phantomsneak/SU");
            FileUtil.writeFile("/data/user/0/com.aoveditor.phantomsneak/Shizuku", "");
            LoadHomeActivity();
        } else {
            DialogHint();
        }
    }

    private final Shizuku.OnRequestPermissionResultListener REQUEST_PERMISSION_RESULT_LISTENER = this::onRequestPermissionsResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chooseutil);
        ChooseUtils();
    }

    @Override
    public void onBackPressed() {
        if (true) { //為了讓這裡沒法按返回退出應用
        }
    }

    private void ChooseUtils() {
        AlertDialog.Builder choose = new AlertDialog.Builder(this);

        final ImageView clickme = new ImageView(this);
        clickme.setImageResource(R.drawable.click_me); //教學影片點我(錄影上傳YT，連結先放，先不公開)

        //custom dialog
        LayoutInflater inflater = getLayoutInflater();
        View customdialogView = inflater.inflate(R.layout.customdialog, null);
        ImageView imageView = customdialogView.findViewById(R.id.imageView);
        TextView textTitle = customdialogView.findViewById(R.id.title);

        // 根據當前模式設置標題文字的顏色
        if (isDarkMode()) {
            textTitle.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        } else {
            textTitle.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
        }

        textTitle.setText("請選擇一種存取方式");

        final CharSequence[] items;

        if (MainActivity.haveSU){
            items = new CharSequence[]{"Shizuku (Sui)", "SAF", "Root"};
        } else {
            items = new CharSequence[]{"Shizuku (Sui)", "SAF"};
        }

        final int[] selectedPosition = {-1};
        choose.setCustomTitle(customdialogView)
                .setCancelable(false)
                .setView(clickme)
                .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // Update the selected position
                        selectedPosition[0] = item;
                    }
                });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                HelpDialog();
            }
        });

        clickme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                String videoId = "zJilQ4vuzVY"; // 替換為實際的YouTube影片ID
                String startTime = "t=217s"; // 替換為想要的開始時間，例如30秒
                String url = "https://www.youtube.com/watch?v=" + videoId + "&" + startTime;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });

        choose.setPositiveButton("確認", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Access the selected position here
                if (selectedPosition[0] != -1) {
                    if (selectedPosition[0] == 0) {
                        //如果選擇Shizuku
                        if (Shizuku.pingBinder()) {
                            if (checkShizukuPermission(0)) { //如果有人手癢(X) 去刪掉 Shizuku 這個檔案，而且是永久授權，再選擇 Shizuku 就會跳到這
                                Method = "Shizuku";
                                FileUtil.deleteFile("/data/user/0/com.aoveditor.phantomsneak/SAF");
                                FileUtil.deleteFile("/data/user/0/com.aoveditor.phantomsneak/SU");
                                FileUtil.writeFile("/data/user/0/com.aoveditor.phantomsneak/Shizuku", "");
                                LoadHomeActivity();
                            }
                        } else {
                            if (checkInstallation(ChooseUtilActivity.this, "moe.shizuku.privileged.api")) {
                                showMessage("尚未啟動 Shizuku 服務");
                                Intent OpenShizuku = getPackageManager().getLaunchIntentForPackage("moe.shizuku.privileged.api");
                                startActivity(OpenShizuku);
                            } else {
                                showMessage("尚未安裝 Shizuku");
                                Intent browserintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=moe.shizuku.privileged.api&pcampaignid=web_share"));
                                startActivity(browserintent);
                            }
                            ChooseUtils();
                        }
                    } else if (selectedPosition[0] == 1){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                            showMessage("很抱歉，安卓14 起\n無法再使用 SAF 模式進行存取");
                            Intent intent = new Intent();
                            intent.setClass(ChooseUtilActivity.this, ChooseUtilActivity.class);
                            ChooseUtilActivity.this.startActivity(intent);
                            ChooseUtilActivity.this.finish();
                            ChooseUtilActivity.this.overridePendingTransition(0, 0);
                        } else {
                            Method = "SAF";
                            FileUtil.deleteFile("/data/user/0/com.aoveditor.phantomsneak/Shizuku");
                            FileUtil.deleteFile("/data/user/0/com.aoveditor.phantomsneak/SU");
                            FileUtil.writeFile("/data/user/0/com.aoveditor.phantomsneak/SAF", "");
                            LoadHomeActivity();
                        }

                    }  else if (selectedPosition[0] == 2){
                        Method = "SU";
                        FileUtil.deleteFile("/data/user/0/com.aoveditor.phantomsneak/Shizuku");
                        FileUtil.deleteFile("/data/user/0/com.aoveditor.phantomsneak/SAF");
                        FileUtil.writeFile("/data/user/0/com.aoveditor.phantomsneak/SU", "");
                        LoadHomeActivity();
                    }
                }
            }
        });
        AlertDialog alert = choose.create();
        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                ((AlertDialog) dialogInterface).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
            }
        });

        alert.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Update the selected position when an item is clicked
                selectedPosition[0] = position;
                alert.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(position != -1);
            }
        });
        alert.show();
    }

    public static boolean checkInstallation(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private boolean checkShizukuPermission(int code) {
        if (Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED) {
            // Granted
            return true;
        } else if (Shizuku.shouldShowRequestPermissionRationale()) {
            // Users choose "Deny and don't ask again"
            DialogHint();
            return false;
        } else {
            // Request the permission
            Shizuku.addRequestPermissionResultListener(REQUEST_PERMISSION_RESULT_LISTENER);
            Shizuku.requestPermission(code);
            return false;
        }
    }

    private void DialogHint() {
        AlertDialog.Builder hint = new AlertDialog.Builder(this);
        hint.setIcon(R.drawable.app_icon_r)
                .setTitle("提示")
                .setMessage("由於您拒絕 Shizuku 權限，所以需請您重新選擇存取模式。\n若是想繼續用 Shizuku 存取，可以的話請按下「允許」，\n不行的話代表您可能按了「拒絕並且不再詢問」，須卸載重裝方能重新詢問。\n或是使用 SAF 進行存取。")
                .setPositiveButton("繼續", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ChooseUtils();
                    }
                });
        hint.create().show();
    }

    private void LoadHomeActivity() {
        showMessage("主畫面右上角\n可切換存取模式唷");
        Intent intent = new Intent();
        intent.setClass(ChooseUtilActivity.this, HomeActivity.class);
        ChooseUtilActivity.this.startActivity(intent);
        ChooseUtilActivity.this.finish();
        ChooseUtilActivity.this.overridePendingTransition(0, 0);
    }

    private void HelpDialog() {
        AlertDialog.Builder help = new AlertDialog.Builder(this);
        ScrollView scrollView = new ScrollView(ChooseUtilActivity.this);
        final TextView link_text = new TextView(ChooseUtilActivity.this);
        link_text.setMovementMethod(LinkMovementMethod.getInstance());

        link_text.setPadding(67,23,67,23);
        if (isDarkMode())
            link_text.setTextColor(Color.WHITE);
        else
            link_text.setTextColor(Color.BLACK);
        link_text.setTextSize(15);
        link_text.setText(Html.fromHtml("<b>選項1：Shizuku</b><br><a>使用者須先下載 Shizuku 應用程式</a><br><a href='https://play.google.com/store/apps/details?id=moe.shizuku.privileged.api&pcampaignid=web_share'>(Play商店連結)</a><br><a>以 wifi無限偵錯 或 連接電腦ADB 啟動 Shizuku 服務來使用系統 API。</a><br><i><font color=#00b050>優點 1：可自動授權 (無需 MT管理器 )</font></i><br><i><font color=#00b050>優點 2：速度快 (堪比 root 用戶 )</font></i><br><i><font color=#CC3333>缺點 1：須啟動 Shizuku 服務才可使用</font></i><br><i><font color=#CC3333>缺點 2：若使用不慎導致遊戲卡加載畫面，須至白魔法主畫面下方，點擊「強制修復資源」</font></i><br>相關文檔：<a href='https://shizuku.rikka.app/zh-hant/guide/setup/'>點我跳轉</a><br><br><b>選項2：SAF</b><br><a>先前的方法，也就是授權 傳說對決 目錄並使用 DocumentFile 進行檔案寫入</a><br><i><font color=#00b050>優點 ：一般型 (多為安卓 11 ) 較方便</font></i><br><i><font color=#CC3333>缺點 1：效率極低</font></i><br><i><font color=#CC3333>缺點 2：無法自動授權 Resources</font></i><br><br><b>選項3：Root (授予root才可見)</b><br><a>給已授予 root 權限的使用者，又不想使用 Shizuku 或是安裝 MagiskModule Sui 的一種方法。</a><b>個人並不推薦</b><a>。原因是 每個「執行」都意味著新程序建立，su 內部使用 socket 與 su daemon 互動，這樣的過程中消耗大量的時間和效能。</a><br><b>※建議做法1：裝 Magisk Sui 模組</b><br><a href='https://github.com/RikkaApps/Sui'>(Github連結)</a><br><a>安裝完成後，返回白魔法選擇 Shizuku(Sui)並按下確認，畫面應如下</a><br><a href='https://raw.githubusercontent.com/JamesYang0826/WhiteMagic-Plugins/main/Photos/0-ChooseAsccessMethod/SUI.png'>圖片1</a><br><b>※建議做法2：安裝 Shizuku</b><br><a>安裝Shizuku後，以root啟動服務。返回白魔法選擇 Shizuku(Sui)並按下確認，畫面應如下</a><br><a href='https://raw.githubusercontent.com/JamesYang0826/WhiteMagic-Plugins/main/Photos/0-ChooseAsccessMethod/Shizuku.png'>圖片2</a><br>"));

        scrollView.addView(link_text);

        help.setIcon(R.drawable.app_icon_r)
                .setTitle("幫助")
                .setCancelable(false) //"選項1：Shizuku\n相關文檔：" +
                .setView(scrollView)
                .setPositiveButton("OK", null);
        help.create().show();
    }

    private boolean isDarkMode() {
        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES;
    }

    private void showMessage(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}