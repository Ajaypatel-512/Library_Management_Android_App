package com.example.library_management_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BookInfo extends AppCompatActivity {
    EditText BookId, bookname, authorid, bookedition;
    Button submitinfo, backinfo;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
        setTitle("Book Information");

        //Using DBHelper class here for  all db query  .
        DB = new DBHelper(this);

        BookId = findViewById(R.id.bookid);
        bookname = findViewById(R.id.bookname);
        authorid = findViewById(R.id.authorname);
        bookedition = findViewById(R.id.edition);
        submitinfo = findViewById(R.id.btnSubmitinfo);
        backinfo = findViewById(R.id.btnBackinfo);

        submitinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String bookid= BookId.getText().toString();
               String BookName= bookname.getText().toString();
               String bookauthor= authorid.getText().toString();
               String BookEdition = bookedition.getText().toString();

                //  calling checkbook function from DBHelper
                Boolean chbook = DB.checkbook(bookid);

                //check all empty fields
                if (bookid.equals("")||BookName.equals("")||bookauthor.equals("")||BookEdition.equals("")) {
                    Toast.makeText(BookInfo.this, "Please Enter All the Fields", Toast.LENGTH_LONG).show();
                }
                // check for book exist or not
                else if(chbook==false) {
                    // calling insertDataStu function from DB
                    Boolean insertbook = DB.insertDataLib(bookid,BookName,bookauthor,BookEdition);
                    if (insertbook==true){
                        Toast.makeText(BookInfo.this,"Book Information Save Successfully",Toast.LENGTH_LONG).show();
//                        bookid.getText().clear();
//                        BookName.getText().clear();
//                        bookauthor.getText().clear();
//                        BookEdition.getText().clear();
                    }
                    else{
                        Toast.makeText(BookInfo.this,"Failed Try Again",Toast.LENGTH_LONG).show();
                    }
                }

                else if(chbook==true) {
                    Toast.makeText(BookInfo.this,"Book Already exist.",Toast.LENGTH_LONG).show();
                }
            }
        });

        backinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}