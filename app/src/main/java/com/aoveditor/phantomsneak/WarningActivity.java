package com.aoveditor.phantomsneak;

import android.content.Intent;
import android.net.Uri;
import android.os.*;
import android.view.View;
import android.widget.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.FirebaseApp;
import java.io.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import android.provider.DocumentsContract;
import androidx.documentfile.provider.DocumentFile;


public class WarningActivity extends AppCompatActivity {
    private Timer _timer = new Timer();

    private int cnt_down;
    private boolean can_press_ok = false;
    private String letter1 = "";
    private String letter2 = "";
    private String letter3 = "";
    private String str1 = "";
    private int c1;
    private int c2;
    private int c3;
    private Button button1;
    private Button button2;

    private Intent page = new Intent();
    private TimerTask t_cnt;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.warning);
        initialize(_savedInstanceState);
        FirebaseApp.initializeApp(this);
        MobileAds.initialize(this);
        initializeLogic();
    }

    private void initialize(Bundle _savedInstanceState) {
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                page.setClass(getApplicationContext(), HomeActivity.class);
                startActivity(page);
                overridePendingTransition(0, 0);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (can_press_ok) {
                    if (HomeActivity.extra_access == "❌"){
                        renameTo("/files/Extra/2019.V2", "Sound_DLC", "Sound_DLC_"+str1);
                        showMessage("①已自動授權完成");
                    }else{
                        showMessage("①無須自動授權");
                    }
                    if (HomeActivity.ISPDiff_access == "❌"){
                        renameTo("/files/Extra/2019.V2", "ISPDiff", "ISPDiff_"+str1);
                        showMessage("②已自動授權完成");
                    }else{
                        showMessage("②無須自動授權");
                    }
                    if (ChooseUtilActivity.Method == "SAF") {
                        showMessage("請再次點擊「重新檢測」");
                    }
                    page.setClass(getApplicationContext(), HomeActivity.class);
                    startActivity(page);
                    overridePendingTransition(0, 0);
                }
            }
        });
    }

    private void initializeLogic() {
        letter1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        letter2 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        letter3 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        c1 = getRandom((int)(0), (int)(61));
        c2 = getRandom((int)(0), (int)(61));
        c3 = getRandom((int)(0), (int)(61));
        str1 = (letter1.substring(c1, c1+1)+letter2.substring(c2, c2+1)+letter3.substring(c3, c3+1));
        can_press_ok = false;
        cnt_down = 5;

        t_cnt = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (cnt_down == 0){
                            button2.setText("確定");
                            can_press_ok = true;
                            t_cnt.cancel();

                        }else{
                            button2.setText("確定 ("+String.valueOf(cnt_down)+")");
                            cnt_down--;
                        }

                    }
                });
            }
        };
        _timer.scheduleAtFixedRate(t_cnt, (int)(0), (int)(1000));
    }

    @Override
    public void onBackPressed() {
        if (true) {
        }
    }

    private DocumentFile getDocumentFile1(DocumentFile documentFile,String dir){
        if (documentFile==null)return null;
        try {
            DocumentFile[] documentFiles = documentFile.listFiles();
            DocumentFile res = null;
            int i = 0;
            while (i < documentFile.length()) {
                if (documentFiles[i].getName().equals(dir) && documentFiles[i].isDirectory()) {
                    res = documentFiles[i];
                    return res;
                }
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean renameTo(String dir,String fileName,String targetName) {
        try {
            Uri uri1 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw");
            DocumentFile documentFile = DocumentFile.fromTreeUri(WarningActivity.this, uri1);
            String[] list = dir.split("/");
            int i = 0;
            while (i < list.length) {
                if (!list[i].equals("")) {
                    DocumentFile a = getDocumentFile1(documentFile, list[i]);
                    if (a == null) {
                        documentFile = documentFile.createDirectory(list[i]);
                    } else {
                        documentFile = a;
                    }
                }
                i++;
            }
            documentFile=documentFile.findFile(fileName);
            return documentFile.renameTo(targetName);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void showMessage(String _s) {
        Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
    }
    public int getRandom(int _min, int _max) {
        Random random = new Random();
        return random.nextInt(_max - _min + 1) + _min;
    }

}
