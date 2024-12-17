package com.aoveditor.phantomsneak.AlertDialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aoveditor.phantomsneak.R;

public class CustomAlertDialogChooseUtil {

    public static void showDialog(Context context, String title, String message, String buttonText, int imageResource) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_alert_dialog_chooseutil, null);

        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText(title);
        TextView textMessage = view.findViewById(R.id.textMessage);
        textMessage.setMovementMethod(LinkMovementMethod.getInstance());
        textMessage.setText(Html.fromHtml(message));
        ((Button) view.findViewById(R.id.buttonAction)).setText(buttonText);
        // 設定 ImageView 的資源
        ImageView imageView = view.findViewById(R.id.imageIcon); // 獲取 ImageView
        // 判斷是否要設置圖像
        if (imageResource != 0) { // 確保 imageResource 不是 0，0 即 不設置圖像
            imageView.setImageResource(imageResource); // 使用傳入的 imageResource
        }

        AlertDialog dialog = builder.create();

        view.findViewById(R.id.buttonAction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        dialog.show();
    }

}