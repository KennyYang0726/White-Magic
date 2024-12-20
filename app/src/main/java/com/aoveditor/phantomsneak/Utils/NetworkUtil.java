package com.aoveditor.phantomsneak.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtil {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    public static boolean isInternetReachable() {
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) (new URL("https://dlmobilegarena-a.akamaihd.net/kgtw/hok/PlayerHeadFrame/pifu_HeadFrame371.png").openConnection());
            urlConnection.setRequestMethod("HEAD"); // Set method to HEAD
            urlConnection.setRequestProperty("User-Agent", "Test");
            urlConnection.setRequestProperty("Connection", "close");
            urlConnection.setConnectTimeout(1000); // 設定連接超時
            urlConnection.connect();
            return (urlConnection.getResponseCode() == 200);
        } catch (IOException e) {
            e.printStackTrace(); // 打印錯誤日誌以便進一步檢查
            return false;
        }
    }
}
