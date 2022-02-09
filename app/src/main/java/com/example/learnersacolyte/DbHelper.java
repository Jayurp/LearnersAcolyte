package com.example.learnersacolyte;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class DbHelper extends SQLiteOpenHelper
{

    AddEventActivity obj = new AddEventActivity();
    public static final String db_name = "MyDatabase";
    /*
    public String table_name = "Registration";
    public String col_name = "name";
    public String col_enr = "enr";
    public String col_sem = "sem";
    public String col_dept = "dept";
    public String col_institute = "institute"; */

    public String ColHour = "Hour", ColMin = "Minute", AmPm = "AMorPM", ColDate = "Date", ColMonth = "Month", ColYear = "Year", ColEvent = "Event";
    public String table_name = "User_DB";

    public DbHelper(Context context)
    {
        super(context,db_name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //String create_table = "create table "+table_name+" ("+col_name+" string ,"+col_enr+" integer ,"+col_sem+" integer ,"+col_dept+" string ,"+col_institute+" string );";


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onCreate(db);
    }

    /*
    public void insertUserDetails(String dname, int denr,int dsem, String ddept, String dinst)
    {
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(col_name, dname);
        cValues.put(col_enr, denr);
        cValues.put(col_sem, dsem);
        cValues.put(col_dept, ddept);
        cValues.put(col_institute, dinst);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(table_name,null, cValues);
        db.close();
    }
        */

    public void insertFireBaseDataInSQ(String date, String month, String year, String minute, String hour, String ampm, String event)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db2 =  this.getReadableDatabase();
        String create_table = "create table if not exists "+table_name+"("+ColDate+" string,"+ColMonth+" string,"+ColYear+" string,"+ColHour+" string,"+ColMin+" string,"+AmPm+" string,"+ColEvent+" string);";
        db.execSQL(create_table);
        ContentValues cValues = new ContentValues();
        cValues.put(ColDate, date);
        cValues.put(ColMonth, month);
        cValues.put(ColYear, year);
        cValues.put(ColMin, minute);
        cValues.put(ColHour, hour);
        cValues.put(AmPm, ampm);
        cValues.put(ColEvent, event);
        long newRowId = db.insert(table_name,null, cValues);
        db.close();
    }

    public void DeleteTable()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("drop table "+table_name+";");
    }
}
