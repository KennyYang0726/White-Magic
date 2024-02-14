package com.aoveditor.phantomsneak.Utils;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import androidx.documentfile.provider.DocumentFile;

import com.aoveditor.phantomsneak.HomeActivity;
import com.aoveditor.phantomsneak.WarningActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SAFUtil {

    public static String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }

    public static void rmUriPath(Context context, String uri) {
        Uri uriA = Uri.parse(uri);
        try {
            try{
                DocumentsContract.deleteDocument(context.getContentResolver(), uriA);
            } catch (FileNotFoundException e) {
            }
        } catch (Exception e) {
        }
    }

    public static boolean cpFilePath2Uribool(Context context, File inputfile, Uri targetUri){
        InputStream fis = null;
        OutputStream fos = null;

        try {
            ContentResolver content = context.getContentResolver();
            fis = new FileInputStream(inputfile);
            fos = content.openOutputStream(targetUri);

            byte[] buff = new byte[1024];
            int length = 0;

            while ((length = fis.read(buff)) > 0) {
                fos.write(buff, 0, length);
            }
        } catch (IOException e) {
            return false;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    return false;
                }
            }
            if (fos != null) {
                try {
                    fos.close();

                } catch (IOException e) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void copyFilePath2Uri(Context context, final String _OriginalFilePath, Uri uri2) {
        File mfile6 = new File(_OriginalFilePath);
        DocumentFile mfile1 = DocumentFile.fromTreeUri(context, uri2);

        mfile1 = mfile1.createFile(getMimeType(_OriginalFilePath), Uri.parse(_OriginalFilePath).getLastPathSegment());
        uri2 = mfile1.getUri();
        if (cpFilePath2Uribool(context, mfile6, uri2)) {
            try {
            } catch (Exception e) {
            }
        } else {
            try {
                showMessage(context, "失敗");
            } catch (Exception e) {

            }
        }
    }

    private static void showMessage(Context context, String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

}
