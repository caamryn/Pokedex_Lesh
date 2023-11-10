package com.example.pokedex_lesh;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.LinkedList;

public class ViewActivity extends AppCompatActivity {

    Cursor mCursor;
    CursorAdapter mCursorAdapter;
    String[] mListColumns;
    String[] mSelectedArgs;
    int[] mListItems;
    LinkedList<String> list;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        listView = findViewById(R.id.list_view);
        listView.setOnItemClickListener(deleteListener);

        mCursor = getContentResolver().query(PokemonContentProvider.CONTENT_URI, null, null, null, null);
        mListColumns = new String[] {PokemonContentProvider.COLUMN_NUMBER, PokemonContentProvider.COLUMN_NAME, PokemonContentProvider.COLUMN_SPECIES,
                PokemonContentProvider.COLUMN_GENDER, PokemonContentProvider.COLUMN_HEIGHT, PokemonContentProvider.COLUMN_WEIGHT, PokemonContentProvider.COLUMN_LEVEL,
                PokemonContentProvider.COLUMN_HP, PokemonContentProvider.COLUMN_ATTACK, PokemonContentProvider.COLUMN_DEFENSE};
        mListItems = new int[] {android.R.layout.simple_list_item_1};
        mCursorAdapter = new SimpleCursorAdapter(this, R.layout.activity_view, mCursor, mListColumns, mListItems);
        //listView.setAdapter(mCursorAdapter);

        list = new LinkedList<>();
        if(mCursor != null){
            mCursor.moveToFirst();
            if(mCursor.getCount() > 0){
                while(!mCursor.isAfterLast()){
                    String num = String.valueOf(mCursor.getInt(1));
                    String name = mCursor.getString(2);
                    String spe = mCursor.getString(3);
                    String gen = mCursor.getString(4);
                    String hei = String.valueOf(mCursor.getDouble(5));
                    String wei = String.valueOf(mCursor.getDouble(6));
                    String lev = mCursor.getString(7);
                    String hp = String.valueOf(mCursor.getInt(8));
                    String at = String.valueOf(mCursor.getInt(9));
                    String def = String.valueOf(mCursor.getInt(10));
                    String temp = "National Number: " + num + ", Name: " + name + ", Species: " + spe +
                            ", Gender: " + gen + ", Height: " + hei + ", Weight: " + wei + ", Level: " +
                            lev + ", HP: " + hp + ", Attack: " + at + ", Defense: " + def;
                    list.add(temp);
                    mCursor.moveToNext();
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
            listView.setAdapter(adapter);
        }

    }

    AdapterView.OnItemClickListener deleteListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Object selected = listView.getAdapter().getItem(i);

        }
    };
}