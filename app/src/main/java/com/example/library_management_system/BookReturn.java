package com.example.library_management_system;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class BookReturn extends AppCompatActivity {
    DBHelper DB;
    EditText ReturnBookId,ReturnRollNo,bookreturndate1;
    Button home,submit,bookreturndatebtn;
    int year,month,day;
    Calendar c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_return);
        setTitle("Book Return");
        //Using DBHelper class here for  all db query  .
        DB = new DBHelper(this);

        //Back Home button
        home =findViewById(R.id.home4);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        //for date
        c = Calendar.getInstance();
        year =c.get(Calendar.YEAR);
        month =c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // extract ids of return date control
        bookreturndate1 = findViewById(R.id.BookReturnDate);
        bookreturndatebtn   = findViewById(R.id.BookReturnDateBtn);

        //button click to show DatePickerDialog
        bookreturndatebtn.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                DatePickerDialog dpd = new DatePickerDialog((Context)BookReturn.this, (DatePickerDialog.OnDateSetListener)(new DatePickerDialog.OnDateSetListener() {
                    public final void onDateSet(DatePicker view, int myear, int monthOfYear, int dayOfMonth) {
                        bookreturndate1.setText((CharSequence)("" + dayOfMonth + "/" + monthOfYear + "/" + myear));
                    }
                }), year, month, day);
                dpd.show();
            }
        }));

        submit =findViewById(R.id.ReturnBookSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Extract ids from controls
                ReturnBookId = findViewById(R.id.ReturnBookId);
                ReturnRollNo =  findViewById(R.id.ReturnRollNo);

                String returnbookid = ReturnBookId.getText().toString();
                String returnsturn=   ReturnRollNo.getText().toString();
                String bookreturndate  = bookreturndate1.getText().toString();

                //  calling checkbookstu function from DBHelper to check bookID and RN
                Boolean chbookstu = DB.checkbookstu(returnbookid,returnsturn);

                //check all empty fields
                if (returnbookid.equals("")||returnsturn.equals("")||bookreturndate.equals("")) {
                    Toast.makeText(BookReturn.this, "Please Enter All the Fields", Toast.LENGTH_LONG).show();
                }

                // check for book or student exist or not
                else if(chbookstu==true) {

                    //calling update book function for book issue
                    Boolean  rebooktable = DB.reupdatebook(returnbookid,returnsturn,bookreturndate);

                    if (rebooktable==true){
                        Toast.makeText(BookReturn.this,"Information Save Successfully",Toast.LENGTH_LONG).show();
                        ReturnBookId.getText().clear();
                        ReturnRollNo.getText().clear();
                        bookreturndate1.getText().clear();
                    }
                    else{
                        Toast.makeText(BookReturn.this,"Failed Try Again",Toast.LENGTH_LONG).show();
                    }
                }
                else if(chbookstu==false) {
                    Toast.makeText(BookReturn.this,"Book/Student not exist.",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
