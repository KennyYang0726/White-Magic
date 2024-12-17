package com.aoveditor.phantomsneak.Utils;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.webkit.MimeTypeMap;

import androidx.documentfile.provider.DocumentFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SAFUtil {

    /** private 為 class 內部自己調用 */
    private static String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }

    private static DocumentFile getDocumentFile(DocumentFile documentFile, String dir){
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

    /** public 可被外部 class 使用 */
    public static void removeUriPath(Context context, Uri uri) {
        try {
            DocumentsContract.deleteDocument(context.getContentResolver(), uri);
        } catch (FileNotFoundException e) {
            // Handle file not found exception if needed
        } catch (Exception e) {
            // Handle other exceptions if needed
        }
    }


    /*
    public void createdirectoryandfile(Context context, String dir, String uridelete) {
        try {
            Uri uri1 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw");
            DocumentFile documentFile = DocumentFile.fromTreeUri(context, uri1);
            String[] list = dir.split("/");
            int i = 0;
            while (i < list.length) {
                if (!list[i].equals("")) {
                    DocumentFile a = getDocumentFile(documentFile, list[i]);
                    if (a == null) {
                        documentFile = documentFile.createDirectory(list[i]);
                    } else {
                        documentFile = a;
                    }
                }
                i++;
            }

            documentFile = documentFile.createFile("text/plain", "test.txt");

            if (uridelete.contains("Sound_DLC")){
                uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2F"+uridelete);
                try {
                    try{
                        DocumentsContract.deleteDocument(context.getContentResolver(), uriA);
                    } catch (FileNotFoundException e) {

                    }
                } catch (Exception e) {
                }
            } else if (uridelete.contains("LobbyMovie")){
                uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2F"+uridelete);
                try {
                    try{
                        DocumentsContract.deleteDocument(context.getContentResolver(), uriA);
                    } catch (FileNotFoundException e) {
                    }
                } catch (Exception e) {
                }
            } else if (uridelete.contains("Config")) {
                uriB = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2F"+uridelete);
                try {
                    try{
                        DocumentsContract.deleteDocument(context.getContentResolver(), uriB);
                    } catch (FileNotFoundException e) {
                    }
                } catch (Exception e) {
                }
            } else {
                try {
                    try{
                        DocumentsContract.deleteDocument(context.getContentResolver(), uriA);
                    } catch (FileNotFoundException e) {
                    }
                } catch (Exception e) {
                }
            }
        }catch (Exception e){
        }
    }*/
    

    public static boolean renameTo(Context context, String dir,String fileName,String targetName) {
        try {
            Uri uri1 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw");
            DocumentFile documentFile = DocumentFile.fromTreeUri(context, uri1);
            String[] list = dir.split("/");
            int i = 0;
            while (i < list.length) {
                if (!list[i].equals("")) {
                    DocumentFile a = getDocumentFile(documentFile, list[i]);
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

    public static boolean copyFileToUri(Context context, File inputFile, Uri targetUri) {
        try (InputStream fis = new FileInputStream(inputFile);
             OutputStream fos = context.getContentResolver().openOutputStream(targetUri)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void copyDirectoryPathToUri(Context context, File sourceDir, Uri targetUri) {
        DocumentFile targetDir = DocumentFile.fromTreeUri(context, targetUri);
        if (targetDir == null || !targetDir.isDirectory()) return;

        // Iterate through files and directories in sourceDir
        for (File file : sourceDir.listFiles()) {
            if (file.isDirectory()) {
                Uri subDirUri = DocumentsContract.buildDocumentUriUsingTree(targetUri,
                        DocumentsContract.getDocumentId(targetUri) + "%2F" + file.getName());
                DocumentFile subDir = targetDir.findFile(file.getName());

                if (subDir != null) {
                    removeUriPath(context, subDir.getUri()); // Remove existing sub-directory
                }

                // Create sub-directory
                subDir = targetDir.createDirectory(file.getName());
                if (subDir != null) {
                    copyDirectoryPathToUri(context, file, subDir.getUri());
                }
            } else {
                DocumentFile targetFile = targetDir.findFile(file.getName());

                if (targetFile != null) {
                    removeUriPath(context, targetFile.getUri()); // Remove existing file if present
                }

                // Create and copy the file
                Uri newFileUri = targetDir.createFile(getMimeType(file.getName()), file.getName()).getUri();
                copyFileToUri(context, file, newFileUri);
            }
        }
    }

    public static void copyDirectoryUriToPath(Context context, Uri sourceUri, File targetDir) {
        DocumentFile sourceDir = DocumentFile.fromTreeUri(context, sourceUri);
        if (sourceDir == null || !sourceDir.isDirectory()) return;

        if (!targetDir.exists()) {
            targetDir.mkdirs(); // Create target directory if it does not exist
        }

        for (DocumentFile file : sourceDir.listFiles()) {
            File newFile = new File(targetDir, file.getName());

            if (file.isDirectory()) {
                copyDirectoryUriToPath(context, file.getUri(), newFile); // Recursive for subdirectories
            } else {
                try (InputStream is = context.getContentResolver().openInputStream(file.getUri());
                     OutputStream os = new FileOutputStream(newFile)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = is.read(buffer)) > 0) {
                        os.write(buffer, 0, length);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
