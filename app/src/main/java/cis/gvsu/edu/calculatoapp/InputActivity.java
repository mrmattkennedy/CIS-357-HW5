package cis.gvsu.edu.calculatoapp;
//Snackbar makes a notification of some sort at the bottom.

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InputActivity extends AppCompatActivity {
    public static final int VICE_SELECT = 1;
    private String selectedMode = "length";
    private EditText input1;
    private EditText input2;
    private TextView inputUnit1;
    private TextView inputUnit2;
    private Button calcBtn;
    private Button clearBtn;
    private Button modeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        input1 = findViewById(R.id.textInput1);
        input2 = findViewById(R.id.textInput2);
        inputUnit1 = findViewById(R.id.inputUnit1);
        inputUnit2 = findViewById(R.id.inputUnit2);
        calcBtn = findViewById(R.id.calcBtn);
        clearBtn = findViewById(R.id.clearBtn);
        modeBtn = findViewById(R.id.modeBtn);

        changeHints();

        /* Erasing Other Inputs On Click */
        input1.setOnFocusChangeListener((View v, boolean b) -> input2.setText(""));
        input2.setOnFocusChangeListener((View v, boolean b) -> input1.setText(""));

        clearBtn.setOnClickListener(v -> clearFields());
        modeBtn.setOnClickListener(v -> swapMode());;
        calcBtn.setOnClickListener(v -> calculate());;
    }


    private void clearFields () {
        input1.setText("");
        input2.setText("");
    }

    private void swapMode() {
        if(selectedMode.equals("length")){
            selectedMode = "volume";
            inputUnit1.setText("Gallons");
            inputUnit2.setText("Liters");
        } else {
            selectedMode = "length";
            inputUnit1.setText("Meters");
            inputUnit2.setText("Yards");
        }
        changeHints();
    }

    private void changeHints() {
        input1.setHint("Enter " + selectedMode + " in " + inputUnit1.getText());
        input2.setHint("Enter " + selectedMode + " in " + inputUnit2.getText());
    }

    private void calculate() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings) {
            Intent i = new Intent(InputActivity.this, ViceSelectionActivity.class);
            i.putExtra("Mode", selectedMode);
            startActivityForResult(i, VICE_SELECT);
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        clearFields();
        Log.d("MyApp", Integer.toString(resultCode));
        if (resultCode == Activity.RESULT_OK) {
            Log.d("MyApp", "HERE");
            inputUnit1.setText(data.getStringExtra("FromUnit"));
            inputUnit2.setText(data.getStringExtra("ToUnit"));

        }
    }
}