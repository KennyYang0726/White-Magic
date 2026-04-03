package com.aoveditor.phantomsneak.AlertDialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.aoveditor.phantomsneak.R;

public class CustomAlertDialog_ChooseTemplete {

    public interface DialogCallback {
        void onResult(boolean isConfirmed, int selectedValue);
    }

    public static void showDialog(
            Context context,
            boolean canCancel,
            String title,
            String buttonText1,
            String buttonText2,
            DialogCallback callback
    ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_alert_dialog_choose_templete, null);

        builder.setView(view);

        TextView textTitle = view.findViewById(R.id.textTitle);
        TextView textMessage = view.findViewById(R.id.textMessage);

        Button btnOption90 = view.findViewById(R.id.btnOption90);
        Button btnOption90120 = view.findViewById(R.id.btnOption90120);
        Button btnOption120 = view.findViewById(R.id.btnOption120);
        Button btnOption120144 = view.findViewById(R.id.btnOption120144);
        Button btnOption144 = view.findViewById(R.id.btnOption144);
        Button btnOption90120144 = view.findViewById(R.id.btnOption90120144);

        Button buttonCancel = view.findViewById(R.id.buttonAction_Cancel);
        Button buttonOk = view.findViewById(R.id.buttonAction);

        textTitle.setText(title);
        buttonCancel.setText(buttonText1);
        buttonOk.setText(buttonText2);

        AlertDialog dialog = builder.create();

        final Button[] optionButtons = new Button[]{
                btnOption90,
                btnOption120,
                btnOption144,
                btnOption90120,
                btnOption120144,
                btnOption90120144
        };

        final int[] selectedValue = {8};

        View.OnClickListener optionClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value;

                if (v.getId() == R.id.btnOption90) {
                    value = 8;
                } else if (v.getId() == R.id.btnOption120) {
                    value = 16;
                } else if (v.getId() == R.id.btnOption144) {
                    value = 32;
                } else if (v.getId() == R.id.btnOption90120) {
                    value = 24;
                } else if (v.getId() == R.id.btnOption120144) {
                    value = 48;
                } else if (v.getId() == R.id.btnOption90120144) {
                    value = 56;
                } else {
                    value = 8;
                }

                selectedValue[0] = value;
                updateSelectedOption(optionButtons, (Button) v);
                updateMessageByCase(textMessage, value);
            }
        };

        for (Button button : optionButtons) {
            button.setOnClickListener(optionClickListener);
        }

        // 預設一打開先選第一個：90
        selectedValue[0] = 8;
        updateSelectedOption(optionButtons, btnOption90);
        updateMessageByCase(textMessage, 8);

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onResult(false, selectedValue[0]);
                dialog.dismiss();
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onResult(true, selectedValue[0]);
                dialog.dismiss();
            }
        });

        dialog.setCancelable(canCancel);
        dialog.setCanceledOnTouchOutside(canCancel);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        dialog.show();
    }

    private static void updateSelectedOption(Button[] allButtons, Button selectedButton) {
        for (Button button : allButtons) {
            if (button == selectedButton) {
                button.setBackgroundResource(R.drawable.custom_alert_dialog_choose_templete_option_selected);
                button.setTextColor(ContextCompat.getColor(button.getContext(), android.R.color.white));
            } else {
                button.setBackgroundResource(R.drawable.custom_alert_dialog_choose_templete_option_unselected);
                button.setTextColor(ContextCompat.getColor(button.getContext(), R.color.text_color));
            }
        }
    }

    private static void updateMessageByCase(TextView textMessage, int value) {
        switch (value) {
            case 8:
                textMessage.setText("僅開放 90 FPS。\n會多出一個「超高」選擇\n請注意是否有內建應用\n導致鎖幀或沒效果");
                break;
            case 16:
                textMessage.setText("僅開放 120 FPS。\n會多出一個「極高」選擇\n請注意是否有內建應用\n導致鎖幀或沒效果");
                break;
            case 32:
                textMessage.setText("僅開放 144 FPS。\n需手機支援才會出現「極致」選項！\n請注意是否有內建應用\n導致鎖幀或沒效果");
                break;
            case 24:
                textMessage.setText("開放 90+120 FPS。\n設置會多出兩個選擇\n請注意是否有內建應用\n導致鎖幀或沒效果");
                break;
            case 48:
                textMessage.setText("開放 120+144 FPS。\n設置會多出兩個選擇\n請注意是否有內建應用\n導致鎖幀或沒效果");
                break;
            case 56:
                textMessage.setText("開放 90-144 FPS。\n設置會多出三個選擇\n選項三要支援144Hz才會顯示\n請注意是否有內建應用\n導致鎖幀或沒效果");
                break;

            default:
                textMessage.setText("請選擇一個項目。");
                break;
        }
    }
}