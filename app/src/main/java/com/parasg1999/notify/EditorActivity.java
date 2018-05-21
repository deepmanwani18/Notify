package com.parasg1999.notify;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.parasg1999.notify.data.NoteContract;

public class EditorActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    /** Identifier for data loader */
    private static final int EXISTING_NOTE_LOADER = 0;

    /** Uri for existing pet, null if it's new */
    private Uri mCurrentNoteUri;

    /** EditText field to enter the title */
    private EditText mTitleEditText;

    /** EditText field to enter the note */
    private EditText mContentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        /*Setting up the toolbar for options Menu*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.editor_toolbar);
        setSupportActionBar(toolbar);
        /*Enabling Back button on toolbar*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        mCurrentNoteUri = intent.getData();
        //TODO Add methods
        /*Check if the note is an existing one or not*/
        if(mCurrentNoteUri == null) {

        } else {
            getLoaderManager().initLoader(EXISTING_NOTE_LOADER, null, this);
        }

        mTitleEditText = (EditText) findViewById(R.id.title_edit_text);
        mContentEditText = (EditText) findViewById(R.id.content_edit_text);



        // TODO: Use Html.fromHtml method for bold and italics
//        final TextView textView = (TextView) findViewById(R.id.aaaa);
//        textView.setText(Html.fromHtml("<b> Bold </b> <br> <i> Italics </i> <br> <u> underline </u> "));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.editor_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.editor_menu_save) {
            save();
            finish();
            return true;
        }

        else if(id == R.id.editor_menu_discard) {

        }

        return super.onOptionsItemSelected(item);
    }

    private void save() {
        /*Read input*/
        String titleString = mTitleEditText.getText().toString().trim();
        String contentString = mContentEditText.getText().toString().trim();

        ContentValues values = new ContentValues();
        values.put(NoteContract.NoteEntry.NOTE_TITLE, titleString);
        values.put(NoteContract.NoteEntry.NOTE_CONTENT, contentString);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
