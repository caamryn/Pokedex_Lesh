package com.example.pokedex_lesh;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class PokemonContentProvider extends ContentProvider {

    public static final String DBNAME = "POKEDB";
    public static final String TABLE_NAME = "Pokemon";
    public static final String COLUMN_NUMBER = "Number";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_SPECIES = "Species";
    public static final String COLUMN_GENDER = "Gender";
    public static final String COLUMN_HEIGHT = "Height";
    public static final String COLUMN_WEIGHT = "Weight";
    public static final String COLUMN_LEVEL = "Level";
    public static final String COLUMN_HP = "HP";
    public static final String COLUMN_ATTACK = "Attack";
    public static final String COLUMN_DEFENSE = "Defense";
    public static final String AUTHORITY = "poke.provider"; //is this right
    public static final Uri CONTENT_URI = Uri.parse(
            "content://" + AUTHORITY +"/" + TABLE_NAME);
    private MainDatabaseHelper mOpenHelper;
    protected static final class MainDatabaseHelper extends SQLiteOpenHelper {
        MainDatabaseHelper(Context context) {
            super(context, DBNAME, null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_MAIN);
        }
        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        }
    };

    private static final String SQL_CREATE_MAIN = "CREATE TABLE " +
            TABLE_NAME +  // Table's name
            "(" +               // The columns in the table
            " _ID INTEGER PRIMARY KEY, " +
            COLUMN_NUMBER + " TEXT," +
            COLUMN_NAME + " TEXT," +
            COLUMN_SPECIES + " TEXT," +
            COLUMN_GENDER + " TEXT," +
            COLUMN_HEIGHT + " TEXT," +
            COLUMN_WEIGHT + " TEXT," +
            COLUMN_LEVEL + " TEXT," +
            COLUMN_HP + " TEXT," +
            COLUMN_ATTACK + " TEXT," +
            COLUMN_DEFENSE + " TEXT)";



    public PokemonContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return mOpenHelper.getWritableDatabase().delete(TABLE_NAME, selection, selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = mOpenHelper.getWritableDatabase().insert(TABLE_NAME, null, values);

        return Uri.withAppendedPath(CONTENT_URI, "" + id);
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new MainDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return mOpenHelper.getReadableDatabase().query(TABLE_NAME, projection, selection, selectionArgs,
                null, null, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return mOpenHelper.getWritableDatabase().update(TABLE_NAME, values, selection, selectionArgs);
    }
}