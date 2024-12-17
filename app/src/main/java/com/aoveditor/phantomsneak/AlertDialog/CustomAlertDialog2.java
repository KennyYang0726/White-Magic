package com.aoveditor.phantomsneak.AlertDialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aoveditor.phantomsneak.R;

public class CustomAlertDialog2 {

    public static void showDialog(Context context, boolean CanCancel, String title, String message, String buttonText1, String buttonText2, int imageResources, DialogCallback callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_alert_dialog2, null);

        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText(title);
        ((TextView) view.findViewById(R.id.textMessage)).setText(message);
        ((Button) view.findViewById(R.id.buttonAction_Cancel)).setText(buttonText1);
        ((Button) view.findViewById(R.id.buttonAction)).setText(buttonText2);
        // 設定 ImageView 的資源
        ImageView imageView = view.findViewById(R.id.imageIcon); // 獲取 ImageView
        // 判斷是否要設置圖像
        if (imageResources != 0) { // 確保 imageResource 不是 0，0 即 不設置圖像
            imageView.setImageResource(imageResources); // 使用傳入的 imageResource
        }

        AlertDialog dialog = builder.create();

        view.findViewById(R.id.buttonAction_Cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onResult(false); // 返回 false 表示取消
                dialog.dismiss();
            }
        });

        view.findViewById(R.id.buttonAction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onResult(true); // 返回 true 表示確認
                dialog.dismiss();
            }
        });

        dialog.setCancelable(CanCancel);
        dialog.setCanceledOnTouchOutside(CanCancel);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        dialog.show();
    }

    // 回調接口，用於返回按鈕點擊的結果
    public interface DialogCallback {
        void onResult(boolean isConfirmed);
    }

}