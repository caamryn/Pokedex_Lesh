package com.example.pokedex_lesh;

import androidx.appcompat.app.AppCompatActivity;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear);

        saveButton = findViewById(R.id.save_Button);
        resetButton = findViewById(R.id.reset_Button);
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
        resetButton.setBackgroundColor(getResources().getColor(R.color.red));
        saveButton.setBackgroundColor(getResources().getColor(R.color.red));

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

            Boolean checker = true;
            int name = nameET.getText().toString().length();
            if(3>= name || name>= 12){
                na.setTextColor(Color.RED);
                checker = false;
            }
            //check HP
            Float hP = Float.valueOf(hPET.getText().toString());
            if(hP <= 1 || hP >= 362){
                hp.setTextColor(Color.RED);
                checker = false;
            }
            // check Attack
            Float at = Float.valueOf(attackET.getText().toString());
            if(at <= 5 || at >= 526){
                a.setTextColor(Color.RED);
                checker = false;
            }
            //check Defense
            Float df = Float.valueOf(defenseET.getText().toString());
            if(df <= 5 || df>= 614){
                d.setTextColor(Color.RED);
                checker = false;
            }
            //check height
            Float he = Float.valueOf(heightET.getText().toString());
            if(he<= 0.3 || he>= 19.99){
                h.setTextColor(Color.RED);
                checker = false;
            }
            //check weight
            Float we = Float.valueOf(weightET.getText().toString());
            if(we<= 0.1 || we>= 820){
                w.setTextColor(Color.RED);
                checker = false;
            }

            boolean checkMale = male.isChecked();
            boolean checkFem = female.isChecked();
            if(checkMale == false && checkFem == false){
                gender.setTextColor(Color.RED);
                checker = false;
            }
            // Checks if all inputs meet requirements and displays toast if they do
           // check();
            if(checker == true){
                String message = "Your information was stored in the database!";
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
            else {
                String message2 = "The information you entered does not meet the field requirements";
                Toast.makeText(MainActivity.this, message2, Toast.LENGTH_LONG).show();

            }
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

}