package com.aoveditor.phantomsneak.Utils;

import com.stericson.RootTools.RootTools;

import java.io.DataOutputStream;
import java.io.IOException;

public class SuperUserUtil {

    public static boolean haveSU() {
        if (RootTools.isRootAvailable()){
            //該手機已root
            try { //會跳出視窗
                java.util.Scanner s = new java.util.Scanner(Runtime.getRuntime().exec(new String[]{"/system/bin/su","-c","cd / && ls"}).getInputStream()).useDelimiter("\\A");
                //true為有root且允許，false為有root但不允許
                return !(s.hasNext() ? s.next() : "").equals("");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        } else {
            //該手機無root
            return false;
        }
    }

    public static void rmWithSU(String target) {
        String comando;
        try{
            comando = "rm -r " + target;
            Process suProcess = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(suProcess.getOutputStream());
            os.writeBytes(comando + "\n");
            os.flush();
            os.writeBytes("exit\n");
            os.flush();
            try {
                suProcess.waitFor();
                if (suProcess.exitValue() != 255) {
                    // TODO Code to run on success
                }else {
                    // TODO Code to run on unsuccessful
                }
            } catch (InterruptedException e) {
                // TODO Code to run in interrupted exception
            }
        } catch (IOException e) {
            // TODO Code to run in input/output exception
        }
    }



    public static void cpWithSU(String original, String target) {
        //無論是否存在，ex:  "/storage/emulated/0/Alarms/aaaaa", "/storage/emulated/0/Android/data/"
        //強制覆蓋，不存在的也OK
        String comando;
        try {
            comando = "cp -r " + original + " " + target;
            Process suProcess = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(suProcess.getOutputStream());
            os.writeBytes(comando + "\n");
            os.flush();
            os.writeBytes("exit\n");
            os.flush();
            try {
                suProcess.waitFor();
                if (suProcess.exitValue() != 255) {
                    // TODO Code to run on success
                }else {
                    // TODO Code to run on unsuccessful
                }
            } catch (InterruptedException e) {
                // TODO Code to run in interrupted exception
            }
        } catch (IOException e) {
            // TODO Code to run in input/output exception
        }
    }

}
