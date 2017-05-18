package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edNote;
    RadioGroup rgStars;
    Button btnInsert, btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edNote = (EditText)findViewById(R.id.editTextNote);
        rgStars = (RadioGroup)findViewById(R.id.radioGroupStars);
        btnInsert = (Button)findViewById(R.id.buttonInsertNote);
        btnShow = (Button)findViewById(R.id.buttonShowList);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

               //getting the selected radio button's text from the radio group
                int radioButtonID = rgStars.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) rgStars.findViewById(radioButtonID);
                String selectedStar = (String) radioButton.getText();
                Integer star = Integer.parseInt(selectedStar);

                //getting data from the edit text field
                String note =  edNote.getText().toString();

                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertNote(note, star);
                db.close();

                Toast.makeText(MainActivity.this, "Inserted!",
                        Toast.LENGTH_LONG).show();
            }

        });

        btnShow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });

    }
}
