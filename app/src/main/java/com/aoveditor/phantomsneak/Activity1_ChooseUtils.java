package com.aoveditor.phantomsneak;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;

import java.io.FileNotFoundException;

import rikka.shizuku.Shizuku;

import com.aoveditor.phantomsneak.AlertDialog.CustomAlertDialogChooseUtil;
import com.aoveditor.phantomsneak.Utils.RootUtil;



/** 這個 Activity 僅在安卓11以上顯示 */
public class Activity1_ChooseUtils extends AppCompatActivity {

    /**Element*/
    private ImageView image_Iillustrate;
    private RadioGroup radioGroup;
    private RadioButton button_SAF;
    private RadioButton button_Root;
    private RadioButton button_Shizuku;
    private Button button_ChooseUtil;

    /**Components*/
    private Intent page = new Intent();
    private Intent SAF_Intent = new Intent();
    private SharedPreferences AppSettings; // 儲存 主題色, SAF授權目錄, AccessMethod, ...

    /**Variable*/
    private boolean BtnEnable = false;
    private int selectedIndex = 0;
    private Uri muri;
    private Uri uri2;
    private DocumentFile mfile;
    private DocumentFile mfile1;
    private static final int SAF_NEW_FOLDER_REQUEST_CODE = 43;
    private final Shizuku.OnRequestPermissionResultListener REQUEST_PERMISSION_RESULT_LISTENER = this::onRequestPermissionsResult;

    // 檢查 Shizuku 權限
    private void onRequestPermissionsResult(int requestCode, int grantResult) {
        boolean granted = grantResult == PackageManager.PERMISSION_GRANTED;
        if (granted) {
            AppSettings.edit().putString("AccessMethod", "Shizuku").apply();
            LoadHomeActivity();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_chooseutils);
        initialize(savedInstanceState);
        initializeLogic();
    }
    @Override
    public void onBackPressed() {
        if (false) { // 為了讓這裡沒法按返回退出應用
            super.onBackPressed();
        }
    }
    // SAF 授權檢查
    @SuppressLint("WrongConstant")
    @Override
    protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
        super.onActivityResult(_requestCode, _resultCode, _data);
        if (_resultCode == Activity.RESULT_OK) {
            if (_data != null) {
                muri = _data.getData();
                if (Uri.decode(muri.toString()).endsWith(":")) {
                    showMessage(getResources().getString(R.string.SAF_ErrorDir));
                    askSAFPermission();
                } else {
                    final int takeFlags = SAF_Intent.getFlags()
                            & (Intent.FLAG_GRANT_READ_URI_PERMISSION
                            | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    // Check for the freshest data.
                    getContentResolver().takePersistableUriPermission(muri, takeFlags);
                    AppSettings.edit().putString("FOLDER_URI", muri.toString()).commit();
                    mfile = DocumentFile.fromTreeUri(this, muri);
                    mfile1 = mfile.createFile("*/*", "test.file");
                    uri2 = mfile1.getUri();
                    AppSettings.edit().putString("DIRECT_FOLDER_URI", uri2.toString().substring(0, (uri2.toString().length() - 9))).commit();
                    try{
                        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uri2);
                    } catch (FileNotFoundException e) {
                    }
                    if (!AppSettings.getString("FOLDER_URI", "").equals("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw")) {
                        askSAFPermission();
                        showMessage(getResources().getString(R.string.SAF_DirectClickHint));
                    } else {
                        // 成功
                        AppSettings.edit().putString("AccessMethod", "SAF").apply();
                        LoadHomeActivity();
                    }
                }
            }
        } else {
            askSAFPermission();
            showMessage(getResources().getString(R.string.SAF_DirectClickHint));
        } switch (_requestCode) {
            default:
                break;
        }
    }

    private void initialize(Bundle savedInstanceState) {
        // 元件
        image_Iillustrate = findViewById(R.id.image_Iillustrate);
        radioGroup = findViewById(R.id.radioGroup);
        button_SAF = findViewById(R.id.btnSAF);
        button_Shizuku = findViewById(R.id.btnShizuku);
        button_Root = findViewById(R.id.btnSU);
        button_ChooseUtil = findViewById(R.id.button_ChooseUtil);
        // 初始化 sp
        AppSettings = getSharedPreferences("AppSettings", Activity.MODE_PRIVATE);
        // 事件
        image_Iillustrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomAlertDialogChooseUtil.showDialog(Activity1_ChooseUtils.this, getResources().getString(R.string.ChooseUtilDialogTitle), getResources().getString(R.string.ChooseUtilDialogMessage), getResources().getString(R.string.DialogOK), 0);
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 若 RadioGroup 有被選擇
                if (checkedId != -1) {
                    // 改變 Btn Background 資源 和 狀態
                    BtnEnable = true;
                    button_ChooseUtil.setBackgroundResource(R.drawable.custom_alert_dialog_btn_blue);
                    // 找到選擇的 RadioButton 索引
                    View selectedRadioButton = findViewById(checkedId);
                    selectedIndex = group.indexOfChild(selectedRadioButton);
                }
            }
        });
        button_ChooseUtil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BtnEnable) {
                    if (selectedIndex == 0) {
                        // Shizuku (Sui)
                        if (Shizuku.pingBinder()) {
                            if (checkShizukuPermission(0)) {
                                AppSettings.edit().putString("AccessMethod", "Shizuku").apply();
                                LoadHomeActivity();
                            }
                        } else {
                            if (checkInstallation("moe.shizuku.privileged.api")) {
                                showMessage(getResources().getString(R.string.ShizukuPingFailed));
                                Intent OpenShizuku = getPackageManager().getLaunchIntentForPackage("moe.shizuku.privileged.api");
                                startActivity(OpenShizuku);
                            } else {
                                showMessage(getResources().getString(R.string.ShizukuNotinstalled));
                                Intent browserintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=moe.shizuku.privileged.api&pcampaignid=web_share"));
                                startActivity(browserintent);
                            }
                        }
                    } else if (selectedIndex == 1) {
                        // SAF
                        if (!AppSettings.getString("FOLDER_URI", "").equals("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw")) {
                            askSAFPermission();
                            showMessage(getResources().getString(R.string.SAF_DirectClickHint));
                        } else {
                            // 已經獲得過權限
                            AppSettings.edit().putString("AccessMethod", "SAF").apply();
                            LoadHomeActivity();
                        }
                    } else if (selectedIndex == 2) {
                        // Root
                        AppSettings.edit().putString("AccessMethod", "Root").apply();
                        LoadHomeActivity();
                    }
                } else {
                    showMessage(getResources().getString(R.string.UnChooseUtilToast));
                }
            }
        });
    }

    private void initializeLogic() {
        // 顯示 root 選項
        if (RootUtil.isRootAvailable()) {
            button_Root.setVisibility(View.VISIBLE);
        }
        /*
        // 安卓11以上 把 SAF選項隱藏 ( 11 後期已無法使用 SAF )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            button_SAF.setVisibility(View.GONE);
        }*/
    }

    // 檢查 app 是否已安裝
    private boolean checkInstallation(String packageName) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    // 檢查 Shizuku 權限
    private boolean checkShizukuPermission(int code) {
        if (Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED) {
            // Granted
            return true;
        } else if (Shizuku.shouldShowRequestPermissionRationale()) {
            // Users choose "Deny and don't ask again"
            return false;
        } else {
            // Request the permission
            Shizuku.addRequestPermissionResultListener(REQUEST_PERMISSION_RESULT_LISTENER);
            Shizuku.requestPermission(code);
            return false;
        }
    }

    // 詢問 SAF 授權
    private void askSAFPermission() {
        SAF_Intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        SAF_Intent.setAction(Intent.ACTION_OPEN_DOCUMENT_TREE);
        muri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw");
        SAF_Intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, muri);
        startActivityForResult(SAF_Intent, SAF_NEW_FOLDER_REQUEST_CODE);
    }

    // 跳轉主頁
    private void LoadHomeActivity() {
        page = new Intent(getApplicationContext(), Activity2_Base.class);
        startActivity(page);
        overridePendingTransition(0, 0);
        finish();
    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}