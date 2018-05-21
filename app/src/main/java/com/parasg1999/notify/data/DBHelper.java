package com.parasg1999.notify.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.parasg1999.notify.data.NoteContract.NoteEntry;

/* Class to manage database creation and management */
public class DBHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = DBHelper.class.getSimpleName();

    /** Name of database file */
    private static final String DATABASE_NAME = "notes.db";

    /** Database version */
    private static final int DATABASE_VERSION = 1;

    /** Constructor of {@link com.parasg1999.notify.data.DBHelper}
     *
     * @param context of the app */
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /** Called when the database is created for the first time*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_NOTES_TABLE = "CREATE TABLE " + NoteEntry.TABLE_NAME + " ("
                + NoteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NoteEntry.NOTE_TITLE + " TEXT, "
                + NoteEntry.NOTE_CONTENT + " TEXT, "
                + NoteEntry.NOTE_CREATED + " DATETIME, "
                + NoteEntry.NOTE_UPDATED + " DATETIME, "
                + NoteEntry.NOTE_COLOR + " INTEGER NOT NULL DEFAULT 0);" ;

        db.execSQL(SQL_CREATE_NOTES_TABLE);
    }

    /** Called when database needs to be updated*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*Current version is 1.
        * TODO: Update when Tags need to be added*/
    }
}
