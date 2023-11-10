package com.example.pokedex_lesh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button saveButton;
    Button resetButton;
    Button viewButton;
    RadioGroup radioGroup;   TextView gender;
    RadioButton male;
    RadioButton female;
    EditText nationalNumET;  TextView n;
    EditText nameET;   TextView na;
    EditText speciesET;  TextView s;
    EditText heightET;    TextView h;
    EditText weightET;   TextView w;
    EditText hPET;    TextView hp;
    EditText attackET;   TextView a;
    EditText defenseET;   TextView d;
    Spinner levelSpin;
    Cursor mCursor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constraint);

        saveButton = findViewById(R.id.save_Button);
        resetButton = findViewById(R.id.reset_Button);
        viewButton = findViewById(R.id.view_button);
        radioGroup = findViewById(R.id.radioG);
        male = findViewById(R.id.male_radio);
        female = findViewById(R.id.female_radio);
        gender = findViewById(R.id.gender_text);
        nationalNumET = findViewById(R.id.nationalN_edit);
        nameET = findViewById(R.id.name_edit);
        speciesET = findViewById(R.id.species_edit);
        heightET = findViewById(R.id.height_edit);
        weightET = findViewById(R.id.weight_edit);
        hPET = findViewById(R.id.hp_edit);
        attackET = findViewById(R.id.attack_edit);
        defenseET = findViewById(R.id.defense_edit);
        levelSpin = findViewById(R.id.spinner);
        n = findViewById(R.id.national_text);
        na = findViewById(R.id.name_text);
        s = findViewById(R.id.specie_text);
        h = findViewById(R.id.height_text);
        w = findViewById(R.id.weight_text);
        hp = findViewById(R.id.hp_text);
        a = findViewById(R.id.attack_text);
        d = findViewById(R.id.defense_text);


        resetButton.setOnClickListener(resetListener);
        saveButton.setOnClickListener(saveListener);
        viewButton.setOnClickListener(viewListener);
        resetButton.setBackgroundColor(getResources().getColor(R.color.red));
        saveButton.setBackgroundColor(getResources().getColor(R.color.red));
        viewButton.setBackgroundColor(getResources().getColor(R.color.red));

        // make array list of values 1-50 for Level spinner
        ArrayList<Integer> valuesList = new ArrayList<>();
        for(int i = 1; i<51; i++){
           valuesList.add(i);
        }

        ArrayAdapter<Integer> TypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, valuesList);
        levelSpin.setAdapter(TypeAdapter);

        //Sets all values to default
        setDefault();

    }

    View.OnClickListener resetListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            n.setTextColor(Color.BLACK);
            setDefault();

        }
    };


    View.OnClickListener saveListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ContentValues mNewValues = new ContentValues();
            String errors = " ";

            Boolean checker = true;
            int name = nameET.getText().toString().length();
            if(3>= name || name>= 12){
                na.setTextColor(Color.RED);
                errors += "name ";
                checker = false;
            }
            //check HP
            Float hP = Float.valueOf(hPET.getText().toString());
            if(hP <= 1 || hP >= 362){
                hp.setTextColor(Color.RED);
                errors += "HP ";
                checker = false;
            }
            // check Attack
            Float at = Float.valueOf(attackET.getText().toString());
            if(at <= 5 || at >= 526){
                a.setTextColor(Color.RED);
                errors += "Attack ";
                checker = false;
            }
            //check Defense
            Float df = Float.valueOf(defenseET.getText().toString());
            if(df <= 5 || df>= 614){
                d.setTextColor(Color.RED);
                errors += "Defense ";
                checker = false;
            }
            //check height
            Float he = Float.valueOf(heightET.getText().toString());
            if(he<= 0.3 || he>= 19.99){
                h.setTextColor(Color.RED);
                errors += "Height ";
                checker = false;
            }
            //check weight
            Float we = Float.valueOf(weightET.getText().toString());
            if(we<= 0.1 || we>= 820){
                w.setTextColor(Color.RED);
                errors += "Weight ";
                checker = false;
            }

            boolean checkMale = male.isChecked();
            boolean checkFem = female.isChecked();
            String MorF = "";
            if(checkMale == false && checkFem == false){
                gender.setTextColor(Color.RED);
                errors += "Gender ";
                checker = false;
            }
            if(checkMale){MorF = "Female";}
            if(checkFem){MorF = "Male";}

            // Checks if all inputs meet requirements and displays toast if they do
           // check();
            if(checker == true && isDupe(MorF)){
                mNewValues.put(PokemonContentProvider.COLUMN_NAME, nameET.getText().toString().trim());
                mNewValues.put(PokemonContentProvider.COLUMN_NUMBER, nationalNumET.getText().toString().trim());
                mNewValues.put(PokemonContentProvider.COLUMN_SPECIES, speciesET.getText().toString().trim());
                mNewValues.put(PokemonContentProvider.COLUMN_GENDER, MorF);
                mNewValues.put(PokemonContentProvider.COLUMN_HEIGHT, heightET.getText().toString().trim());
                mNewValues.put(PokemonContentProvider.COLUMN_WEIGHT, weightET.getText().toString().trim());
                String spin = levelSpin.getSelectedItem().toString();  //this may not work
                mNewValues.put(PokemonContentProvider.COLUMN_LEVEL, spin);
                mNewValues.put(PokemonContentProvider.COLUMN_HP, hPET.getText().toString().trim());
                mNewValues.put(PokemonContentProvider.COLUMN_ATTACK, attackET.getText().toString().trim());
                mNewValues.put(PokemonContentProvider.COLUMN_DEFENSE, defenseET.getText().toString().trim());

                String message = "Your information was stored in the database!";
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
            else {
                String message2 = "The information you entered does not meet the field requirements";
                //String message2 = "The following fields do not me the field requirements:";
                Toast.makeText(MainActivity.this, message2, Toast.LENGTH_LONG).show();
                //Toast.makeText(MainActivity.this, errors, Toast.LENGTH_LONG).show();
            }
        }

    };

    View.OnClickListener viewListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setContentView(R.layout.activity_view);  //may not need this
            Intent intent = new Intent(MainActivity.this, ViewActivity.class);
            startActivity(intent);
        }
    };

    public void setDefault(){
        // sets all fields to default value
        nationalNumET.setText("896");
        nameET.setText("Glastrier");
        speciesET.setText("Wild Horse Pokemon");
        heightET.setText("2.2 m");
        weightET.setText("800.0 kg");
        hPET.setText("0");
        attackET.setText("0");
        defenseET.setText("0");
        //sets all labels to black
        n.setTextColor(Color.BLACK);
        s.setTextColor(Color.BLACK);
        na.setTextColor(Color.BLACK);
        hp.setTextColor(Color.BLACK);
        a.setTextColor(Color.BLACK);
        d.setTextColor(Color.BLACK);
        h.setTextColor(Color.BLACK);
        w.setTextColor(Color.BLACK);
    }

    private boolean isDupe(String s){
        if(mCursor != null) {
            mCursor.moveToFirst();
            if (mCursor.getCount() > 0) {
                while (!mCursor.isAfterLast()) {
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
                    if (nationalNumET.getText().toString() == num && nameET.getText().toString() == name && speciesET.getText().toString() == spe
                            && s == gen && heightET.getText().toString() == hei && weightET.getText().toString() == wei && levelSpin.getSelectedItem().toString() == lev
                            && hPET.getText().toString() == hp && attackET.getText().toString() == at && defenseET.getText().toString() == def) {
                        String message3 = "The information you entered have already been stored in the database";
                        Toast.makeText(MainActivity.this, message3, Toast.LENGTH_LONG).show();
                        return false;
                    }
                    mCursor.moveToNext();
                }
            }
        }
        return true;
    }

}