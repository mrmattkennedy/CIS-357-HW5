package cis.gvsu.edu.calculatoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import java.util.ArrayList;
import java.util.List;

import edu.gvsu.cis.convcalc.UnitsConverter;

public class ViceSelectionActivity extends AppCompatActivity {
    private Spinner fromSpinner;
    private Spinner toSpinner;
    private FloatingActionButton saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vice_selection);
        fromSpinner = findViewById(R.id.fromSpinner);
        toSpinner =  findViewById(R.id.toSpinner);
        saveButton = findViewById(R.id.saveButton);
        Bundle extras = getIntent().getExtras();
        String mode = extras.get("Mode").toString();
        List<String> spinnerArray;

        /* Loading spinner based on mode */
        if(mode.equals("length")){
            spinnerArray = new ArrayList<String>();
            spinnerArray.add("Yards");
            spinnerArray.add("Meters");
            spinnerArray.add("Miles");
        } else {
            spinnerArray = new ArrayList<String>();
            spinnerArray.add("Gallons");
            spinnerArray.add("Liters");
            spinnerArray.add("Quarts");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);
        saveButton.setOnClickListener((View v) -> returnChanges());
    }

    public void returnChanges(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("FromUnit", fromSpinner.getSelectedItem().toString());
        resultIntent.putExtra("ToUnit", toSpinner.getSelectedItem().toString());
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}

