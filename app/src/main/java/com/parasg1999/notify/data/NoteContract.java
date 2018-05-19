package com.parasg1999.notify.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class NoteContract {

    /** Prevent someone from accidentally creating the instance of the {@link NoteContract}*/
    private NoteContract() {}

    /**The "Content authority" is a name for the entire content provider*/
    public static final String CONTENT_AUTHORITY = "com.parasg1999.notify";

    /** The {@link #CONTENT_AUTHORITY} will create the base of all Uri that can be used to contact the content provider
    * All the Uri for content providers start with content://
    */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**Possible path (appended to {@link #BASE_CONTENT_URI})
     * content://com.parasg1999.notify/notes will be a valid URI to fetch notes*/
    public static final String PATH_NOTES = "notes";

    /** Defining constant values of the Note Table*/
    public static final class NoteEntry implements BaseColumns {

        /** The content URI to access the notes from the provider */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_NOTES);

        /** The MIME type of the {@link #CONTENT_URI} for all notes. */
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NOTES;

        /** The MIME type of the {@link #CONTENT_URI} for a single pet.*/
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NOTES;

        /* Name of the table for notes*/
        public final static String TABLE_NAME = "notes";

        /*Unique Id for the use in database*/
        public final static String _ID = BaseColumns._ID;

        /* Title of the note*/
        public final static String NOTE_TITLE =  "title";

        /*Content of the note*/
        public final static String NOTE_CONTENT = "content";

        /*Date and time for creating*/
        public final static String NOTE_CREATED = "created";

        /*Date and time for updating*/
        public final static String NOTE_UPDATED = "updated";

        /**Color of note
         * The possible values being
         * {@link #COLOR_WHITE} or
         * {@link #COLOR_RED} or
         * {@link #COLOR_BLUE} or
         * {@link #COLOR_YELLOW} or
         * {@link #COLOR_GREEN} */
        public final static String NOTE_COLOR = "updated";

        /*Possible values of color*/
        public static final int COLOR_WHITE = 0;
        public static final int COLOR_RED = 1;
        public static final int COLOR_BLUE = 2;
        public static final int COLOR_YELLOW = 3;
        public static final int COLOR_GREEN = 4;


    }
}
