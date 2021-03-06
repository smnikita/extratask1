package ru.ifmo.md.extratask1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class ImagesContentProvider extends ContentProvider {
    private static String AUTHORITY = "ru.ifmo.md.extratask1.imagesContentProvider";

    public static final Uri IMAGES_URI = Uri.parse("content://" + AUTHORITY + "/" + DBImages.TABLE_IMAGES);

    private DBImages dbImages;

    @Override
    public boolean onCreate() {
        dbImages = new DBImages(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbImages.getReadableDatabase();
        return db.query(uri.getLastPathSegment(), projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbImages.getWritableDatabase();
        String tableName = uri.getLastPathSegment();
        long id = db.insert(tableName, null, values);
        return Uri.parse("content://" + AUTHORITY + "/" + tableName + "/" + Long.toString(id));
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbImages.getWritableDatabase();
        return db.delete(uri.getLastPathSegment(), selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbImages.getWritableDatabase();
        return db.update(uri.getLastPathSegment(), values, selection, selectionArgs);
    }

}
