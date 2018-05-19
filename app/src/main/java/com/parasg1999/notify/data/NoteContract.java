package com.parasg1999.notify.data;

import android.provider.BaseColumns;

public class NoteContract {

    /** Prevent someone from accidentally creating the instance of the {@link NoteContract}*/
    private NoteContract() {}

    /** Defining constant values of the Note Table*/
    public static final class NoteEntry implements BaseColumns {

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
