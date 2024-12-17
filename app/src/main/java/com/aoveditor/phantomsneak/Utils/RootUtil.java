package com.aoveditor.phantomsneak.Utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

public class RootUtil {

    private static Process rootProcess = null;
    private static DataOutputStream rootOutputStream = null;

    public static boolean initRootAccess() {
        try {
            // 執行 su 命令並保持 Process 活躍
            rootProcess = Runtime.getRuntime().exec("su");
            rootOutputStream = new DataOutputStream(rootProcess.getOutputStream());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isRootAvailable() {
        if (rootProcess == null || rootOutputStream == null) {
            return false; // Not initialized
        }

        try {
            // Try to execute a command that requires root access
            rootOutputStream.writeBytes("id\n"); // This command returns the current user ID
            rootOutputStream.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(rootProcess.getInputStream()));
            String line = reader.readLine();

            // If the output contains "uid=0", it means we have root access
            return line != null && line.contains("uid=0");
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Assume no root access if any error occurs
        }
    }

    public static void executeRootCommand(String command) {
        try {
            if (isRootAvailable()) {
                rootOutputStream.writeBytes(command + "\n");
                rootOutputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void closeRootAccess() {
        try {
            if (rootOutputStream != null) {
                rootOutputStream.writeBytes("exit\n");
                rootOutputStream.flush();
                rootOutputStream.close();
            }
            if (rootProcess != null) {
                rootProcess.waitFor();
                rootProcess.destroy();
            }
            rootOutputStream = null;
            rootProcess = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

