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
import android.widget.RadioGroup;
import android.widget.Toast;
import java.util.ArrayList;

public class DSS03_MainActivity extends AppCompatActivity {

    private EditText ed_hh_member_id,
            ed_Name,
            ed_dob,
            ed_age_yrs,
            ed_mother_hhid,
            ed_years_formal_education,
            ed_occupation_oth,
            ed_hh_relationship,
            ed_hh_relationship_oth;

    private RadioGroup radioGroupGender,radiooccupation,radiorelationshipHead;
    private Button buttonSave;

    private DSS03_DB userDatabase;
    private ArrayList<DSS03_declare> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_hh_member_id = findViewById(R.id.hh_member_id);
        ed_Name = findViewById(R.id.Name);
        ed_dob = findViewById(R.id.dob);
        ed_age_yrs = findViewById(R.id.age_yrs);
        ed_mother_hhid = findViewById(R.id.mother_hhid);
        ed_years_formal_education = findViewById(R.id.years_formal_education);
        ed_occupation_oth = findViewById(R.id.occupation_oth);
        ed_hh_relationship_oth = findViewById(R.id.hh_relationship_oth);

        radioGroupGender = findViewById(R.id.radioGroupGender);
        radiooccupation = findViewById(R.id.radiooccupation);
        radiorelationshipHead = findViewById(R.id.radiorelationshipHead);
        buttonSave = findViewById(R.id.buttonSave);

        userDatabase = new DSS03_DB(this);
        userList = userDatabase.getAllUsers();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hh_member_id = ed_hh_member_id.getText().toString().trim();
                String Name = ed_Name.getText().toString().trim();
                String dob = ed_dob.getText().toString().trim();
                String age_yrs = ed_age_yrs.getText().toString().trim();
                String mother_hhid = ed_mother_hhid.getText().toString().trim();
                String years_formal_education = ed_years_formal_education.getText().toString().trim();
                String occupation_oth = ed_occupation_oth.getText().toString().trim();
                String hh_relationship_oth = ed_hh_relationship_oth.getText().toString().trim();

                int gender = radioGroupGender.getCheckedRadioButtonId();
                String genderString;
                if (gender == R.id.radioGroupGenderM) {
                    genderString = "Male";
                } else if (gender == R.id.radioGroupGenderF){
                    genderString = "Female";
                }else{
                    genderString = "Transexual";
                }

                int occupation = radiooccupation.getCheckedRadioButtonId();
                String occupationString;
                if (occupation == R.id.radiooccupationue) {
                    occupationString = "Unemployed";
                } if (occupation == R.id.radiooccupationus) {
                    occupationString = "Unskilled worker";
                }if (occupation == R.id.radiooccupationsmw) {
                    occupationString = "Semiskilled worker";
                }if (occupation == R.id.radiooccupationsw) {
                    occupationString = "Skilled worker";
                }if (occupation == R.id.radiooccupationown) {
                    occupationString = "Clerk,shop owner,farm worker";
                }if (occupation == R.id.radiooccupation_semiprof) {
                    occupationString = "Semi professional";
                }if (occupation == R.id.radiooccupation_prof) {
                    occupationString = "Professional";
                }if (occupation == R.id.radiooccupationChil) {
                    occupationString = "Child < 6 yrs";
                }if (occupation == R.id.radiooccupationOthe) {
                    occupationString = "Other (specify)";
                }
                else{
                    occupationString = "Student";
                }

                int relationship = radiorelationshipHead.getCheckedRadioButtonId();
                String occupationString;
                if (relationship == R.id.radiorelationshipHus) {
                    relationshipString = "Husband";
                } if (relationship == R.id.radiorelationshipWife) {
                    relationshipString = "Wife";
                }if (relationship == R.id.radiorelationshipSon) {
                    relationshipString = "Son/ Daughter";
                }if (relationship == R.id.radiorelationshipDaugh) {
                    relationshipString = "Daughter/son-in-law";
                }if (relationship == R.id.radiorelationshipGran) {
                    relationshipString = "Grandson/ Granddaughter";
                }if (relationship == R.id.radiorelationshipFather) {
                    relationshipString = "Father/Mother";
                }if (relationship == R.id.radiorelationshipBroth) {
                    relationshipString = "Brother/sister";
                }if (relationship == R.id.radiorelationshipUncl) {
                    relationshipString = "Uncle/aunt";
                }if (relationship == R.id.radiorelationshipNeph) {
                    relationshipString = "Nephew/niece";
                }if (relationship == R.id.radiorelationshipMothe) {
                    relationshipString = "Mother/father in law";
                }
                else{
                    relationshipString = "Other (specify)";
                }

                if (hh_member_id.isEmpty() || Name.isEmpty() || dob.isEmpty() || age_yrs.isEmpty() || mother_hhid.isEmpty() || gender == -1) {
                    Toast.makeText(DSS03_MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                int age = Integer.parseInt(age_yrs);
                DSS03_declare user = new DSS03_declare(hh_member_id,Name,dob,age_yrs,mother_hhid,years_formal_education,occupation_oth,hh_relationship_oth,genderString,occupationString,occupationString);
                userDatabase.addUser(user);
                userList.add(user);
                Toast.makeText(DSS03_MainActivity.this, "User added successfully", Toast.LENGTH_SHORT).show();

                ed_hh_member_id.setText("");
                ed_Name.setText("");
                ed_dob.setText("");
                ed_age_yrs.setText("");
                ed_mother_hhid .setText("");
                ed_years_formal_education.setText("");
                ed_occupation_oth.setText("");
                ed_hh_relationship_oth.setText("");
                radioGroupGender.clearCheck();
                radiooccupation.clearCheck();
                radiorelationshipHead.clearCheck();
            }
        });
    }
}

