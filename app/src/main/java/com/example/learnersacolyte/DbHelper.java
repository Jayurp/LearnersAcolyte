package com.example.learnersacolyte;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper
{
    public static final String db_name = "MyDatabase";
    public String table_name = "Registration";
    public String col_name = "name";
    public String col_enr = "enr";
    public String col_sem = "sem";
    public String col_dept = "dept";
    public String col_institute = "institute";

    public DbHelper(Context context)
    {
        super(context,db_name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String create_table = "create table "+table_name+" ("+col_name+" string ,"+col_enr+" integer ,"+col_sem+" integer ,"+col_dept+" string ,"+col_institute+" string );";
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onCreate(db);
    }

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
}
