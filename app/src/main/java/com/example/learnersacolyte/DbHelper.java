package com.example.learnersacolyte;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public String ColTitle = "Title", ColHour = "Hour", ColMin = "Minute", AmPm = "AMorPM", ColDate = "Date", ColMonth = "Month", ColYear = "Year", ColEvent = "Event", ColID = "ID";
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

    public void insertFireBaseDataInSQ(String title, String date, String month, String year, String minute, String hour, String ampm, String event, String ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db2 =  this.getReadableDatabase();
        String create_table = "create table if not exists "+table_name+"("+ColTitle+" string,"+ColDate+" string,"+ColMonth+" string,"+ColYear+" string,"+ColHour+" string,"+ColMin+" string,"+AmPm+" string,"+ColEvent+" string,"+ColID+" string);";
        db.execSQL(create_table);
        ContentValues cValues = new ContentValues();
        cValues.put(ColTitle, title);
        cValues.put(ColDate, date);
        cValues.put(ColMonth, month);
        cValues.put(ColYear, year);
        cValues.put(ColMin, minute);
        cValues.put(ColHour, hour);
        cValues.put(AmPm, ampm);
        cValues.put(ColEvent, event);
        cValues.put(ColID, ID);
        long newRowId = db.insert(table_name,null, cValues);
        db.close();
    }

    public void CreateTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String create_table = "create table if not exists "+table_name+"("+ColTitle+" string,"+ColDate+" string,"+ColMonth+" string,"+ColYear+" string,"+ColHour+" string,"+ColMin+" string,"+AmPm+" string,"+ColEvent+" string,"+ColID+" string);";
        db.execSQL(create_table);
    }

    public void DeleteTable()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("drop table if exists "+table_name+";");
    }

    public List<EventDataStructure> ReadTitle(String year, String month, String date)
    {
        List<EventDataStructure> title = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String s = "select Title from User_DB";
        String query = "select Title from User_DB where Year = \""+year+"\" and Month = \""+month+"\" and Date = \""+date+"\";";
        Cursor cursor = db.rawQuery(query, null);
        while(cursor.moveToNext())
        {
            EventDataStructure obj = new EventDataStructure();
            obj.setTitle(cursor.getString(0));
            title.add(obj);
        }
        return title;
    }

    public String[] gettitle(String year, String month, String date)
    {
            String title[] = new String[100];
            String s = "select Title from User_DB;";
            String query = "select Title from User_DB where Year = \""+year+"\" and Month = \""+month+"\" and Date = \""+date+"\" order by Title;";
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery(query,null);
            int a = 0;
            while(cursor.moveToNext() && a < title.length)
            {

                title[a] = cursor.getString(0);
                a++;
            }
            return Arrays.copyOf(title, a);
    }

    public String[] getTime(String year, String month, String date)
    {
        String query = "select Hour,Minute,AmorPM from User_DB where Year = \""+year+"\" and Month = \""+month+"\" and Date = \""+date+"\" order by Title;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        String hour[] = new String[100];
        String minute[] = new String[100];
        String amorpm[] = new String[100];
        String time[] = new String[100];
        int a = 0;

        while (cursor.moveToNext())
        {
            hour[a] = cursor.getString(0);
            minute[a] = cursor.getString(1);
            amorpm[a] = cursor.getString(2);
            time[a] = hour[a]+":"+minute[a]+" "+amorpm[a];
            a++;
        }
        return time;
    }

    public String[] getID(String year, String month, String date)
    {
        String query = "select ID from User_DB where Year = \""+year+"\" and Month = \""+month+"\" and Date = \""+date+"\" order by Title;";
        String ID[] = new String[100];
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int a = 0;
        while(cursor.moveToNext() && a < ID.length)
        {
            ID[a] = cursor.getString(0);
            a++;
        }

        return Arrays.copyOf(ID, a);
    }

    public void deleteDuplicateRows()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM User_DB;");
    }

    public Cursor FetchFullEvent(String id)
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = "select * from User_DB where ID = \""+id+"\";";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public Cursor fetchByDate(String date, String month, String year)
    {
        String query = "select * from User_DB where Date = \""+date+"\" and Month = \""+month+"\" and Year = \""+year+"\" order by Title;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
}
