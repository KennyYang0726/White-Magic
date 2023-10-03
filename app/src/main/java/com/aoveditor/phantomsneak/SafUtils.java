package com.aoveditor.phantomsneak;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.OpenableColumns;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.FileInputStream;

public class SafUtils {

    public static final String MIME_TYPE_IS_DIRECTORY = "vnd.android.document/directory";
    public static final String COLUMNS_DISPLAY_NAME = OpenableColumns.DISPLAY_NAME;
    public static final String COLUMNS_MIME_TYPE = "mime_type";
    public static final String COLUMNS_SIZE = OpenableColumns.SIZE; // Added for file size
    public static long TotalSize = 0;

    public static void walkSafTree(
            ContentResolver contentResolver,
            Uri treeUri,
            String docId,
            Callback<Integer> onNewLevel,
            Callback<Cursor> onNewFile
    ) {
        onNewLevel.call(1);
        Uri childrenUri = DocumentsContract.buildChildDocumentsUriUsingTree(treeUri, docId);
        Cursor cursor = contentResolver.query(childrenUri, null, null, null, null);
        if (cursor != null) {
            int nameIndex = cursor.getColumnIndex(COLUMNS_DISPLAY_NAME);
            int mimeIndex = cursor.getColumnIndex(COLUMNS_MIME_TYPE);
            int sizeIndex = cursor.getColumnIndex(COLUMNS_SIZE); // Added for file size
            while (cursor.moveToNext()) {
                if (onNewFile.call(cursor)) break;
                String mimeType = cursor.getString(mimeIndex);
                long fileSize = cursor.getLong(sizeIndex); // Get the file size
                Log.d("SIZE", "Size: " + fileSize + " bytes"); // Log or use the file size as needed
                TotalSize += fileSize;
                if (MIME_TYPE_IS_DIRECTORY.equals(mimeType)) {
                    String displayName = cursor.getString(nameIndex);
                    walkSafTree(contentResolver, treeUri, docId + "/" + displayName, onNewLevel, onNewFile);
                }
            }
            cursor.close();
        }
        onNewLevel.call(-1);
    }

    public interface Callback<T> {
        boolean call(T arg);
    }
}
