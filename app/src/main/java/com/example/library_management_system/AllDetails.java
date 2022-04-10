package com.example.library_management_system;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AllDetails extends AppCompatActivity {
    DBHelper DB;
    Button btnbookid,btnsturn;
    EditText detailsbookid,detailssturn;
    TextView TXT1;
    TextView TXT2;
    TextView TXT3;
    TextView TXT4;
    TextView TXT5;
    TextView TXT6;
    TextView TXT7;
    TextView TXT8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_details);
        setTitle("Details");

        //Using DBHelper class here for  all db query  .
        DB = new DBHelper(this);

        //Extract all ids from contols
        //for book details
        btnbookid= findViewById(R.id.DetailsBookbtn);

        btnbookid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsbookid= findViewById(R.id.DetailsBookId);
                String detailsbookid1= detailsbookid.getText().toString();

                //  calling checkbook function from DBHelper
                Boolean chbook  = DB.checkbook(detailsbookid1);


                //check  empty fields
                if (detailsbookid1.equals("")) {
                    Toast.makeText(AllDetails.this, "Please fill the Field", Toast.LENGTH_LONG).show();
                }

                // check for book exist or not
                else if(chbook==true) {
                    //calling All Details of book  function
                    Cursor result = DB.allbook(detailsbookid1);

                    while(result.moveToNext()) {
                        TXT1 = findViewById(R.id.txt1);
                        TXT1.setText(result.getString(1));
                        TXT2 = findViewById(R.id.txt2);
                        TXT2.setText(result.getString(2));
                        TXT3 = findViewById(R.id.txt3);
                        TXT3.setText(result.getString(3));
                        TXT4 = findViewById(R.id.txt4);
                        TXT4.setText(result.getString(4));
                        TXT5 = findViewById(R.id.txt5);
                        TXT5.setText(result.getString(5));
                        TXT6 = findViewById(R.id.txt6) ;
                        TXT6.setText(result.getString(6));
                        TXT7 = findViewById(R.id.txt7);
                        TXT7.setText(result.getString(7));
                        TXT8 = findViewById(R.id.txt8);
                        TXT8.setText(result.getString(8));
                    }
                    detailsbookid.getText().clear();
                }

                else if(chbook==false) {
                    Toast.makeText(AllDetails.this,"Book not exist.",Toast.LENGTH_LONG).show();
                }
            }
        });


        //for student details
        btnsturn= findViewById(R.id.DetailsStubtn);
        btnsturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailssturn = findViewById (R.id.DetailsStudentRollNo);
                String detailssturn1 = detailssturn.getText().toString();

                //  calling checkbook function from DBHelper
                Boolean chstudent = DB.checkstu(detailssturn1);

                //check  empty fields
                if (detailssturn1.equals("")) {
                    Toast.makeText(AllDetails.this, "Please fill the Field", Toast.LENGTH_LONG).show();
                }
                else if (chstudent == true) {
                    //calling All Details of Student  function
                    Cursor result1 = DB.allStudent(detailssturn1);

                    while (result1.moveToNext()) {

                        TXT1 = findViewById (R.id.txt1);
                        TXT1.setText(result1.getString(1));
                        TXT2 = findViewById  (R.id.txt2);
                        TXT2.setText(result1.getString(2));
                        TXT3 = findViewById (R.id.txt3);
                        TXT3.setText(result1.getString(3));
                        TXT4 = findViewById (R.id.txt4);
                        TXT4.setText(result1.getString(4));
                        TXT5 = findViewById  (R.id.txt5);
                        TXT5.setText(result1.getString(5));
                        TXT6 = findViewById (R.id.txt6);
                        TXT6.setText(result1.getString(6));
                    }
                    detailssturn.getText().clear();

                } else {
                    Toast.makeText(AllDetails.this, "Student not exist.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
