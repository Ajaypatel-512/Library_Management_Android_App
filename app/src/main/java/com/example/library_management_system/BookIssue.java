package com.example.library_management_system;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class BookIssue extends AppCompatActivity {
    EditText bookid,rollno, issuedate, duedate;
    Button submit;
    Button back;
    Button issuedatebtn;
    Button duedatebtn;
    DBHelper DB;
    private int year, month, day;
    private Calendar c;
    private DatePickerDialog dpd;
    
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_issue);

        setTitle("Book Issue");

        //Using DBHelper class here for  all db query  .
        DB = new DBHelper(this);

        bookid = findViewById(R.id.bookID);
        rollno = findViewById(R.id.rollNO);
        back = findViewById(R.id.btnBackissue);

        //for date
        c = Calendar.getInstance();
        year =c.get(Calendar.YEAR);
        month =c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // extract ids of issue date control
        issuedate=  findViewById(R.id.issueDate);
        issuedatebtn = findViewById(R.id.DateI);

//        button click to show DatePickerDialog
        issuedatebtn.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                DatePickerDialog dpd = new DatePickerDialog((Context)BookIssue.this, (DatePickerDialog.OnDateSetListener)(new DatePickerDialog.OnDateSetListener() {
                    public final void onDateSet(DatePicker view, int myear, int monthOfYear, int dayOfMonth) {
                        issuedate.setText((CharSequence)("" + dayOfMonth + "/" + monthOfYear + "/" + myear));
                    }
                }), year, month, day);
                dpd.show();
            }
        }));


        //extract ids for due date control
        duedate= findViewById(R.id.dueDate);
        duedatebtn = findViewById(R.id.DateD);

        //button click to show DatePickerDialog
        duedatebtn.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                DatePickerDialog dpd = new DatePickerDialog((Context)BookIssue.this, (DatePickerDialog.OnDateSetListener)(new DatePickerDialog.OnDateSetListener() {
                    public final void onDateSet(DatePicker view, int myear, int monthOfYear, int dayOfMonth) {
                        duedate.setText((CharSequence)("" + dayOfMonth + "/" + monthOfYear + "/" + myear));
                    }
                }), year, month, day);
                dpd.show();
            }
        }));


        submit = findViewById(R.id.btnSubmitissue);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String issuebookid1= bookid.getText().toString();
                String issuesturn1= rollno.getText().toString();
                String issuedate1 =issuedate.getText().toString();
                String duedate1 = duedate.getText().toString();


                //  calling checkbook function from DBHelper
                Boolean chbook = DB.checkbook(issuebookid1);

                //  calling checkstu function from DBHelper
                Boolean chstu = DB.checkstu(issuesturn1);

                //check all empty fields
                if (issuebookid1.equals("")||issuesturn1.equals("")||issuedate1.equals("")||duedate1.equals("")) {
                    Toast.makeText(BookIssue.this, "Please Enter All the Fields", Toast.LENGTH_LONG).show();
                }
                // check for book or student exist or not
                else if(chbook==true && chstu==true) {
                    //calling update book function for book issue
                    Boolean  upbooktable = DB.updatebook(issuebookid1,issuesturn1,issuedate1,duedate1);

                    if (upbooktable==true){
                        Toast.makeText(BookIssue.this,"Information Save Successfully",Toast.LENGTH_LONG).show();
                        bookid.getText().clear();
                        rollno.getText().clear();
                        issuedate.getText().clear();
                        duedate.getText().clear();
                    }
                    else {
                        Toast.makeText(BookIssue.this, "Failed Try Again", Toast.LENGTH_LONG).show();
                    }
                }
                else if(chbook==false||chstu==false) {
                    Toast.makeText(BookIssue.this,"Book/Student not exist.",Toast.LENGTH_LONG).show();
                }
            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}