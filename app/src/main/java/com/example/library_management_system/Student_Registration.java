package com.example.library_management_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Student_Registration extends AppCompatActivity {
    TextView RollNo, Name, MobileNo, College, Branch, Semester;
    Button btnSubmit1, btnBack1;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        setTitle("Student Registration");
        RollNo = findViewById(R.id.rollno);
        Name = findViewById(R.id.name);
        MobileNo = findViewById(R.id.mobile);
        College = findViewById(R.id.cllg);
        Branch = findViewById(R.id.branch);
        Semester = findViewById(R.id.sem);
        btnSubmit1 = findViewById(R.id.btnSubmit1);
        btnBack1 = findViewById(R.id.btnBack1);

        DB = new DBHelper(this);

        btnSubmit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rollno = RollNo.getText().toString();
                String name = Name.getText().toString();
                String mob = MobileNo.getText().toString();
                String cllg = College.getText().toString();
                String branch = Branch.getText().toString();
                String sem = Semester.getText().toString();

                //  calling checkstu function from DBHelper
                Boolean chstu = DB.checkstu(rollno);

                //check for all fields
                if (rollno.equals("")||name.equals("")||mob.equals("")||cllg.equals("")||branch.equals("")||sem.equals("")) {
                    Toast.makeText(Student_Registration.this, "Please Enter All the Fields", Toast.LENGTH_LONG).show();
                }
                else if(chstu==false) {
                    // calling insertDataStu function from DB
                    Boolean insertstu = DB.insertDataStu(rollno,name,mob,cllg,branch,sem);

                    if (insertstu==true){
                        Toast.makeText(Student_Registration.this,"Information Save Successfully",Toast.LENGTH_LONG).show();
//                        RollNo.getText().clear();
//                        name.getText().clear();
//                        mob.getText().clear();
//                        cllg.getText().clear();
//                        branch.getText().clear();
//                        sem.getText().clear();
                    }
                    else{
                        Toast.makeText(Student_Registration.this,"Registration Failed",Toast.LENGTH_LONG).show();
                    }
                }
                else if(chstu==true) {
                    Toast.makeText(Student_Registration.this, "Student Already exist.", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}