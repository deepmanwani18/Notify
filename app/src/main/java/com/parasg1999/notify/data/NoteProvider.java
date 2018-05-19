package com.parasg1999.notify.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.net.URI;

public class NoteProvider extends ContentProvider {

    /** Tag for the log messages */
    public static final String LOG_TAG = NoteProvider.class.getSimpleName();

    /** URI matcher code for the content URI for the notes table */
    private static final int NOTES = 100;

    /** URI matcher code for the content URI for a single note */
    private static final int NOTE_ID = 101;

    /*UriMatcher object to match a content URI to a corresponding code.*/
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    /* Static initializer. This is run the first time anything is called from this class. */
    static {
        /** The calls to addURI() go here, for all of the content URI patterns that the provider
         should recognize. All paths added to the UriMatcher have a corresponding code to return when a match is found. */

        /** The content URI of the form "content://com.parasg1999.notes/notes" will map to the
         integer code {@link #NOTES}. This URI is used to provide access to MULTIPLE rows of the notes table. */
        sUriMatcher.addURI(NoteContract.CONTENT_AUTHORITY, NoteContract.PATH_NOTES, NOTES);

         /** The content URI of the form "content://com.parasg1999.notes/notes/#" will map to the
         integer code {@link #NOTE_ID}. This URI is used to provide access to ONE single row
         of the notes table.

         In this case, the "#" wildcard is used where "#" can be substituted for an integer.
         For example, "content://com.parasg1999.notes/notes/3" matches, but
         "content://com.parasg1999.notes/notes" (without a number at the end) doesn't match. */
        sUriMatcher.addURI(NoteContract.CONTENT_AUTHORITY, NoteContract.PATH_NOTES + "/#", NOTE_ID);
    }

    /*Database helper object*/
    private DBHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new DBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        /*Get a readable database*/
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        /*Cursor to hold the result of the query*/
        Cursor cursor;

        /*Match the Uri using the UriMatcher*/
        int match = sUriMatcher.match(uri);
        switch (match) {
            case NOTES:
                cursor = database.query(NoteContract.NoteEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case NOTE_ID:
                selection = NoteContract.NoteEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(NoteContract.NoteEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannoit query the unknown URI" + uri);
        }

        /* Set notification URI on the Cursor, so we know what content URI the Cursor was created for.
         * If the data at this URI changes, then we know we need to update the Cursor. */
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int match = sUriMatcher.match(uri);
        switch (match) {
            case NOTES:
                return insertNote(uri, values);
            default:
                throw new IllegalArgumentException("You cannot insert for the URI " + uri);
        }
    }

    private Uri insertNote(Uri uri, ContentValues contentValues) {

        /*Get a writable database*/
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        long id = database.insert(NoteContract.NoteEntry.TABLE_NAME , null , contentValues);

        // If the ID is -1, then the insertion failed. Log an error and return null.
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        // Notify all listeners that the data has changed for the pet content URI
        getContext().getContentResolver().notifyChange(uri, null);

        // Return the new URI with the ID (of the newly inserted row) appended at the end
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        // Get writeable database
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
