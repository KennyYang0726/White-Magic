package com.aoveditor.phantomsneak.NetworkChecker;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aoveditor.phantomsneak.R;
import com.aoveditor.phantomsneak.Utils.NetworkUtil;

public class NetworkChecker {

    private final Context context;
    private final Handler handler;
    private final int CHECK_INTERVAL = 7000; // 每 7 秒檢查一次網路連線
    private boolean isChecking = false;
    private boolean isDialogShowing = false; // 新增布林值跟踪對話框狀態

    public NetworkChecker(Context context) {
        this.context = context;
        this.handler = new Handler(Looper.getMainLooper());
    }

    // 開始檢查網路
    public void startChecking() {
        isChecking = true;
        checkNetwork();
    }

    // 停止檢查網路
    public void stopChecking() {
        isChecking = false;
        handler.removeCallbacksAndMessages(null); // 停止檢查
        isDialogShowing = false; // 停止檢查時重置對話框狀態
    }

    private void checkNetwork() {
        if (!isChecking) return;

        new Thread(() -> {
            boolean isConnected = NetworkUtil.isNetworkAvailable(context) && NetworkUtil.isInternetReachable();
            Log.d("NetworkState", isConnected ? "YES" : "NO");

            if (!isConnected && !isDialogShowing) { // 確保只有在對話框未顯示的情況下顯示
                // 在主線程中顯示Dialog
                handler.post(() -> showNetworkErrorDialog(context));
            }

            // 5秒後再次檢查
            handler.postDelayed(this::checkNetwork, CHECK_INTERVAL);
        }).start();
    }


    private void showNetworkErrorDialog(Context context) {
        isDialogShowing = true;
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_alert_dialog1, null);

        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText(context.getResources().getString(R.string.NetErr_Title));
        ((TextView) view.findViewById(R.id.textMessage)).setText(context.getResources().getString(R.string.NetErr_Message));
        ((Button) view.findViewById(R.id.buttonAction)).setText(context.getResources().getString(R.string.NetErr_Btn));
        // 設定 ImageView 的資源
        ImageView imageView = view.findViewById(R.id.imageIcon); // 獲取 ImageView
        imageView.setImageResource(R.drawable.ic_neterr); // 設定圖像資源，替換為你的資源ID

        AlertDialog dialog = builder.create();

        view.findViewById(R.id.buttonAction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDialogShowing = false; // 重置對話框顯示狀態
                checkNetwork();
                dialog.dismiss();
            }
        });

        dialog.setCancelable(false);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        dialog.show();
    }

}
