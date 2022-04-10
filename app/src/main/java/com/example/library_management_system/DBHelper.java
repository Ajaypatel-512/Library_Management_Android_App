package com.example.library_management_system;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME= "LIBRARYMANAGEMENT.db";

    //constructur Name
    public DBHelper(Context context) {
        super(context, "LIBRARYMANAGEMENT.db",null, 1); //create a Database
    }


// on create method
    @Override
    public void onCreate(SQLiteDatabase MyDB) {


// create table Student
MyDB.execSQL("create Table Student(SN INTEGER PRIMARY KEY AUTOINCREMENT , RollNo VARCHAR NOT NULL UNIQUE, FullName TEXT NOT NULL, Mobile VARCHAR NOT NULL, CollegeName VARCHAR NOT NULL , Branch TEXT NOT NULL, Semester VARCHAR NOT NULL, BookID VARCHAR )");

// create table book

MyDB.execSQL("create Table Book(SN INTEGER PRIMARY KEY AUTOINCREMENT, BookId VARCHAR NOT NULL UNIQUE,BookName VARCHAR NOT NULL, BookAuthor TEXT NOT NULL, BookEdition VARCHAR NOT NULL, StuRN VARCHAR , IssueDate VARCHAR ,DueDate VARCHAR, ReturnDate VARCHAR )");

    }


// On Update method

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists Student"); //drop table Student if exists
        MyDB.execSQL("drop Table if exists Book ");   // drop table Book if exists

    }

//function for insert data in student table

public Boolean insertDataStu(String roll_no,String fullname , String mobile,String collegename, String branch, String semester){
SQLiteDatabase MyDB = this.getWritableDatabase();
    ContentValues contentValues= new ContentValues();
    contentValues.put("RollNO",roll_no);
    contentValues.put("FullName",fullname);
    contentValues.put("Mobile",mobile);
    contentValues.put("CollegeName",collegename);
    contentValues.put("Branch",branch);
    contentValues.put("Semester",semester);
    long result= MyDB.insert("Student",null,contentValues);

    if(result==-1) return false;
    else
        return  true;

}
// function for insert data in library table

    public Boolean insertDataLib(String bookid,String bookname , String bookauthor,String bookedition){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("BookId",bookid);
        contentValues.put("BookName",bookname);
        contentValues.put("BookAuthor",bookauthor);
        contentValues.put("BookEdition",bookedition);

        long result= MyDB.insert("Book",null,contentValues);

        if(result==-1) return false;
        else
            return  true;

    }

    // function for check student exist or not
    public  Boolean  checkstu(String rn){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from Student where RollNo= ?",new String[] {rn});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }


    // function for check book exist or not
    public  Boolean  checkbook(String BI){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from Book where BookId= ?",new String[] {BI});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    // function for check book id and student id in book table  exist or not
    public  Boolean  checkbookstu(String BI,String SRN){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from Book where BookId= ? AND StuRN= ?",new String[] {BI,SRN});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }







    // function for update book table for issue book
    public  Boolean  updatebook(String BI,String SRN,String ISD,String DUD){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("StuRN",SRN);
        contentValues.put("IssueDate",ISD);
        contentValues.put("DueDate",DUD);

      long result=  MyDB.update("Book",contentValues, "BookId=?", new String[]{BI});

        if(result==-1) return false;
        else
            return  true;

    }




    // function for update book table for return book
    public  Boolean  reupdatebook(String BI,String SRN,String RD){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("ReturnDate",RD);

        long result=  MyDB.update("Book",contentValues, "BookId=? AND StuRN=?", new String[]{BI,SRN});

        if(result==-1) return false;
        else
            return  true;

    }


    // function for All Details of book

    public Cursor  allbook(String BI){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from Book where BookId= ?",new String[] {BI});
        return cursor;

    }


    // function for All Details of Student

    public Cursor  allStudent(String SN){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from Student where RollNo= ?",new String[] {SN});
        return cursor;

    }





}
