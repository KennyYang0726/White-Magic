package com.aoveditor.phantomsneak.ModdingVeryHighFrame;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import com.aoveditor.phantomsneak.Utils.FileUtil;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class ModdingVeryHighFrame {

    // 獲取手機型號
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    private static String capitalize(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        return s.toLowerCase();
    }

    // 修改檔案機型
    public static void ModDevice(Context context, String Device) {
        String FilePath0 = FileUtil.getPackageDataDir(context)+("/tmp/VeryHighFrameModeBlackList.bytes");
        String FilePath1 = FileUtil.getPackageDataDir(context)+("/tmp/VeryHighFrameModeBlackList0.bytes");

        byte[] oldDevice = ("xiaomi M2102J20SG").getBytes();
        byte[] newDevice = (Device).getBytes();

        try {
            FileInputStream fis = new FileInputStream(FilePath0);
            FileOutputStream fos = new FileOutputStream(FilePath1);

            byte[] buffer = new byte[1024];
            int bytesRead;
            boolean wordFound;

            while ((bytesRead = fis.read(buffer)) != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    if (buffer[i] == oldDevice[0]) {
                        wordFound = true;
                        int matchLength = 1;
                        for (int j = 1; j < oldDevice.length; j++) {
                            if (i + j >= bytesRead || buffer[i + j] != oldDevice[j]) {
                                wordFound = false;
                                break;
                            }
                            matchLength++;
                        }

                        if (wordFound) {
                            // Calculate the size difference between oldWord and newWord
                            int sizeDifference = newDevice.length - matchLength;

                            // Extend the buffer if necessary to accommodate the newWord
                            if (sizeDifference > 0) {
                                byte[] tempBuffer = new byte[bytesRead + sizeDifference];
                                System.arraycopy(buffer, 0, tempBuffer, 0, i);
                                System.arraycopy(newDevice, 0, tempBuffer, i, newDevice.length);
                                System.arraycopy(buffer, i + matchLength, tempBuffer, i + newDevice.length, bytesRead - i - matchLength);
                                buffer = tempBuffer;
                            } else if (sizeDifference < 0) {
                                // Shrink the buffer if necessary to accommodate the newWord
                                System.arraycopy(buffer, i + matchLength, buffer, i + newDevice.length, bytesRead - i - matchLength);
                                System.arraycopy(newDevice, 0, buffer, i, newDevice.length);
                            } else {
                                // If the lengths are the same, just replace the oldWord with the newWord
                                System.arraycopy(newDevice, 0, buffer, i, newDevice.length);
                            }
                            // Update the bytesRead variable accordingly
                            bytesRead += sizeDifference;
                        }
                    }
                }
                fos.write(buffer, 0, bytesRead);
            }

            fis.close();
            fos.close();
            FileUtil.deleteFile(FileUtil.getPackageDataDir(context)+("/tmp/VeryHighFrameModeBlackList.bytes"));
        } catch (IOException e) {
            Log.e("ERR", e.toString());
        }
    }

    // 修改偏移
    public static void ModOffest(Context context, int len_different) {
        String FilePath0 = FileUtil.getPackageDataDir(context)+("/tmp/VeryHighFrameModeBlackList0.bytes");
        String FilePath1 = FileUtil.getPackageDataDir(context)+("/tmp/VeryHighFrameModeBlackList.bytes");

        byte[] oldOffest = {0x1b, 0x00, 0x00, 0x00, 0x04, 0x00, 0x00, 0x00, 0x12};
        byte[] newOffest = {0x1b, 0x00, 0x00, 0x00, 0x04, 0x00, 0x00, 0x00, 0x12};
        newOffest[0] = (byte) (oldOffest[0] + (byte) len_different);
        newOffest[8] = (byte) (oldOffest[8] + (byte) len_different);

        try {
            FileInputStream fis = new FileInputStream(FilePath0);
            FileOutputStream fos = new FileOutputStream(FilePath1);
            byte[] buffer = new byte[1024];
            int bytesRead;
            boolean wordFound;

            while ((bytesRead = fis.read(buffer)) != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    // Check if the oldWord starts at the current position
                    if (buffer[i] == oldOffest[0]) {
                        wordFound = true;
                        for (int j = 1; j < oldOffest.length; j++) {
                            // Check if the rest of the oldWord matches
                            if (i + j >= bytesRead || buffer[i + j] != oldOffest[j]) {
                                wordFound = false;
                                break;
                            }
                        }

                        if (wordFound) {
                            // Replace oldWord with newWord in the buffer
                            System.arraycopy(newOffest, 0, buffer, i, newOffest.length);
                        }
                    }
                }
                fos.write(buffer, 0, bytesRead);
            }

            fis.close();
            fos.close();
            FileUtil.deleteFile(FileUtil.getPackageDataDir(context)+("/tmp/VeryHighFrameModeBlackList0.bytes"));
        } catch (IOException e) {
            Log.e("ERR", e.toString());
        }
    }

    // aes加解密
    private static final byte[] key = new byte[] { (byte)0xfc, (byte)0x88, (byte)0x8a, (byte) 0x32, (byte)0x0e, (byte)0xef, (byte)0xb6, (byte)0xfd, (byte)0xd2, (byte)0x91, (byte)0x8d, (byte)0x25, (byte)0x31, (byte)0x7c, (byte)0xb0, (byte)0xf1 };
    private static final byte[] iv = new byte[] { (byte)0x00, (byte)0x00, (byte)0x00, (byte) 0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00 };

    public static void Decrypt(Context context){
        byte[] input = FileUtil.readBinaryFile(FileUtil.getPackageDataDir(context).concat("/tmp/VeryHighFrameModeBlackList.bytes"));
        assert input != null;
        byte[] input_content = Arrays.copyOfRange(input, 8, input.length);
        FileUtil.deleteFile(FileUtil.getPackageDataDir(context).concat("/tmp/VeryHighFrameModeBlackList.bytes"));
        try {
            SecretKeySpec KEY = new SecretKeySpec(key, 0, key.length, "AES");
            IvParameterSpec IV = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(Cipher.DECRYPT_MODE, KEY, IV);
            byte[] output = cipher.doFinal(input_content);
            FileUtil.writeBinaryFile(output, FileUtil.getPackageDataDir(context).concat("/tmp/VeryHighFrameModeBlackList.bytes"));
        }catch (Exception e){
            Log.e("ERR", e.toString());
        }
    }

    public static void Encrypt(Context context){
        byte[] input = FileUtil.readBinaryFile(FileUtil.getPackageDataDir(context).concat("/tmp/VeryHighFrameModeBlackList.bytes"));
        byte[] head = new byte[] {(byte)0x22, (byte)0x4a, (byte)0x67, (byte) 0x00};
        assert input != null;
        byte[] size = reverse(ByteBuffer.allocate(4).putInt(input.length).array());
        byte[] header = concatenateByteArrays(head, size);

        FileUtil.deleteFile(FileUtil.getPackageDataDir(context)+("/tmp/VeryHighFrameModeBlackList.bytes"));

        try {
            SecretKeySpec KEY = new SecretKeySpec(key, 0, key.length, "AES");
            IvParameterSpec IV = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(Cipher.ENCRYPT_MODE, KEY, IV);
            byte[] output = cipher.doFinal(input);
            FileUtil.writeBinaryFile(concatenateByteArrays(header, output), FileUtil.getPackageDataDir(context)+("/tmp/VeryHighFrameModeBlackList.bytes"));
        }catch (Exception e){
            Log.e("ERR", e.toString());
        }
    }

    private static byte[] concatenateByteArrays(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    private static byte[] reverse(byte[] array) {
        int i = 0;
        int j = array.length - 1;
        byte tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
        return array;
    }

}
