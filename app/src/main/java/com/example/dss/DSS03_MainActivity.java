//package com.example.dss;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//}

package com.example.dss;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextAge, editTextAddress;
    private RadioGroup radioGroupGender;
    private Button buttonSave;

    private DSS03_DB userDatabase;
    private ArrayList<DSS03_declare> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextAddress = findViewById(R.id.editTextAddress);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        buttonSave = findViewById(R.id.buttonSave);

        userDatabase = new DSS03_DB(this);
        userList = userDatabase.getAllUsers();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String ageString = editTextAge.getText().toString().trim();
                String address = editTextAddress.getText().toString().trim();
                int gender = radioGroupGender.getCheckedRadioButtonId();
                String genderString;
                if (gender == R.id.radioButtonMale) {
                    genderString = "Male";
                } else {
                    genderString = "Female";
                }

                if (name.isEmpty() || ageString.isEmpty() || address.isEmpty() || gender == -1) {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                int age = Integer.parseInt(ageString);
                DSS03_declare user = new DSS03_declare(name, age, genderString, address);
                userDatabase.addUser(user);
                userList.add(user);
                Toast.makeText(MainActivity.this, "User added successfully", Toast.LENGTH_SHORT).show();

                editTextName.setText("");
                editTextAge.setText("");
                editTextAddress.setText("1");
                radioGroupGender.clearCheck();
            }
        });
    }
}

